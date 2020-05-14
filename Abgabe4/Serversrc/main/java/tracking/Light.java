package tracking;

public class Light {
	private long timestamp;
	private float lx;
	
	@Override
	public String toString() {
		return "Light Timestamp = " + timestamp + ", lx = " + lx + "\n";
	}

	public Light(long timestamp, float lx) {
		super();
		this.timestamp = timestamp;
		this.lx = lx;
	}

}
