/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.product.shared;

/**
 * Local home interface for ProductManager.
 */
public interface ProductManagerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ProductManagerLocal";
   public static final String JNDI_NAME="LocalProductManager";

   public org.hyperic.hq.product.shared.ProductManagerLocal create()
      throws javax.ejb.CreateException;

}
