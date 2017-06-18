package GPAcalculator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel title = new JLabel("GPA计算器（高二年级）");
	JLabel inf = new JLabel("请在对应科目填入分数（0到100整数），并勾选水平");
	JLabel yuwen = new JLabel("语文：");
	JLabel qianghua = new JLabel("强化：");
	JLabel wenxue = new JLabel("文学：");
	JLabel e6 = new JLabel("E6：");
	JLabel shuxue = new JLabel("数学：");
	JLabel ap1 = new JLabel("ap1：");
	JLabel ap2 = new JLabel("ap2：");
	JLabel lishi = new JLabel("历史：");
	JLabel zhengzhi = new JLabel("政治：");

	JPanel titlep = new JPanel();
	JPanel infp = new JPanel();
	JPanel yuwenp = new JPanel();
	JPanel qianghuap = new JPanel();
	JPanel wenxuep = new JPanel();
	JPanel e6p = new JPanel();
	JPanel shuxuep = new JPanel();
	JPanel ap1p = new JPanel();
	JPanel ap2p = new JPanel();
	JPanel liship = new JPanel();
	JPanel zhengzhip = new JPanel();

	JTextField yuwent = new JTextField();
	JTextField qianghuat = new JTextField();
	JTextField wenxuet = new JTextField();
	JTextField e6t = new JTextField();
	JTextField shuxuet = new JTextField();
	JTextField ap1t = new JTextField();
	JTextField ap2t = new JTextField();
	JTextField lishit = new JTextField();
	JTextField zhengzhit = new JTextField();

	JButton queding = new JButton("确定");
	JButton bangzhu = new JButton("帮助与管理");

	JComboBox<String> yuwenb = new JComboBox<String>();
	JComboBox<String> qianghuab = new JComboBox<String>();
	JComboBox<String> wenxueb = new JComboBox<String>();
	JComboBox<String> e6b = new JComboBox<String>();
	JComboBox<String> shuxueb = new JComboBox<String>();
	JComboBox<String> ap1b = new JComboBox<String>();
	JComboBox<String> ap2b = new JComboBox<String>();
	JComboBox<String> lishib = new JComboBox<String>();
	JComboBox<String> zhengzhib = new JComboBox<String>();

	public MyFrame(int x, int y) {
		//创建窗口基本信息
		this.setTitle("GPA calculator 4.0");
		this.setSize(330, 330);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);

		if (x == -1 && y == -1)
			this.setLocationRelativeTo(null);
		else
			this.setLocation(x, y);

		this.setLayout(new GridLayout(13, 1));
		
		/*
		 * 结算分数 即确定键的 ActionListener2
		 * 构造函数传入每一个文本框和强化文学的默认等级，即 H
		 */
		myActionListener2 mal2 = new myActionListener2(this, yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t,
				lishit, zhengzhit, myActionListener.H, myActionListener.H);
		myActionListener qianghuamal = new myActionListener(mal2, myActionListener.QIANGHUAMAL);
		myActionListener wenxuemal = new myActionListener(mal2, myActionListener.WENXUEMAL);

		this.add(titlep);
		titlep.add(title);

		this.add(infp);
		infp.add(inf);

		this.add(yuwenp);
		yuwenp.setLayout(new GridLayout(1, 3));
		yuwenp.add(yuwen);
		yuwenp.add(yuwent);
		yuwenp.add(yuwenb);
		yuwenb.addItem("H");

		this.add(qianghuap);
		qianghuap.setLayout(new GridLayout(1, 3));
		qianghuap.add(qianghua);
		qianghuap.add(qianghuat);
		qianghuap.add(qianghuab);
		qianghuab.addItem("H");
		qianghuab.addItem("H+");
		qianghuab.addItem("H-");
		qianghuab.addActionListener(qianghuamal);

		this.add(wenxuep);
		wenxuep.setLayout(new GridLayout(1, 3));
		wenxuep.add(wenxue);
		wenxuep.add(wenxuet);
		wenxuep.add(wenxueb);
		wenxueb.addItem("H");
		wenxueb.addItem("H+");
		wenxueb.addItem("H-");
		wenxueb.addActionListener(wenxuemal);

		this.add(e6p);
		e6p.setLayout(new GridLayout(1, 3));
		e6p.add(e6);
		e6p.add(e6t);
		e6p.add(e6b);
		e6b.addItem("S");

		this.add(shuxuep);
		shuxuep.setLayout(new GridLayout(1, 3));
		shuxuep.add(shuxue);
		shuxuep.add(shuxuet);
		shuxuep.add(shuxueb);
		shuxueb.addItem("AP");

		this.add(ap1p);
		ap1p.setLayout(new GridLayout(1, 3));
		ap1p.add(ap1);
		ap1p.add(ap1t);
		ap1p.add(ap1b);
		ap1b.addItem("AP");

		this.add(ap2p);
		ap2p.setLayout(new GridLayout(1, 3));
		ap2p.add(ap2);
		ap2p.add(ap2t);
		ap2p.add(ap2b);
		ap2b.addItem("AP");

		this.add(liship);
		liship.setLayout(new GridLayout(1, 3));
		liship.add(lishi);
		liship.add(lishit);
		liship.add(lishib);
		lishib.addItem("S");
		this.add(zhengzhip);
		zhengzhip.setLayout(new GridLayout(1, 3));
		zhengzhip.add(zhengzhi);
		zhengzhip.add(zhengzhit);
		zhengzhip.add(zhengzhib);
		zhengzhib.addItem("S");
		this.add(queding);
		this.add(bangzhu);

		queding.addActionListener(mal2);
		bangzhu.addActionListener(new helpListener());
		//回车键向下移动focus的方法
		myKeyListener mkl1 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,1);
		myKeyListener mkl2 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,2);
		myKeyListener mkl3 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,3);
		myKeyListener mkl4 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,4);
		myKeyListener mkl5 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,5);
		myKeyListener mkl6 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,6);
		myKeyListener mkl7 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,7);
		myKeyListener mkl8 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,8);
		myKeyListener mkl9 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,9);
		myKeyListener mkl10 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,10);
		myKeyListener mkl11 = new myKeyListener(yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit,yuwenb, qianghuab, wenxueb, e6b, shuxueb, ap1b, ap2b, lishib, zhengzhib,
				queding,11);
		yuwent.addKeyListener(mkl1);
		qianghuat.addKeyListener(mkl2);
		wenxuet.addKeyListener(mkl3);
		e6t.addKeyListener(mkl4);
		shuxuet.addKeyListener(mkl5);
		ap1t.addKeyListener(mkl6);
		ap2t.addKeyListener(mkl7);
		lishit.addKeyListener(mkl8);
		zhengzhit.addKeyListener(mkl9);
		qianghuab.addKeyListener(mkl10);
		wenxueb.addKeyListener(mkl11);
	}

}
/**
 * 复选框监听器
 * @author lenovo
 *
 */
