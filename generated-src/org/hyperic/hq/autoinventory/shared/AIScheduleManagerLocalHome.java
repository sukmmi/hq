/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.autoinventory.shared;

/**
 * Local home interface for AIScheduleManager.
 */
public interface AIScheduleManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/AIScheduleManagerLocal";
   public static final String JNDI_NAME="LocalAIScheduleManager";

   public org.hyperic.hq.autoinventory.shared.AIScheduleManagerLocal create()
      throws javax.ejb.CreateException;

}
