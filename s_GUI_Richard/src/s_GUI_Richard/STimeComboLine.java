package s_GUI_Richard;

import javax.swing.JComboBox;

/**
 * ����ʱ���ʽ�������� ��Ϣ����H:M
 * 
 * @author WYP
 *
 */
public class STimeComboLine extends SLine {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> hour, minute;

	/**
	 * 
	 * @param inf
	 *            ��ʾ��
	 */
	public STimeComboLine(String inf) {
		super.add(new SLabel(inf));
		hour = new JComboBox<String>();
		minute = new JComboBox<String>();
		for (int i = 1; i < 25; i++) {
			hour.addItem("" + i);
		}
		for (int i = 0; i < 60; i++) {
			minute.addItem("" + i);
		}
		super.add(hour);
		super.add(new SLabel("ʱ"));
		super.add(minute);
		super.add(new SLabel("��"));
	}

	@Override
	String getAnswer() {
		return hour.getSelectedItem().toString() + ":" + minute.getSelectedItem().toString();
	}

}
