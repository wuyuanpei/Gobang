package test;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import entity.Obstacle;
import entity.Player;
import entity.Timer;
import global.Param;
import serialization.HighestSerializer;
import serialization.Identity;
import serialization.Writer;
import view.GamePanel;
import view.LoginPanel;
import view.PreparePanel;
import view.PrepreparePanel;

public class ParkourTest {

	public static final ArrayList<Obstacle> OBSTACLES = new ArrayList<>();
	public static final ArrayList<Player> PLAYERS = new ArrayList<>();
	private static Image img = Toolkit.getDefaultToolkit().getImage(ParkourTest.class.getResource("parkourIcon.png"));
	public static JFrame preprepareFrame = new JFrame();
	public static JFrame frame = new JFrame() {
		private static final long serialVersionUID = 5001837831919714291L;
		{
			this.setIconImage(img);
		}
	};
	public static JFrame prepareFrame = new JFrame() {
		private static final long serialVersionUID = -5564322970476621648L;
		{
			this.setIconImage(img);
		}
	};
	public static JFrame loginFrame = new JFrame() {
		private static final long serialVersionUID = 306078233773000718L;
		{
			this.setIconImage(img);
		}
	};
	// LIU,NIU,FU,ZHOU
	public static Player player = Player.LIU;

	public static Identity id;

	public static GamePanel gamePanel = new GamePanel();

	public static PreparePanel preparePanel;
	
	public static PrepreparePanel prepreparePanel = new PrepreparePanel();

	public static LoginPanel loginPanel = new LoginPanel();

	public static Controller controller = new Controller();

	public static int SPEED = Param.SLOW_SPEED;

	public static boolean pause = false;

	public static Timer timer = new Timer();

	public static int score = 0;

	public static int energy;

	public static AudioClip BGM = Applet.newAudioClip(ParkourTest.class.getResource("BGM1.wav"));

	public static AudioClip prepareBGM = Applet.newAudioClip(ParkourTest.class.getResource("prepareBGM.wav"));

	public static void main(String[] args) {
		preprepareFrame();
		initLoginFrame();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginFrame.setVisible(true);
		preprepareFrame.setVisible(false);
	}

	public static void initLoginFrame() {
		loginFrame.setTitle(Param.FRAME_TITLE);
		loginFrame.setSize(Param.WIDTH, Param.LENGTH);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.add(loginPanel);
		loginFrame.setResizable(false);
	}

	public static void initGame() {
		initPrepareFrame();
		prepareBGM.loop();
		prepareFrame.setVisible(true);
		pause = true;
		initFrame();// 窗体
		startGame();// 开始游戏
	}

