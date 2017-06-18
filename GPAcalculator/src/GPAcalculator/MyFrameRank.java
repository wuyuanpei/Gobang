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
	// ��������
	private JPanel north = new JPanel();
	private JTextArea record = new JTextArea(100, 100);
	private JLabel namel = new JLabel("���������");
	private JTextField name = new JTextField(13);
	private JButton tijiao = new JButton("�ύ");
	private JButton jieshu = new JButton("����");

	// ������
	public MyFrameRank(int x, int y, String gpa,boolean flag,String nameAlready) {
		// x,y����Ĵ���λ�ã�gpaΪ������flagΪ�Ƿ����ύ�ɼ�
		// ��������
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
			// �����ļ���
			File f2 = new File("D:\\Program Files\\GPAcalculator");
			String inf="";
			// ��������ڴ����ļ���
			if (!f2.exists()) {
				f2.mkdirs();
			}
			// ���ļ����´����ļ�
			File f = new File("D:\\Program Files\\GPAcalculator\\GPA.RECORD");
			if(!f.exists()){
				record.setText("��û����ʷ��¼��");
			}
			if(f.exists()){
			// ����������
			FileInputStream fis = new FileInputStream(f);
			// ��ȡ�ļ�
			byte[] b = new byte[1024];
			int bytes;
			while ((bytes = fis.read(b, 0, b.length)) != -1) {
				inf = inf.concat(new String(b, 0, bytes));
			}
			fis.close();
			// ��ɨ�����ݷŵ�����
			record.setText("��ʷ��¼��\n"+inf);}
			// ����������
			// ����gpa JTextField �Լ��ô��� ��Ϊ�㰴ť���Զ�ˢ��
			myActionListener4 mal4 = new myActionListener4(name, gpa, f, inf,this);
			tijiao.addActionListener(mal4);
			myKeyListener2 mkl2=new myKeyListener2(name, gpa, f, inf,this);
			name.addKeyListener(mkl2);
			record.setFocusable(false);
		} catch (Exception e) {
			// �ǵô������
			record.setText("���棺�����Ҳ������޷�������д��");
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
			// �Ծ����ڵ��ļ�
			FileOutputStream fos = new FileOutputStream(f);
//			//����������תΪProperties ��������
//			Properties p=new Properties();
//			p.list(new PrintStream(fos));
//			
			String names = name.getText();
			names+="k";
			if(names.equals("k")) names="����";
			else
				names=names.substring(0,names.length()-1);
			//3.0�汾�ֽ�������
			String all =names + "\t" + gpa + "\n"+inf;
			
			//���¼�¼д���������ٸ���֮ǰ��inf��Ϣ
			byte[] bs = all.getBytes();
			fos.write(bs);
			fos.flush();
			fos.close();
			// System.out.println("д��ɹ�");
		} catch (Exception e1) {
			// �ǵô������
			gpa="���棺�����Ҳ������޷�������д��";
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
			// �Ծ����ڵ��ļ�
			FileOutputStream fos = new FileOutputStream(f);
			String names = name.getText();
			names+="k";
			if(names.equals("k")) names="����";
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
			// System.out.println("д��ɹ�");
		} catch (Exception e1) {
			// �ǵô������
			gpa="���棺�����Ҳ������޷�������д��";
		}
		
		newOne = new MyFrameRank(old.getLocation().x, old.getLocation().y, gpa,true,name.getText());
		newOne.setVisible(true);
		old.setVisible(false);
	}
}