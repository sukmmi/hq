/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.escalation.shared;

/**
 * Local home interface for EscalationManager.
 */
public interface EscalationManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/EscalationManagerLocal";
   public static final String JNDI_NAME="LocalEscalationManager";

   public org.hyperic.hq.escalation.shared.EscalationManagerLocal create()
      throws javax.ejb.CreateException;

}
