<%@ page language="java" %>
<%@ page errorPage="/common/Error2.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%--
  NOTE: This copyright does *not* cover user programs that use HQ
  program services by normal system calls through the application
  program interfaces provided as part of the Hyperic Plug-in Development
  Kit or the Hyperic Client Development Kit - this is merely considered
  normal use of the program, and does *not* fall under the heading of
  "derived work".
  
  Copyright (C) [2004, 2005, 2006], Hyperic, Inc.
  This file is part of HQ.
  
  HQ is free software; you can redistribute it and/or modify
  it under the terms version 2 of the GNU General Public License as
  published by the Free Software Foundation. This program is distributed
  in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
  even the implied warranty of MERCHANTABILITY or FITNESS FOR A
  PARTICULAR PURPOSE. See the GNU General Public License for more
  details.
  
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
  USA.
 --%>
<tiles:importAttribute name="formAction" ignore="true"/>

<c:if test="${empty formAction}">
	<c:set var="formAction" value="createUser"/>
</c:if>

<!-- FORM -->
<s:form action="%{#attr.formAction}" >

<!--  HEADER TITLE -->
<tiles:insertDefinition name=".header.tab">  
	<tiles:putAttribute name="tabKey" value="admin.user.GeneralProperties"/>  
</tiles:insertDefinition>

<!--  /  -->

<!--  UserForm -->
<tiles:insertTemplate template="/admin/user/UserFormNG.jsp"/>
<!--  /UserForm -->

<!--  empty -->
<tiles:insertDefinition name=".toolbar.empty"/>  
<!--  /empty -->

<!--  ok -->
<tiles:insertDefinition name=".admin.user.new.buttons">
	<tiles:putAttribute name="cancelAction"  value="cancelUser" />
	<tiles:putAttribute name="resetAction"  value="resetUser" />
</tiles:insertDefinition>
<!--  /ok -->

</s:form>
<!-- /  -->
