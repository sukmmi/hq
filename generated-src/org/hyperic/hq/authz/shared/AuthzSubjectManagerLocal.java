/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.authz.shared;

/**
 * Local interface for AuthzSubjectManager.
 */
public interface AuthzSubjectManagerLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Create a subject.
    * @param whoami The current running user.
    * @return Value-object for the new Subject.
    */
   public org.hyperic.hq.authz.server.session.AuthzSubject createSubject( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.String name,boolean active,java.lang.String dsn,java.lang.String dept,java.lang.String email,java.lang.String first,java.lang.String last,java.lang.String phone,java.lang.String sms,boolean html ) throws org.hyperic.hq.authz.shared.PermissionException, javax.ejb.CreateException;

   /**
    * Update user settings for the target
    * @param whoami The current running user.
    * @param target The subject to save. The rest of the parameters specify settings to update. If they are null, then no change will be made to them.
    */
   public void updateSubject( org.hyperic.hq.authz.server.session.AuthzSubject whoami,org.hyperic.hq.authz.server.session.AuthzSubject target,java.lang.Boolean active,java.lang.String dsn,java.lang.String dept,java.lang.String email,java.lang.String firstName,java.lang.String lastName,java.lang.String phone,java.lang.String sms,java.lang.Boolean useHtml ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Check if a subject can modify users
    */
   public void checkModifyUsers( org.hyperic.hq.authz.server.session.AuthzSubject caller ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Delete the specified subject.
    * @param whoami The current running user.
    * @param subject The ID of the subject to delete.
    */
   public void removeSubject( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer subject ) throws javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject findByAuth( java.lang.String name,java.lang.String authDsn ) ;

   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectById( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.Integer id ) throws org.hyperic.hq.authz.shared.PermissionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectById( java.lang.Integer id ) ;

   public org.hyperic.hq.authz.server.session.AuthzSubject getSubjectById( java.lang.Integer id ) ;

   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectByName( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.lang.String name ) throws org.hyperic.hq.authz.shared.PermissionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject findSubjectByName( java.lang.String name ) ;

   public org.hyperic.util.pager.PageList findMatchingName( java.lang.String name,org.hyperic.util.pager.PageControl pc ) ;

   /**
    * List all subjects in the system
    * @param excludes the IDs of subjects to exclude from result    */
   public org.hyperic.util.pager.PageList getAllSubjects( org.hyperic.hq.authz.server.session.AuthzSubject whoami,java.util.Collection excludes,org.hyperic.util.pager.PageControl pc ) throws javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Get the subjects with the specified ids NOTE: This method returns an empty PageList if a null or empty array of ids is received.
    * @param ids the subject ids
    */
   public org.hyperic.util.pager.PageList getSubjectsById( org.hyperic.hq.authz.server.session.AuthzSubject subject,java.lang.Integer[] ids,org.hyperic.util.pager.PageControl pc ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Find the e-mail of the subject specified by id
    * @param id id of the subject.
    * @return The e-mail address of the subject
    */
   public java.lang.String getEmailById( java.lang.Integer id ) ;

   /**
    * Find the e-mail of the subject specified by name
    * @param userName Name of the subjects.
    * @return The e-mail address of the subject
    */
   public java.lang.String getEmailByName( java.lang.String userName ) ;

   /**
    * Get the Preferences for a specified user
    */
   public org.hyperic.util.config.ConfigResponse getUserPrefs( org.hyperic.hq.authz.server.session.AuthzSubject who,java.lang.Integer subjId ) throws org.hyperic.hq.authz.shared.PermissionException;

   /**
    * Set the Preferences for a specified user
    */
   public void setUserPrefs( org.hyperic.hq.authz.server.session.AuthzSubject who,java.lang.Integer subjId,org.hyperic.util.config.ConfigResponse prefs ) throws org.hyperic.hq.authz.shared.PermissionException;

   public org.hyperic.hq.authz.server.session.AuthzSubject getOverlordPojo(  ) ;

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
