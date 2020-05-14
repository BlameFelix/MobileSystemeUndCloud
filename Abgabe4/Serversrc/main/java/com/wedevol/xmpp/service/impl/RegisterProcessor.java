package com.wedevol.xmpp.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wedevol.xmpp.bean.CcsInMessage;
import com.wedevol.xmpp.server.CcsClient;
import com.wedevol.xmpp.service.PayloadProcessor;

import tracking.Accelerator;

/**
 * Handles a user registration request
 */
public class RegisterProcessor implements PayloadProcessor {
	private final static Logger LOGGER = Logger.getLogger(LightProcessor.class.getName());
	private CcsClient client = CcsClient.getInstance();
	
	@Override
	public void handleMessage(CcsInMessage msg) {
		// TODO: handle the user registration. Keep in mind that a user name can
		// have more reg IDs associated. The messages IDs should be unique.
		LOGGER.log(Level.INFO, "RegisterProcessor triggered");
		String vorname = msg.getDataPayload().get("vorname");
		String nachname = msg.getDataPayload().get("nachname");
		String email = msg.getDataPayload().get("email");
		String googleID = msg.getDataPayload().get("googleID");
		String clientToken = msg.getDataPayload().get("clientToken");
		if(vorname != null && nachname != null && email != null && googleID != null && clientToken != null) {
			LOGGER.log(Level.INFO, "GPSProcessor triggered. vorname: " + vorname + ", nachname: " + nachname + ", email: " + email + ", googleID" + googleID + ", clientToken: " + clientToken);
			client.person.setVorname(vorname);
			client.person.setNachname(nachname);
			client.person.setEmail(email);
			client.person.setGoogleID(googleID);
			client.person.setClToken(clientToken);
		} else {
			LOGGER.log(Level.INFO, "Values null");
		}
	}

}