package pojo;

public class InsideLift {
	private int floor;
	private int weigth;
	private boolean[] btn;
	private int upOrDown;
	
	final static public int UP = 1;
	final static public int Down = 2;
	final static public int Wait = 3;
	
	public InsideLift() {
		super();
	}

	public InsideLift(int floor, int weigth, boolean[] btn, int upOrDown) {
		super();
		this.floor = floor;
		this.weigth = weigth;
		this.btn = btn;
		this.upOrDown = upOrDown;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}

	public boolean[] getBtn() {
		return btn;
	}

	public void setBtn(boolean[] btn) {
		this.btn = btn;
	}

	public int getUpOrDown() {
		return upOrDown;
	}

	public void setUpOrDown(int upOrDown) {
		this.upOrDown = upOrDown;
	}
	
}
