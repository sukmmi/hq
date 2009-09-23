/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.bizapp.shared;

/**
 * Remote interface for EventLogBoss.
 */
public interface EventLogBoss
   extends javax.ejb.EJBObject
{
   /**
    * Find events based on event type and time range for a resource
    * @param eventType Event classname (ControlEvent.class.getName())
    * @return List of EventLogValue objects or an empty List if no events are found
    */
   public java.util.List getEvents( int sessionId,java.lang.String eventType,org.hyperic.hq.appdef.shared.AppdefEntityID id,long beginTime,long endTime )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Find events based on event type and time range for multiple resources
    * @param eventType Event classname (ControlEvent.class.getName())
    * @return List of EventLogValue objects or an empty List if no events are found
    */
   public java.util.List getEvents( int sessionId,java.lang.String eventType,org.hyperic.hq.appdef.shared.AppdefEntityID[] ids,long beginTime,long endTime )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Find events based on event type and time range for multiple resources
    * @param eventTypes Array of event class names. (ControlEvent.class.getName())
    * @return List of EventLogValue objects or an empty List if no events are found
    */
   public java.util.List getEvents( int sessionId,org.hyperic.hq.appdef.shared.AppdefEntityID aeid,java.lang.String[] eventTypes,long beginTime,long endTime )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Find events based on status and time range for multiple resources
    * @return List of EventLogValue objects or an empty List if no events are found
    */
   public java.util.List getEvents( int sessionId,org.hyperic.hq.appdef.shared.AppdefEntityID aeid,java.lang.String status,long beginTime,long endTime )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   /**
    * Get an array of boolean indicating if logs exist per interval, for an entity over a given time range.
    * @param aeid the entity ID
    * @return boolean array indicating if logs exist per interval.
    */
   public boolean[] logsExistPerInterval( int sessionId,org.hyperic.hq.appdef.shared.AppdefEntityID aeid,long beginTime,long endTime,int intervals )
      throws org.hyperic.hq.auth.shared.SessionNotFoundException, org.hyperic.hq.auth.shared.SessionTimeoutException, java.rmi.RemoteException;

   public void startup(  )
      throws java.rmi.RemoteException;

}
