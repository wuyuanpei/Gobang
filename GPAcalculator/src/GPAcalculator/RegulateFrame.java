package GPAcalculator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegulateFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JLabel ip=new JLabel("ip��ַ:");
	JLabel port=new JLabel("�˿�:");
	JTextField ipt=new JTextField(10);
	JTextField portt=new JTextField(10);
	JTextArea inf = new JTextArea(300,300);
	String content ="";
	JButton queding = new JButton("ȷ��");
	JButton shuaxin = new JButton("���ҳ��");
	JButton shuchu = new JButton("���Ӳ����");
	JButton shuru = new JButton("���Ӳ�����");
	JPanel north = new JPanel();
	JPanel buttons = new JPanel();
	public RegulateFrame(int x, int y) {
		this.setTitle("GPA calculator 4.0");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocation(x,y);
		this.setLayout(new BorderLayout());
		this.add(north,BorderLayout.NORTH);
		north.add(ip);
		north.add(ipt);
		north.add(port);
		north.add(portt);
		this.add(inf, BorderLayout.CENTER);
		inf.setFocusable(false);
		this.add(buttons, BorderLayout.SOUTH);
		buttons.add(queding);
		buttons.add(shuaxin);
		buttons.add(shuchu);
		buttons.add(shuru);
		
		queding.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						RegulateFrame.this.setVisible(false);
			}
		});
		shuaxin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
						new RegulateFrame(RegulateFrame.this.getLocation().x,RegulateFrame.this.getLocation().y).setVisible(true);
						RegulateFrame.this.setVisible(false);
			}
		});
		shuchu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							content="��������......\n"+content;
							inf.setText(content);
							Socket s = new Socket(ipt.getText(),Integer.valueOf(portt.getText()));
							content="���������ӳɹ�\n"+content;
							inf.setText(content);
							OutputStream out = s.getOutputStream();
							File f = new File("D:\\Program Files\\GPAcalculator\\GPA.RECORD");
							if(!f.exists()){
								out.write(new String("").getBytes());
							}else{
								FileInputStream in = new FileInputStream(f);
								byte[] bs = new byte[1024];
								int bytes;
								while((bytes=in.read(bs, 0, bs.length))!=-1){
									out.write(bs, 0, bytes);
								}
								in.close();
							}
							content = "�ļ�����ɹ�\n"+content;
							inf.setText(content);
							out.close();
							s.close();
						} catch (Exception e) {
							content="���Ӵ������������������ĵ�ַ\n"+content;
							inf.setText(content);
							
						}
					}
					
				}).start();
			}
			
		});
		shuru.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							content="��������......\n"+content;
							inf.setText(content);
							Socket s = new Socket(ipt.getText(),Integer.valueOf(portt.getText()));
							content="���������ӳɹ�\n"+content;
							inf.setText(content);
							File f = new File("D:\\Program Files\\GPAcalculator\\GPA.RECORD");
							InputStream inn = s.getInputStream();
							FileOutputStream o = new FileOutputStream(f);
							byte[] by = new byte[1024];
							int bytey;
							while((bytey=inn.read(by, 0, by.length))!=-1){
								o.write(by, 0, bytey);
							}
							content = "�ļ����ճɹ�\n"+content;
							inf.setText(content);
							inn.close();
							o.close();
							s.close();
						} catch (Exception e) {
							content="���Ӵ������������������ĵ�ַ\n"+content;
							inf.setText(content);
							
						}
					}
					
				}).start();
			}
			
		});
	}
}
