package view;

import java.util.ArrayList;

import javax.swing.JButton;

import controller.CmdActionListener;
import global.Param;

public class CmdButton extends JButton{
	
	private static final long serialVersionUID = -3027534664458512513L;
	
	public static final ArrayList<CmdButton> buttons = new ArrayList<>();
	
	public static final CmdButton plus = new CmdButton("＋");
	public static final CmdButton minus = new CmdButton("－");
	public static final CmdButton multiply = new CmdButton("×");
	public static final CmdButton divide = new CmdButton("÷");
	public static final CmdButton back = new CmdButton("还原");
	public static final CmdButton restart = new CmdButton("重来");
	
	private CmdButton(String text){
		this.addActionListener(new CmdActionListener());
		this.setText(text);
		if(text!="重来")
			this.setForeground(Param.CMD_COLOR);
		else
			this.setForeground(Param.RESTART_COLOR);
		this.setFocusable(false);
		this.setFont(Param.CMD_FONT);
		this.setContentAreaFilled(false);
		if(text!="重来"){
			this.setBorderPainted(false);
		}
		
		CmdButton.buttons.add(this);
	}
}
