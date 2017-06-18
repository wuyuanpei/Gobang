package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Listener;
import entity.Player;
import view.GamePanel;
import view.PrepreparePanel;

public class FightingGameTest {
	public static boolean operatingComputer;// false台式机，true笔记本

	public static JFrame frame = new JFrame();
	public static JFrame choosingFrame = new JFrame("游戏设置");
	public static GamePanel gamePanel = new GamePanel();

	public static boolean pause = false;

	public static void main(String[] args) {
		preprepareFrame();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		preprepareFrame.setVisible(false);
		choosingFrame();
		initFrame();
		// frame.setVisible(true);
		startGame();
		winningListener();

	}

	public static void AI() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Player.FU.energy = 400;
					if (new Random().nextInt(10) % 6 == 0)
						Player.FU.shieldOn = true;
					else
						Player.FU.shieldOn = false;
					if (new Random().nextInt(10) % 8 == 0)
						Player.FU.skill3();
					if (new Random().nextInt(10) % 9 == 0)
						Player.FU.skill2();
					if (new Random().nextInt(10) % 7 == 0) {
						Player.FU.skill1On = true;
						Player.FU.skill1(Player.LIU);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Player.FU.skill1On = false;
					}
					Player.FU.addBall();
					if (new Random().nextInt(10) % 3 == 0)
						Player.FU.throwBall(Player.LIU);
					Player.FU.leftMove = false;
					Player.FU.rightMove = false;
					if (new Random().nextInt(10) % 2 == 0)
						Player.FU.rightMove = true;
					else
						Player.FU.leftMove = true;
				}

			}

		}).start();
	}

	public static JFrame preprepareFrame = new JFrame();
	private static Image img = Toolkit.getDefaultToolkit()
			.getImage(FightingGameTest.class.getResource("snowProgramIcon.png"));
	public static ImageIcon choosingBG = new ImageIcon(FightingGameTest.class.getResource("choosingBG.jpg"));

	public static void preprepareFrame() {
		preprepareFrame.setSize(450, 275);
		preprepareFrame.setLocationRelativeTo(null);
		preprepareFrame.setIconImage(img);
		preprepareFrame.setResizable(false);
		preprepareFrame.add(new PrepreparePanel());
		preprepareFrame.setUndecorated(true);
		preprepareFrame.setVisible(true);
	}

	public static void choosingFrame() {
		choosingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choosingFrame.setSize(500, 350);
		choosingFrame.setLocationRelativeTo(null);
		choosingFrame.setIconImage(img);
		choosingFrame.setResizable(false);
		choosingFrame.add(new JPanel() {
			private static final long serialVersionUID = -2524642424680043652L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				choosingBG.paintIcon(null, g, 0, 0);
			}

			{
				this.setLayout(new GridLayout(1, 2));
				this.add(new JButton() {
					private static final long serialVersionUID = 1L;

					{
						this.setContentAreaFilled(false);
						this.setBorderPainted(false);
						this.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								choosingFrame.setVisible(false);
								frame.setVisible(true);
								AI();
							}

						});
					}
				});
				this.add(new JButton() {
					private static final long serialVersionUID = 1L;

					{
						this.setContentAreaFilled(false);
						this.setBorderPainted(false);
						this.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								operatingComputer = true;
								choosingFrame.setVisible(false);
								frame.setVisible(true);
								AI();
							}

						});
					}
				});
			}

		});
		choosingFrame.setUndecorated(true);
		choosingFrame.setVisible(true);
	}

	public static Listener listener = new Listener();

	public static void initFrame() {
		frame.setIconImage(img);
		frame.addKeyListener(listener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = d.width;
		int y = d.height;
		if (x > 1366)
			x = 1366;
		if (y > 768)
			y = 768;
		frame.setSize(x, y);
		frame.setResizable(false);
		frame.add(gamePanel);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
	}

	public static void startGame() {
		Player.moving();
	}

	public static void winningListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (Player.FU.blood <= 0 || Player.LIU.blood <= 0) {
						frame.setEnabled(false);
						JFrame end = new JFrame() {
							private static final long serialVersionUID = 1L;
							{
								JFrame the = this;
								this.setSize(300, 175);
								this.setLocationRelativeTo(null);
								this.setUndecorated(true);
								this.addKeyListener(new KeyAdapter() {
									@Override
									public void keyPressed(KeyEvent e) {
										if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
											System.exit(0);
									}
								});
								this.add(new JPanel() {
									private static final long serialVersionUID = -5262642900320167670L;

									@Override
									public void paintComponent(Graphics g) {
										super.paintComponent(g);
										new ImageIcon(GamePanel.class.getResource("frame.jpg")).paintIcon(null, g, 0,
												0);
										;
									}

									{
										this.setBackground(new Color(121, 205, 205));
										this.add(new JLabel("     GameOver     ") {
											private static final long serialVersionUID = 8285149266102967466L;
											{
												this.setFont(new Font("黑体", Font.PLAIN, 30));
												this.setForeground(Color.RED);
											}
										});
										this.add(new JLabel("恭喜..........") {
											private static final long serialVersionUID = -3312679130188185876L;
											{
												this.setFont(new Font("黑体", Font.PLAIN, 30));
												this.setForeground(Color.BLACK);
											}
										});
										this.add(new JLabel() {
											private static final long serialVersionUID = -3312679130188185876L;
											{
												this.setFont(new Font("黑体", Font.PLAIN, 30));
												this.setForeground(Color.BLACK);

												if (Player.LIU.blood <= 0) {
													this.setText("豪哥获得胜利！");
													gamePanel.whoWins = false;
												} else {
													this.setText("洲神获得胜利！");
													gamePanel.whoWins = true;
												}
												gamePanel.drawResult = true;
												gamePanel.repaint();
											}
										});
										this.add(new JButton() {
											private static final long serialVersionUID = 1744565578250410146L;
											{
												this.setText("重新开始");
												this.setFont(new Font("黑体", Font.PLAIN, 20));
												this.setFocusable(false);
												this.addActionListener(new ActionListener() {
													@Override
													public void actionPerformed(ActionEvent e) {
														gamePanel.drawResult = false;
														frame.setEnabled(true);
														Player.reset();
														winningListener();
														the.setVisible(false);
													}

												});
											}
										});
										this.add(new JButton() {
											private static final long serialVersionUID = 1744565578250410146L;
											{
												this.setText("退出游戏");
												this.setFont(new Font("黑体", Font.PLAIN, 20));
												this.setFocusable(false);
												this.addActionListener(new ActionListener() {
													@Override
													public void actionPerformed(ActionEvent e) {
														System.exit(0);
													}

												});
											}
										});
									}
								});
							}
						};
						end.setVisible(true);
						break;
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

}
