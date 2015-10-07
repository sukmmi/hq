/*
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 * 
 * Copyright (C) [2004, 2005, 2006], Hyperic, Inc.
 * This file is part of HQ.
 * 
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package org.hyperic.hq.ui.action.resource.common.monitor.alerts.config;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.hyperic.hq.appdef.shared.AppdefEntityID;
import org.hyperic.hq.auth.shared.SessionNotFoundException;
import org.hyperic.hq.auth.shared.SessionTimeoutException;
import org.hyperic.hq.authz.shared.PermissionException;
import org.hyperic.hq.bizapp.shared.EventsBoss;
import org.hyperic.hq.events.shared.AlertDefinitionValue;
import org.hyperic.hq.ui.Constants;
import org.hyperic.hq.ui.action.BaseActionNG;
import org.hyperic.hq.ui.exception.ParameterNotFoundException;
import org.hyperic.hq.ui.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An Action that retrieves data from the BizApp to facilitate display of the
 * form to add other emailAddresses( non-CAM) to an alert Definition
 */
@Component("addOthersFormPrepareActionNG")
public class AddOthersFormPrepareActionNG extends BaseActionNG implements
		ViewPreparer {

	protected final Log log = LogFactory
			.getLog(AddOthersFormPrepareActionNG.class.getName());

	@Autowired
	private EventsBoss eventsBoss;

	/**
	 * Retrieve this data and store it in the specified request parameters:
	 * 
	 * <ul>
	 * <li><code>OwnedRoleValue</code> object identified by
	 * <code>Constants.ROLE_PARAM</code> request parameter in in
	 * <code>Constants.ROLE_ATTR</code></li>
	 * <li><code>List</code> of available <code>AuthzSubjectValue</code> objects
	 * (those not already associated with the role) in
	 * </ul>
	 */
	public void execute(TilesRequestContext tilesContext,
			AttributeContext attributeContext) {
		request = getServletRequest();
		AddOthersFormNG addForm = new AddOthersFormNG();
		if (request.getParameter("ad") != null) {
			addForm.setAd(Integer.parseInt(request.getParameter("ad")));
		}
		Integer alertDefId = addForm.getAd();
		if (alertDefId == null) {
			throw new ParameterNotFoundException(
					"alert definition id not found");
		}

		try {
			AppdefEntityID appTypeId = RequestUtils.getEntityTypeId(request);
			addForm.setAetid(appTypeId.getAppdefKey());
		} catch (ParameterNotFoundException e) {
			AppdefEntityID aeid = RequestUtils.getEntityId(request);
			addForm.setType(new Integer(aeid.getType()));
			addForm.setRid(aeid.getId());
		}

		Integer sessionId;
		try {
			sessionId = RequestUtils.getSessionId(request);

			AlertDefinitionValue alertDef = (AlertDefinitionValue) request
					.getAttribute(Constants.ALERT_DEFS_ATTR);
			if (alertDef == null) {
				alertDef = eventsBoss.getAlertDefinition(sessionId.intValue(),
						alertDefId);
			}
			addForm.setAd(alertDef.getId());

			request.setAttribute(Constants.ALERT_DEFINITION_ATTR, alertDef);
		} catch (ServletException e) {
			log.error(e);
		} catch (SessionNotFoundException e) {
			log.error(e);
		} catch (SessionTimeoutException e) {
			log.error(e);
		} catch (PermissionException e) {
			log.error(e);
		}
	}
}
