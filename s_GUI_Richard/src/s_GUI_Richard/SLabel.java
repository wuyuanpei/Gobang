package s_GUI_Richard;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
/**
 * �����κ���ʾ�Ĳ�������
 * @author WYP
 *
 */
public class SLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private static Font currentFont;
	private static Color currentColor;
	public static final int PLAIN = Font.PLAIN;
	public static final int BOLD = Font.BOLD;
	public static final int ITALIC = Font.ITALIC;
	
	/**
	 * ���õ�ǰ����
	 * @param fontName ������
	 * @param form �����ʽ
	 * @param size �����С
	 */
	public static void setCurrentFont(String fontName, int form, int size) {
		currentFont = new Font(fontName, form, size);
	}
	/**
	 * ���õ�ǰ������ɫ
	 * @param fontColor ������ɫ
	 */
	public static void setColor(Color fontColor){
		currentColor = fontColor;
	}
	SLabel(String inf) {
		super(inf);
		if (currentFont == null)
			super.setFont(new Font("������κ", Font.BOLD, 20));
		else
			super.setFont(currentFont);
		if(currentColor==null)
			super.setForeground(Color.BLACK);
		else
			super.setForeground(currentColor);
	}
}
