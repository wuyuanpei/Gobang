package GPAcalculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrameEnd extends JFrame {
	private static final long serialVersionUID = 1L;
	public String usefulGPA;

	myActionListener3 mal3 = new myActionListener3(this);

	JLabel inf = new JLabel();
	JLabel inf2 = new JLabel();
	JLabel inf3 = new JLabel();

	JPanel p = new JPanel();// ��ť���

	Font standard = new Font(null, Font.BOLD, 20);

	JButton queding = new JButton("�ҿ��úܺò�ȷ��");
	JButton queding2 = new JButton("�ҿ���һ�㲢ȷ��");
	JButton queding3 = new JButton("�ҿ��ò��ò�ȷ��");

	public MyFrameEnd(double gpa, int error, Point point) {
		this.setTitle("GPA calculator 4.0");
		this.setSize(330, 330);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		// this.setLocationRelativeTo(null);
		this.setLocation(point.x, point.y);
		this.setLayout(new GridLayout(4, 1));

		inf.setFont(standard);
		inf2.setFont(standard);
		inf3.setFont(standard);

		if (error == 404) {
			this.inf.setText("������Ϣ��ʽ����ȷ����Ϣ������");
			this.inf2.setText("������0~100����������ѡˮƽ��");
			this.inf3.setText("                  ����������");
		} else {
			String myGPA = String.valueOf(gpa);
			if (myGPA.length() >= 10) {
				myGPA = myGPA.substring(0, 10);
			} else {
				int num = myGPA.length();
				for (; num < 10; num++) {
					myGPA = myGPA + "0";
				}
			}
			double gpaAcurate = Calculator.roundTo(gpa);
			String myGPAAcurate = String.valueOf(gpaAcurate);
			if (myGPAAcurate.length() == 3) {
				myGPAAcurate = myGPAAcurate + "0";
			}
			int rank = Calculator.rank(gpaAcurate);
			if (rank < 5)
				rank = 5;
			this.inf.setText("���GPAΪ��" + myGPAAcurate);
			usefulGPA = myGPAAcurate;
			this.inf2.setText("��ľ�ȷGPAΪ��" + myGPA);
			this.inf3.setText("���Ԥ���꼶����Ϊ����" + (rank - 4) + "~" + rank + "��");
		}
		// this.gpa.setFont(standard);
		this.add(inf);
		this.add(inf2);
		this.add(inf3);
		// this.add(this.gpa);
		this.add(p);
		p.setLayout(new GridLayout(3, 1));
		queding.addActionListener(mal3);
		p.add(queding);
		queding2.addActionListener(mal3);
		p.add(queding2);
		queding3.addActionListener(mal3);
		p.add(queding3);
	}
}

class myActionListener3 implements ActionListener {
	private MyFrameEnd mfe;

	public myActionListener3(MyFrameEnd mfe) {
		this.mfe = mfe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mfe.setVisible(false);

		// String[] args=new String[]{mfe.getLocation().toString()};
		// Main.main(args);
		if (mfe.usefulGPA == null) {
			MyFrameRank mfr = new MyFrameRank(mfe.getLocation().x, mfe.getLocation().y, mfe.usefulGPA, true, "");
			mfr.setVisible(true);
		} else {
			MyFrameRank mfr = new MyFrameRank(mfe.getLocation().x, mfe.getLocation().y, mfe.usefulGPA, false, "");
			mfr.setVisible(true);
		}
	}
}