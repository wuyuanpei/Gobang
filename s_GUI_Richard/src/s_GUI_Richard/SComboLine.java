package s_GUI_Richard;

import javax.swing.JComboBox;

/**
 * ����һ����ʾ����һ�������˵�����
 * 
 * @author WYP
 *
 */
public class SComboLine extends SLine {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> box;

	/**
	 * 
	 * @param inf
	 *            ��ʾ��
	 * @param options
	 *            �����˵���һ��ѡ��
	 */
	public SComboLine(String inf, String... options) {
		super.add(new SLabel(inf));
		box = new JComboBox<String>();
		for (int i = 0; i < options.length; i++) {
			box.addItem(options[i]);
		}
		super.add(box);
	}

	@Override
	String getAnswer() {
		return box.getSelectedItem().toString();
	}

}
