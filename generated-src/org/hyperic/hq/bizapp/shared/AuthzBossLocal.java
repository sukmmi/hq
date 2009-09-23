/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.bizapp.shared;

/**
 * Local interface for AuthzBoss.
 */
public interface AuthzBossLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Check if the current logged in user can administer CAM
    * @return true - if user has adminsterCAM op false otherwise
    */
   public boolean hasAdminPermission( int sessionId ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>ResourceTypeValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public java.util.List getAllResourceTypes( java.lang.Integer sessionId,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.CreateException, javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return the full <code>List</code> of <code>ResourceTypeValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public java.util.List getAllResourceTypes( java.lang.Integer sessionId ) throws javax.ejb.CreateException, javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>OperationValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public java.util.List getAllOperations( java.lang.Integer sessionId,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return the full <code>List</code> of <code>OperationValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public java.util.List getAllOperations( java.lang.Integer sessionId ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>AuthzSubjectValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public org.hyperic.util.pager.PageList getAllSubjects( java.lang.Integer sessionId,java.util.Collection excludes,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Return a sorted, paged <code>List</code> of <code>AuthzSubjectValue</code> objects corresponding to the specified id values.
    */
   public org.hyperic.util.pager.PageList getSubjectsById( java.lang.Integer sessionId,java.lang.Integer[] ids,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>AuthzSubjectValue</code> objects matching name as substring
    */
   public org.hyperic.util.pager.PageList getSubjectsByName( java.lang.Integer sessionId,java.lang.String name,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>ResourceGroupValue</code> objects representing every resource type in the system that the user is allowed to view.
    */
   public java.util.List getAllResourceGroups( java.lang.Integer sessionId,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Return a sorted, paged <code>List</code> of <code>ResourceGroupValue</code> objects corresponding to the specified id values.
    */
   public org.hyperic.util.pager.PageList getResourceGroupsById( java.lang.Integer sessionId,java.lang.Integer[] ids,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   public java.util.Map findResourcesByIds( java.lang.Integer sessionId,org.hyperic.hq.appdef.shared.AppdefEntityID[] entities ) throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException;

   /**
    * Remove the user identified by the given ids from the subject as well as principal tables.
    */
   public void removeSubject( java.lang.Integer sessionId,java.lang.Integer[] ids ) throws javax.ejb.FinderException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Update a subject
    */
   public void updateSubject( java.lang.Integer sessionId,org.hyperic.hq.authz.server.session.AuthzSubject target,java.lang.Boolean active,java.lang.String dsn,java.lang.String dept,java.lang.String email,java.lang.String first,java.lang.String last,java.lang.String phone,java.lang.String sms,java.lang.Boolean useHtml ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.auth.shared.SessionException;

   /**
    * Create the user identified by the given ids from the subject as well as principal tables.
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject createSubject( java.lang.Integer sessionId,java.lang.String name,boolean active,java.lang.String dsn,java.lang.String dept,java.lang.String email,java.lang.String first,java.lang.String last,java.lang.String phone,java.lang.String sms,boolean useHtml ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.CreateException, org.hyperic.hq.auth.shared.SessionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject getCurrentSubject( int sessionid ) throws org.hyperic.hq.auth.shared.SessionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject getCurrentSubject( java.lang.String name ) throws org.hyperic.hq.auth.shared.SessionException, org.hyperic.hq.common.ApplicationException;

   /**
    * Return the <code>AuthzSubject</code> object identified by the given subject id.
    * @throws SessionTimeoutException
    * @throws SessionNotFoundException
    * @throws PermissionException
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectById( java.lang.Integer sessionId,java.lang.Integer subjectId ) throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Return the <code>AuthzSubject</code> object identified by the given username.
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectByName( java.lang.Integer sessionId,java.lang.String subjectName ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Return the <code>AuthzSubject</code> object identified by the given username. This method should only be used in cases where displaying the user does not require an Authz check. An example of this is when the owner and last modifier need to be displayed, and the user viewing the resource does not have permissions to view other users. See bug #5452 for more information
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectByNameNoAuthz( java.lang.Integer sessionId,java.lang.String subjectName ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Return a ConfigResponse matching the UserPreferences
    * @throws ApplicationException
    * @throws ConfigPropertyException
    * @throws LoginException
    */
   public org.hyperic.util.config.ConfigResponse getUserPrefs( java.lang.String username ) throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.common.ApplicationException, org.hyperic.util.ConfigPropertyException;

   /**
    * Return a ConfigResponse matching the UserPreferences
    */
   public org.hyperic.util.config.ConfigResponse getUserPrefs( java.lang.Integer sessionId,java.lang.Integer subjectId ) ;

   /**
    * Set the UserPreferences
    */
   public void setUserPrefs( java.lang.Integer sessionId,java.lang.Integer subjectId,org.hyperic.util.config.ConfigResponse prefs ) throws org.hyperic.hq.common.ApplicationException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Get the current user's dashboard
    */
   public org.hyperic.util.config.ConfigResponse getUserDashboardConfig( java.lang.Integer sessionId ) throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the email of a user by name
    */
   public java.lang.String getEmailByName( java.lang.Integer sessionId,java.lang.String userName ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

   /**
    * Get the email of a user by id
    */
   public java.lang.String getEmailById( java.lang.Integer sessionId,java.lang.Integer userId ) throws javax.ejb.FinderException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException;

}
