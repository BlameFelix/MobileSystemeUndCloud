package com.wedevol.xmpp.server;

import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.service.impl.AcceleratorProcessor;
import com.wedevol.xmpp.service.impl.EchoProcessor;
import com.wedevol.xmpp.service.impl.GPSProcessor;
import com.wedevol.xmpp.service.impl.LightProcessor;
import com.wedevol.xmpp.service.impl.MessageProcessor;
import com.wedevol.xmpp.service.impl.RegisterProcessor;
import com.wedevol.xmpp.util.Util;

/**
 * Manages the creation of different payload processors based on the desired
 * action
 */

public class ProcessorFactory {

	public static PayloadProcessor getProcessor(String action) {
		if (action == null) {
			throw new IllegalStateException("ProcessorFactory: Action must not be null! Options: 'REGISTER', 'ECHO', 'MESSAGE', 'LIGHT', 'GPS', 'ACCELERATOR'");
		}
		if (action.equals(Util.BACKEND_ACTION_REGISTER)) {
			return new RegisterProcessor();
		} else if (action.equals(Util.BACKEND_ACTION_ECHO)) {
			return new EchoProcessor();
		} else if (action.equals(Util.BACKEND_ACTION_MESSAGE)) {
			return new MessageProcessor();
		} else if (action.equals(Util.BACKEND_ACTION_GPS)) {
			return new GPSProcessor();
		} else if (action.equals(Util.BACKEND_ACTION_ACCELERATOR)) {
			return new AcceleratorProcessor();
		} else if (action.equals(Util.BACKEND_ACTION_LIGHT)) {
			return new LightProcessor();
		}
		
		throw new IllegalStateException("ProcessorFactory: Unknown action: " + action + ". Options: 'REGISTER', 'ECHO', 'MESSAGE', 'LIGHT', 'GPS', 'ACCELERATOR'");
	}
}