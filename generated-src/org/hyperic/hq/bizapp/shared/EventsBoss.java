/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.bizapp.shared;

/**
 * Remote interface for EventsBoss.
 */
public interface EventsBoss
   extends javax.ejb.EJBObject
{
   /**
    * Get the number of alerts for the given array of AppdefEntityID's
    */
   public int[] getAlertCount( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID[] ids )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException, java.rmi.RemoteException;

   /**
    * Create an alert definition
    */
   public org.hyperic.hq.events.shared.AlertDefinitionValue createAlertDefinition( int sessionID,org.hyperic.hq.events.shared.AlertDefinitionValue adval )
      throws org.hyperic.hq.events.AlertDefinitionCreateException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.util.config.InvalidOptionException, org.hyperic.util.config.InvalidOptionValueException, org.hyperic.hq.auth.shared.SessionException, java.rmi.RemoteException;

   /**
    * Create an alert definition for a resource type
    */
   public org.hyperic.hq.events.shared.AlertDefinitionValue createResourceTypeAlertDefinition( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityTypeID aetid,org.hyperic.hq.events.shared.AlertDefinitionValue adval )
      throws org.hyperic.hq.events.AlertDefinitionCreateException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.util.config.InvalidOptionException, org.hyperic.util.config.InvalidOptionValueException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   public void inheritResourceTypeAlertDefinition( org.hyperic.hq.authz.server.session.AuthzSubject subject,org.hyperic.hq.appdef.shared.AppdefEntityID id )
      throws org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.util.config.InvalidOptionException, org.hyperic.util.config.InvalidOptionValueException, org.hyperic.hq.events.AlertDefinitionCreateException, java.rmi.RemoteException;

   public org.hyperic.hq.events.server.session.Action createAction( int sessionID,java.lang.Integer adid,java.lang.String className,org.hyperic.util.config.ConfigResponse config )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.events.ActionCreateException, javax.ejb.RemoveException, javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Activate/deactivate a collection of alert definitions
    */
   public void activateAlertDefinitions( int sessionID,java.lang.Integer[] ids,boolean activate )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Activate or deactivate alert definitions by AppdefEntityID.
    */
   public void activateAlertDefinitions( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID[] eids,boolean activate )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Update just the basics
    */
   public void updateAlertDefinitionBasic( int sessionID,java.lang.Integer alertDefId,java.lang.String name,java.lang.String desc,int priority,boolean activate )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.FinderException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   public void updateAlertDefinition( int sessionID,org.hyperic.hq.events.shared.AlertDefinitionValue adval )
      throws org.hyperic.hq.events.TriggerCreateException, org.hyperic.util.config.InvalidOptionException, org.hyperic.util.config.InvalidOptionValueException, org.hyperic.hq.events.AlertConditionCreateException, org.hyperic.hq.events.ActionCreateException, javax.ejb.FinderException, javax.ejb.RemoveException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Get actions for a given alert.
    * @param alertId the alert id
    */
   public java.util.List getActionsForAlert( int sessionId,java.lang.Integer alertId )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Update an action
    */
   public void updateAction( int sessionID,org.hyperic.hq.events.shared.ActionValue aval )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Delete a collection of alert definitions
    */
   public void deleteAlertDefinitions( int sessionID,java.lang.Integer[] ids )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Delete list of alerts
    */
   public void deleteAlerts( int sessionID,java.lang.Integer[] ids )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Delete all alerts for a resource
    */
   public int deleteAlerts( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID aeid )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Delete all alerts for a given period of time
    */
   public int deleteAlerts( int sessionID,long begin,long end )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Delete all alerts for a list of alert definitions
    * @throws FinderException if alert definition is not found
    */
   public int deleteAlertsForDefinitions( int sessionID,java.lang.Integer[] adids )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.RemoveException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException, java.rmi.RemoteException;

   /**
    * Get an alert definition by ID
    */
   public org.hyperic.hq.events.shared.AlertDefinitionValue getAlertDefinition( int sessionID,java.lang.Integer id )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, javax.ejb.FinderException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Find an alert by ID
    */
   public org.hyperic.hq.events.server.session.Alert getAlert( int sessionID,java.lang.Integer id )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.events.AlertNotFoundException, java.rmi.RemoteException;

   /**
    * Get a list of all alert definitions
    */
   public org.hyperic.util.pager.PageList findAllAlertDefinitions( int sessionID )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Get a collection of alert definitions for a resource
    */
   public org.hyperic.util.pager.PageList findAlertDefinitions( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID id,org.hyperic.util.pager.PageControl pc )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Get a collection of alert definitions for a resource or resource type
    */
   public org.hyperic.util.pager.PageList findAlertDefinitions( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityTypeID id,org.hyperic.util.pager.PageControl pc )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Find all alert definition names for a resource
    * @return Map of AlertDefinition names and IDs
    */
   public java.util.Map findAlertDefinitionNames( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID id,java.lang.Integer parentId )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Get a list of all alerts
    */
   public org.hyperic.util.pager.PageList findAllAlerts( int sessionID )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Find all alerts for an appdef resource
    */
   public org.hyperic.util.pager.PageList findAlerts( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID id,org.hyperic.util.pager.PageControl pc )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Find all alerts for an appdef resource
    */
   public org.hyperic.util.pager.PageList findAlerts( int sessionID,org.hyperic.hq.appdef.shared.AppdefEntityID id,long begin,long end,org.hyperic.util.pager.PageControl pc )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Search alerts given a set of criteria
    * @param username the username
    * @param count the maximum number of alerts to return
    * @param priority allowable values: 0 (all), 1, 2, or 3
    * @param timeRange the amount of time from current time to include
    * @param ids the IDs of resources to include or null for ALL
    * @return a list of {@link Escalatable}s
    */
   public java.util.List findRecentAlerts( java.lang.String username,int count,int priority,long timeRange,org.hyperic.hq.appdef.shared.AppdefEntityID[] ids )
      throws javax.security.auth.login.LoginException, org.hyperic.hq.common.ApplicationException, org.hyperic.util.ConfigPropertyException, java.rmi.RemoteException;

   /**
    * Search recent alerts given a set of criteria
    * @param sessionID the session token
    * @param count the maximum number of alerts to return
    * @param priority allowable values: 0 (all), 1, 2, or 3
    * @param timeRange the amount of time from current time to include
    * @param ids the IDs of resources to include or null for ALL
    * @return a list of {@link Escalatable}s
    */
   public java.util.List findRecentAlerts( int sessionID,int count,int priority,long timeRange,org.hyperic.hq.appdef.shared.AppdefEntityID[] ids )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Get config schema info for an action class
    */
   public org.hyperic.util.config.ConfigSchema getActionConfigSchema( int sessionID,java.lang.String actionClass )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.util.config.EncodingException, java.rmi.RemoteException;

   /**
    * Get config schema info for a trigger class
    */
   public org.hyperic.util.config.ConfigSchema getRegisteredTriggerConfigSchema( int sessionID,java.lang.String triggerClass )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.util.config.EncodingException, java.rmi.RemoteException;

   public void deleteEscalationByName( int sessionID,java.lang.String name )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.ApplicationException, java.rmi.RemoteException;

   public void deleteEscalationById( int sessionID,java.lang.Integer id )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.ApplicationException, java.rmi.RemoteException;

   /**
    * remove escalation by id
    */
   public void deleteEscalationById( int sessionID,java.lang.Integer[] ids )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.ApplicationException, java.rmi.RemoteException;

   /**
    * retrieve escalation name by alert definition id.
    */
   public java.lang.Integer getEscalationIdByAlertDefId( int sessionID,java.lang.Integer id,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException, java.rmi.RemoteException;

   /**
    * set escalation name by alert definition id.
    */
   public void setEscalationByAlertDefId( int sessionID,java.lang.Integer id,java.lang.Integer escId,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * unset escalation by alert definition id.
    */
   public void unsetEscalationByAlertDefId( int sessionID,java.lang.Integer id,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * retrieve escalation JSONObject by alert definition id.
    */
   public org.json.JSONObject jsonEscalationByAlertDefId( int sessionID,java.lang.Integer id,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType )
      throws org.hyperic.hq.auth.shared.SessionException, org.hyperic.hq.authz.shared.PermissionException, org.json.JSONException, javax.ejb.FinderException, java.rmi.RemoteException;

   /**
    * retrieve escalation object by escalation id.
    */
   public org.hyperic.hq.escalation.server.session.Escalation findEscalationById( int sessionID,java.lang.Integer id )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   public void addAction( int sessionID,org.hyperic.hq.escalation.server.session.Escalation e,org.hyperic.hq.events.ActionConfigInterface cfg,long waitTime )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   public void removeAction( int sessionID,java.lang.Integer escId,java.lang.Integer actId )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Retrieve a list of {@link EscalationState}s, representing the active escalations in the system.
    */
   public java.util.List getActiveEscalations( int sessionId,int maxEscalations )
      throws org.hyperic.hq.auth.shared.SessionException, java.rmi.RemoteException;

   /**
    * Gets the escalatable associated with the specified state
    */
   public org.hyperic.hq.escalation.server.session.Escalatable getEscalatable( int sessionId,org.hyperic.hq.escalation.server.session.EscalationState state )
      throws org.hyperic.hq.auth.shared.SessionException, java.rmi.RemoteException;

   /**
    * retrieve all escalation policy names as a Array of JSONObject. Escalation json finders begin with json* to be consistent with DAO finder convention
    */
   public org.json.JSONArray listAllEscalationName( int sessionID )
      throws org.json.JSONException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, java.rmi.RemoteException;

   /**
    * Create a new escalation. If alertDefId is non-null, the escalation will also be associated with the given alert definition.
    */
   public org.hyperic.hq.escalation.server.session.Escalation createEscalation( int sessionID,java.lang.String name,java.lang.String desc,boolean allowPause,long maxWaitTime,boolean notifyAll,boolean repeat,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType,java.lang.Integer alertDefId )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.DuplicateObjectException, java.rmi.RemoteException;

   /**
    * Update basic escalation properties
    */
   public void updateEscalation( int sessionID,org.hyperic.hq.escalation.server.session.Escalation escalation,java.lang.String name,java.lang.String desc,long maxWait,boolean pausable,boolean notifyAll,boolean repeat )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.common.DuplicateObjectException, java.rmi.RemoteException;

   public boolean acknowledgeAlert( int sessionID,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType,java.lang.Integer alertID,long pauseWaitTime,java.lang.String moreInfo )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.events.ActionExecuteException, java.rmi.RemoteException;

   /**
    * Fix a single alert. Method is "NotSupported" since all the alert fixes may take longer than the jboss transaction timeout. No need for a transaction in this context.
    */
   public void fixAlert( int sessionID,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType,java.lang.Integer alertID,java.lang.String moreInfo )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.events.ActionExecuteException, java.rmi.RemoteException;

   /**
    * Fix a batch of alerts. Method is "NotSupported" since all the alert fixes may take longer than the jboss transaction timeout. No need for a transaction in this context.
    */
   public void fixAlert( int sessionID,org.hyperic.hq.escalation.server.session.EscalationAlertType alertType,java.lang.Integer alertID,java.lang.String moreInfo,boolean fixAllPrevious )
      throws org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.events.ActionExecuteException, java.rmi.RemoteException;

   /**
    * Get the last fix if available
    */
   public java.lang.String getLastFix( int sessionID,java.lang.Integer defId )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, javax.ejb.FinderException, java.rmi.RemoteException;

   /**
    * Get a maintenance event by group id
    */
   public org.hyperic.hq.events.MaintenanceEvent getMaintenanceEvent( int sessionId,java.lang.Integer groupId )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, org.quartz.SchedulerException, java.rmi.RemoteException;

   /**
    * Schedule a maintenance event
    */
   public org.hyperic.hq.events.MaintenanceEvent scheduleMaintenanceEvent( int sessionId,org.hyperic.hq.events.MaintenanceEvent event )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, org.quartz.SchedulerException, java.rmi.RemoteException;

   /**
    * Schedule a maintenance event
    */
   public void unscheduleMaintenanceEvent( int sessionId,org.hyperic.hq.events.MaintenanceEvent event )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, org.hyperic.hq.authz.shared.PermissionException, org.quartz.SchedulerException, java.rmi.RemoteException;

   public void startup(  )
      throws java.rmi.RemoteException;

}
