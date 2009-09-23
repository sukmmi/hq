/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.authz.shared;

/**
 * Local interface for ResourceGroupManager_test.
 */
public interface ResourceGroupManager_testLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Test creating two groups of the same name.
    */
   public void testDuplicateNameCreate(  ) throws java.lang.Exception;

   /**
    * Test renaming a group to a name which already exists.
    */
   public void testUpdate(  ) throws java.lang.Exception;

   /**
    * Test setting criteria list for a group. This test will set the criteria on the resource group more than once to ensure that the criteria list can be overwritten correctly and persisted appropriately to the database.
    */
   public void testResourceGroupSetCriteria(  ) throws java.lang.Exception;

}
