/*
 * Generated by XDoclet - Do not edit!
 */
package org.hyperic.hq.common.shared;

/**
 * Local interface for CalendarManager.
 */
public interface CalendarManagerLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Create a calendar with the specified name. This name is only used to distinguish between calendars and must be unique, however it will be changed in the future to be used in the UI.
    */
   public org.hyperic.hq.common.server.session.Calendar createCalendar( java.lang.String name ) ;

   /**
    * Find all calendars in the system
    * @return {@link Calendar}s
    */
   public java.util.Collection findAll(  ) ;

   /**
    * Delete a calendar and all of its entries
    */
   public void remove( org.hyperic.hq.common.server.session.Calendar c ) ;

   /**
    * Add a weekly entry to a calendar.
    * @param weekDay Day of the week (0 to 6)
    * @param startTime # of minutes since midnight
    * @param endTime # of minutes since midnight
    */
   public org.hyperic.hq.common.server.session.WeekEntry addWeekEntry( org.hyperic.hq.common.server.session.Calendar c,int weekDay,int startTime,int endTime ) ;

   /**
    * Remove a calendar entry from a calendar
    */
   public void removeEntry( org.hyperic.hq.common.server.session.Calendar c,org.hyperic.hq.common.server.session.CalendarEntry ent ) ;

   /**
    * Remove calendar entries from a calendar
    */
   public void removeEntries( org.hyperic.hq.common.server.session.Calendar c ) ;

   public org.hyperic.hq.common.server.session.Calendar findCalendarById( int id ) ;

   public org.hyperic.hq.common.server.session.CalendarEntry findEntryById( int id ) ;

}
