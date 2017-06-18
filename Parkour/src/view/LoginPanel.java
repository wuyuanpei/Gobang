package view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import global.Param;
import serialization.Identity;
import serialization.Reader;
import serialization.Writer;
import test.ParkourTest;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 2607264486146393893L;
	private ImageIcon loginBG = new ImageIcon(this.getClass().getResource("loginBG.jpg"));
	private AudioClip audio = Applet.newAudioClip(this.getClass().getResource("loginBGM.wav"));
	private boolean drawError,drawRight;
	private JTextField nameTextField = new JTextField(15);
	public LoginPanel(){
		audio.loop();
		this.add(new JLabel("�����û�����"));
		this.add(nameTextField);
		this.add(new JButton("��¼/ע��"){
			private static final long serialVersionUID = -2536746950547396642L;
			{
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable(){
							@Override
							public void run() {
								String name = nameTextField.getText();
								if(!name.matches("[0-9A-Za-z]{3,15}")){
									drawError = true;
									paintInf();
									return;
								}
								drawError = false;
								drawRight = true;
								nameTextField.setEnabled(false);
								setEnabled(false);
								paintInf();
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								Identity id = Reader.getTheIdentity(name);
								ParkourTest.id = id;
								if(ParkourTest.id.getLastDay()!=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
									ParkourTest.id.setPower(ParkourTest.id.getPower()+10);
									ParkourTest.id.setLastDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
									Writer.recordTheIdentity(ParkourTest.id);
								}
								audio.stop();
								drawRight = false;
								setEnabled(true);
								nameTextField.setEnabled(true);
								ParkourTest.initGame();
								ParkourTest.loginFrame.setVisible(false);
							}
						}).start();
					}		
				});
			}
		});
	}
	public void paintInf(){
		repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		loginBG.paintIcon(null,g,0,0);
		if(drawError){
			g.setColor(Color.RED);
			g.setFont(new Font("",Font.BOLD,15));
			g.drawString("�û���������3-15���ַ��ڣ�ΪӢ�Ļ�����",200,50);
		}
		if(drawRight){
			g.setColor(Color.BLACK);
			g.setFont(new Font("",Font.BOLD,15));
			g.drawString("��Ϣ��֤�У����Ե�...", 200,50);
		}
		g.setColor(Color.RED);
		g.setFont(new Font("�����п�",Font.BOLD,50));
		g.drawString("����", 25,100);
		g.drawString("ע��", 325,100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("������κ",Font.ITALIC,20));
		g.drawString(Param.FRAME_TITLE, 118,150);
		g.setFont(new Font("������κ",Font.BOLD,20));
		g.drawString("��ӭ����                          ��", 25,150);
		g.drawString("       �������������鵽ԭ֭", 25,185);
		g.drawString("ԭζ���ܿ���Ϸ��", 25,210);
		g.drawString("       ����ţţ���ƺơ���", 25,245);
		g.drawString("�罫��Ϊ��һ��Ӣ�ۣ�����", 25,270);
		g.drawString("Խ���ϰ�(w��up��)�����", 25,295);
		g.drawString("��(s��down��)���������", 25,320);
		g.drawString("(j��space��)��������(d", 25,345);
		g.drawString("��l��right��)��ÿ��Ӣ����", 25,370);
		g.drawString("��һ�޶��ļ��ܣ�(k��b��)", 25,395);
		g.drawString("��Ϸ��ʼǰ����Ҫ��һ����", 325,150);
		g.drawString("���˺ţ�ע�⣺���˺�ֻ��", 325,175);
		g.drawString("��̨������Ч��", 325,200);
		g.drawString("       �����Ϸ��ı���������", 325,235);
		g.drawString("�û����������û���������", 325,260);
		g.drawString("����Ϊע����һ�����û���", 325,285);
		g.drawString("���Ͽ�ʼ�ܿ�֮�ðɣ�", 325,310);
		g.drawString("�û���������3-15���ַ���", 325,345);
		g.drawString("ΪӢ�Ļ�����", 325,370);
	}
}
