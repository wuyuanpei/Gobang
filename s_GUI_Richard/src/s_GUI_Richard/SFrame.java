package s_GUI_Richard;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 基本页面
 * 
 * @author WYP
 *
 */
public class SFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int lines, width;
	private JPanel mainPanel;
	private GridLayout layout;
	private JButton okayButton;
	private String[] response;
	private SFrame th = this;
	private boolean waiting = true;

	/**
	 * 创建一个SFrame获得一个基本页面 包含窗体中的提示和确定按钮 参数如下：除页面宽度外其余可以为null
	 * 
	 * @param title
	 *            页面题目
	 * @param inf
	 *            页面提示
	 * @param width
	 *            页面宽度
	 * @param icon
	 *            页面图标
	 */
	public SFrame(String title, String inf, int width, Image icon) {
		super(title);
		this.width = width;
		super.setIconImage(icon);
		this.lines = 2;
		super.setResizable(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(width, lines * 49);
		super.setLocationRelativeTo(null);
		this.mainPanel = new JPanel();
		this.add(mainPanel);
		this.layout = new GridLayout(2, 1);
		this.mainPanel.setLayout(layout);
		this.mainPanel.add(new SLabel(inf));
		this.okayButton = new JButton("确定");
		this.okayButton.addActionListener(new OkayListener());
		this.addOkayButton();
	}

	/**
	 * 采用构造器构造SFrame
	 * 
	 * @param cons
	 *            构造SFrame
	 */
	public SFrame(SFrameCons cons) {
		this(cons.title, cons.inf, cons.width, cons.icon);
	}

	/**
	 * 展示页面 等同于setVisible(true)
	 */
	public void shw() {
		super.setVisible(true);
	}

	/**
	 * 等待返回结果 在用户点击确定后得到返回值 在此之前线程停止
	 * 
	 * @return 包含每行答案集的String数组
	 */
	public String[] accept() {
		synchronized (this) {
			try {
				while (waiting)
					this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * 添加每一行，SLine类的子类
	 * 
	 * @param line
	 *            添加的行
	 */
	public void addLine(SLine line) {
		lines++;
		this.setSize(width, lines * 40);
		this.setLocationRelativeTo(null);
		((GridLayout) mainPanel.getLayout()).setRows(lines);
		mainPanel.remove(lines - 2);
		mainPanel.add(line);
		addOkayButton();
	}

	private void addOkayButton() {
		JPanel temp = new JPanel();
		temp.add(okayButton);
		this.mainPanel.add(temp);
	}

	private class OkayListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			response = new String[lines - 2];
			for (int i = 1; i < lines - 1; i++) {
				response[i - 1] = ((SLine) mainPanel.getComponent(i)).getAnswer();
			}
			th.setVisible(false);
			synchronized (th) {
				waiting = false;
				th.notify();
			}
		}
	}
}
