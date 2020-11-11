package pojo;

public class OutsideLift {
	private boolean[] up;
	private boolean[] down;
	public OutsideLift() {
		super();
	}
	public OutsideLift(boolean[] up, boolean[] down) {
		super();
		this.up = up;
		this.down = down;
	}
	public boolean[] getUp() {
		return up;
	}
	public void setUp(boolean[] up) {
		this.up = up;
	}
	public boolean[] getDown() {
		return down;
	}
	public void setDown(boolean[] down) {
		this.down = down;
	}
	
	
	
}
