package s_GUI_Richard;

import java.awt.Image;
/**
 * SFrame�Ĺ��������ڴ���SFrameʱ��Ϊ����
 * ��titleΪnullʱ Ĭ�ϡ��û����桱
 * ��infΪnullʱ Ĭ�ϡ����
 * @author WYP
 *
 */
public class SFrameCons {
	public String title, inf;
	public int width;
	public Image icon;

	/**
	 * 
	 * @param title ҳ����Ŀ
	 * @param inf ҳ����ʾ
	 * @param width ҳ����
	 * @param icon ҳ��ͼ��
	 */
	public SFrameCons(String title, String inf, int width, Image icon) {
		super();
		if (title == null)
			this.title = "�û�����";
		else
			this.title = title;
		if (inf == null)
			this.inf = "����";
		else
			this.inf = inf;
		this.width = width;
		this.icon = icon;
	}

}
