/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.appdef.shared;

/**
 * Local interface for ServerManager.
 */
public interface ServerManagerLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Clone a Server to a target Platform
    */
   public org.hyperic.hq.appdef.server.session.Server cloneServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Platform targetPlatform,org.hyperic.hq.appdef.server.session.Server serverToClone ) throws org.hyperic.hq.appdef.shared.ValidationException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.RemoveException, org.hyperic.hq.common.VetoException, javax.ejb.CreateException, javax.ejb.FinderException;

   /**
    * Move a Server to the given Platform
    * @param subject The user initiating the move.
    * @param target The target {@link org.hyperic.hq.appdef.server.session.Server} to move.
    * @param destination The destination {@link Platform}.
    * @throws org.hyperic.hq.authz.shared.PermissionException If the passed user does not have permission to move the Server.
    * @throws org.hyperic.hq.common.VetoException If the operation canot be performed due to incompatible types.
    */
   public void moveServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Server target,org.hyperic.hq.appdef.server.session.Platform destination ) throws org.hyperic.hq.common.VetoException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Create a Server on the given platform.
    * @return ServerValue - the saved value object
    * @exception CreateException - if it fails to add the server
    */
   public org.hyperic.hq.appdef.server.session.Server createServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platformId,java.lang.Integer serverTypeId,org.hyperic.hq.appdef.shared.ServerValue sValue ) throws javax.ejb.CreateException, org.hyperic.hq.appdef.shared.ValidationException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.appdef.shared.AppdefDuplicateNameException;

   /**
    * Create a virtual server
    * @throws FinderException
    * @throws CreateException
    * @throws PermissionException
    */
   public org.hyperic.hq.appdef.server.session.Server createVirtualServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Platform platform,org.hyperic.hq.appdef.server.session.ServerType st ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.CreateException, javax.ejb.FinderException;

   /**
    * A removeServer method that takes a ServerLocal. Used by PlatformManager.removePlatform when cascading removal to servers.
    */
   public void removeServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Server server ) throws javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.VetoException;

   public void handleResourceDelete( org.hyperic.hq.authz.server.session.Resource resource ) ;

   /**
    * Find all server types
    * @return list of serverTypeValues
    */
   public org.hyperic.util.pager.PageList getAllServerTypes( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException;

   public org.hyperic.hq.appdef.server.session.Server getServerByName( org.hyperic.hq.appdef.server.session.Platform host,java.lang.String name ) ;

   /**
    * Find viewable server types
    * @return list of serverTypeValues
    */
   public org.hyperic.util.pager.PageList getViewableServerTypes( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Find viewable server non-virtual types for a platform
    * @return list of serverTypeValues
    */
   public org.hyperic.util.pager.PageList getServerTypesByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.appdef.shared.ServerNotFoundException;

   /**
    * Find viewable server types for a platform
    * @return list of serverTypeValues
    */
   public org.hyperic.util.pager.PageList getServerTypesByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,boolean excludeVirtual,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.appdef.shared.ServerNotFoundException;

   /**
    * Find all ServerTypes for a givent PlatformType id. This can go once we begin passing POJOs to the UI layer.
    * @return A list of ServerTypeValue objects for thie PlatformType.
    */
   public org.hyperic.util.pager.PageList getServerTypesByPlatformType( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platformTypeId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.PlatformNotFoundException;

   public org.hyperic.hq.appdef.server.session.Server findServerByAIID( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Platform platform,java.lang.String aiid ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Find a Server by Id.
    */
   public org.hyperic.hq.appdef.server.session.Server findServerById( java.lang.Integer id ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException;

   /**
    * Get a Server by Id.
    * @return The Server with the given id, or null if not found.    */
   public org.hyperic.hq.appdef.server.session.Server getServerById( java.lang.Integer id ) ;

   /**
    * Find a ServerType by id
    */
   public org.hyperic.hq.appdef.server.session.ServerType findServerType( java.lang.Integer id ) ;

   /**
    * Find a server type by name
    * @param name - the name of the server
    * @return ServerTypeValue
    */
   public org.hyperic.hq.appdef.server.session.ServerType findServerTypeByName( java.lang.String name ) throws javax.ejb.FinderException;

   public java.util.List findServersByType( org.hyperic.hq.appdef.server.session.Platform p,org.hyperic.hq.appdef.server.session.ServerType st ) ;

   public java.util.Collection findDeletedServers(  ) ;

   /**
    * Get server lite value by id. Does not check permission.
    */
   public org.hyperic.hq.appdef.server.session.Server getServerById( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer id ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get server IDs by server type.
    * @param subject The subject trying to list servers.
    * @param servTypeId server type id.
    * @return An array of Server IDs.    */
   public java.lang.Integer[] getServerIds( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer servTypeId ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get server by service.
    */
   public org.hyperic.hq.appdef.shared.ServerValue getServerByService( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer sID ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.ServiceNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get server by service. The virtual servers are not filtere out of returned list.
    */
   public org.hyperic.util.pager.PageList getServersByServices( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.util.List sIDs ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.ServerNotFoundException;

   /**
    * Get all servers.
    * @param subject The subject trying to list servers.
    * @return A List of ServerValue objects representing all of the servers that the given subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getAllServers( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException;

   public java.util.Collection getViewableServers( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.server.session.Platform platform ) ;

   /**
    * Get servers by platform.
    * @param subject The subject trying to list servers.
    * @param platId platform id.
    * @param excludeVirtual true if you dont want virtual (fake container) servers in the returned list
    * @param pc The page control.
    * @return A PageList of ServerValue objects representing servers on the specified platform that the subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getServersByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,boolean excludeVirtual,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get servers by server type and platform.
    * @param subject The subject trying to list servers.
    * @param servTypeId server type id.
    * @param platId platform id.
    * @param pc The page control.
    * @return A PageList of ServerValue objects representing servers on the specified platform that the subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getServersByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,java.lang.Integer servTypeId,boolean excludeVirtual,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get servers by server type and platform.
    * @param subject The subject trying to list servers.
    * @param platId platform id.
    * @return A PageList of ServerValue objects representing servers on the specified platform that the subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getServersByPlatformServiceType( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,java.lang.Integer svcTypeId ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get servers by server type and platform.
    * @param subject The subject trying to list servers.
    * @param typeId server type id.
    * @return A PageList of ServerValue objects representing servers on the specified platform that the subject is allowed to view.    */
   public java.util.List getServersByType( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.String name ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.InvalidAppdefTypeException;

   /**
    * Get non-virtual server IDs by server type and platform.
    * @param subject The subject trying to list servers.
    * @param platId platform id.
    * @return An array of Integer[] which represent the ServerIds specified platform that the subject is allowed to view.    */
   public java.lang.Integer[] getServerIdsByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get non-virtual server IDs by server type and platform.
    * @param subject The subject trying to list servers.
    * @param servTypeId server type id.
    * @param platId platform id.
    * @return An array of Integer[] which represent the ServerIds    */
   public java.lang.Integer[] getServerIdsByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,java.lang.Integer servTypeId ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get server IDs by server type and platform.
    * @param subject The subject trying to list servers.
    * @param servTypeId server type id.
    * @param platId platform id.
    * @return A PageList of ServerValue objects representing servers on the specified platform that the subject is allowed to view.    */
   public java.lang.Integer[] getServerIdsByPlatform( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer platId,java.lang.Integer servTypeId,boolean excludeVirtual ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.PlatformNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get servers by application.
    * @param subject The subject trying to list servers.
    * @param appId Application id.
    * @param pc The page control for this page list.
    * @return A List of ServerValue objects representing servers that support the given application that the subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getServersByApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer appId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get servers by application and serverType.
    * @param subject The subject trying to list servers.
    * @param appId Application id.
    * @param pc The page control for this page list.
    * @return A List of ServerValue objects representing servers that support the given application that the subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getServersByApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer appId,java.lang.Integer servTypeId,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get server IDs by application and serverType.
    * @param subject The subject trying to list servers.
    * @param appId Application id.
    * @return A List of ServerValue objects representing servers that support the given application that the subject is allowed to view.    */
   public java.lang.Integer[] getServerIdsByApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer appId,java.lang.Integer servTypeId ) throws org.hyperic.hq.appdef.shared.ServerNotFoundException, org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Update a server
    * @param existing
    */
   public org.hyperic.hq.appdef.server.session.Server updateServer( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.ServerValue existing ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.UpdateException, org.hyperic.hq.appdef.shared.AppdefDuplicateNameException, org.hyperic.hq.appdef.shared.ServerNotFoundException;

   /**
    * Update server types
    */
   public void updateServerTypes( java.lang.String plugin,org.hyperic.hq.product.ServerTypeInfo[] infos ) throws javax.ejb.CreateException, javax.ejb.FinderException, javax.ejb.RemoveException, org.hyperic.hq.common.VetoException;

   public void deleteServerType( org.hyperic.hq.appdef.server.session.ServerType serverType,org.hyperic.hq.authz.server.session.AuthzSubject overlord,org.hyperic.hq.authz.shared.ResourceGroupManager resGroupMan,org.hyperic.hq.authz.shared.ResourceManager resMan ) throws org.hyperic.hq.common.VetoException, javax.ejb.RemoveException;

   public void setAutodiscoveryZombie( org.hyperic.hq.appdef.server.session.Server server,boolean zombie ) ;

   /**
    * Returns a list of 2 element arrays. The first element is the name of the server type, the second element is the # of servers of that type in the inventory.
    */
   public java.util.List getServerTypeCounts(  ) ;

   /**
    * Get the # of servers within HQ inventory. This method ingores virtual server types.
    */
   public java.lang.Number getServerCount(  ) ;

   /**
    * Check for createPlatform permission for a resource
    * @param subject
    * @throws PermissionException
    */
   public void checkCreatePlatformPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for modify permission for a given resource
    */
   public void checkModifyPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for view permission for a given resource
    */
   public void checkViewPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for control permission for a given resource
    */
   public void checkControlPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for control permission for a given resource
    */
   public void checkRemovePermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for monitor permission for a given resource
    */
   public void checkMonitorPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check for manage alerts permission for a given resource
    */
   public void checkAlertingPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check the scope of alertable resources for a give subject
    * @return a list of AppdefEntityIds
    */
   public java.util.List checkAlertingScope( org.hyperic.hq.authz.server.session.AuthzSubject subj ) ;

   /**
    * Check for create child object permission for a given resource Child Resources: Platforms -> servers Servers -> services Any other resource will throw an InvalidAppdefTypeException since no other resources have this parent->child relationship with respect to their permissions
    * @param subject
    * @param id - what
    * @param subject - who
    */
   public void checkCreateChildPermission( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the AppdefResourcePermissions for a given resource
    * @deprecated Use the individual check*Permission methods instead.    */
   public org.hyperic.hq.appdef.shared.AppdefResourcePermissions getResourcePermissions( org.hyperic.hq.authz.server.session.AuthzSubject who,org.hyperic.hq.appdef.shared.AppdefEntityID eid ) throws javax.ejb.FinderException;

   /**
    * Change appdef entity owner
    */
   public void changeOwner( org.hyperic.hq.authz.server.session.AuthzSubject who,org.hyperic.hq.appdef.server.session.AppdefResource res,org.hyperic.hq.authz.server.session.AuthzSubject newOwner ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.ServerNotFoundException;

}