class myActionListener implements ActionListener {

	myActionListener2 mal2;
	public final int type;
	public static final int QIANGHUAMAL = 1;
	public static final int WENXUEMAL = 2;
	
	public myActionListener(myActionListener2 mal2, int type) {
		this.mal2 = mal2;
		this.type = type;
	}

	private int message = 1;
	public static final int H = 1;
	public static final int H1 = 2;// H+
	public static final int H2 = 3;// H-

	public void actionPerformed(ActionEvent e) {
		int i = e.getSource().toString().lastIndexOf("=");
		String command = e.getSource().toString().substring(i + 1, e.getSource().toString().length() - 1);
		// System.out.println(command);
		if (command.equals("H+"))
			message = H1;
		else if (command.equals("H-"))
			message = H2;
		else
			message = H;
		mal2.changeLevel(this, message);
	}
}

class myActionListener2 implements ActionListener {
	MyFrame mf;
	private final JTextField yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit;
	private int yuwen, qianghua, wenxue, e6, shuxue, ap1, ap2, lishi, zhengzhi;
	private int qianghualevel, wenxuelevel;
	private int error;
	private static final int ERROR_NUM = 404;

	public myActionListener2(MyFrame mf, JTextField yuwent, JTextField qianghuat, JTextField wenxuet, JTextField e6t,
			JTextField shuxuet, JTextField ap1t, JTextField ap2t, JTextField lishit, JTextField zhengzhit,
			int qianghualevel, int wenxuelevel) {
		this.yuwent = yuwent;
		this.qianghuat = qianghuat;
		this.wenxuet = wenxuet;
		this.e6t = e6t;
		this.shuxuet = shuxuet;
		this.ap1t = ap1t;
		this.ap2t = ap2t;
		this.lishit = lishit;
		this.zhengzhit = zhengzhit;
		this.mf = mf;
		this.qianghualevel = qianghualevel;
		this.wenxuelevel = wenxuelevel;
	}

