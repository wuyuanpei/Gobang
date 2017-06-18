package s_GUI_Richard;

import javax.swing.JComboBox;

/**
 * 包含一个提示符与一个下拉菜单的行
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
	 *            提示符
	 * @param options
	 *            下拉菜单的一下选项
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
