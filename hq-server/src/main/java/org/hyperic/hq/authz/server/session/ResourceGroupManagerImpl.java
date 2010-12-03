/**
 * NOTE: This copyright does *not* cover user programs that use Hyperic
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 *  "derived work".
 *
 *  Copyright (C) [2004-2010], VMware, Inc.
 *  This file is part of Hyperic.
 *
 *  Hyperic is free software; you can redistribute it and/or modify
 *  it under the terms version 2 of the GNU General Public License as
 *  published by the Free Software Foundation. This program is distributed
 *  in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more
 *  details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA.
 *
 */

package org.hyperic.hq.authz.server.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hyperic.hibernate.PageInfo;
import org.hyperic.hq.appdef.server.session.ResourceCreatedZevent;
import org.hyperic.hq.appdef.server.session.ResourceDeletedZevent;
import org.hyperic.hq.appdef.shared.AppdefEntityConstants;
import org.hyperic.hq.appdef.shared.AppdefEntityID;
import org.hyperic.hq.appdef.shared.AppdefEntityTypeID;
import org.hyperic.hq.appdef.shared.AppdefGroupValue;
import org.hyperic.hq.authz.shared.AuthzConstants;
import org.hyperic.hq.authz.shared.AuthzSubjectManager;
import org.hyperic.hq.authz.shared.GroupCreationException;
import org.hyperic.hq.authz.shared.PermissionException;
import org.hyperic.hq.authz.shared.PermissionManager;
import org.hyperic.hq.authz.shared.PermissionManagerFactory;
import org.hyperic.hq.authz.shared.ResourceGroupCreateInfo;
import org.hyperic.hq.authz.shared.ResourceGroupManager;
import org.hyperic.hq.authz.shared.ResourceGroupValue;
import org.hyperic.hq.authz.shared.ResourceManager;
import org.hyperic.hq.common.DuplicateObjectException;
import org.hyperic.hq.common.NotFoundException;
import org.hyperic.hq.common.SystemException;
import org.hyperic.hq.common.VetoException;
import org.hyperic.hq.events.MaintenanceEvent;
import org.hyperic.hq.events.shared.EventLogManager;
import org.hyperic.hq.grouping.Critter;
import org.hyperic.hq.grouping.CritterList;
import org.hyperic.hq.grouping.CritterTranslationContext;
import org.hyperic.hq.grouping.CritterTranslator;
import org.hyperic.hq.grouping.GroupException;
import org.hyperic.hq.grouping.shared.GroupDuplicateNameException;
import org.hyperic.hq.grouping.shared.GroupEntry;
import org.hyperic.hq.inventory.domain.Resource;
import org.hyperic.hq.inventory.domain.ResourceGroup;
import org.hyperic.hq.zevents.ZeventManager;
import org.hyperic.util.pager.PageControl;
import org.hyperic.util.pager.PageList;
import org.hyperic.util.pager.Pager;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use this session bean to manipulate ResourceGroups,
 * 
 * All arguments and return values are value-objects.
 * 
 */
@Transactional
@Service
public class ResourceGroupManagerImpl implements ResourceGroupManager, ApplicationContextAware {
    private final String BUNDLE = "org.hyperic.hq.authz.Resources";
    private Pager _groupPager;
    private Pager _ownedGroupPager;
    private static final String GROUP_PAGER = PagerProcessor_resourceGroup.class.getName();
    private static final String OWNEDGROUP_PAGER = PagerProcessor_ownedResourceGroup.class
        .getName();

    private AuthzSubjectManager authzSubjectManager;
    private EventLogManager eventLogManager;
    private final Log log = LogFactory.getLog(ResourceGroupManagerImpl.class);
    private ResourceManager resourceManager;
   
    private ApplicationContext applicationContext;
    private CritterTranslator critterTranslator;

