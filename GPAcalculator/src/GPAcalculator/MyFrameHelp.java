package GPAcalculator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyFrameHelp extends JFrame{

	private static final long serialVersionUID = 1L;
	JTextArea inf = new JTextArea(300,300);
	JButton queding = new JButton("确定");
	JButton guanli = new JButton("管理者");
	JPanel buttons = new JPanel();
	public MyFrameHelp() {
		this.setTitle("GPA calculator 4.0");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocation(5, 5);
		this.setLayout(new BorderLayout());
		this.add(inf,BorderLayout.CENTER);
		this.add(buttons,BorderLayout.SOUTH);
		buttons.add(queding);
		queding.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MyFrameHelp.this.setVisible(false);
			}
			
		});
		buttons.add(guanli);
		guanli.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegulateFrame(getLocation().x,getLocation().y).setVisible(true);
				MyFrameHelp.this.setVisible(false);
			}
			
		});
		inf.setFocusable(false);
		
		inf.setText("帮助文档\n欢迎使用GPA计算器4.0版本！\n1)"
				+ "请在文本框输入各门科目对应的成绩，并在复选框勾选对应的水平，即H,H+,H-\n"
				+ "2)本计算器仅接受0~100的整数，不接受0.5的小数，请四舍五入\n3)输入完成"
				+ "后按回车跳转到下一个文本框，最后一个文本框将跳转到强化文学等"
				+ "级的复选框\n4)在复选框按上或下键可以切换水平，在按钮位置按空格可以选定"
				+ "\n5)结算页面提供估算到两位小数的GPA值、精确GPA值与估计年级排名\n6)"
				+ "在提交分数页面输入名字后提交即可记录分数，若不愿意提交则可直接结束\n7)若结算"
				+ "页面出现“输入信息格式不正确或信息不完整”，请再次核对输入的内容\n8"
				+ ")4.0版本提供管理者权限，管理者可以直接处理数据并与服务器通信\n\n"
				+ "开发者：吴元培");

	}
}