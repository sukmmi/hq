/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.appdef.shared;

/**
 * Local home interface for ConfigManager.
 */
public interface ConfigManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ConfigManagerLocal";
   public static final String JNDI_NAME="LocalConfigManager";

   public org.hyperic.hq.appdef.shared.ConfigManagerLocal create()
      throws javax.ejb.CreateException;

}