    @Autowired
    public ResourceGroupManagerImpl(AuthzSubjectManager authzSubjectManager,
                                    EventLogManager eventLogManager,
                                    ResourceManager resourceManager,
                                    CritterTranslator critterTranslator) {
        this.authzSubjectManager = authzSubjectManager;
        this.eventLogManager = eventLogManager;
        this.resourceManager = resourceManager;
        this.critterTranslator = critterTranslator;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        _groupPager = Pager.getPager(GROUP_PAGER);
        _ownedGroupPager = Pager.getPager(OWNEDGROUP_PAGER);
    }

    /**
     * Create a resource group. Currently no permission checking.
     * 
     * @param roles List of {@link Role}s
     * @param resources List of {@link Resource}s
     * 
     * 
     */
    public ResourceGroup createResourceGroup(AuthzSubject whoami, ResourceGroupCreateInfo cInfo,
                                             Collection<Role> roles, Collection<Resource> resources)
        throws GroupCreationException, GroupDuplicateNameException {
        ResourceGroup res = createGroup(whoami, cInfo, roles, resources);
        applicationContext.publishEvent(new GroupCreatedEvent(res));
        return res;
    }

    public ResourceGroup createResourceGroup(AuthzSubject whoami, ResourceGroupCreateInfo cInfo,
                                             Collection<Role> roles,
                                             Collection<Resource> resources,
                                             CritterList criteriaList)
        throws GroupCreationException, GroupDuplicateNameException {
        ResourceGroup group = createGroup(whoami, cInfo, roles, resources);
        try {
            setCriteria(whoami, group, criteriaList);
        } catch (PermissionException e) {
            throw new GroupCreationException(
                "Error creating group.  Unable to set group criteria.", e);
        } catch (GroupException e) {
            throw new GroupCreationException(
                "Error creating group.  Unable to set group criteria.", e);
        }
        applicationContext.publishEvent(new GroupCreatedEvent(group));
        return group;
    }

    private ResourceGroup createGroup(AuthzSubject whoami, ResourceGroupCreateInfo cInfo,
                                      Collection<Role> roles, Collection<Resource> resources)
        throws GroupDuplicateNameException, GroupCreationException {
        ResourceGroup existing = ResourceGroup.findByName(cInfo.getName());

        if (existing != null) {
            throw new GroupDuplicateNameException("Group by the name [" + cInfo.getName() +
                                                  "] already exists");
        }

        ResourceGroup res = new ResourceGroup();
        res.persist();
        //TODO other res stuff
        
        //TODO why?
        //resourceEdgeDAO.create(res.getResource(), res.getResource(), 0, resourceRelationDAO.findById(AuthzConstants.RELATION_CONTAINMENT_ID)); // Self-edge
        applicationContext.publishEvent(new GroupCreatedEvent(res));
        return res;
    }

    /**
     * Do not allow resources to be added or removed from a group if the group
     * has a downtime schedule in progress.
     */
    private void checkGroupMaintenance(AuthzSubject subj, ResourceGroup group)
        throws PermissionException, VetoException {

        try {
            MaintenanceEvent event = PermissionManagerFactory.getInstance()
                .getMaintenanceEventManager().getMaintenanceEvent(subj, group.getId());

            if (event != null && MaintenanceEvent.STATE_RUNNING.equals(event.getState())) {
                String msg = ResourceBundle.getBundle(BUNDLE).getString(
                    "resourceGroup.update.error.downtime.running");

                throw new VetoException(MessageFormat.format(msg, new String[] { group.getName() }));
            }
        } catch (SchedulerException se) {
            // This should not happen. Indicates a serious system error.

            String msg = ResourceBundle.getBundle(BUNDLE).getString(
                "resourceGroup.update.error.downtime.scheduler.failure");

            throw new SystemException(MessageFormat.format(msg, new String[] { group.getName() }),
                se);
        }
    }

