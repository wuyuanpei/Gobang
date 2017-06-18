package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import global.Param;
import test.Robot24Test;
import view.NumButton;

public class NumActionListener implements ActionListener{
	
	//��������һ�β����еĵ�һ�����ֺ͵ڶ�������
	public static NumButton button1;
	public static NumButton button2;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Robot24Test.panel.repaint();
		
		//�ڶ�����ͬһ����ť����ͬʱ�ٵ�һ��һ����ťû����
		if(button1==e.getSource()) return;
		
		double num = Double.valueOf(e.getActionCommand());
		
		//���true Num1�õ���ֵ��button1��ֵ
		if(DataController.setNum(num)){
			button1 = (NumButton)e.getSource();
			button1.setForeground(Param.MOUSE_SELECT_COLOR);
		}else{
			//���false Num2�õ���ֵ��button2��ֵ
			button2 =(NumButton) e.getSource();
			((NumButton)e.getSource()).setForeground(Param.MOUSE_SELECT_COLOR);
			button1.setVisible(false);
			((NumButton)e.getSource()).setNum(DataController.getResult());
			//�ж�ʤ��
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
