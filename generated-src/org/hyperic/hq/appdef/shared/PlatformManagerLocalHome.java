/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.appdef.shared;

/**
 * Local home interface for PlatformManager.
 */
public interface PlatformManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PlatformManagerLocal";
   public static final String JNDI_NAME="LocalPlatformManager";

   public org.hyperic.hq.appdef.shared.PlatformManagerLocal create()
      throws javax.ejb.CreateException;

}
