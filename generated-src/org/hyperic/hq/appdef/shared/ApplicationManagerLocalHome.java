/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.appdef.shared;

/**
 * Local home interface for ApplicationManager.
 */
public interface ApplicationManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ApplicationManagerLocal";
   public static final String JNDI_NAME="LocalApplicationManager";

   public org.hyperic.hq.appdef.shared.ApplicationManagerLocal create()
      throws javax.ejb.CreateException;

}
