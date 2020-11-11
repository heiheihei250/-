package pojo;

public class StatusLift {
	private int floor;
	private boolean status;
	
	final static public boolean WAIT = true;
	final static public boolean RUN = false;
	
	public StatusLift() {
		super();
	}
	public StatusLift(int floor, boolean status) {
		super();
		this.floor = floor;
		this.status = status;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StatusLift [floor=" + floor + ", status=" + status + "]";
	}
	
}
