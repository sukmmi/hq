/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.appdef.shared;

/**
 * Local interface for ApplicationManager.
 */
public interface ApplicationManagerLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Get all Application types
    * @return list of ApplicationTypeValue objects
    */
   public java.util.List getAllApplicationTypes( org.hyperic.hq.authz.server.session.AuthzSubject who ) throws javax.ejb.FinderException;

   /**
    * Get ApplicationType by ID
    */
   public org.hyperic.hq.appdef.server.session.ApplicationType findApplicationType( java.lang.Integer id ) ;

   /**
    * Create a Application of a specified type
    * @param subject - who
    * @param newApp - the new application to create
    * @param services - A collection of ServiceValue objects that will be the initial set of services for the application. This can be null if you are creating an empty application.
    */
   public org.hyperic.hq.appdef.server.session.Application createApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.ApplicationValue newApp,java.util.Collection services ) throws org.hyperic.hq.appdef.shared.ValidationException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.CreateException, org.hyperic.hq.appdef.shared.AppdefDuplicateNameException;

   /**
    * Update the basic properties of an application. Will NOT update service dependencies, etc.
    */
   public org.hyperic.hq.appdef.shared.ApplicationValue updateApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.ApplicationValue newValue ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.UpdateException, org.hyperic.hq.appdef.shared.AppdefDuplicateNameException, javax.ejb.FinderException;

   /**
    * Remove an application
    */
   public void removeApplication( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer id ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.RemoveException, org.hyperic.hq.common.VetoException;

   /**
    * Remove an application service.
    * @param caller - Valid spider subject of caller.
    * @param appId - The application identifier.
    * @param appServiceId - The service identifier
    * @throws ApplicationException when unable to perform remove
    * @throws ApplicationNotFoundException - when the app can't be found
    * @throws PermissionException - when caller is not authorized to remove.
    */
   public void removeAppService( org.hyperic.hq.authz.server.session.AuthzSubject caller,java.lang.Integer appId,java.lang.Integer appServiceId ) throws org.hyperic.hq.common.ApplicationException, org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   public void handleResourceDelete( org.hyperic.hq.authz.server.session.Resource resource ) ;

   /**
    * Get the service dependency map for an application
    * @param subject
    * @param appId    */
   public org.hyperic.hq.appdef.shared.DependencyTree getServiceDepsForApp( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer pk ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the # of applications within HQ inventory
    */
   public java.lang.Number getApplicationCount(  ) ;

   /**
    * Set the dependency map for an application
    * @param depTree
    * @param subject    */
   public void setServiceDepsForApp( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.DependencyTree depTree ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.CreateException;

   /**
    * Get application pojo by id.
    */
   public org.hyperic.hq.appdef.server.session.Application findApplicationById( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer id ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   public java.util.Collection findDeletedApplications(  ) ;

   /**
    * Get all applications.
    * @param subject The subject trying to list applications.
    * @return A List of ApplicationValue objects representing all of the applications that the given subject is allowed to view.    */
   public org.hyperic.util.pager.PageList getAllApplications( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get all the application services for this application
    * @param subject
    * @param appId
    * @retur list of AppServiceValue objects
    */
   public java.util.List getApplicationServices( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer appId ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Set the application services for this application
    * @param subject
    * @param map key: Integer service ID value: Boolean indicating that the service is an entry point
    */
   public void setApplicationServices( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer appId,java.util.List entityIds ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, javax.ejb.CreateException, org.hyperic.hq.appdef.shared.AppdefGroupNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get all applications for a resource.
    */
   public org.hyperic.util.pager.PageList getApplicationsByResource( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID resource,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get all application IDs that use the specified resource.
    * @param subject The subject trying to get the app list
    * @param resource Server ID.
    * @param pagenum The page number to start listing. First page is zero.
    * @param pagesize The size of the page (the number of items to return).
    * @param sort The sort order.
    * @return A List of ApplicationValue objects which use the specified resource.    */
   public java.lang.Integer[] getApplicationIDsByResource( org.hyperic.hq.appdef.shared.AppdefEntityID resource ) throws org.hyperic.hq.appdef.shared.ApplicationNotFoundException;

   public boolean isApplicationMember( org.hyperic.hq.appdef.shared.AppdefEntityID application,org.hyperic.hq.appdef.shared.AppdefEntityID service ) ;

   /**
    * Generate a resource tree based on the root resources and the traversal (one of ResourceTreeGenerator.TRAVERSE_*)
    */
   public org.hyperic.hq.appdef.shared.resourceTree.ResourceTree getResourceTree( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID[] resources,int traversal ) throws org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   public void startup(  ) ;

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
