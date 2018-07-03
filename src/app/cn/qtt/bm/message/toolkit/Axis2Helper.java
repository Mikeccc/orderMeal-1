/*************************************************************
 * File: Axis2Helper.java
 * Copyright (c) 2012
 * Author: Shaobin.Software@gmail.com Shaobin_Software@163.com 
 * Date: Jan 28, 2013
 * Description: 
 ************************************************************/
package app.cn.qtt.bm.message.toolkit;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 */
public class Axis2Helper {

	private static final Log LOG = LogFactory.getLog(Axis2Helper.class);

	public static void cleanup(ServiceClient serviceClient) {
		cleanup(serviceClient, LOG);
	}

	public static void cleanup(ServiceClient serviceClient, Log log) {
		if (serviceClient != null) {
			try {
				serviceClient.cleanup();
			} catch (AxisFault e) {
				logWarn(e, log);
			}
		}
	}

	public static void cleanup(Stub stub) {
		cleanup(stub, LOG);
	}

	public static void cleanup(Stub stub, Log log) {
		if (stub != null) {
			try {
				stub.cleanup();
			} catch (AxisFault e) {
				logWarn(e, log);
			}
		}
	}

	public static void cleanupTransport(ServiceClient serviceClient) {
		cleanupTransport(serviceClient, LOG);
	}

	public static void cleanupTransport(ServiceClient serviceClient, Log log) {
		if (serviceClient != null) {
			try {
				serviceClient.cleanupTransport();
			} catch (AxisFault e) {
				logWarn(e, log);
			}
		}
	}

	private static void logWarn(Throwable cause, Log log) {
		final Log logger = log == null ? LOG : log;
		logger.warn(cause.getMessage(), cause);
	}

}
