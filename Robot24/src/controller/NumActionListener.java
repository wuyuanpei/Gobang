package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import global.Param;
import test.Robot24Test;
import view.NumButton;

public class NumActionListener implements ActionListener{
	
	//用来保存一次操作中的第一个数字和第二个数字
	public static NumButton button1;
	public static NumButton button2;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Robot24Test.panel.repaint();
		
		//第二个是同一个按钮或者同时再点一遍一个按钮没有用
		if(button1==e.getSource()) return;
		
		double num = Double.valueOf(e.getActionCommand());
		
		//如果true Num1得到赋值，button1赋值
		if(DataController.setNum(num)){
			button1 = (NumButton)e.getSource();
			button1.setForeground(Param.MOUSE_SELECT_COLOR);
		}else{
			//如果false Num2得到赋值，button2赋值
			button2 =(NumButton) e.getSource();
			((NumButton)e.getSource()).setForeground(Param.MOUSE_SELECT_COLOR);
			button1.setVisible(false);
			((NumButton)e.getSource()).setNum(DataController.getResult());
			//判断胜利
			identifyWin();
		}
	}
	public void identifyWin(){
		ArrayList<NumButton> buttons = NumButton.buttons;
		int countVisible = 0;
		for(int i = 0;i<buttons.size();i++){
			if(buttons.get(i).isVisible()){
				countVisible++;
			}
		}
		if(countVisible == 1){
			for(int i = 0; i<buttons.size();i++){
				if(buttons.get(i).isVisible()&&buttons.get(i).getNum()>=23.98&&buttons.get(i).getNum()<=24.02){
					Robot24Test.nextLevel();
				}
			}
		}
	}
}
