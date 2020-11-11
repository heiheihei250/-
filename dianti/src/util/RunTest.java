package util;

import pojo.InsideLift;
import pojo.OutsideLift;
import pojo.StatusLift;
import service.RunLift;

public class RunTest {
	public static void main(String[] args) {
		int n = 30;
		InsideLift insideLift = new InsideLift(1, 0, new boolean[RunLift.MAXFLOOR + 1], InsideLift.Wait);
		OutsideLift outsideLift = new OutsideLift(new boolean[RunLift.MAXFLOOR + 1], 
				new boolean[RunLift.MAXFLOOR + 1]);
		StatusLift statusLift = null;
		while(n > 0) {
			n--;
			
			statusLift = RunLift.next(insideLift, outsideLift);
			System.out.println(statusLift.getFloor());
		}
	}
}
