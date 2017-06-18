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
	JButton queding = new JButton("ȷ��");
	JButton guanli = new JButton("������");
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
		
		inf.setText("�����ĵ�\n��ӭʹ��GPA������4.0�汾��\n1)"
				+ "�����ı���������ſ�Ŀ��Ӧ�ĳɼ������ڸ�ѡ��ѡ��Ӧ��ˮƽ����H,H+,H-\n"
				+ "2)��������������0~100��������������0.5��С��������������\n3)�������"
				+ "�󰴻س���ת����һ���ı������һ���ı�����ת��ǿ����ѧ��"
				+ "���ĸ�ѡ��\n4)�ڸ�ѡ���ϻ��¼������л�ˮƽ���ڰ�ťλ�ð��ո����ѡ��"
				+ "\n5)����ҳ���ṩ���㵽��λС����GPAֵ����ȷGPAֵ������꼶����\n6)"
				+ "���ύ����ҳ���������ֺ��ύ���ɼ�¼����������Ը���ύ���ֱ�ӽ���\n7)������"
				+ "ҳ����֡�������Ϣ��ʽ����ȷ����Ϣ�������������ٴκ˶����������\n8"
				+ ")4.0�汾�ṩ������Ȩ�ޣ������߿���ֱ�Ӵ������ݲ��������ͨ��\n\n"
				+ "�����ߣ���Ԫ��");

	}
}