	public void changeLevel(myActionListener mal, int level) {
		if (mal.type == myActionListener.QIANGHUAMAL)
			qianghualevel = level;
		if (mal.type == myActionListener.WENXUEMAL)
			wenxuelevel = level;
	}

	public void actionPerformed(ActionEvent e) {
		commandDo();
	}
	public void commandDo(){
		try {
			this.yuwen = Integer.valueOf(yuwent.getText());
			this.qianghua = Integer.valueOf(qianghuat.getText());
			this.wenxue = Integer.valueOf(wenxuet.getText());
			this.e6 = Integer.valueOf(e6t.getText());
			this.shuxue = Integer.valueOf(shuxuet.getText());
			this.ap1 = Integer.valueOf(ap1t.getText());
			this.ap2 = Integer.valueOf(ap2t.getText());
			this.lishi = Integer.valueOf(lishit.getText());
			this.zhengzhi = Integer.valueOf(zhengzhit.getText());
			//错误判断
			if (yuwent.getText().length() > 1 && yuwent.getText().startsWith("0"))
				throw new Exception();
			if (qianghuat.getText().length() > 1 && qianghuat.getText().startsWith("0"))
				throw new Exception();
			if (wenxuet.getText().length() > 1 && wenxuet.getText().startsWith("0"))
				throw new Exception();
			if (e6t.getText().length() > 1 && e6t.getText().startsWith("0"))
				throw new Exception();
			if (shuxuet.getText().length() > 1 && shuxuet.getText().startsWith("0"))
				throw new Exception();
			if (ap1t.getText().length() > 1 && ap1t.getText().startsWith("0"))
				throw new Exception();
			if (ap2t.getText().length() > 1 && ap2t.getText().startsWith("0"))
				throw new Exception();
			if (lishit.getText().length() > 1 && lishit.getText().startsWith("0"))
				throw new Exception();
			if (zhengzhit.getText().length() > 1 && zhengzhit.getText().startsWith("0"))
				throw new Exception();

			if (yuwent.getText().startsWith("-"))
				throw new Exception();
			if (qianghuat.getText().startsWith("-"))
				throw new Exception();
			if (wenxuet.getText().startsWith("-"))
				throw new Exception();
			if (e6t.getText().startsWith("-"))
				throw new Exception();
			if (shuxuet.getText().startsWith("-"))
				throw new Exception();
			if (ap1t.getText().startsWith("-"))
				throw new Exception();
			if (ap2t.getText().startsWith("-"))
				throw new Exception();
			if (lishit.getText().startsWith("-"))
				throw new Exception();
			if (zhengzhit.getText().startsWith("-"))
				throw new Exception();

			if (yuwen > 100 || yuwen < 0)
				throw new Exception();
			if (qianghua > 100 || qianghua < 0)
				throw new Exception();
			if (wenxue > 100 || wenxue < 0)
				throw new Exception();
			if (e6 > 100 || e6 < 0)
				throw new Exception();
			if (shuxue > 100 || shuxue < 0)
				throw new Exception();
			if (ap1 > 100 || ap1 < 0)
				throw new Exception();
			if (ap2 > 100 || ap2 < 0)
				throw new Exception();
			if (lishi > 100 || lishi < 0)
				throw new Exception();
			if (zhengzhi > 100 || zhengzhi < 0)
				throw new Exception();
		} catch (Exception e2) {
			error = ERROR_NUM;
		}
		double gpa = 0;
		if (error != 404)
			gpa = Calculator.calculate(yuwen, qianghua, wenxue, e6, shuxue, ap1, ap2, lishi, zhengzhi, qianghualevel,
					wenxuelevel);
		MyFrameEnd mfe = new MyFrameEnd(gpa, error, mf.getLocation());
		mfe.setVisible(true);
		mf.setVisible(false);
	}
}