    /**
     * Find the group that has the given ID. Performs authz checking
     * @param whoami user requesting to find the group
     * @return {@link ResourceGroup} or null if it does not exist XXX scottmf,
     *         why is this method called find() but calls dao.get()???
     * 
     */
    @Transactional(readOnly = true)
    public ResourceGroup findResourceGroupById(AuthzSubject whoami, Integer id)
        throws PermissionException {
        ResourceGroup group = ResourceGroup.findResourceGroup(id);
        if (group == null) {
            return null;
        }
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_viewResourceGroup);
        return group;
    }

    private void checkGroupPermission(AuthzSubject whoami, Integer group, Integer op)
        throws PermissionException {
        PermissionManager pm = PermissionManagerFactory.getInstance();
        pm.check(whoami.getId(), AuthzConstants.authzGroup, group, op);
    }

    /**
     * Find the group that has the given ID. Does not do any authz checking
     * 
     */
    @Transactional(readOnly = true)
    public ResourceGroup findResourceGroupById(Integer id) {
        return ResourceGroup.findResourceGroup(id);
    }

    /**
     * Find the role that has the given name.
     * @param whoami user requesting to find the group
     * @param name The name of the role you're looking for.
     * @return The value-object of the role of the given name.
     * @throws PermissionException whoami does not have viewResourceGroup on the
     *         requested group
     * 
     */
    @Transactional(readOnly = true)
    public ResourceGroup findResourceGroupByName(AuthzSubject whoami, String name)
        throws PermissionException {
        ResourceGroup group = ResourceGroup.findResourceGroupByName(name);

        if (group == null) {
            return null;
        }

        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_viewResourceGroup);
        return group;
    }

    /**
     * 
     */
    @Transactional(readOnly = true)
    public Collection<ResourceGroup> findDeletedGroups() {
        //TODO deleted groups?
        return null;
        //return resourceGroupDAO.findDeletedGroups();
    }

    /**
     * Update some of the fundamentals of groups (name, description, location).
     * If name, description or location are null, the associated properties of
     * the passed group will not change.
     * 
     * @throws DuplicateObjectException if an attempt to rename the group would
     *         result in a group with the same name.
     * 
     */
    public void updateGroup(AuthzSubject whoami, ResourceGroup group, String name,
                            String description, String location) throws PermissionException,
        GroupDuplicateNameException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_modifyResourceGroup);

        // XXX: Add Auditing
        if (name != null && !name.equals(group.getName())) {
            ResourceGroup existing = ResourceGroup.findResourceGroupByName(name);

            if (existing != null) {
                throw new GroupDuplicateNameException("Group by that name [" + name +
                                                      "] already exists");
            }
            group.setName(name);
           
        }

        if (description != null && !description.equals(group.getDescription())) {
            group.setDescription(description);
        }

        if (location != null && !location.equals(group.getLocation())) {
            group.setLocation(location);
        }
    }

    /**
     * Remove all groups compatable with the specified resource prototype.
     * 
     * @throws VetoException if another subsystem cannot allow it (for
     *         constraint reasons)
     * 
     */
    public void removeGroupsCompatibleWith(Resource proto) throws VetoException {
        AuthzSubject overlord = authzSubjectManager.getOverlordPojo();

        for (ResourceGroup group : getAllResourceGroups()) {
            //TODO not really doing this
//            if (group.isCompatableWith(proto)) {
//                try {
//                    removeResourceGroup(overlord, group);
//                } catch (PermissionException exc) {
//                    log.warn("Perm denied while deleting group [" + group.getName() + " id=" +
//                             group.getId() + "]", exc);
//                }
//            }
        }
    }

    /**
     * Delete the specified ResourceGroup.
     * @param whoami The current running user.
     * @param group The group to delete.
     * 
     */
    public void removeResourceGroup(AuthzSubject whoami, ResourceGroup group)
        throws PermissionException, VetoException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_removeResourceGroup);
        // TODO scottmf, this should be invoking a pre-transaction callback
        eventLogManager.deleteLogs(group);
        applicationContext.publishEvent(new GroupDeleteRequestedEvent(group));
        group.remove();


        // Send resource delete event
        ResourceDeletedZevent zevent = new ResourceDeletedZevent(whoami, AppdefEntityID
            .newGroupID(group.getId()));
        ZeventManager.getInstance().enqueueEventAfterCommit(zevent);
    }
    
    public void removeResourceGroup(AuthzSubject whoami, Integer groupId) throws PermissionException, VetoException {
        ResourceGroup group = ResourceGroup.findResourceGroup(groupId);
        removeResourceGroup(whoami, group);
    }

    /**
     * 
     */
    public void addResources(AuthzSubject subj, ResourceGroup group, List<Resource> resources)
        throws PermissionException, VetoException {
        checkGroupPermission(subj, group.getId(), AuthzConstants.perm_modifyResourceGroup);
        checkGroupMaintenance(subj, group);
        addResources(group, resources);
    }

    private void addResources(ResourceGroup group, Collection<Resource> resources) {
        for(Resource resource: resources) {
            group.addMember(resource);
        }
        applicationContext.publishEvent(new GroupMembersChangedEvent(group));
    }

    /**
     * Add a resource to a group by resource id and resource type
     * 
     */
    public ResourceGroup addResource(AuthzSubject whoami, ResourceGroup group, Resource resource)
        throws PermissionException, VetoException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_modifyResourceGroup);

        checkGroupMaintenance(whoami, group);
        addResources(group, Collections.singletonList(resource));
        return group;
    }

    public void addResource(AuthzSubject whoami, Resource resource, Collection<ResourceGroup> groups)
        throws PermissionException, VetoException {
        // Do all of the pre-condition checks first before
        // iterating through addResources() because
        // ResourceGroupDAO().addMembers() will commit
        // the changes after each iteration.

        for (ResourceGroup g : groups) {
            checkGroupPermission(whoami, g.getId(), AuthzConstants.perm_modifyResourceGroup);
            checkGroupMaintenance(whoami, g);
        }

        for (ResourceGroup g : groups) {
            addResources(g, Collections.singletonList(resource));
        }
    }

    public void removeResource(AuthzSubject whoami, Resource resource,
                               Collection<ResourceGroup> groups) throws PermissionException,
        VetoException {
        // Do all of the pre-condition checks first before
        // iterating through removeResources() because
        // ResourceGroupDAO().removeMembers() will commit
        // the changes after each iteration.

        for (ResourceGroup g : groups) {
            checkGroupPermission(whoami, g.getId(), AuthzConstants.perm_modifyResourceGroup);
            checkGroupMaintenance(whoami, g);
        }

        for (ResourceGroup g : groups) {
            removeResources(g, Collections.singletonList(resource));
        }
    }

    /**
     * RemoveResources from a group.
     * @param whoami The current running user.
     * @param group The group .
     * 
     */
    public void removeResources(AuthzSubject whoami, ResourceGroup group,
                                Collection<Resource> resources) throws PermissionException,
        VetoException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_modifyResourceGroup);
        checkGroupMaintenance(whoami, group);
        removeResources(group, resources);
    }

    private void removeResources(ResourceGroup group, Collection<Resource> resources) {
        for(Resource resource: resources) {
            group.removeMember(resource);
        }
        applicationContext.publishEvent(new GroupMembersChangedEvent(group));
    }

    /**
     * Sets the criteria list for this group and updates the groups members
     * based on the criteria
     * @param whoami The current running user.
     * @param group This group.
     * @param critters List of critters to associate with this resource group.
     * @throws PermissionException whoami does not own the resource.
     * @throws GroupException critters is not a valid list of criteria.
     * 
     */
    public void setCriteria(AuthzSubject whoami, ResourceGroup group, CritterList critters)
        throws PermissionException, GroupException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_modifyResourceGroup);
        //TODO not doing criteria list yet.  Maybe remove from API?
        //group.setCritterList(critters);
        updateGroupMembers(whoami, group);
    }

    /**
     * Change the resource contents of a group to the specified list of
     * resources.
     * 
     * @param resources A list of {@link Resource}s to be in the group
     * 
     */
    public void setResources(AuthzSubject whoami, ResourceGroup group,
                             Collection<Resource> resources) throws PermissionException,
        VetoException {
        checkGroupPermission(whoami, group.getId(), AuthzConstants.perm_modifyResourceGroup);
        checkGroupMaintenance(whoami, group);
        group.setMembers(new HashSet<Resource>(resources));
        applicationContext.publishEvent(new GroupMembersChangedEvent(group));
    }

    /**
     * List the resources in this group that the caller is authorized to see.
     * @param whoami The current running user.
     * @param groupValue This group.
     * @param pc Paging information for the request
     * @return list of authorized resources in this group.
     * 
     */
    @Transactional(readOnly = true)
    public Collection<Resource> getResources(AuthzSubject whoami, Integer id) {
        return PermissionManagerFactory.getInstance().getGroupResources(whoami.getId(), id,
            Boolean.FALSE);
    }

    /**
     * Get all the resource groups including the root resource group.
     * 
     */
    @Transactional(readOnly = true)
    public List<ResourceGroupValue> getAllResourceGroups(AuthzSubject subject, PageControl pc)
        throws PermissionException {
        return getAllResourceGroups(subject, pc, false);
    }

    /**
     * Get all the members of a group.
     * 
     * @return {@link Resource}s
     * 
     */
    @Transactional(readOnly = true)
    public Collection<Resource> getMembers(ResourceGroup g) {
        return g.getMembers();
    }

    /**
     * Get the member type counts of a group
     * 
     */
    @Transactional(readOnly = true)
    public Map<String, Number> getMemberTypes(ResourceGroup g) {
        //TODO member types?
        //return resourceGroupDAO.getMemberTypes(g);
        return null;
    }

    /**
     * Get all the groups a resource belongs to
     * 
     * @return {@link ResourceGroup}s
     * 
     */
    @Transactional(readOnly = true)
    public Collection<ResourceGroup> getGroups(Resource r) {
        return r.getResourceGroups();
    }

    /**
     * Get the # of groups within HQ inventory
     * 
     */
    @Transactional(readOnly = true)
    public Number getGroupCount() {
        return ResourceGroup.countResourceGroups();
    }

    /**
     * Returns true if the passed resource is a member of the given group.
     * 
     */
    @Transactional(readOnly = true)
    public boolean isMember(ResourceGroup group, Resource resource) {
        return group.isMember(resource);
    }

    /**
     * Get the # of members in a group
     * 
     */
    @Transactional(readOnly = true)
    public int getNumMembers(ResourceGroup g) {
        return getMembers(g).size();
    }
    
    @Transactional(readOnly = true)
    public AppdefGroupValue getGroupConvert(AuthzSubject subject, Integer groupId) {
        ResourceGroup group = findResourceGroupById(groupId);
        return getGroupConvert(subject, group);
    }

    /**
     * Temporary method to convert a ResourceGroup into an AppdefGroupValue
     * 
     */
    @Transactional(readOnly = true)
    public AppdefGroupValue getGroupConvert(AuthzSubject subj, ResourceGroup g) {
        AppdefGroupValue retVal = new AppdefGroupValue();
        Collection<Resource> members = getMembers(g);

        // Create our return group vo
        retVal.setId(g.getId());
        retVal.setName(g.getName());
        retVal.setDescription(g.getDescription());
        retVal.setLocation(g.getLocation());
        retVal.setGroupType(g.getType().getId());
        //TODO don't have res type at the momennt
        //retVal.setGroupEntType(g.getGroupEntType().intValue());
        //retVal.setGroupEntResType(g.getGroupEntResType().intValue());
        retVal.setTotalSize(members.size());
        retVal.setSubject(subj);
        //TODO don't have these at the moment
        //retVal.setClusterId(g.getClusterId().intValue());
        //retVal.setMTime(new Long(g.getMtime()));
        //retVal.setCTime(new Long(g.getCtime()));
        //retVal.setModifiedBy(g.getModifiedBy());
        retVal.setOwner(g.getOwner().getName());

        // Add the group members
        for (Resource r : members) {
            if (r.getType() != null) {
                GroupEntry ge = new GroupEntry(r.getInstanceId(), r.getType().getName());
                retVal.addEntry(ge);
            }
        }

        retVal.setAppdefResourceTypeValue(retVal.getAppdefResourceTypeValue(subj, g));
        return retVal;
    }

    /**
     * Get a list of {@link ResourceGroup}s which are compatible with the
     * specified prototype.
     * 
     * Do not return any groups contained within 'excludeGroups' (a list of
     * {@link ResourceGroup}s
     * 
     * @param prototype If specified, the resulting groups must be compatible
     *        with the prototype.
     * 
     * @param pInfo Pageinfo with a sort field of type
     *        {@link ResourceGroupSortField}
     * 
     * 
     */
    @Transactional(readOnly = true)
    public PageList<ResourceGroup> findGroupsNotContaining(AuthzSubject subject, Resource member,
                                                           Resource prototype,
                                                           Collection<ResourceGroup> excGrps,
                                                           PageInfo pInfo) {
        //TODO not supporting compat groups
        return null;
        //return resourceGroupDAO.findGroupsClusionary(subject, member, prototype, excGrps, pInfo,
          //  false);
    }

    /**
     * Get a list of {@link ResourceGroup}s which are compatible with the
     * specified prototype.
     * 
     * Do not return any groups contained within 'excludeGroups' (a list of
     * {@link ResourceGroup}s
     * 
     * @param prototype If specified, the resulting groups must be compatible
     *        with the prototype.
     * 
     * @param pInfo Pageinfo with a sort field of type
     *        {@link ResourceGroupSortField}
     * 
     * 
     */
    @Transactional(readOnly = true)
    public PageList<ResourceGroup> findGroupsContaining(AuthzSubject subject, Resource member,
                                                        Collection<ResourceGroup> excludeGroups,
                                                        PageInfo pInfo) {
        //TODO not supporting compat groups
        return null;
        //return resourceGroupDAO.findGroupsClusionary(subject, member, null, excludeGroups, pInfo,
          //  true);
    }

    /**
     * Get all the resource groups excluding the root resource group.
     * 
     */
    @Transactional(readOnly = true)
    public Collection<ResourceGroup> getAllResourceGroups(AuthzSubject subject, boolean excludeRoot)
        throws PermissionException {
        // first get the list of groups subject can view
        PermissionManager pm = PermissionManagerFactory.getInstance();
        List<Integer> groupIds;

        /**
         * XXX: Seems this could be optimized to actually get the real list of
         * viewable resource groups instead of going through the perm manager to
         * get the IDs
         */
        try {
            groupIds = pm.findOperationScopeBySubject(subject,
                AuthzConstants.groupOpViewResourceGroup, AuthzConstants.groupResourceTypeName);
        } catch (NotFoundException e) {
            // Makes no sense
            throw new SystemException(e);
        }

        // now build a collection for all of them
        Collection<ResourceGroup> groups = new ArrayList<ResourceGroup>();
        for (int i = 0; i < groupIds.size(); i++) {
            ResourceGroup rgloc = ResourceGroup.findResourceGroup((Integer) groupIds.get(i));
            if (excludeRoot) {
                String name = rgloc.getName();
                if (!name.equals(AuthzConstants.groupResourceTypeName) &&
                    !name.equals(AuthzConstants.rootResourceGroupName))
                    groups.add(rgloc);
            } else {
                groups.add(rgloc);
            }
        }

        return groups;
    }

    /**
     * Get all {@link ResourceGroup}s
     * 
     * 
     */
    @Transactional(readOnly = true)
    public Collection<ResourceGroup> getAllResourceGroups() {
        return ResourceGroup.findAllResourceGroups();
    }

    /**
     * Get all compatible resource groups of the given entity type and resource
     * type.
     * 
     * 
     */
    @Transactional(readOnly = true)
    public Collection<ResourceGroup> getCompatibleResourceGroups(AuthzSubject subject,
                                                                 Resource resProto)
        throws PermissionException, NotFoundException {
        // first get the list of groups subject can view
        PermissionManager pm = PermissionManagerFactory.getInstance();
        List<Integer> groupIds = pm.findOperationScopeBySubject(subject, AuthzConstants.groupOpViewResourceGroup,
            AuthzConstants.groupResourceTypeName);
        
        //TODO compat groups?
        return null;

        //Collection<ResourceGroup> groups = resourceGroupDAO.findCompatible(resProto);
        //for (Iterator<ResourceGroup> i = groups.iterator(); i.hasNext();) {
          //  ResourceGroup g = (ResourceGroup) i.next();
           // if (!groupIds.contains(g.getId())) {
             //   i.remove();
            //}
        //}

        //return groups;
    }

    /**
     * Get all the resource groups excluding the root resource group and paged
     */
    @Transactional(readOnly = true)
    private PageList<ResourceGroupValue> getAllResourceGroups(AuthzSubject subject, PageControl pc,
                                                              boolean excludeRoot)
        throws PermissionException {
        Collection<ResourceGroup> groups = getAllResourceGroups(subject, excludeRoot);
        return _ownedGroupPager.seek(groups, pc.getPagenum(), pc.getPagesize());
    }

    /**
     * Get the resource groups with the specified ids
     * @param ids the resource group ids
     * @param pc Paging information for the request
     * 
     */
    @Transactional(readOnly = true)
    public PageList<ResourceGroupValue> getResourceGroupsById(AuthzSubject whoami, Integer[] ids,
                                                              PageControl pc)
        throws PermissionException {
        if (ids.length == 0)
            return new PageList<ResourceGroupValue>();

        PageControl allPc = new PageControl();
        // get all roles, sorted but not paged
        allPc.setSortattribute(pc.getSortattribute());
        allPc.setSortorder(pc.getSortorder());
        Collection<ResourceGroup> all = getAllResourceGroups(whoami, false);

        // build an index of ids
        HashSet<Integer> index = new HashSet<Integer>();
        index.addAll(Arrays.asList(ids));
        int numToFind = index.size();

        // find the requested roles
        List<ResourceGroup> groups = new ArrayList<ResourceGroup>(numToFind);
        Iterator<ResourceGroup> i = all.iterator();
        while (i.hasNext() && groups.size() < numToFind) {
            ResourceGroup g = (ResourceGroup) i.next();
            if (index.contains(g.getId()))
                groups.add(g);
        }

        // TODO: G
        PageList<ResourceGroupValue> plist = _groupPager.seek(groups, pc.getPagenum(), pc
            .getPagesize());
        plist.setTotalSize(groups.size());

        return plist;
    }

    /**
     * Change owner of a group.
     * 
     * 
     */
    public void changeGroupOwner(AuthzSubject subject, ResourceGroup group, AuthzSubject newOwner)
        throws PermissionException {
        //TODO this used to call ResourceManager. Perm check?
        group.setOwner(newOwner);
        group.setModifiedBy(newOwner.getName());
    }

    /**
     * Get a ResourceGroup owner's AuthzSubjectValue
     * @param gid The group id
     * @exception NotFoundException Unable to find a group by id
     * 
     */
    @Transactional(readOnly = true)
    public AuthzSubject getResourceGroupOwner(Integer gid) throws NotFoundException {
        Resource gResource = resourceManager.findResourceByInstanceId(resourceManager
            .findResourceTypeByName(AuthzConstants.groupResourceTypeName), gid);
        return gResource.getOwner();
    }

    /**
     * 
     */
    @Transactional(readOnly = true)
    public ResourceGroup getResourceGroupByResource(Resource resource) {
        //TODO not supporting this
        return null;
    }

    /**
     * Set a ResourceGroup modifiedBy attribute
     * @param whoami user requesting to find the group
     * @param id The ID of the role you're looking for.
     * 
     */
    public void setGroupModifiedBy(AuthzSubject whoami, Integer id) {
        ResourceGroup groupLocal = ResourceGroup.findResourceGroup(id);
        groupLocal.setModifiedBy(whoami.getName());
    }

    /**
     * 
     */
    public void updateGroupType(AuthzSubject subject, ResourceGroup g, int groupType,
                                int groupEntType, int groupEntResType) throws PermissionException {
        checkGroupPermission(subject, g.getId(), AuthzConstants.perm_modifyResourceGroup);

        g.setGroupType(new Integer(groupType));

        if (groupType == AppdefEntityConstants.APPDEF_TYPE_GROUP_COMPAT_PS ||
            groupType == AppdefEntityConstants.APPDEF_TYPE_GROUP_COMPAT_SVC) {
            Resource r = findPrototype(new AppdefEntityTypeID(groupEntType, groupEntResType));
            g.setPrototype(r);
        }
    }

    /**
     * Updates the group with all resources meeting the group criteria
     * @param whoami The user token
     * @param group The group whose resources should be updated to match its
     *        criteria
     * @throws HibernateException
     * @throws GroupException
     */
    @SuppressWarnings("unchecked")
    private void updateGroupMembers(AuthzSubject whoami, ResourceGroup group) throws GroupException {
        //TODO not doing critter stuff
        //CritterTranslationContext translationContext = new CritterTranslationContext(whoami);
        //List<Resource> proposedResources = critterTranslator.translate(translationContext,
          //  group.getCritterList()).list();
        List<Resource> proposedResources = new ArrayList<Resource>();
        Collection<Resource> groupMembers = getMembers(group);
        Collection<Resource> resourcesToRemove = new HashSet<Resource>(groupMembers);
        Collection<Resource> resourcesToAdd = new HashSet<Resource>(proposedResources);
        // elements in existing group not in proposed group
        resourcesToRemove.removeAll(proposedResources);
        // elements in proposed group not in existing group
        resourcesToAdd.removeAll(groupMembers);

        if (!resourcesToRemove.isEmpty()) {
            removeResources(group, resourcesToRemove);
        }
        if (!(resourcesToAdd.isEmpty())) {
            addResources(group, resourcesToAdd);
        }
    }

    public void updateGroupMembers(List<ResourceCreatedZevent> resourceEvents) {
        for (ResourceCreatedZevent resourceEvent : resourceEvents) {
            updateGroupMember(resourceEvent);
        }
    }

    private void updateGroupMember(ResourceCreatedZevent resourceEvent) {
        final Resource resource = resourceManager.findResource(resourceEvent.getAppdefEntityID());
        final AuthzSubject subject = authzSubjectManager.findSubjectById(resourceEvent
            .getAuthzSubjectId());
        for (ResourceGroup group : getAllResourceGroups()) {
            try {
                //TODO critter stuff
                //CritterList groupCriteria = group.getCritterList();
                //if (isCriteriaMet(groupCriteria, resource)) {
                    try {
                        addResource(subject, group, resource);
                    } catch (Exception e) {
                        log.error("Unable to add resource " + resource + " to group " +
                                  group.getName());
                    }
                //}
            } catch (Exception e) {
                log.error("Unable to process criteria for group " + group.getName() +
                          " while processing event " + resourceEvent +
                          ".  The groups' members may not be updated.");
            }
        }
    }

    private boolean isCriteriaMet(CritterList groupCriteria, Resource resource) {
        //TODO critter
//        if (groupCriteria.getCritters().isEmpty()) {
//            return false;
//        }
//        if (groupCriteria.isAll()) {
//            for (Critter groupCrit : groupCriteria.getCritters()) {
//                if (!groupCrit.meets(resource)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        for (Critter groupCrit : groupCriteria.getCritters()) {
//            if (groupCrit.meets(resource)) {
//                return true;
//            }
//        }
//        return false;
        return true;
    }

    private Resource findPrototype(AppdefEntityTypeID id) {
        Integer authzType;

        switch (id.getType()) {
            case AppdefEntityConstants.APPDEF_TYPE_PLATFORM:
                authzType = AuthzConstants.authzPlatformProto;
                break;
            case AppdefEntityConstants.APPDEF_TYPE_SERVER:
                authzType = AuthzConstants.authzServerProto;
                break;
            case AppdefEntityConstants.APPDEF_TYPE_SERVICE:
                authzType = AuthzConstants.authzServiceProto;
                break;
            default:
                throw new IllegalArgumentException("Unsupported prototype type: " + id.getType());
        }
        return Resource.findByInstanceId(authzType, id.getId());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
