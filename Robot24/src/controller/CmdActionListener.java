package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import test.Robot24Test;
import view.NumButton;

public class CmdActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Robot24Test.panel.repaint();
		String cmd = e.getActionCommand();
		if(cmd.equals("＋")){
			DataController.setCmd(DataController.PLUS_CMD);
		}else
		if(cmd.equals("－")){
			DataController.setCmd(DataController.MINUS_CMD);
		}else
		if(cmd.equals("×")){
			DataController.setCmd(DataController.MULTIPLY_CMD);
		}else
		if(cmd.equals("÷")){
			DataController.setCmd(DataController.DIVIDE_CMD);
		}else
		if(cmd.equals("还原")){
			DataController.clear();
			NumButton.allVisible();
			NumActionListener.button1 = null;
			NumActionListener.button2 = null;
		}else
		if(cmd.equals("重来")){
			Robot24Test.restart();
		}
	}
}
