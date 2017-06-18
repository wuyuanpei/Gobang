package control;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;

import entity.*;
import listener.SnakeListener;
import view.GamePanel;
import view.OperatorPanel;

/**
 * 控制器类
 * 
 * @author lenovo
 *
 */
public class Controller extends KeyAdapter implements SnakeListener {
	private Snake snake1, snake2;
	private Food food;
	private Ground ground;
	private Timer timer;
	private GamePanel gamePanel;
	private JFrame frame;
	private OperatorPanel operatorPanel;

	public Controller(Snake snake1, Snake snake2, Food food, Ground ground, Timer timer, GamePanel gamePanel,
			JFrame frame, OperatorPanel operatorPanel) {
		this.snake1 = snake1;
		this.snake2 = snake2;
		this.food = food;
		this.ground = ground;
		this.timer = timer;
		this.gamePanel = gamePanel;
		this.frame = frame;
		this.operatorPanel = operatorPanel;
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		// System.out.println("得到信息");
		int keycode = e.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_I:
			snake2.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_W:
			snake1.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_K:
			snake2.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_S:
			snake1.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_L:
			snake2.changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_D:
			snake1.changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_J:
			snake2.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_A:
			snake1.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_B:
			operatorPanel.start();
			break;
		case KeyEvent.VK_P:
			operatorPanel.pause();
			break;
		case KeyEvent.VK_R:
			operatorPanel.restart();
			break;
		case KeyEvent.VK_E:
			operatorPanel.exit();
			break;
		// case KeyEvent.VK_E:
		// operatorPanel.exit();
		// break;
		// case KeyEvent.VK_J:
		// operatorPanel.rank();
		// break;
		case KeyEvent.VK_H:
			operatorPanel.help();
			break;
		}
	}

	private boolean thread =true;

	@Override
	public void snakeMoved(Snake snake1, Snake snake2) {
//		System.out.println(Thread.currentThread().getName());
		// manifest snake, food and ground
		if (snake1.getLife() && snake2.getLife())
			// gamePanel.requestFocus();
			gamePanel.display(snake1, snake2, food, ground, timer);
		if (snake1 == this.snake1) {
//			System.out.println(Thread.currentThread().getName());
			thread = true;	
		}
		if (snake1 == this.snake2) {
			while (thread){
//				System.out.println("kkk:"+Thread.currentThread().getName());
				Thread.yield();
			} 
			return;
		}
//		// 只使用1的判断 避免线程混乱
//		if (snake1 == this.snake2){
//			return;
//		}
		// System.out.println("判断蛇是否碰到身体，食物，障碍物");
//		System.out.println("1");
//		if (timer.getSeconds() == 59) {
//			snake1.setLife(false);
//			snake2.setLife(false);
//			endingFrame(true, true);
//			thread = false;
//			return;
//		}
		food.isEatBySnake(snake1, 1);

		if ((!food.foodCondition) && food.refresh) {
			snake1.eatFood(food);
			// produce new food
			Point p;
			do {
				p = ground.getPoint();
			} while (snake1.filterBody(p) || snake2.filterBody(p));
			food.addFood(p);
			food.foodCondition = true;
		}

		food.isEatBySnake(snake2, 2);

		if ((!food.foodCondition) && food.refresh) {
			snake2.eatFood(food);
			// produce new food
			Point p;
			do {
				p = ground.getPoint();
			} while (snake1.filterBody(p) || snake2.filterBody(p));
			food.addFood(p);
			food.foodCondition = true;
		}
		int sameTime = 0;
		// 如果蛇碰上障碍物打开结束窗体
		if (ground.isEatBySnake(snake1)) {
			// System.out.println("判定撞到墙...");
			snake1.setLife(false);
			snake2.setLife(false);
			// System.out.println("设置死亡...");
			// endingFrame(false,true);
			// return;
			sameTime++;
		}
		if (ground.isEatBySnake(snake2)) {
			// System.out.println("判定撞到墙...");
			snake1.setLife(false);
			snake2.setLife(false);
			// System.out.println("设置死亡...");
			// endingFrame(true,false);
			// return;
			sameTime -= 2;
		}
		if (sameTime == 1) {
			endingFrame(false, true);
			thread = false;
			return;
		}
		if (sameTime == -2) {
			endingFrame(true, false);
			thread = false;
			return;
		}
		if (sameTime == -1) {
			endingFrame(false, false);
			thread = false;
			return;
		}

		int sameTime2 = 0;
		// 如果蛇碰上自己打开结束窗体
		if (snake1.isEatSelf() || snake2.filterBody(snake1.getHead())) {
			snake1.setLife(false);
			snake2.setLife(false);
			// endingFrame(false,true);
			// return;
			sameTime2++;
		}
		if (snake2.isEatSelf() || snake1.filterBody(snake2.getHead())) {
			snake1.setLife(false);
			snake2.setLife(false);
			// endingFrame(true,false);
			// return;
			sameTime2 -= 2;
		}

		if (sameTime2 == 1) {
			endingFrame(false, true);
			thread = false;
			return;
		}
		if (sameTime2 == -2) {
			endingFrame(true, false);
			thread = false;
			return;
		}
		if (sameTime2 == -1) {
			endingFrame(false, false);
			thread = false;
			return;
		}

	

		if (!food.refresh) {
			food.refresh = true;
			Point p;
			do {
				p = ground.getPoint();
			} while (snake1.filterBody(p) || snake2.filterBody(p));
			food.addFood(p);
		}
		thread = false;
//		if (snake1.getLife() && snake2.getLife())
//			// gamePanel.requestFocus();
//			gamePanel.display(snake1, snake2, food, ground, timer);
	}

	/**
	 * start the game
	 */
	public synchronized void startGame() {
		snake1.start();// move the snake
		snake2.start();
		food.addFood(ground.getPoint());
	}

	public synchronized void endingFrame(boolean snakeALive, boolean snakeBLive) {
		new JFrame() {
			private static final long serialVersionUID = 1L;
			{
				frame.setEnabled(false);
				this.setSize(350, 220);
				this.setResizable(false);
				this.setTitle("游戏结束");
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel p = new JPanel();
				p.setBackground(Color.LIGHT_GRAY);
				this.add(p);
				p.setLayout(new GridLayout(5, 1));
				JPanel gameOver = new JPanel();
				gameOver.add(new JLabel("Game Over"));
				p.add(gameOver);

				JLabel result1 = new JLabel("                                                     平手!");
				result1.setForeground(Color.RED);

				JLabel result2 = new JLabel("                                               小黄蛇赢了!");
				result2.setForeground(Color.RED);

				JLabel result3 = new JLabel("                                               小红蛇赢了!");
				result3.setForeground(Color.RED);

				String die = "   <死了>";
				String live = "   <活着>";

				// 如果都活着的话
				if (snakeALive && snakeBLive) {
					if (food.scoreA == food.scoreB)
						p.add(result1);
					if (food.scoreA > food.scoreB)
						p.add(result2);
					if (food.scoreA < food.scoreB)
						p.add(result3);
					p.add(new JLabel("   小黄蛇吃了: " + food.scoreA + " 个食物" + live));
					p.add(new JLabel("   小红蛇吃了: " + food.scoreB + " 个食物" + live));

				} else if ((!snakeALive) && (!snakeBLive)) {
					if (food.scoreA == food.scoreB)
						p.add(result1);
					if (food.scoreA > food.scoreB)
						p.add(result2);
					if (food.scoreA < food.scoreB)
						p.add(result3);
					p.add(new JLabel("   小黄蛇吃了: " + food.scoreA + " 个食物" + die));
					p.add(new JLabel("   小红蛇吃了: " + food.scoreB + " 个食物" + die));
				} else {
					if (snakeALive) {
						p.add(result2);
						p.add(new JLabel("   小黄蛇吃了: " + food.scoreA + " 个食物" + live));
						p.add(new JLabel("   小红蛇吃了: " + food.scoreB + " 个食物" + die));
					} else {
						p.add(result3);
						p.add(new JLabel("   小黄蛇吃了: " + food.scoreA + " 个食物" + die));
						p.add(new JLabel("   小红蛇吃了: " + food.scoreB + " 个食物" + live));
					}
				}

				// System.out.println("创建新的按钮");
				JButton restart = new JButton("重新开始");
				// JButton rankIn = new JButton("提交分数");
				// restart.grabFocus();
				JPanel buttons = new JPanel();
				p.add(buttons);
				buttons.add(restart);
				// buttons.add(rankIn);
				// rankIn.addActionListener(new ActionListener() {
				//
				// @Override
				// public void actionPerformed(ActionEvent e) {
				// setVisible(false);
				// JFrame set = new JFrame();
				// set.setVisible(true);
				// set.setSize(350, 220);
				// set.setResizable(false);
				// set.setTitle("提交分数");
				// set.setLayout(new GridLayout(5, 1));
				// set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// set.setLocation(getLocation().x, getLocation().y);
				// JTextField name = new JTextField(10);
				//
				// JPanel p1 = new JPanel();
				// p1.add(new JLabel("记录你的分数吧！"));
				// set.add(p1);
				//
				// JPanel p2 = new JPanel();
				// p2.setBackground(Color.LIGHT_GRAY);
				// set.add(p2);
				// p2.add(new JLabel("你的名字:"));
				// p2.add(name);
				//
				// JPanel p3 = new JPanel();
				//
				// p3.add(new JLabel(" 你的游戏分数：" + food.getScore()));
				// p3.setBackground(Color.LIGHT_GRAY);
				// set.add(p3);
				//
				// JPanel p4 = new JPanel();
				// p4.setBackground(Color.LIGHT_GRAY);
				// p4.add(new JLabel("按”确定提交”查看当前排行榜！"));
				// set.add(p4);
				// name.setFocusable(true);
				// JPanel p5 = new JPanel();
				// JButton quedingtijiao = new JButton("确定提交");
				// set.add(p5);
				// p5.add(quedingtijiao);
				// quedingtijiao.addActionListener(new ActionListener() {
				//
				// @Override
				// public void actionPerformed(ActionEvent e) {
				// String information = null;
				// File f = new File("D:\\Program
				// Files\\贪吃蛇2.0\\GreedySnake.dat");
				// try {
				// // 创建文件夹
				// File f2 = new File("D:\\Program Files\\贪吃蛇2.0");
				// information = "";
				// // 如果不存在创造文件夹
				// if (!f2.exists()) {
				// f2.mkdirs();
				// }
				// // 在文件夹下创建文件
				//
				// if (f.exists()) {
				// // 创建输入流
				// FileInputStream fis = new FileInputStream(f);
				// // 读取文件
				// byte[] b = new byte[1024];
				// int bytes;
				// while ((bytes = fis.read(b, 0, b.length)) != -1) {
				// information = information.concat(new String(b, 0, bytes));
				// }
				// fis.close();
				// }
				// // 以经存在的文件
				// FileOutputStream fos = new FileOutputStream(f);
				// String names = name.getText();
				// names += "k";
				// if (names.equals("k"))
				// names = "匿名";
				// else
				// names = names.substring(0, names.length() - 1);
				//
				// String all = names + "\t分数:" + food.getScore() + "\n" +
				// information;
				//
				// // 最新记录写在最上面再跟上之前的inf信息
				// byte[] bs = all.getBytes();
				// fos.write(bs);
				// fos.flush();
				// fos.close();
				// // System.out.println("写入成功");
				// } catch (Exception e1) {
				// e1.printStackTrace();
				// }
				// operatorPanel.rank();
				//
				// set.setVisible(false);
				// frame.setEnabled(true);
				// // restart.grabFocus();
				// snake1.clearOrigin();
				// snake1.init(2);
				// snake2.clearOrigin();
				// snake2.init(2);
				// timer.init();
				// food.setScore(0);
				// food.refresh = false;
				//
				// if (!snake1.getLife()) {
				// snake1.setLife(true);
				// // snake.start();
				// }
				// if (!snake2.getLife()) {
				// snake2.setLife(true);
				// // snake.start();
				// }
				// }
				//
				// });
				// }
				//
				// });
				restart.addActionListener(new ActionListener() {
					@Override
					public synchronized void actionPerformed(ActionEvent e) {
						frame.setEnabled(true);
						// restart.grabFocus();
						snake1.clearOrigin();
						snake1.init(3);
						snake2.clearOrigin();
						snake2.init(3);
						timer.init();
						food.scoreA = 0;
						food.scoreB = 0;
						food.refresh = false;

						if (!snake1.getLife()) {
							snake1.setLife(true);
							// snake.start();
						}
						if (!snake2.getLife()) {
							snake2.setLife(true);
							// snake.start();
						}
						setVisible(false);
					}

				});
			}
		}.setVisible(true);
		// System.out.println("生成页面");

	}

}
