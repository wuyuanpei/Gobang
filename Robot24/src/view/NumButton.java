package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

import calculator.NumProducer;
import controller.MousePutListener;
import controller.NumActionListener;
import global.Param;

public class NumButton extends JButton{

	private static final long serialVersionUID = 3165168475277711526L;
	
	private double num;

	public static final ArrayList<NumButton> buttons = new ArrayList<>();
	
	public static final NumButton num1 = new NumButton(Param.NUM1_COLOR,MousePutListener.button1);
	public static final NumButton num2 = new NumButton(Param.NUM2_COLOR,MousePutListener.button2);
	public static final NumButton num3 = new NumButton(Param.NUM3_COLOR,MousePutListener.button3);
	public static final NumButton num4 = new NumButton(Param.NUM4_COLOR,MousePutListener.button4);
	
	private NumButton(Color buttonColor,int theButton) {
		this.addMouseMotionListener(new MousePutListener(theButton));
		this.addActionListener(new NumActionListener());
		this.setForeground(buttonColor);
		this.setFocusable(false);
		this.setFont(Param.NUM_FONT);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		NumButton.buttons.add(this);
	}
	
	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
		//如果.0结尾证明是整数
		if(new String(""+num).endsWith(".0")){
			String newIntText = new String(""+num).substring(0,new String(""+num).lastIndexOf("."));
			setString(newIntText);
		}else{
			//不然是小数
			String newDoubleText = ""+num;
			if(newDoubleText.length() > 4)
				newDoubleText = newDoubleText.substring(0, 5);
			setString(newDoubleText);
		}
	}
	public void setString(String newText){
		if(newText.length() == 1){
			this.setFont(Param.NUM_FONT);
			this.setText(newText);
		}else
		if(newText.length() == 2){
			this.setFont(Param.NUM2_FONT);
			this.setText(newText);
		}else
		if(newText.length() == 3){
			this.setFont(Param.NUM3_FONT);
			this.setText(newText);
		}else
		if(newText.length() >= 4){
			this.setFont(Param.NUM4_FONT);
			this.setText(newText);
		}
	}
	public static void allVisible(){
		ArrayList<NumButton> buttons = NumButton.buttons;
		for(int j = 0; j<buttons.size();j++){
			buttons.get(j).setNum(NumProducer.result[j]);
			buttons.get(j).setVisible(true);
		}
	}
}
