package global;

import java.awt.Color;
import java.awt.Font;

/**
 * 游戏参数
 * @author WYP
 *
 */
public class Param {
	
	public static final String FRAME_TITLE = "SuperParkour1.0";//窗体标题
	
	public static final int WIDTH = 600;
	public static final int LENGTH = 450;//窗体大小
	
	public static final int GROUND_LOCATION = 270;//地面高度
	
	public static final Color BACKGROUND_COLOR = Color.WHITE;//背景颜色
	
	public static final int FAST_SPEED = 2;
	public static final int SLOW_SPEED = 4;//快速和慢速 每一个单位的毫秒数
	public static final int EACH_MOVE = 2;//一次移动的像素格
			
	public static final Color MARGIN_COLOR = Color.LIGHT_GRAY;//提示边页面颜色
	
	public static final Font ENDING_TITLE_FONT = new Font("",Font.BOLD,20);//提示标题颜色

	public static final Color FONT_COLOR = Color.BLACK;//字体颜色
	public static final String ADMIN_CODE = "1i4j50u1";//管理者账户
}
