package s_GUI_Richard;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ����ҳ��
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
	 * ����һ��SFrame���һ������ҳ�� ���������е���ʾ��ȷ����ť �������£���ҳ�������������Ϊnull
	 * 
	 * @param title
	 *            ҳ����Ŀ
	 * @param inf
	 *            ҳ����ʾ
	 * @param width
	 *            ҳ����
	 * @param icon
	 *            ҳ��ͼ��
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
		this.okayButton = new JButton("ȷ��");
		this.okayButton.addActionListener(new OkayListener());
		this.addOkayButton();
	}

	/**
	 * ���ù���������SFrame
	 * 
	 * @param cons
	 *            ����SFrame
	 */
	public SFrame(SFrameCons cons) {
		this(cons.title, cons.inf, cons.width, cons.icon);
	}

	/**
	 * չʾҳ�� ��ͬ��setVisible(true)
	 */
	public void shw() {
		super.setVisible(true);
	}

	/**
	 * �ȴ����ؽ�� ���û����ȷ����õ�����ֵ �ڴ�֮ǰ�߳�ֹͣ
	 * 
	 * @return ����ÿ�д𰸼���String����
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
	 * ���ÿһ�У�SLine�������
	 * 
	 * @param line
	 *            ��ӵ���
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
