package s_GUI_Richard;
/**
 * ����һ����ʾ������
 * @author WYP
 *
 */
public class SLabelLine extends SLine{

	private static final long serialVersionUID = 1L;

	/**
	 * @param inf ��ʾ��
	 */
	public SLabelLine(String inf){
		this.add(new SLabel(inf));
	}

	@Override
	String getAnswer() {
		return "null";
	}
}
