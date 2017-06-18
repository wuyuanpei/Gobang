package s_GUI_Richard;

import javax.swing.JTextField;
/**
 * 包含一个提示符，一个文本框，一个提示符的行
 * @author WYP
 *
 */
public class STextLine extends SLine{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	/**
	 * 
	 * @param inf 提示符
	 * @param space 文本框大小
	 * @param inf2 后缀提示符
	 */
	public STextLine(String inf, int space,String inf2){
		super.add(new SLabel(inf));
		text = new JTextField(space);
		super.add(text);
		super.add(new SLabel(inf2));
	}
	@Override
	String getAnswer() {
		return text.getText();
	}
}
