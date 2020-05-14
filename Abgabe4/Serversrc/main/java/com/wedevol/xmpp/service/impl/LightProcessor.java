package com.wedevol.xmpp.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.service.PayloadProcessor;

import tracking.Light;

public class LightProcessor implements PayloadProcessor {
	private final static Logger LOGGER = Logger.getLogger(LightProcessor.class.getName());
	private CcsClient client = CcsClient.getInstance();
	
	@Override
	public void handleMessage(CcsInMessage msg) {
		LOGGER.log(Level.INFO, "LightProcessor triggered");
		String lux = msg.getDataPayload().get("lux");
		String timestamp = msg.getDataPayload().get("timestamp");
		if(lux != null && timestamp != null) {
			LOGGER.log(Level.INFO, "LightProcessor triggered. Timestamp: " + timestamp + ", lux: " + lux);
			client.person.addLight(new Light(Long.valueOf(timestamp), Float.valueOf(lux)));
		} else {
			LOGGER.log(Level.INFO, "Values null");
		}
		
	}

}
