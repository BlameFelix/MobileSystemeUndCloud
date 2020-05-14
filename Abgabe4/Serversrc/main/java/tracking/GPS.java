package tracking;

public class GPS {
	private long timestamp;
	private double lat;
	private double lon;
	
	public GPS(long timestamp, double lat, double lon) {
		this.timestamp = timestamp;
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "GPS Timestamp = " + timestamp + ", lat = " + lat + ", lon = " + lon + "\n";
	}
	


}
