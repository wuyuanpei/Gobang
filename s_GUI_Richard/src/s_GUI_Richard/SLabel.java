package s_GUI_Richard;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
/**
 * 对于任何提示的参数处理
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
	 * 设置当前字体
	 * @param fontName 字体名
	 * @param form 字体格式
	 * @param size 字体大小
	 */
	public static void setCurrentFont(String fontName, int form, int size) {
		currentFont = new Font(fontName, form, size);
	}
	/**
	 * 设置当前字体颜色
	 * @param fontColor 字体颜色
	 */
	public static void setColor(Color fontColor){
		currentColor = fontColor;
	}
	SLabel(String inf) {
		super(inf);
		if (currentFont == null)
			super.setFont(new Font("华文新魏", Font.BOLD, 20));
		else
			super.setFont(currentFont);
		if(currentColor==null)
			super.setForeground(Color.BLACK);
		else
			super.setForeground(currentColor);
	}
}
