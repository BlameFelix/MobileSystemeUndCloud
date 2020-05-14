package tracking;

public class Accelerator {
	private long timestamp;
	private double x;
	private double y;
	private double z;
	
	public Accelerator(long timestamp, double x, double y, double z) {
		this.timestamp = timestamp;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "Accelerator Timestamp = " + timestamp + ", x = " + x + ", y = " + y + ", z = " + z + "\n";
	}

}
