package global;

import java.awt.Color;
import java.awt.Font;

public class Param {
	
	public static final String FRAME_TITLE = "24-Point Game PRO";//窗体名字
	
	public static final Color BACKGROUND_COLOR = Color.BLACK;//窗体背景
	public static final Color MARGIN_COLOR = new Color(25,25,25);//边框背景
	
	//每个数字按钮和命令按钮的颜色
	public static final Color NUM1_COLOR = Color.ORANGE;
	public static final Color NUM2_COLOR = Color.ORANGE;
	public static final Color NUM3_COLOR = Color.ORANGE;
	public static final Color NUM4_COLOR = Color.ORANGE;
	public static final Color CMD_COLOR = new Color(255,255,0);
	public static final Color RESTART_COLOR = new Color(255,255,0);
	
	//鼠标移在数字按钮上的颜色
	public static final Color MOUSE_PUT_COLOR = Color.RED;
	
	//选定的瞬间颜色
	public static final Color MOUSE_SELECT_COLOR = new Color(255,200,0);
	
	//提示字符颜色
	public static final Color FONT_COLOR = new Color(255,255,240);
	public static final Color TIMER_COLOR = new Color(255,255,240);
	public static final Color TIMER_WARNING_COLOR = new Color(191,62,255);
	
	//不同长度数字字体
	public static final Font NUM_FONT = new Font("宋体",Font.BOLD,110);
	public static final Font NUM2_FONT = new Font("宋体",Font.BOLD,98);
	public static final Font NUM3_FONT = new Font("宋体",Font.BOLD,60);
	public static final Font NUM4_FONT = new Font("宋体",Font.BOLD,35);
	
	//按钮字体
	public static final Font CMD_FONT = new Font("黑体",Font.BOLD,27);
	
	//提示字体
	public static final Font PROCESS_FONT = new Font("楷书",Font.PLAIN,22);
	public static final Font TIMER_FONT = new Font("宋体",Font.PLAIN,15);
	public static final Font ENDING_TITLE_FONT = new Font("",Font.BOLD,20);
}
