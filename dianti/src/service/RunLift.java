package service;

import pojo.InsideLift;
import pojo.OutsideLift;
import pojo.StatusLift;


public class RunLift {
	final public static int MAXWEIGHT = 1000;
	final public static int MAXFLOOR = 12;
	
	public static StatusLift next(InsideLift insideLift, OutsideLift outsideLift){
		StatusLift statusLift = new StatusLift();
		statusLift.setFloor(insideLift.getFloor());
		//如果超重，返回当前楼层并等待
		if(insideLift.getWeigth() > MAXWEIGHT) {
			statusLift.setStatus(StatusLift.WAIT);
			return statusLift;
		}
		if(insideLift.getUpOrDown() == InsideLift.Down) {//如果当前电梯处于向下状态
			System.out.println("down");
			int i, z = 0;
			//从当前楼层向下找
			for (i=insideLift.getFloor() - 1;i>=1;i--) {
				if (outsideLift.getUp()[i] == true) z = i;
				//如果电梯内部该楼层被按下，或外部下按钮被按下，返回当前楼层的下一层并运行状态
				if (insideLift.getBtn()[i] == true || outsideLift.getDown()[i] == true) {
					statusLift.setStatus(StatusLift.RUN);
					insideLift.setFloor(insideLift.getFloor() - 1);
					statusLift.setFloor(insideLift.getFloor());
					//如果下面楼层的电梯内部按钮被按下，或外部下按钮被按下，则等待（打开电梯门）
					if (insideLift.getBtn()[insideLift.getFloor()] == true ||
							outsideLift.getDown()[insideLift.getFloor()] == true) {
						statusLift.setStatus(StatusLift.WAIT);
					}
					//电梯下到一楼后等待
					if (insideLift.getFloor() == 1) {
						insideLift.setUpOrDown(InsideLift.Wait);
						statusLift.setStatus(StatusLift.WAIT);//mark
					}
					//将按钮取消
					insideLift.getBtn()[insideLift.getFloor()] = false;
					outsideLift.getDown()[insideLift.getFloor()] = false;
					break;
				}
				
			}
			//如果内外均为按钮被按下，则电梯处于等待状态
			if (i < 1) {
				if (z != 0) {
					statusLift.setStatus(StatusLift.RUN);
					insideLift.setFloor(insideLift.getFloor() - 1);
					statusLift.setFloor(insideLift.getFloor());
					return statusLift;
				}
				statusLift.setStatus(StatusLift.WAIT);
				insideLift.setUpOrDown(InsideLift.Wait);
			}
			return statusLift;
		}else if(insideLift.getUpOrDown() == InsideLift.UP){//如果当前电梯处于向上状态
			System.out.println("up");
			int i, z = 0;
			//从当前楼层向上找
			for (i=insideLift.getFloor() + 1;i<=MAXFLOOR;i++) {
				if (outsideLift.getDown()[i] == true) z = i;
				//如果电梯内部该楼层被按下，或外部上按钮被按下，返回当前楼层的上一层并运行状态
				if (insideLift.getBtn()[i] == true || outsideLift.getUp()[i] == true) {
					statusLift.setStatus(StatusLift.RUN);
					insideLift.setFloor(insideLift.getFloor() + 1);
					statusLift.setFloor(insideLift.getFloor());
					//如果下面楼层的电梯内部按钮被按下，或外部上按钮被按下，则等待（打开电梯门）
					if (insideLift.getBtn()[insideLift.getFloor()] == true || 
							outsideLift.getUp()[insideLift.getFloor()] == true) {
						statusLift.setStatus(StatusLift.WAIT);
					}
					//电梯上到顶楼后等待
					if (insideLift.getFloor() == RunLift.MAXFLOOR) {
						insideLift.setUpOrDown(InsideLift.Wait);
						statusLift.setStatus(StatusLift.WAIT);
					}
					//将按钮取消
					insideLift.getBtn()[insideLift.getFloor()] = false;
					outsideLift.getUp()[insideLift.getFloor()] = false;
					break;
				}
			}
			//如果内外均为按钮被按下，则电梯处于等待状态
			if (i > MAXFLOOR) {
				if (z != 0) {
					statusLift.setStatus(StatusLift.RUN);
					insideLift.setFloor(insideLift.getFloor() + 1);
					statusLift.setFloor(insideLift.getFloor());
					return statusLift;
				}
				statusLift.setFloor(insideLift.getFloor());
				statusLift.setStatus(StatusLift.WAIT);
				insideLift.setUpOrDown(InsideLift.Wait);
				System.out.println("当前楼层：" + statusLift.getFloor() + "\n当前状态" + statusLift.isStatus());
			}
			return statusLift;
		}else {//如果当前电梯处于等待状态
			System.out.println("wait");
			//从1楼往上找
			insideLift.getBtn()[insideLift.getFloor()] = false;
			outsideLift.getUp()[insideLift.getFloor()] = false;
			outsideLift.getDown()[insideLift.getFloor()] = false;
			for (int i=1;i<=MAXFLOOR;i++) {
				//如果该楼层内外按钮被按下
				if(insideLift.getBtn()[i] == true || outsideLift.getUp()[i] == true || 
						outsideLift.getDown()[i] == true) {
					//如果该楼层在当前楼层上面
					if(i > insideLift.getFloor()) {
						//将电梯状态改为向上运行
						statusLift.setStatus(StatusLift.RUN);
						insideLift.setUpOrDown(InsideLift.UP);
					}else if(i == insideLift.getFloor()) {//如果该楼层在当前楼层
						//将电梯状态改为原地等待（开门）
						statusLift.setStatus(StatusLift.WAIT);
						//将按钮取消
						insideLift.getBtn()[insideLift.getFloor()] = false;
						outsideLift.getUp()[insideLift.getFloor()] = false;
						outsideLift.getDown()[insideLift.getFloor()] = false;
						
					}else {//如果该楼层在当前楼层下面
						//将电梯状态改为向下运行
						statusLift.setStatus(StatusLift.RUN);
						insideLift.setUpOrDown(InsideLift.Down);
					}
					return statusLift;
				}
			}
			statusLift.setStatus(StatusLift.WAIT);
		}
		
		return statusLift;
		
	}
}
