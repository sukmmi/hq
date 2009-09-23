/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.product.shared;

/**
 * Local interface for ProductManager.
 */
public interface ProductManagerLocal
   extends javax.ejb.EJBLocalObject
{

   public boolean isReady(  ) ;

   public org.hyperic.hq.product.TypeInfo getTypeInfo( org.hyperic.hq.appdef.shared.AppdefEntityValue value ) throws org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException;

   public org.hyperic.hq.product.PluginManager getPluginManager( java.lang.String type ) throws org.hyperic.hq.product.PluginException;

   public java.lang.String getMonitoringHelp( org.hyperic.hq.appdef.shared.AppdefEntityValue entityVal,java.util.Map props ) throws org.hyperic.hq.product.PluginNotFoundException, org.hyperic.hq.authz.shared.PermissionException, org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException;

   public org.hyperic.util.config.ConfigSchema getConfigSchema( java.lang.String type,java.lang.String name,org.hyperic.hq.appdef.shared.AppdefEntityValue entityVal,org.hyperic.util.config.ConfigResponse baseResponse ) throws org.hyperic.hq.product.PluginException, org.hyperic.hq.appdef.shared.AppdefEntityNotFoundException, org.hyperic.hq.authz.shared.PermissionException;

   public void deploymentNotify( java.lang.String pluginName ) throws org.hyperic.hq.product.PluginNotFoundException, javax.ejb.FinderException, javax.ejb.CreateException, javax.ejb.RemoveException, org.hyperic.hq.common.VetoException;

   public void updateDynamicServiceTypePlugin( java.lang.String pluginName,java.util.Set serviceTypes ) throws org.hyperic.hq.product.PluginNotFoundException, javax.ejb.FinderException, javax.ejb.RemoveException, javax.ejb.CreateException, org.hyperic.hq.common.VetoException;

}
