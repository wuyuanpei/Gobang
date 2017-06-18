package s_GUI_Richard;
/**
 * 包含一个提示符的行
 * @author WYP
 *
 */
public class SLabelLine extends SLine{

	private static final long serialVersionUID = 1L;

	/**
	 * @param inf 提示符
	 */
	public SLabelLine(String inf){
		this.add(new SLabel(inf));
	}

	@Override
	String getAnswer() {
		return "null";
	}
}
