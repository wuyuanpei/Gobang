package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import global.Param;
import test.Robot24Test;
import view.GamePanel;
import view.NumButton;

public class MousePutListener extends MouseMotionAdapter{

	public static final int button1 = 0;
	public static final int button2 = 1;
	public static final int button3 = 2;
	public static final int button4 = 3;
	
	private int theButton;
	
	public MousePutListener(int theButton) {
		super();
		this.theButton = theButton;
	}

	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x>20&&x<125&&y>20&&y<125){
			if(theButton ==button1){
				GamePanel.MOUSE_ON_1 = true;
				NumButton.num1.setForeground(Param.MOUSE_PUT_COLOR);
			}
			if(theButton ==button2){
				GamePanel.MOUSE_ON_2 = true;
				NumButton.num2.setForeground(Param.MOUSE_PUT_COLOR);
			}
			if(theButton ==button3){
				GamePanel.MOUSE_ON_3 = true;
				NumButton.num3.setForeground(Param.MOUSE_PUT_COLOR);
			}
			if(theButton ==button4){
				GamePanel.MOUSE_ON_4 = true;
				NumButton.num4.setForeground(Param.MOUSE_PUT_COLOR);
			}
		}else{
			GamePanel.MOUSE_ON_1 = false;
			GamePanel.MOUSE_ON_2 = false;
			GamePanel.MOUSE_ON_3 = false;
			GamePanel.MOUSE_ON_4 = false;
			
			NumButton.num1.setForeground(Param.NUM1_COLOR);
			NumButton.num2.setForeground(Param.NUM2_COLOR);
			NumButton.num3.setForeground(Param.NUM3_COLOR);
			NumButton.num4.setForeground(Param.NUM4_COLOR);
		}
		
		Robot24Test.panel.repaint();
	}

}
