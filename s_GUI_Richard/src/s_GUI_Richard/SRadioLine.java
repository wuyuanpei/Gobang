package s_GUI_Richard;

import javax.swing.JRadioButton;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
/**
 * ����һ����ʾ����һЩ��ѡ��ť����
 * @author WYP
 *
 */
public class SRadioLine extends SLine{
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttons;
	/**
	 * 
	 * @param inf ��ʾ��
	 * @param options ��ѡ��ť��ѡ��
	 */
	public SRadioLine(String inf,String... options){
		super.add(new SLabel(inf));
		buttons = new ButtonGroup();
		for(int i =0;i<options.length;i++){
			JRadioButton btn = new JRadioButton(options[i],true);
			super.add(btn);
			buttons.add(btn);
		}
	}
	@Override
	String getAnswer() {
		Enumeration<AbstractButton> theButtons = buttons.getElements();
		while(theButtons.hasMoreElements()){
			AbstractButton btn = theButtons.nextElement();
			if(btn.isSelected())
				return btn.getText();
		}
		return "null";//impossible
	}
}
