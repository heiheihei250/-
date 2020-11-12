package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import pojo.InsideLift;
import pojo.OutsideLift;
import pojo.StatusLift;
import service.RunLift;

public class LiftGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	InsideLift insideLift = new InsideLift(1, 0, new boolean[RunLift.MAXFLOOR + 1], InsideLift.Wait);
	OutsideLift outsideLift = new OutsideLift(new boolean[RunLift.MAXFLOOR + 1], 
			new boolean[RunLift.MAXFLOOR + 1]);
	private StatusLift statusLift = null;
	final private int WAITTIMEWAIT = 4;
	final private int WAITTIMERUN = 1;
	
	Color background;
	Color textBackground;
	
	// 等待时间
	private int waitTime = 0;
	
	private boolean waitOrRun;

	// 文本框
	private JTextField field;// 楼层文本框
	private JTextField timeField;// 等待时间文本框
	private JTextField weightField;// 重量文本框

	// 按钮
	private JButton[] buttons1;
	private JButton buttonsOpen;
	private JButton buttonsClose;
	private JButton[] buttonUps;
	private JButton[] buttonDowns;

	// 面板
	private JPanel jp;
	private JPanel jp1;
	private JPanel jp2;
	
	// 电梯当前重量
	private int nowWeight;
	
	public LiftGui() throws Exception{
		String[] name1={"1","2","3","4","5","6","7","8","9","10","11","12"};
		// 图片对象
		Icon imageUp = new ImageIcon("images/up.png");
		Icon imageDown = new ImageIcon("images/down.png");
		Icon imageOpen = new ImageIcon("images/open.png");
		Icon imageClose = new ImageIcon("images/close.png");

		this.setTitle("电梯");
		this.setLocation(300, 200);
		this.setSize(500,800);
		this.setResizable(false);
		buttons1 = new JButton[RunLift.MAXFLOOR + 1];
		buttonUps = new JButton[RunLift.MAXFLOOR + 1];
		buttonDowns = new JButton[RunLift.MAXFLOOR + 1];

		jp=new JPanel(null);

		jp1=new JPanel(null);
		jp2=new JPanel(null);

		GridLayout gridLayout=new GridLayout(12,1,0,5);

		jp.setLayout(gridLayout);

		jp.setBounds(80,160,60,500);

		jp1.setLayout(new GridLayout(1,2,5,0));
		jp1.setBounds(50,680,120,40);
		Font font2 =new Font("hei'ti",Font.BOLD,16) ;

		for (int i = buttons1.length - 1; i>=1; i--) {
			buttons1[i] = new JButton(name1[i - 1]);
			buttons1[i].setSize(60,20);
			jp.add(buttons1[i]);
			buttons1[i].setFont(font2);//设置文字格式
			buttons1[i].addActionListener(this);
		}
		// 开关电梯按钮
		buttonsOpen =new JButton(imageOpen);
		buttonsOpen.setSize(60,40);
		buttonsOpen.addActionListener(this);
		jp1.add(buttonsOpen);
		buttonsClose =new JButton(imageClose);
		buttonsClose.setSize(60,40);
		buttonsClose.addActionListener(this);
		jp1.add(buttonsClose);
		
		// 面板2
		jp2.setLayout(new GridLayout(12,2,5,5));
		jp2.setBounds(320,160,120,500);
		
		// 将每一层的按钮都加入到面板二
		for(int i=12;i>=1;i--){
			buttonUps[i] = new JButton(imageUp);
			buttonUps[i].setToolTipText(i+"");
			buttonUps[i].addActionListener(this);
			buttonDowns[i]=new JButton(imageDown);
			buttonDowns[i].setToolTipText(i+"");
			buttonDowns[i].addActionListener(this);
			jp2.add(buttonUps[i]);
			jp2.add(buttonDowns[i]);
		}

		field=new JTextField();
		timeField=new JTextField();
		weightField = new JTextField();

		field.setBounds(60, 10, 380, 60);
		timeField.setBounds(120, 80, 100, 60);
		weightField.setBounds(280, 80, 100, 60);

		field.setBackground(Color.WHITE);
		timeField.setBackground(Color.WHITE);
		weightField.setBackground(Color.white);

		field.setHorizontalAlignment(JTextField.CENTER);
		timeField.setHorizontalAlignment(JTextField.CENTER);
		weightField.setHorizontalAlignment(JTextField.CENTER);
		
		Font font1 = new Font("微软雅黑",Font.BOLD,30);
		Font fontTime = new Font("微软雅黑",Font.BOLD,20);

		field.setFont(font1);
		timeField.setFont(fontTime);
		weightField.setFont(fontTime);
		
		// 设置显示楼层文本框的默认颜色
		field.setBackground(Color.decode("#cde6c7"));

		field.setEditable(false);//使文本框不可选中
		timeField.setEditable(false);
		weightField.setEditable(false);
		
		
		// 获取按钮的默认颜色
		background = buttonsOpen.getBackground();
		textBackground = weightField.getBackground();

		Container c = this.getContentPane();
		c.setLayout(null);
		c.add(jp);
		c.add(field);
		c.add(jp1);
		c.add(jp2);
		c.add(timeField);
		c.add(weightField);
		this.setVisible(true);
		
		// 使用线程实现电梯等待效果
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					// 判断状态，确定是否到达指定的楼层，到达则将按钮弹起（改变颜色）
					statusLift = RunLift.next(insideLift, outsideLift);
					
//					System.out.println("状态-->"+waitOrRun);
					
					if(insideLift.getUpOrDown() == InsideLift.Wait){
						insideLift.getBtn()[statusLift.getFloor()] = false;
						outsideLift.getDown()[statusLift.getFloor()] = false;
						outsideLift.getUp()[statusLift.getFloor()] = false;
					}
					
					if(statusLift.isStatus() == StatusLift.WAIT) {
						
						if (insideLift.getBtn()[statusLift.getFloor()] == false)
							buttons1[statusLift.getFloor()].setBackground(background); 
						if (outsideLift.getUp()[statusLift.getFloor()] == false)
							buttonUps[statusLift.getFloor()].setBackground(background); 
						if (outsideLift.getDown()[statusLift.getFloor()] == false)
							buttonDowns[statusLift.getFloor()].setBackground(background); 
						
						// 判断电梯是否还要继续工作
						waitOrRun = true;
						for(int i=1;i<=RunLift.MAXFLOOR;i++){
							if(insideLift.getBtn()[i]||outsideLift.getUp()[i]||outsideLift.getDown()[i]){
								waitOrRun = false;
								break;
							}
						}
						
						// 重置等待时间
						waitTime = WAITTIMEWAIT;
						// 获取随机体重
						nowWeight = (int)(Math.random() * 1200);
						if(waitOrRun){
							timeField.setText("---");
							weightField.setText("---");
						}else{
							// 若体重超过最大承重，修改颜色，否则，置为之前的颜色
							if(nowWeight>RunLift.MAXWEIGHT) weightField.setBackground(Color.decode("#D71345"));
							else weightField.setBackground(textBackground);
							insideLift.setWeigth(nowWeight);
							// 重量显示
							weightField.setText(insideLift.getWeigth() + "kg");
							// 时间显示
							timeField.setText(String.valueOf(waitTime));
						}
						
					}else {
						if(waitOrRun){
							timeField.setText("---");
							weightField.setText("---");
						}else{
							// 时间显示
							waitTime = WAITTIMERUN;
							timeField.setText(String.valueOf(waitTime));
//							// 重量显示
//							insideLift.setWeigth(nowWeight);
//							weightField.setText(insideLift.getWeigth() + "kg");
						}
					}
					field.setText(String.valueOf(statusLift.getFloor()));
					System.out.println(statusLift.getFloor());
					try {
						while(waitTime > 0) {
							if(!waitOrRun) {
								timeField.setText(String.valueOf(waitTime));
								insideLift.setWeigth(nowWeight);
								weightField.setText(insideLift.getWeigth() + "kg");
							}
							Thread.sleep(500);
							waitTime--;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();

	}
	public static void main(String[] args) throws Exception{
		new LiftGui();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			if(event.getSource() == buttonsOpen) {
				if(statusLift.isStatus() == StatusLift.WAIT){
					waitTime = WAITTIMEWAIT;
					timeField.setText(String.valueOf(waitTime));
				}
			}else if(event.getSource() == buttonsClose){
				waitTime = 0;
				timeField.setText(String.valueOf(waitTime));
			}else {
				for(int i=1;i<=12;i++) {
					if(event.getSource() == buttons1[i]) {
						insideLift.getBtn()[i] = true;
						buttons1[i].setBackground(Color.decode("#d3d7d4"));
						dataInit();
						break;
					}else if(event.getSource() == buttonUps[i]) {
						outsideLift.getUp()[i] = true;
						buttonUps[i].setBackground(Color.decode("#d3d7d4"));
						dataInit();
						break;
					}else if(event.getSource() == buttonDowns[i]) {
						outsideLift.getDown()[i] = true;
						buttonDowns[i].setBackground(Color.decode("#d3d7d4"));
						dataInit();
						break;
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void dataInit(){
		if(waitOrRun&&statusLift.isStatus() == StatusLift.WAIT){
			waitTime = WAITTIMEWAIT;
			if(nowWeight>RunLift.MAXWEIGHT) weightField.setBackground(Color.decode("#D71345"));
			else weightField.setBackground(textBackground);
			insideLift.setWeigth(nowWeight);
			// 重量显示
			weightField.setText(insideLift.getWeigth() + "kg");
			timeField.setText(String.valueOf(waitTime));
			waitOrRun = false;
		}
	}
	
}
