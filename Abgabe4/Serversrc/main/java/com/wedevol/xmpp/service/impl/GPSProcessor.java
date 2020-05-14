package com.wedevol.xmpp.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.bean.CcsOutMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.server.MessageHelper;
import com.wedevol.xmpp.service.PayloadProcessor;
import com.wedevol.xmpp.util.Util;

import tracking.GPS;

public class GPSProcessor implements PayloadProcessor {
	private final static Logger LOGGER = Logger.getLogger(GPSProcessor.class.getName());
	private CcsClient client = CcsClient.getInstance();
	
	@Override
	public void handleMessage(CcsInMessage msg) {
		LOGGER.log(Level.INFO, "GPSProcessor triggered");
		String latitude = msg.getDataPayload().get("latitude");
		String longitude = msg.getDataPayload().get("longitude");
		String timestamp = msg.getDataPayload().get("timestamp");
		if(latitude != null && timestamp != null && longitude != null) {
			LOGGER.log(Level.INFO, "GPSProcessor triggered. Timestamp: " + timestamp + ", longitude: " + longitude + ", latitude: " + latitude);
			client.person.addGPS(new GPS(Long.valueOf(timestamp), Double.valueOf(latitude), Double.valueOf(longitude)));
		} else {
			LOGGER.log(Level.INFO, "Values null");
		}
	}
}
