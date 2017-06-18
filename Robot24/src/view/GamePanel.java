package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import calculator.ValidCalculator;
import controller.DataController;
import global.Param;
import test.Level;
import test.Robot24Test;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;

	public static boolean MOUSE_ON_1 = false;
	public static boolean MOUSE_ON_2 = false;
	public static boolean MOUSE_ON_3 = false;
	public static boolean MOUSE_ON_4 = false;

	public ImageIcon img;
	
	public JPanel north = new JPanel();

	public GamePanel() {
		this.setBackground(Param.BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
		north.setOpaque(false);
		north.setLayout(new GridLayout(2, 2));
		//添加按钮
		for (int i = 0; i < NumButton.buttons.size(); i++) {
			north.add(NumButton.buttons.get(i));
		}
		this.add(north, BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.setOpaque(false);
		for (int i = 0; i < CmdButton.buttons.size(); i++) {
			center.add(CmdButton.buttons.get(i));
		}
		this.add(center, BorderLayout.CENTER);
		URL url = Robot24Test.class.getResource("icon.png");
		img = new ImageIcon(url);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawProcess(g);
		drawRec(g);
		drawTime(g);
		drawLevel(g);
		drawRobot(g);
	}
	public void drawLevel(Graphics g){
		g.setColor(Param.TIMER_COLOR);
		g.setFont(Param.TIMER_FONT);
		g.drawString("关卡-"+Level.level,5,460);
	}
	public void drawTime(Graphics g){
		g.setColor(Param.TIMER_COLOR);
		if(Robot24Test.timer.getSeconds()<=15)
			g.setColor(Param.TIMER_WARNING_COLOR);
		g.setFont(Param.TIMER_FONT);
		g.drawString("倒计时:"+Robot24Test.timer.getSeconds()+"秒",195,460);
	}
	public void drawProcess(Graphics g){
		g.setColor(Param.FONT_COLOR);
		g.setFont(Param.PROCESS_FONT);
		String process = "没有计算过程哦！";
		if (DataController.getNum1() != DataController.NO_NUM) {
			String num1 = String.valueOf(DataController.getNum1());
			//如果结尾是.0去除
			if(num1.endsWith(".0")){
				num1 = num1.substring(0, num1.lastIndexOf("."));
			}
			//添加字符串
			if (DataController.getCmd() == DataController.PLUS_CMD) {
				process = new String(num1 + "＋");
			} else if (DataController.getCmd() == DataController.MINUS_CMD) {
				process = new String(num1  + "－");
			} else if (DataController.getCmd() == DataController.MULTIPLY_CMD) {
				process = new String(num1  + "×");
			} else if (DataController.getCmd() == DataController.DIVIDE_CMD) {
				process = new String(num1  + "÷");
			} else {
				process = new String(num1);
			}	
		}
		if(DataController.getNum2()!=DataController.NO_NUM){
			String num2 = String.valueOf(DataController.getNum2());
			//如果结尾是.0去除
			if(num2.endsWith(".0")){
				num2 = num2.substring(0,num2.lastIndexOf("."));
			}
			String result = String.valueOf(DataController.getResult());
			if(result.endsWith(".0")){
				result = result.substring(0,result.lastIndexOf("."));
			}
			process = process.concat(num2+"＝"+result);
		}
		if(Robot24Test.timer.getSeconds()>15)
			g.drawString("计算：" + process, 27, 420);
		else
			g.drawString("提示：" + ValidCalculator.hintFinal, 27, 420);
	}
	
	public void drawRec(Graphics g){
		//设置颜色与画出方块
		/*
				if (!MOUSE_ON_1) {
					g.setColor(Param.NUM1_COLOR);
				} else {
					g.setColor(Param.MOUSE_PUT_COLOR);
				}
				g.drawRect(10, 10, 125, 125);
				if (!MOUSE_ON_2) {
					g.setColor(Param.NUM2_COLOR);
				} else {
					g.setColor(Param.MOUSE_PUT_COLOR);
				}
				g.drawRect(150, 10, 125, 125);
				if (!MOUSE_ON_3) {
					g.setColor(Param.NUM3_COLOR);
				} else {
					g.setColor(Param.MOUSE_PUT_COLOR);
				}
				g.drawRect(10, 145, 125, 125);
				if (!MOUSE_ON_4) {
					g.setColor(Param.NUM4_COLOR);
				} else {
					g.setColor(Param.MOUSE_PUT_COLOR);
				}
				g.drawRect(150, 145, 125, 125);
				*/
	}
	public void drawRobot(Graphics g){
		
//		img.paintIcon(null, g, 111, 105);
	}
}
