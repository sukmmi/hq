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

package org.hyperic.hq.ui.action.resource.common.monitor.visibility;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.hq.appdef.shared.AppdefEntityID;
import org.hyperic.hq.appdef.shared.AppdefEntityTypeID;
import org.hyperic.hq.bizapp.shared.MeasurementBoss;
import org.hyperic.hq.ui.Constants;
import org.hyperic.hq.ui.WebUser;
import org.hyperic.hq.ui.action.BaseActionNG;
import org.hyperic.hq.ui.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * A <code>BaseAction</code> that handles metrics display form submissions.
 */
@Component("metricsDisplayActionNG")
public class MetricsDisplayActionNG extends BaseActionNG implements
		ModelDriven<MetricsDisplayFormNG> {

	private final Log log = LogFactory.getLog(MetricsDisplayActionNG.class
			.getName());

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private MeasurementBoss measurementBoss;

	private MetricsDisplayFormNG displayForm = new MetricsDisplayFormNG();

	/**
	 * Modify the metrics summary display as specified in the given
	 * <code>MetricsDisplayForm</code>.
	 */
	public String execute() throws Exception {

		AppdefEntityID entityId = displayForm.getEntityId();
		Map<String, Object> forwardParams = displayForm.getForwardParams();

		WebUser user = RequestUtils.getWebUser(request);
		Integer sessionId = user.getSessionId();

		if (displayForm.isCompareClicked()) {
			return Constants.COMPARE_URL;
		} else if (displayForm.isChartClicked()) {
			forwardParams.put(Constants.METRIC_PARAM, displayForm.getM());
			return Constants.CHART_URL;
		} else if (displayForm.isThresholdClicked()) {
			Integer threshold = displayForm.getT();
			user.setPreference(WebUser.PREF_METRIC_THRESHOLD, threshold);
			log.trace("saving threshold pref [" + threshold + "]");
			LogFactory.getLog("user.preferences").trace(
					"Invoking setUserPrefs" + " in MetricsDisplayAction "
							+ " for " + user.getId() + " at "
							+ System.currentTimeMillis() + " user.prefs = "
							+ user.getPreferences());

			authzBoss.setUserPrefs(sessionId, user.getId(),
					user.getPreferences());

			return Constants.SUCCESS_URL;
		} else if (displayForm.isOkClicked()) {
			Integer[] m = displayForm.getM();
			long interval = displayForm.getIntervalTime();

			// Don't make any back-end call if user has not selected any metrics
			if (m != null && m.length > 0) {

				if (displayForm.getCtype() == null || entityId.isGroup())
					measurementBoss.updateMeasurements(sessionId.intValue(),
							entityId, m, interval);
				else {
					AppdefEntityTypeID ctid = new AppdefEntityTypeID(
							displayForm.getCtype());

					measurementBoss.updateAGMeasurements(sessionId.intValue(),
							entityId, ctid, m, interval);
				}

			}
			addActionMessage("resource.common.monitor.visibility.config.ConfigMetrics."
					+ "Confirmation");

			return Constants.SUCCESS_URL;
		} else if (displayForm.isRemoveClicked()) {
			Integer[] m = displayForm.getM();
			// Don't make any back-end call if user has not selected any metrics
			if (m != null && m.length > 0) {

				if (displayForm.getCtype() == null || entityId.isGroup())
					measurementBoss.disableMeasurements(sessionId.intValue(),
							entityId, m);
				else {
					AppdefEntityTypeID ctid = new AppdefEntityTypeID(
							displayForm.getCtype());
					measurementBoss.disableAGMeasurements(sessionId.intValue(),
							entityId, ctid, m);
				}

				addActionMessage("resource.common.monitor.visibility.config.RemoveMetrics."
						+ "Confirmation");
			}

			return Constants.SUCCESS_URL;
		}

		MetricsControlActionNG metricsControlActionNG = (MetricsControlActionNG) appContext
				.getBean("metricsControlActionNG");
		return metricsControlActionNG.execute(getServletRequest());
	}

	/*
	 * // ---------------------------------------------------- Private Methods
	 * 
	 * private ActionForward returnCompare(HttpServletRequest request,
	 * ActionMapping mapping, Map<String, Object> params) throws Exception { //
	 * set return path String returnPath = ActionUtils.findReturnPath(mapping,
	 * params); SessionUtils.setReturnPath(request.getSession(), returnPath);
	 * 
	 * return constructForward(request, mapping, Constants.COMPARE_URL, params,
	 * NO_RETURN_PATH); }
	 * 
	 * private ActionForward returnChart(HttpServletRequest request,
	 * ActionMapping mapping, Map<String, Object> params) throws Exception { //
	 * set return path String returnPath = ActionUtils.findReturnPath(mapping,
	 * params); SessionUtils.setReturnPath(request.getSession(), returnPath);
	 * 
	 * return constructForward(request, mapping, Constants.CHART_URL, params,
	 * NO_RETURN_PATH); }
	 */

	public MetricsDisplayFormNG getModel() {

		return displayForm;
	}
	
	public Integer[] getM(){
		return displayForm.getM();
	}
	
	
	
}