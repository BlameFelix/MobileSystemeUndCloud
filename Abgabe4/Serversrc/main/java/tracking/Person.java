package tracking;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wedevol.xmpp.service.impl.LightProcessor;

public class Person {
	private final static Logger LOGGER = Logger.getLogger(LightProcessor.class.getName());
	
	private String vorname;
	private String nachname;
	private String email;
	private String googleID;
	private String clToken;
	
	private ArrayList<Light> light = new ArrayList<Light>();
	private ArrayList<GPS> gps = new ArrayList<GPS>();
	private ArrayList<Accelerator> acc = new ArrayList<Accelerator>();
	
	
	@Override
	public String toString() {
		return "\n\nPerson:\nVorname = " + vorname + "\nNachname = " + nachname + "\nemail = " + email + "\ngoogleID = " + googleID
				+ "\nclToken = " + clToken + "\n\nlight:\n" + light + "\n\nngps:\n" + gps + "\n\nacc:\n" + acc;
	}

	public void addLight(Light l) {
		light.add(l);
		LOGGER.log(Level.INFO, this.toString());
	}
	
	public void addGPS(GPS g) {
		gps.add(g);
		LOGGER.log(Level.INFO, this.toString());
	}
	
	public void addAccelerator(Accelerator a) {
		acc.add(a);
		LOGGER.log(Level.INFO, this.toString());
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
		LOGGER.log(Level.INFO, this.toString());
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
		LOGGER.log(Level.INFO, this.toString());
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		LOGGER.log(Level.INFO, this.toString());
	}
	public String getGoogleID() {
		return googleID;
	}
	public void setGoogleID(String googleID) {
		this.googleID = googleID;
		LOGGER.log(Level.INFO, this.toString());
	}
	public String getClToken() {
		return clToken;
	}
	public void setClToken(String clToken) {
		this.clToken = clToken;
		LOGGER.log(Level.INFO, this.toString());
	}
	
}