	public static void playBGM() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				BGM.loop();
			}
		}).start();

	}

	public static void initPrepareFrame() {
		preparePanel = new PreparePanel();
		prepareFrame.setTitle(Param.FRAME_TITLE);
		prepareFrame.setSize(Param.WIDTH, Param.LENGTH);
		prepareFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prepareFrame.setLocationRelativeTo(null);
		prepareFrame.setResizable(false);
		prepareFrame.add(preparePanel);
	}

	public static void initFrame() {
		frame.setTitle(Param.FRAME_TITLE);
		frame.setSize(Param.WIDTH, Param.LENGTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(gamePanel);
		frame.addKeyListener(controller);
	}

	private static AudioClip over = Applet.newAudioClip(ParkourTest.class.getResource("over.wav"));
	private static AudioClip dead = Applet.newAudioClip(ParkourTest.class.getResource("dead.wav"));

	public static void startGame() {
		new Thread(new Runnable() {
			private void refreshParam() {
				score++;// 分数自增
				if (energy < player.getEnergy()) {// 能量递增
					if (SPEED == Param.FAST_SPEED)// 快速状态下再增加一点
						energy++;
					energy++;
				}
				Obstacle.decreaseDistance();
				gamePanel.repaint();
			}

			private int countDeath = 0;

			private void identifyDeath() {
				if (!gamePanel.getObstacle().identifyDeath()) {
					dead.play();
					Player.specialVisit = true;
					if (player.skill() && countDeath == 0) {
						Player.specialVisit = false;
						countDeath++;
						return;
					}
					Player.specialVisit = false;
					countDeath = 0;
					BGM.stop();
					modifyWrite();
					endFrame();
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					over.play();
				}
			}

			@Override
			public void run() {
				gamePanel.setObstacle(Obstacle.randomObstacle());// 获得第一个障碍物
				while (true) {
					refreshParam();
					try {
						Thread.sleep(SPEED);// 暂停要求的毫秒数
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (Obstacle.getDistance() <= -100) {// <=防止遗漏导致bug
						Obstacle.distanceToOrigin();
						gamePanel.setObstacle(Obstacle.randomObstacle());
					} // 障碍物回归原位并创建新的障碍物
					identifyDeath();
					while (pause) {
						Thread.yield();
					}
				}
			}
		}).start();
	}

	public static void modifyWrite() {
		id.setCoins(id.getCoins() + score / 100);
		id.setPower(id.getPower() - 1);
		if (id.getHighestScore() < score / 10) {
			id.setHighestScore(score / 10);
		}
		HighestSerializer.recordHighest(id);
		Writer.recordTheIdentity(id);
	}

	public static void endFrame() {
		frame.setEnabled(false);
		pause = true;
		JFrame endFrame = new JFrame("游戏结束") {
			private static final long serialVersionUID = -7533906648749129357L;
			{
				this.setIconImage(img);
			}
		};
		endFrame.setSize(300, 170);
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setResizable(false);
		endFrame.setLocationRelativeTo(null);
		endFrame.setBackground(Param.BACKGROUND_COLOR);
		endFrame.setLayout(new GridLayout(4, 1));
		JPanel p1 = new JPanel();
		endFrame.add(p1);
		p1.setBackground(Param.MARGIN_COLOR);
		p1.add(new JLabel("Game Over") {
			private static final long serialVersionUID = -6922109056076543466L;
			{
				this.setForeground(Color.RED);
				this.setFont(Param.ENDING_TITLE_FONT);

			}
		});
		JPanel p2 = new JPanel();
		p2.setBackground(Param.BACKGROUND_COLOR);
		endFrame.add(p2);
		p2.add(new JLabel("时间： " + timer.getMinutes() + "分" + timer.getSeconds() + "秒       金币： " + score / 100) {

			private static final long serialVersionUID = 9170252757037303732L;

			{
				this.setForeground(Param.FONT_COLOR);
			}
		});
		JPanel p3 = new JPanel();
		p3.setBackground(Param.BACKGROUND_COLOR);
		endFrame.add(p3);
		p3.add(new JLabel("分数： " + score / 10 + "       最高分： " + id.getHighestScore()) {

			private static final long serialVersionUID = 9170252757037303732L;

			{
				this.setForeground(Param.FONT_COLOR);
			}
		});
		JPanel p4 = new JPanel();
		p4.setBackground(Param.MARGIN_COLOR);
		endFrame.add(p4);
		p4.add(new JButton("请稍等(3s)...") {
			private static final long serialVersionUID = 1702110863261643654L;
			{
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						setEnabled(true);
						setText("确定");
					}
				}).start();
				this.setEnabled(false);
				this.setContentAreaFilled(false);
				this.setFocusable(false);
				this.setForeground(Param.FONT_COLOR);
				this.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						prepareBGM.loop();
						prepareFrame.setVisible(true);
						frame.setVisible(false);
						endFrame.setVisible(false);
						GamePanel.setStooping(false);
						SPEED = Param.SLOW_SPEED;
						energy = player.getEnergy();
						Obstacle.distanceToOrigin();
						gamePanel.setObstacle(Obstacle.randomObstacle());
						player.setSkillUsed(false);
					}
				});
			}
		});
		endFrame.setVisible(true);
	}

	public static void preprepareFrame(){
		preprepareFrame.setSize(450,275);
		preprepareFrame.setLocationRelativeTo(null);
		preprepareFrame.setIconImage(img);
		preprepareFrame.setResizable(false);
		preprepareFrame.add(prepreparePanel);
		preprepareFrame.setUndecorated(true);
		preprepareFrame.setVisible(true);
	}
}
