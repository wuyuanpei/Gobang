package s_GUI_Richard;

import java.awt.Image;
/**
 * SFrame的构造器，在创建SFrame时作为参数
 * 当title为null时 默认“用户界面”
 * 当inf为null时 默认“事项”
 * @author WYP
 *
 */
public class SFrameCons {
	public String title, inf;
	public int width;
	public Image icon;

	/**
	 * 
	 * @param title 页面题目
	 * @param inf 页面提示
	 * @param width 页面宽度
	 * @param icon 页面图标
	 */
	public SFrameCons(String title, String inf, int width, Image icon) {
		super();
		if (title == null)
			this.title = "用户界面";
		else
			this.title = title;
		if (inf == null)
			this.inf = "事项";
		else
			this.inf = inf;
		this.width = width;
		this.icon = icon;
	}

}
