/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.livedata.shared;

/**
 * Local home interface for LiveDataManager.
 */
public interface LiveDataManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/LiveDataManagerLocal";
   public static final String JNDI_NAME="LocalLiveDataManager";

   public org.hyperic.hq.livedata.shared.LiveDataManagerLocal create()
      throws javax.ejb.CreateException;

}
