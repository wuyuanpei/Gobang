package GPAcalculator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrameRank extends JFrame {
	private static final long serialVersionUID = 1L;
	// 窗体设置
	private JPanel north = new JPanel();
	private JTextArea record = new JTextArea(100, 100);
	private JLabel namel = new JLabel("你的姓名：");
	private JTextField name = new JTextField(13);
	private JButton tijiao = new JButton("提交");
	private JButton jieshu = new JButton("结束");

	// 构造器
	public MyFrameRank(int x, int y, String gpa,boolean flag,String nameAlready) {
		// x,y传入的窗口位置，gpa为分数，flag为是否已提交成绩
		// 布局设置
		this.setTitle("GPA calculator 4.0");
		this.setSize(330, 330);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		north.add(namel);
		north.add(name);
		name.setFocusable(false);
		if(!flag){
		north.add(tijiao);
		name.setFocusable(true);
		}
		name.setText(nameAlready);
		this.add(record, BorderLayout.CENTER);
		this.add(jieshu, BorderLayout.SOUTH);
		myActionListener5 mal5=new myActionListener5(this);
		jieshu.addActionListener(mal5);
		try {
			// 创建文件夹
			File f2 = new File("D:\\Program Files\\GPAcalculator");
			String inf="";
			// 如果不存在创造文件夹
			if (!f2.exists()) {
				f2.mkdirs();
			}
			// 在文件夹下创建文件
			File f = new File("D:\\Program Files\\GPAcalculator\\GPA.RECORD");
			if(!f.exists()){
				record.setText("还没有历史记录！");
			}
			if(f.exists()){
			// 创建输入流
			FileInputStream fis = new FileInputStream(f);
			// 读取文件
			byte[] b = new byte[1024];
			int bytes;
			while ((bytes = fis.read(b, 0, b.length)) != -1) {
				inf = inf.concat(new String(b, 0, bytes));
			}
			fis.close();
			// 将扫描内容放到主板
			record.setText("历史记录：\n"+inf);}
			// 创建监听器
			// 传入gpa JTextField 以及该窗体 因为点按钮后自动刷新
			myActionListener4 mal4 = new myActionListener4(name, gpa, f, inf,this);
			tijiao.addActionListener(mal4);
			myKeyListener2 mkl2=new myKeyListener2(name, gpa, f, inf,this);
			name.addKeyListener(mkl2);
			record.setFocusable(false);
		} catch (Exception e) {
			// 记得处理错误
			record.setText("警告：数据找不到或无法正常读写！");
		}
	}
}

class myActionListener4 implements ActionListener {
	private MyFrameRank old;
	private MyFrameRank newOne;
	private String inf;
	private JTextField name;
	private File f;
	private String gpa;

	public myActionListener4(JTextField name, String gpa, File f,String inf,MyFrameRank old) {
		this.name = name;
		this.gpa = gpa;
		this.old = old;
		this.f=f;
		this.inf=inf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {			
			// 以经存在的文件
			FileOutputStream fos = new FileOutputStream(f);
//			//将该输入流转为Properties 导入数据
//			Properties p=new Properties();
//			p.list(new PrintStream(fos));
//			
			String names = name.getText();
			names+="k";
			if(names.equals("k")) names="匿名";
			else
				names=names.substring(0,names.length()-1);
			//3.0版本字节输入流
			String all =names + "\t" + gpa + "\n"+inf;
			
			//最新记录写在最上面再跟上之前的inf信息
			byte[] bs = all.getBytes();
			fos.write(bs);
			fos.flush();
			fos.close();
			// System.out.println("写入成功");
		} catch (Exception e1) {
			// 记得处理错误
			gpa="警告：数据找不到或无法正常读写！";
		}
		
		newOne = new MyFrameRank(old.getLocation().x, old.getLocation().y, gpa,true,name.getText());
		newOne.setVisible(true);
		old.setVisible(false);
	}
}
class myActionListener5 implements ActionListener {
	private MyFrameRank mfr;

	public myActionListener5(MyFrameRank mfr) {
		this.mfr = mfr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mfr.setVisible(false);

		 String[] args=new String[]{mfr.getLocation().toString()};
		 Calculator.main(args);
	}
}
class myKeyListener2 extends KeyAdapter{
	private MyFrameRank old;
	private MyFrameRank newOne;
	private String inf;
	private JTextField name;
	private File f;
	private String gpa;

	public myKeyListener2(JTextField name, String gpa, File f,String inf,MyFrameRank old) {
		this.name = name;
		this.gpa = gpa;
		this.old = old;
		this.f=f;
		this.inf=inf;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()!='\n') return;
		try {
			// 以经存在的文件
			FileOutputStream fos = new FileOutputStream(f);
			String names = name.getText();
			names+="k";
			if(names.equals("k")) names="匿名";
			else
				names=names.substring(0,names.length()-1);
			String all =names + "\t" + gpa + "\n"+inf;
			byte[] bs = all.getBytes();
			// for(int i=0;i<bs.length;i++){
			// System.out.print(bs[i]+" ");
			// }
			fos.write(bs);
			fos.flush();
			fos.close();
			// System.out.println("写入成功");
		} catch (Exception e1) {
			// 记得处理错误
			gpa="警告：数据找不到或无法正常读写！";
		}
		
		newOne = new MyFrameRank(old.getLocation().x, old.getLocation().y, gpa,true,name.getText());
		newOne.setVisible(true);
		old.setVisible(false);
	}
}