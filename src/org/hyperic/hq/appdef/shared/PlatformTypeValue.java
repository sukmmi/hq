/*
 * xdoclet generated code.
 * legacy DTO pattern (targeted to be replaced with hibernate pojo).
 */
package org.hyperic.hq.appdef.shared;

/**
 * Value object for PlatformType.
 *
 */
public class PlatformTypeValue
   extends org.hyperic.hq.appdef.shared.AppdefResourceTypeValue
   implements java.io.Serializable
{
   private java.lang.String sortName;
   private boolean sortNameHasBeenSet = false;
   private java.lang.String name;
   private boolean nameHasBeenSet = false;
   private java.lang.String description;
   private boolean descriptionHasBeenSet = false;
   private java.lang.String plugin;
   private boolean pluginHasBeenSet = false;
   private java.lang.Integer id;
   private boolean idHasBeenSet = false;
   private java.lang.Long mTime;
   private boolean mTimeHasBeenSet = false;
   private java.lang.Long cTime;
   private boolean cTimeHasBeenSet = false;
   private java.util.Collection ServerTypeValues = new java.util.HashSet();

   public PlatformTypeValue()
   {
   }

   public PlatformTypeValue( java.lang.String sortName,java.lang.String name,java.lang.String description,java.lang.String plugin,java.lang.Integer id,java.lang.Long mTime,java.lang.Long cTime )
   {
	  this.sortName = sortName;
	  sortNameHasBeenSet = true;
	  this.name = name;
	  nameHasBeenSet = true;
	  this.description = description;
	  descriptionHasBeenSet = true;
	  this.plugin = plugin;
	  pluginHasBeenSet = true;
	  this.id = id;
	  idHasBeenSet = true;
	  this.mTime = mTime;
	  mTimeHasBeenSet = true;
	  this.cTime = cTime;
	  cTimeHasBeenSet = true;
   }

   //TODO Cloneable is better than this !
   public PlatformTypeValue( PlatformTypeValue otherValue )
   {
	  this.sortName = otherValue.sortName;
	  sortNameHasBeenSet = true;
	  this.name = otherValue.name;
	  nameHasBeenSet = true;
	  this.description = otherValue.description;
	  descriptionHasBeenSet = true;
	  this.plugin = otherValue.plugin;
	  pluginHasBeenSet = true;
	  this.id = otherValue.id;
	  idHasBeenSet = true;
	  this.mTime = otherValue.mTime;
	  mTimeHasBeenSet = true;
	  this.cTime = otherValue.cTime;
	  cTimeHasBeenSet = true;
	// TODO Clone is better no ?
	  this.ServerTypeValues = otherValue.ServerTypeValues;
   }

   public java.lang.String getSortName()
   {
	  return this.sortName;
   }

   public void setSortName( java.lang.String sortName )
   {
	  this.sortName = sortName;
	  sortNameHasBeenSet = true;

   }

   public boolean sortNameHasBeenSet(){
	  return sortNameHasBeenSet;
   }
   public java.lang.String getName()
   {
	  return this.name;
   }

   public void setName( java.lang.String name )
   {
	  this.name = name;
	  nameHasBeenSet = true;

   }

   public boolean nameHasBeenSet(){
	  return nameHasBeenSet;
   }
   public java.lang.String getDescription()
   {
	  return this.description;
   }

   public void setDescription( java.lang.String description )
   {
	  this.description = description;
	  descriptionHasBeenSet = true;

   }

   public boolean descriptionHasBeenSet(){
	  return descriptionHasBeenSet;
   }
   public java.lang.String getPlugin()
   {
	  return this.plugin;
   }

   public void setPlugin( java.lang.String plugin )
   {
	  this.plugin = plugin;
	  pluginHasBeenSet = true;

   }

   public boolean pluginHasBeenSet(){
	  return pluginHasBeenSet;
   }
   public java.lang.Integer getId()
   {
	  return this.id;
   }

   public void setId( java.lang.Integer id )
   {
	  this.id = id;
	  idHasBeenSet = true;
   }

   public boolean idHasBeenSet(){
	  return idHasBeenSet;
   }
   public java.lang.Long getMTime()
   {
	  return this.mTime;
   }

   public void setMTime( java.lang.Long mTime )
   {
	  this.mTime = mTime;
	  mTimeHasBeenSet = true;

   }

   public boolean mTimeHasBeenSet(){
	  return mTimeHasBeenSet;
   }
   public java.lang.Long getCTime()
   {
	  return this.cTime;
   }

   public void setCTime( java.lang.Long cTime )
   {
	  this.cTime = cTime;
	  cTimeHasBeenSet = true;

   }

   public boolean cTimeHasBeenSet(){
	  return cTimeHasBeenSet;
   }

   protected java.util.Collection addedServerTypeValues = new java.util.HashSet();
   protected java.util.Collection removedServerTypeValues = new java.util.HashSet();
   protected java.util.Collection updatedServerTypeValues = new java.util.HashSet();

   public java.util.Collection getAddedServerTypeValues() { return addedServerTypeValues; }
   public java.util.Collection getRemovedServerTypeValues() { return removedServerTypeValues; }
   public java.util.Collection getUpdatedServerTypeValues() { return updatedServerTypeValues; }

   public org.hyperic.hq.appdef.shared.ServerTypeValue[] getServerTypeValues()
   {
	  return (org.hyperic.hq.appdef.shared.ServerTypeValue[])this.ServerTypeValues.toArray(new org.hyperic.hq.appdef.shared.ServerTypeValue[ServerTypeValues.size()]);
   }

   public void addServerTypeValue(org.hyperic.hq.appdef.shared.ServerTypeValue added)
   {
	  this.ServerTypeValues.add(added);
	  if ( ! this.addedServerTypeValues.contains(added))
		 this.addedServerTypeValues.add(added);
   }

   public void removeServerTypeValue(org.hyperic.hq.appdef.shared.ServerTypeValue removed)
   {
	  this.ServerTypeValues.remove(removed);
	  this.removedServerTypeValues.add(removed);
	  if (this.addedServerTypeValues.contains(removed))
		 this.addedServerTypeValues.remove(removed);
	  if (this.updatedServerTypeValues.contains(removed))
		 this.updatedServerTypeValues.remove(removed);
   }

   public void removeAllServerTypeValues()
   {
        // DOH. Clear the collection - javier 2/24/03
        this.ServerTypeValues.clear();
   }

   public void updateServerTypeValue(org.hyperic.hq.appdef.shared.ServerTypeValue updated)
   {
	  if ( ! this.updatedServerTypeValues.contains(updated))
		 this.updatedServerTypeValues.add(updated);
   }

   public void cleanServerTypeValue(){
	  this.addedServerTypeValues = new java.util.HashSet();
	  this.removedServerTypeValues = new java.util.HashSet();
	  this.updatedServerTypeValues = new java.util.HashSet();
   }

   public void copyServerTypeValuesFrom(org.hyperic.hq.appdef.shared.PlatformTypeValue from)
   {
	  // TODO Clone the List ????
	  this.ServerTypeValues = from.ServerTypeValues;
   }

   public String toString()
   {
	  StringBuffer str = new StringBuffer("{");

	  str.append("sortName=" + getSortName() + " " + "name=" + getName() + " " + "description=" + getDescription() + " " + "plugin=" + getPlugin() + " " + "id=" + getId() + " " + "mTime=" + getMTime() + " " + "cTime=" + getCTime());
	  str.append('}');

	  return(str.toString());
   }

   /**
	* A Value object have an identity if its attributes making its Primary Key
	* has all been set.  One object without identity is never equal to any other
	* object.
	*
	* @return true if this instance have an identity.
	*/
   protected boolean hasIdentity()
   {
	  boolean ret = true;
	  ret = ret && idHasBeenSet;
	  return ret;
   }

   public boolean equals(Object other)
   {
	  if ( ! hasIdentity() ) return false;
	  if (other instanceof PlatformTypeValue)
	  {
		 PlatformTypeValue that = (PlatformTypeValue) other;
		 if ( ! that.hasIdentity() ) return false;
		 boolean lEquals = true;
		 if( this.id == null )
		 {
			lEquals = lEquals && ( that.id == null );
		 }
		 else
		 {
			lEquals = lEquals && this.id.equals( that.id );
		 }

		 lEquals = lEquals && isIdentical(that);

		 return lEquals;
	  }
	  else
	  {
		 return false;
	  }
   }

   public boolean isIdentical(Object other)
   {
	  if (other instanceof PlatformTypeValue)
	  {
		 PlatformTypeValue that = (PlatformTypeValue) other;
		 boolean lEquals = true;
		 if( this.sortName == null )
		 {
			lEquals = lEquals && ( that.sortName == null );
		 }
		 else
		 {
			lEquals = lEquals && this.sortName.equals( that.sortName );
		 }
		 if( this.name == null )
		 {
			lEquals = lEquals && ( that.name == null );
		 }
		 else
		 {
			lEquals = lEquals && this.name.equals( that.name );
		 }
		 if( this.description == null )
		 {
			lEquals = lEquals && ( that.description == null );
		 }
		 else
		 {
			lEquals = lEquals && this.description.equals( that.description );
		 }
		 if( this.plugin == null )
		 {
			lEquals = lEquals && ( that.plugin == null );
		 }
		 else
		 {
			lEquals = lEquals && this.plugin.equals( that.plugin );
		 }
		 if( this.mTime == null )
		 {
			lEquals = lEquals && ( that.mTime == null );
		 }
		 else
		 {
			lEquals = lEquals && this.mTime.equals( that.mTime );
		 }
		 if( this.cTime == null )
		 {
			lEquals = lEquals && ( that.cTime == null );
		 }
		 else
		 {
			lEquals = lEquals && this.cTime.equals( that.cTime );
		 }
		 if( this.getServerTypeValues() == null )
		 {
			lEquals = lEquals && ( that.getServerTypeValues() == null );
		 }
		 else
		 {
            // XXX Covalent Custom - dont compare the arrays, as order is not significant. ever.    
            // - javier 7/16/03
            java.util.Collection cmr1 = java.util.Arrays.asList(this.getServerTypeValues());
            java.util.Collection cmr2 = java.util.Arrays.asList(that.getServerTypeValues());
			// lEquals = lEquals && java.util.Arrays.equals(this.getServerTypeValues() , that.getServerTypeValues()) ;
            lEquals = lEquals && cmr1.containsAll(cmr2);
		 }

		 return lEquals;
	  }
	  else
	  {
		 return false;
	  }
   }

   public int hashCode(){
	  int result = 17;
      result = 37*result + ((this.sortName != null) ? this.sortName.hashCode() : 0);

      result = 37*result + ((this.name != null) ? this.name.hashCode() : 0);

      result = 37*result + ((this.description != null) ? this.description.hashCode() : 0);

      result = 37*result + ((this.plugin != null) ? this.plugin.hashCode() : 0);

      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.mTime != null) ? this.mTime.hashCode() : 0);

      result = 37*result + ((this.cTime != null) ? this.cTime.hashCode() : 0);

	  result = 37*result + ((this.getServerTypeValues() != null) ? this.getServerTypeValues().hashCode() : 0);
	  return result;
   }

}
