package com.wedevol.xmpp.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.service.PayloadProcessor;

import tracking.Accelerator;

public class AcceleratorProcessor implements PayloadProcessor {
	private final static Logger LOGGER = Logger.getLogger(AcceleratorProcessor.class.getName());
	private CcsClient client = CcsClient.getInstance();
	
	@Override
	public void handleMessage(CcsInMessage msg) {
		LOGGER.log(Level.INFO, "AcceleratorProcessor triggered");
		String x = msg.getDataPayload().get("x");
		String y = msg.getDataPayload().get("y");
		String z = msg.getDataPayload().get("z");
		String timestamp = msg.getDataPayload().get("timestamp");
		if(x != null && y != null && z != null && timestamp != null) {
			LOGGER.log(Level.INFO, "GPSProcessor triggered. Timestamp: " + timestamp + ", x: " + x + ", y: " + y + ", z: " + z);
			client.person.addAccelerator(new Accelerator(Long.valueOf(timestamp), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z)));
		} else {
			LOGGER.log(Level.INFO, "Values null");
		}
		
	}

}
