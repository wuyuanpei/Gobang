package test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.Controller;
import controller.Timer;
import entity.Block;
import entity.Ground;
import util.Global;
import util.Param;
import view.GamePanel;
import view.MarginPanel;
import view.PreparePanel;

public class TetrisTest {

	public static int score = 0;
	
	public static JButton helpButton = new JButton("�򿪰���(q)");

	public static Timer timer = new Timer();

	public static JFrame frame = new JFrame();
	
	public static JFrame helpFrame = new JFrame();
	
	public static JFrame prepareFrame = new JFrame();

	public static Ground ground = new Ground();

	public static Controller controller = new Controller(ground);

	public static GamePanel panel = new GamePanel(ground);
	
	public static MarginPanel marginPanel = new MarginPanel();
	
	public static PreparePanel preparePanel = new PreparePanel();

	public static boolean isAlive = true;

	public static Block nextBlock;
	
	public static String prepareText = "";
	
	public static void main(String args[]) {
		Thread.currentThread().setName("mainThread");
		 prepareFrame();
		bufferedSecond();
		initFrame();
		bufferedSecond();
		startGame();
		bufferedSecond();
		frame.setVisible(true);
		prepareFrame.setVisible(false);
	}
	public static void prepareFrame(){
		prepareFrame.setSize(450,275);
		prepareFrame.setLocationRelativeTo(null);
		URL url = TetrisTest.class.getResource("img.jpeg");
		Image icon = Toolkit.getDefaultToolkit().getImage(url);
		prepareFrame.setIconImage(icon);
		prepareFrame.setResizable(false);
		prepareFrame.add(preparePanel);
		prepareFrame.setUndecorated(true);
		prepareFrame.setVisible(true);
		changeText("��ȡҳ��ͼƬ...");
		bufferedSecond();
		changeText("��Ϸ׼����...");
	}
	public static void initFrame() {
		URL url = TetrisTest.class.getResource("icon.png");
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		frame.setIconImage(img);
		changeText("���ô���ͼ��...");
		frame.setTitle(Param.FRAME_TITLE);
		changeText("���ô�����Ŀ...");
		frame.setSize(Param.WIDTH * Param.SIZE + 130, Param.LENGTH * Param.SIZE + 32);
		changeText("���ô����С...");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		changeText("���ô�������...");
		frame.setLocationRelativeTo(null);
		changeText("���ô���λ��...");
		frame.setLayout(new BorderLayout());
		changeText("���ô��岼��...");
		frame.add(panel,BorderLayout.CENTER);
		frame.addKeyListener(controller);	
		frame.add(marginPanel,BorderLayout.EAST);
		changeText("�������óɹ�...");
	}
	public static void exitGame(){
		System.exit(0);
	}
	public static void openEndFrame() {
		
		JFrame endFrame = new JFrame();
		URL url = TetrisTest.class.getResource("icon.png");
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		endFrame.setIconImage(img);
		endFrame.setTitle("��Ϸ����");
		endFrame.setSize(300, 170);
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setResizable(false);
		endFrame.setLocationRelativeTo(null);
		endFrame.setLayout(new GridLayout(4, 1));

		JPanel p1 = new JPanel();
		p1.setBackground(Param.MARGIN_COLOR);
		JPanel p2 = new JPanel();
		p2.setBackground(Param.BACKGROUND_COLOR);
		JPanel p3 = new JPanel();
		p3.setBackground(Param.BACKGROUND_COLOR);
		JPanel p4 = new JPanel();
		p4.setBackground(Param.MARGIN_COLOR);
		endFrame.add(p1);
		endFrame.add(p2);
		endFrame.add(p3);
		endFrame.add(p4);
		JLabel endLabel = new JLabel("Game Over");
		endLabel.setFont(new Font("",Font.BOLD,20));
		endLabel.setForeground(Param.ENDING_TITLE_COLOR);
		JLabel timeLabel = new JLabel("ʱ�䣺" + TetrisTest.timer.getMinutes() + "��" + TetrisTest.timer.getSeconds() + "��");
		timeLabel.setForeground(Param.ENDING_FONT_COLOR);
		JLabel scoreLabel = new JLabel("������" + score);
		scoreLabel.setForeground(Param.ENDING_FONT_COLOR);
		p1.add(endLabel);
		p2.add(timeLabel);
		p3.add(scoreLabel);
		p4.add(new JButton("���¿�ʼ") {
			private static final long serialVersionUID = -5793755459619562628L;

			{
				this.setFocusable(false);
				this.setForeground(Param.ENDING_FONT_COLOR);
				this.setContentAreaFilled(false);
				addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						restartGame();
						endFrame.setVisible(false);
					}
				});
			}
		});
		endFrame.setVisible(true);
	}
	public static void openHelpFrame(){
		
		if(!helpButton.isEnabled()){
			helpFrame.setVisible(false);
			helpButton.setEnabled(true);
			return;
		}
		URL url = TetrisTest.class.getResource("icon.png");
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		helpFrame.setIconImage(img);
		helpButton.setEnabled(false);
		helpFrame.setTitle("����");
		helpFrame.setSize(400, 400);
		helpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		helpFrame.setResizable(false);
		helpFrame.setLocation(900, 100);
		helpFrame.setVisible(true);
		helpFrame.setLayout(new BorderLayout());
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Param.MARGIN_COLOR);
		JLabel title = new JLabel("����  for "+Param.FRAME_TITLE);
		title.setForeground(Param.ENDING_TITLE_COLOR);
		upPanel.add(title);
		helpFrame.add(upPanel,BorderLayout.NORTH);
		JTextArea inf = new JTextArea();
		inf.setBackground(Param.BACKGROUND_COLOR);
		inf.setEditable(false);
		inf.setText("          Welcome to the world of Tetris��������ռ��������һ����ʱ��ᱻ\n"
				+ "��ȥ�������鳬�������߽�ʱ��Ϸ���������Ͻǻ���ʾ��Ϸ���������Ͻ�\n"
				+ "����ʾ��Ϸʱ�䣡����ÿ����һ�£���1�֡���ȥһ�У���100�֣�\n\n"
				+ "��Ϸ����\tfor "+Param.FRAME_TITLE+"\n"
				+ "    wsad\t���Բ���������ƶ�\n"
				+ "    space\t������ʱ����ת����\n"
				+ "    c\t������������еķ���\n"
				+ "    q\t�򿪰����ĵ�\n"
				+ "    e\t�˳���Ϸ\n\n"
				+ "����Ȩ��\tfor "+Param.FRAME_TITLE+"\n"
				+ "          ����Ϸ����ο�������Ϸ����˹���飬�������롢���ΪRichard"
				+ "\nԭ����ͼ����Դ��www.easyicon.net����ʼҳ��ͼƬ��Դ��\n"
				+ "www.duitang.com    �����κ������bug����ϵ������");
		inf.setForeground(Param.ENDING_FONT_COLOR);
		helpFrame.add(inf,BorderLayout.CENTER);
		JPanel downPanel = new JPanel();
		downPanel.setBackground(Param.MARGIN_COLOR);
		downPanel.add(new JButton("ȷ�����ر�"){
			private static final long serialVersionUID = -4711560291803137827L;

			{
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setForeground(Param.ENDING_FONT_COLOR);
				addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						helpFrame.setVisible(false);
						helpButton.setEnabled(true);
					}					
				});
			}
		});
		helpFrame.add(downPanel, BorderLayout.SOUTH);
		
	}
	public static void restartGame() {
		score = 0;
		timer.init();
		TetrisTest.isAlive = true;
		ground.clearBackground();
		Controller.DISPLAY_EACH_TIME = Param.STANDAD_DISPLAY_EACH_TIME;
		panel.repaint();
		
	}

	public static void startGame() {
		new Thread() {
			public void run() {
				changeText("������Ϸ�߳�...");
				Thread.currentThread().setName("gameThread");
				int count = 0;
				while (true) {
					Block block = null;
					nextBlock = Block.getBlock(new Random().nextInt(Global.SHAPE_NUM));
					if(count==0)changeText("��ʼ��ģ��...");
					frame.requestFocus();
					if(count==0)changeText("�������󽹵�...");
					if(count==0)changeText("������Ϸ...");
					if(count==0)bufferedSecond();
					count++;
					while (isAlive) {
						frame.requestFocus();
						if (block != null){
							ground.downMove(block);
						}
						if (!ground.isMoving()) {
							ground.delete();
							ground.identifyLiving();
						}
						if (!ground.isMoving() && isAlive) {
							block = nextBlock;
							ground.addBlock(block);
     						int random = new Random().nextInt(Global.SHAPE_NUM);
							nextBlock = Block.getBlock(random);				
							ground.setMoving(true);
							controller.setCurrentBlock(block);
							marginPanel.repaint();
						}
						score++;
						panel.repaint();

						try {
							Thread.sleep((long) (Controller.DISPLAY_EACH_TIME * 1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					openEndFrame();
					while (!isAlive) {
						Thread.yield();
					}
				}
			}
		}.start();
	}
	public static void changeText(String text){
		prepareText = text;
		preparePanel.repaint();
		bufferedSecond(150);
	}
	public static void bufferedSecond(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void bufferedSecond(){
		bufferedSecond(750);
	}
}