class myKeyListener extends KeyAdapter {
	private final JTextField  yuwent, qianghuat, wenxuet, e6t, shuxuet, ap1t, ap2t, lishit, zhengzhit;
	private final JComboBox<String>  qianghuab, wenxueb;
	private final JButton queding;
	private final int FIELD_TYPE;



	public myKeyListener(JTextField yuwent, JTextField qianghuat, JTextField wenxuet, JTextField e6t,
			JTextField shuxuet, JTextField ap1t, JTextField ap2t, JTextField lishit, JTextField zhengzhit,
			JComboBox<String> yuwenb, JComboBox<String> qianghuab, JComboBox<String> wenxueb, JComboBox<String> e6b, JComboBox<String> shuxueb, JComboBox<String> ap1b,
			JComboBox<String> ap2b, JComboBox<String> lishib, JComboBox<String> zhengzhib,JButton queding, int FIELD_TYPE) {
		this.yuwent = yuwent;
		this.qianghuat = qianghuat;
		this.wenxuet = wenxuet;
		this.e6t = e6t;
		this.shuxuet = shuxuet;
		this.ap1t = ap1t;
		this.ap2t = ap2t;
		this.lishit = lishit;
		this.zhengzhit = zhengzhit;
		
		this.qianghuab = qianghuab;
		this.wenxueb = wenxueb;
		
		this.queding=queding;
		this.FIELD_TYPE = FIELD_TYPE;
	}



	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			switch (FIELD_TYPE) {
			case 1: 
				qianghuat.grabFocus();
				break;
			case 2:
				wenxuet.grabFocus();
				break;
			case 3:
				e6t.grabFocus();
				break;
			case 4:
				shuxuet.grabFocus();
				break;
			case 5:
				ap1t.grabFocus();
				break;
			case 6:
				ap2t.grabFocus();
				break;
			case 7:
				lishit.grabFocus();
				break;
			case 8:
				zhengzhit.grabFocus();
				break;
			case 9:
				qianghuab.grabFocus();
				break;
			case 10:
				wenxueb.grabFocus();
				break;
			case 11:
				queding.grabFocus();
				break;
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			switch (FIELD_TYPE) {
			case 1: 
				break;
			case 2:
				yuwent.grabFocus();
				break;
			case 3:
				qianghuat.grabFocus();
				break;
			case 4:
				wenxuet.grabFocus();
				break;
			case 5:
				e6t.grabFocus();
				break;
			case 6:
				shuxuet.grabFocus();
				break;
			case 7:
				ap1t.grabFocus();
				break;
			case 8:
				ap2t.grabFocus();
				break;
			case 9:
				lishit.grabFocus();
				break;
			case 10:
				zhengzhit.grabFocus();
				break;
			case 11:
				qianghuab.grabFocus();
				break;
			}
		}
	}
}
class helpListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new MyFrameHelp().setVisible(true);
		
	}
	
}