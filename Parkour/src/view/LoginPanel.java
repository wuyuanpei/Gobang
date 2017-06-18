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
		this.add(new JLabel("本地用户名："));
		this.add(nameTextField);
		this.add(new JButton("登录/注册"){
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
			g.drawString("用户名必须在3-15个字符内，为英文或数字",200,50);
		}
		if(drawRight){
			g.setColor(Color.BLACK);
			g.setFont(new Font("",Font.BOLD,15));
			g.drawString("信息验证中，请稍等...", 200,50);
		}
		g.setColor(Color.RED);
		g.setFont(new Font("华文行楷",Font.BOLD,50));
		g.drawString("帮助", 25,100);
		g.drawString("注册", 325,100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("华文新魏",Font.ITALIC,20));
		g.drawString(Param.FRAME_TITLE, 118,150);
		g.setFont(new Font("华文新魏",Font.BOLD,20));
		g.drawString("欢迎来到                          ！", 25,150);
		g.drawString("       在这里您将体验到原汁", 25,185);
		g.drawString("原味的跑酷游戏！", 25,210);
		g.drawString("       洲神、牛牛、浩浩、豪", 25,245);
		g.drawString("哥将成为第一波英雄，带您", 25,270);
		g.drawString("越过障碍(w或up键)、躲过", 25,295);
		g.drawString("鸟(s或down键)、消灭怪兽", 25,320);
		g.drawString("(j或space键)、冲锋加速(d", 25,345);
		g.drawString("、l或right键)。每个英雄有", 25,370);
		g.drawString("独一无二的技能！(k或b键)", 25,395);
		g.drawString("游戏开始前您需要有一个本", 325,150);
		g.drawString("地账号，注意：该账号只在", 325,175);
		g.drawString("本台电脑有效！", 325,200);
		g.drawString("       请在上方文本框中输入", 325,235);
		g.drawString("用户名，若该用户名不存在", 325,260);
		g.drawString("则视为注册了一个新用户！", 325,285);
		g.drawString("马上开始跑酷之旅吧！", 325,310);
		g.drawString("用户名必须在3-15个字符内", 325,345);
		g.drawString("为英文或数字", 325,370);
	}
}
