package s_GUI_Richard;

import javax.swing.JTextField;
/**
 * ����һ����ʾ����һ���ı���һ����ʾ������
 * @author WYP
 *
 */
public class STextLine extends SLine{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	/**
	 * 
	 * @param inf ��ʾ��
	 * @param space �ı����С
	 * @param inf2 ��׺��ʾ��
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
