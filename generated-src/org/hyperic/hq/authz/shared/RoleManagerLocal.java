/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.authz.shared;

/**
 * Local interface for RoleManager.
 */
public interface RoleManagerLocal
   extends javax.ejb.EJBLocalObject
{

   public boolean isRootRoleMember( org.hyperic.hq.authz.server.session.AuthzSubject subject ) ;

   /**
    * Create a role.
    * @param whoami The current running user.
    * @param role The to be created.
    * @param operations Operations to associate with the new role. Use null if you want to associate operations later.
    * @param subjectIds Ids of subjects to add to the new role. Use null to add subjects later.
    * @param groupIds Ids of resource groups to add to the new role. Use null to add subjects later.
    * @return OwnedRoleValue for the role.
    * @throws CreateException Unable to create the specified entity.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami may not perform createResource on the covalentAuthzRole ResourceType.
    */
   public java.lang.Integer createOwnedRole( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.shared.RoleValue role,org.hyperic.hq.authz.server.session.Operation[] operations,java.lang.Integer[] subjectIds,java.lang.Integer[] groupIds ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.AuthzDuplicateNameException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Delete the specified role.
    * @param whoami The current running user.
    * @param role The role to delete.
    * @throws RemoveException Unable to delete the specified entity.
    */
   public void removeRole( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer rolePk ) throws javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Write the specified entity out to permanent storage.
    * @param whoami The current running user.
    * @param role The role to save.
    * @throws PermissionException whoami may not perform modifyRole on this role.
    */
   public void saveRole( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.shared.RoleValue role ) throws org.hyperic.hq.authz.shared.AuthzDuplicateNameException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Change the owner of the role.
    * @param whoami The current running user.
    * @param id The ID of the role to change
    * @param ownerVal The new owner of the role..
    * @throws PermissionException whoami may not perform modifyRole on this role.
    */
   public void changeOwner( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id,org.hyperic.hq.authz.server.session.AuthzSubject owner ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Associate operations with this role.
    * @param whoami The current running user.
    * @param role The role.
    * @param operations The operations to associate with the role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami may not perform addOperation on this role.
    */
   public void addOperations( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.Role role,org.hyperic.hq.authz.server.session.Operation[] operations ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Disassociate all operations from this role.
    * @param whoami The current running user.
    * @param role The role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami may not perform removeOperation on this role.
    */
   public void removeAllOperations( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.Role role ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Set the operations for this role. To get the operations call getOperations() on the value-object.
    * @param whoami The current running user.
    * @param id The ID of the role.
    * @param operations Operations to associate with this role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform setOperations on this role.
    */
   public void setOperations( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id,org.hyperic.hq.authz.server.session.Operation[] operations ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Associate ResourceGroups with this role.
    * @param whoami The current running user.
    * @param role This role.
    * @param gids The ids of the groups to associate with this role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform addResourceGroup on this role.
    */
   public void addResourceGroups( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer rid,java.lang.Integer[] gids ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Associate ResourceGroup with list of roles.
    * @param whoami The current running user.
    * @param roles The roles.
    * @param ids The id of the group to associate with the roles.
    * @throws PermissionException whoami is not allowed to perform addResourceGroup on this role.
    * @throws FinderException SQL error looking up roles scope
    */
   public void addResourceGroupRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer gid,java.lang.Integer[] ids ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Disassociate ResourceGroups from this role.
    * @param whoami The current running user.
    * @param id This role.
    * @param gids The ids of the groups to disassociate.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform modifyRole on this role.
    */
   public void removeResourceGroups( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id,java.lang.Integer[] gids ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Disassociate roles from this ResourceGroup.
    * @param whoami The current running user.
    * @param role This role.
    * @param ids The ids of the groups to disassociate.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform modifyRole on this role.
    */
   public void removeResourceGroupRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer gid,java.lang.Integer[] ids ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Disassociate all ResourceGroups of this role from this role.
    * @param whoami The current running user.
    * @param role This role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws NamingException
    * @throws PermissionException whoami is not allowed to perform modifyRole on this role.
    */
   public void removeAllResourceGroups( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.Role role ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the # of roles within HQ inventory
    */
   public java.lang.Number getRoleCount(  ) ;

   /**
    * Get the # of subjects within HQ inventory
    */
   public java.lang.Number getSubjectCount(  ) ;

   /**
    * Get a Role by id
    */
   public org.hyperic.hq.authz.server.session.Role getRoleById( int id ) ;

   public org.hyperic.hq.authz.server.session.Role findRoleById( int id ) ;

   public org.hyperic.hq.authz.server.session.Role findRoleByName( java.lang.String name ) ;

   /**
    * Create a calendar under a role for a specific type. Calendars created in this manner are tied directly to the role and should not be used by other roles.
    * @throws PermissionException if user is not allowed to modify role
    */
   public org.hyperic.hq.authz.server.session.RoleCalendar createCalendar( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.Role r,java.lang.String calendarName,org.hyperic.hq.authz.server.session.RoleCalendarType type ) throws org.hyperic.hq.authz.shared.PermissionException;

   public boolean removeCalendar( org.hyperic.hq.authz.server.session.RoleCalendar c ) ;

   /**
    * Find the owned role that has the given ID.
    * @param id The ID of the role you're looking for.
    * @return The owned value-object of the role of the given ID.
    * @throws FinderException Unable to find a given or dependent entities.
    */
   public org.hyperic.hq.authz.values.OwnedRoleValue findOwnedRoleById( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get role permission Map For a given role id, find the resource types and permissions which are supported by it
    * @param subject
    * @param roleId
    * @return map - keys are resource type names, values are lists of operation values which are supported on the resouce type.
    */
   public java.util.Map getRoleOperationMap( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer roleId ) throws org.hyperic.hq.authz.shared.PermissionException;

   public java.util.Collection getAllRoles(  ) ;

   /**
    * List all Roles in the system
    * @param pc Paging information for the request
    * @return List a list of RoleValues
    */
   public java.util.List getAllRoles( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException;

   /**
    * List all OwnedRoles in the system
    * @param subject
    * @param pc Paging and sorting information.
    * @return List a list of OwnedRoleValues
    */
   public java.util.List getAllOwnedRoles( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) ;

   /**
    * List all Roles in the system, except system roles.
    * @return List a list of OwnedRoleValues that are not system roles
    * @throws FinderException if sort attribute is unrecognized
    */
   public org.hyperic.util.pager.PageList getAllNonSystemOwnedRoles( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer[] excludeIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Get the roles with the specified ids
    * @param subject
    * @param ids the role ids
    * @param pc Paging information for the request
    * @throws FinderException
    * @throws PermissionException
    */
   public org.hyperic.util.pager.PageList getRolesById( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer[] ids,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Associate roles with this subject.
    * @param whoami The current running user.
    * @param subject The subject.
    * @param roles The roles to associate with the subject.
    * @throws PermissionException whoami may not perform addRole on this subject.
    */
   public void addRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer[] roles ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Disassociate roles from this subject.
    * @param whoami The current running user.
    * @param subject The subject.
    * @param roles The subjects to disassociate.
    * @throws PermissionException whoami may not perform removeRole on this subject.
    */
   public void removeRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer[] roles ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Get the roles for a subject
    * @param whoami
    * @param subject
    * @param pc Paging and sorting information.
    * @return Set of Roles
    */
   public java.util.List getRoles( org.hyperic.hq.authz.server.session.AuthzSubject subjectValue,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the owned roles for a subject.
    * @param whoami
    * @param subject
    * @param pc Paging and sorting information.
    * @return Set of Roles
    */
   public java.util.List getOwnedRoles( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the owned roles for a subject, except system roles.
    * @param callerSubjectValue is the subject of caller.
    * @param intendedSubjectValue is the subject of intended subject.
    * @param pc The PageControl object for paging results.
    * @return List a list of OwnedRoleValues that are not system roles
    * @throws CreateException indicating ejb creation / container failure.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException caller is not allowed to perform listRoles on this role.
    * @throws FinderException SQL error looking up roles scope    */
   public org.hyperic.util.pager.PageList getNonSystemOwnedRoles( org.hyperic.hq.authz.server.session.AuthzSubject callerSubjectValue,org.hyperic.hq.authz.server.session.AuthzSubject intendedSubjectValue,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Get the owned roles for a subject, except system roles.
    * @param callerSubjectValue is the subject of caller.
    * @param intendedSubjectValue is the subject of intended subject.
    * @param pc The PageControl object for paging results.
    * @return List a list of OwnedRoleValues that are not system roles
    * @throws CreateException indicating ejb creation / container failure.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException caller is not allowed to perform listRoles on this role.
    * @throws FinderException SQL error looking up roles scope    */
   public org.hyperic.util.pager.PageList getNonSystemOwnedRoles( org.hyperic.hq.authz.server.session.AuthzSubject callerSubjectValue,org.hyperic.hq.authz.server.session.AuthzSubject intendedSubjectValue,java.lang.Integer[] excludeIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * List the roles that this subject is not in and that are not one of the specified roles.
    * @param whoami The current running user.
    * @param system If true, then only system roles are returned. If false, then only non-system roles are returned.
    * @param subjectId The id of the subject.
    * @return List of roles.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform listRoles on this role.
    * @throws FinderException
    */
   public org.hyperic.util.pager.PageList getAvailableRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,boolean system,java.lang.Integer subjectId,java.lang.Integer[] roleIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * List the roles that this subject is not in and that are not one of the specified roles.
    * @param whoami The current running user.
    * @param system If true, then only system roles are returned. If false, then only non-system roles are returned.
    * @param groupId The id of the subject.
    * @return List of roles.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform listRoles on this role.
    * @throws FinderException if the sort attribute was not recognized
    */
   public org.hyperic.util.pager.PageList getAvailableGroupRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer groupId,java.lang.Integer[] roleIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Get the resource groups applicable to a given role
    */
   public org.hyperic.util.pager.PageList getResourceGroupsByRoleIdAndSystem( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer roleId,boolean system,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Return the roles of a group
    * @throws PermissionException
    */
   public org.hyperic.util.pager.PageList getResourceGroupRoles( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer groupId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * List the groups not in this role and not one of the specified groups.
    * @param whoami The current running user.
    * @param roleId The id of the role.
    * @return List of groups in this role.
    * @throws PermissionException whoami is not allowed to perform listGroups on this role.
    * @throws FinderException
    */
   public org.hyperic.util.pager.PageList getAvailableResourceGroups( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer roleId,java.lang.Integer[] groupIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * List the subjects in this role.
    * @param whoami The current running user.
    * @param roleId The id of the role.
    * @return List of subjects in this role.
    * @throws PermissionException whoami is not allowed to perform listSubjects on this role.
    * @throws FinderException if the sort attribute is not recognized
    */
   public org.hyperic.util.pager.PageList getSubjects( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer roleId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * List the subjects not in this role and not one of the specified subjects.
    * @param whoami The current running user.
    * @param roleId The id of the role.
    * @return List of subjects in this role.
    * @throws FinderException Unable to find a given or dependent entities.
    * @throws PermissionException whoami is not allowed to perform listSubjects on this role.
    * @throws FinderException if the sort attribute is not recognized
    */
   public org.hyperic.util.pager.PageList getAvailableSubjects( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer roleId,java.lang.Integer[] subjectIds,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException;

   /**
    * Add subjects to this role.
    * @param whoami The current running user.
    * @param id The ID of the role.
    * @param sids Ids of ubjects to add to role.
    * @throws PermissionException whoami is not allowed to perform addSubject on this role.
    */
   public void addSubjects( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id,java.lang.Integer[] sids ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Remove subjects from this role.
    * @param whoami The current running user.
    * @param id The ID of the role.
    * @param ids The ids of the subjects to remove.
    * @throws PermissionException whoami is not allowed to perform removeSubject on this role.
    */
   public void removeSubjects( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id,java.lang.Integer[] ids ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Find all {@link Operation} objects
    */
   public java.util.Collection findAllOperations(  ) ;

   /**
    * Find the subject that has the given name and authentication source.
    * @param name Name of the subject.
    * @param authDsn DSN of the authentication source. Authentication sources are defined externally.
    * @return The value-object of the subject of the given name and authenticating source.
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectByAuth( java.lang.String name,java.lang.String authDsn ) throws org.hyperic.hq.auth.shared.SubjectNotFoundException;

   public org.hyperic.hq.authz.server.session.ResourceRelation getContainmentRelation(  ) ;

   public org.hyperic.hq.authz.server.session.ResourceRelation getNetworkRelation(  ) ;

}
