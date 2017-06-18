package view;

import java.awt.BorderLayout;
//import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import entity.Food;
import entity.Snake;
import entity.Timer;

public class OperatorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton start, pause, restart, help, exit;
	private Snake snake1, snake2;
	private Food food;
	private Timer timer;

	public OperatorPanel(Snake snake1, Snake snake2, Food food, Timer timer,GamePanel gamePanel) {
		this.snake1 = snake1;
		this.snake2 = snake2;
		this.food = food;
		this.timer = timer;
		start = new JButton("开始(B)");
		pause = new JButton("暂停(P)");
		restart = new JButton("重新开始(R)");
		// rank = new JButton("记录(J)");
		exit = new JButton("退出(E)");
		help = new JButton("帮助(H)");

		this.setLayout(new FlowLayout());
		this.add(start);
		this.add(pause);
		this.add(restart);
		// this.add(rank);
		this.add(exit);
		this.add(help);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.requestFocus();
				start();
			}
		});
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.requestFocus();
				restart();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				help();
			}
		});
		// rank.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// rank();
		// }
		//
		// });
	}

	public void start() {
		// 有一个死了那么两个都死了
		if (!snake1.getLife()) {
			timer.startTiming();
			snake1.setLife(true);
			snake2.setLife(true);
		}
	}

	public void pause() {
		// 有一个活着那么两个都活着
		if (snake1.getLife()) {
			timer.stopTiming();
			snake1.setLife(false);
			snake2.setLife(false);
		}
	}

	public void restart() {
		timer.init();
		food.scoreA = 0;
		food.scoreB = 0;
		food.refresh = false;
		snake1.clearOrigin();

		snake2.clearOrigin();
		snake1.init(3);
		snake2.init(3);

		if (!snake2.getLife()) {
			snake2.setLife(true);
		}
		if (!snake1.getLife()) {
			snake1.setLife(true);
		}
	}

	public void exit() {
		System.exit(0);
	}

	public void help() {
		if (!help.isEnabled())
			return;
		new JFrame() {
			private static final long serialVersionUID = 1L;
			{
				help.setEnabled(false);
				this.setTitle("帮助");
				this.setSize(405, 600);
				this.setLocation(950, 75);
				this.setResizable(false);
				this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

				JTextArea inf = new JTextArea();
				JButton okay = new JButton("确定并退出");

				this.setLayout(new BorderLayout());
				this.add(inf, BorderLayout.CENTER);
				this.add(okay, BorderLayout.SOUTH);

				inf.setFocusable(false);
				inf.setText(new String("游戏帮助\t" + "    欢迎来到贪吃蛇无尽版！\n"
						+ "            在这里，你需要和你的对手斗智斗勇！只有撞到对手身体才会死亡！\n    wsad\"可以控制黄色蛇的上下左右移动\n    \"ikjl\"可以控制粉红色蛇的上下左右移动"
						+ "\n    蛇为粉红和黄色，食物为橙色，土地为淡黄色\n" + "    当蛇碰到对手时，游戏结束\n"
						+ "    暂停(P)：停止游戏计时    开始(B)：恢复游戏\n    重新开始(R)：初始化游戏    退出(E)：退出游戏\n    帮助(H)：打开本窗口\n\n"
						+ "bug问题\tfor贪吃蛇2.3\n" + "重新开始游戏或者游戏结束后重新开始游戏有一定几率会导致无法操控游戏\n的问题已经得到修复啦！"
						+ "点击本游戏窗口即可操纵游戏！若有其他bug，请\n联系 11(2) 吴元培\n\n" + "开发权限\tfor贪吃蛇2.3\n"
						+ "Food,Ground,Snake,Global,GamePanel,SnakeGameTest,Controller类与\n"
						+ "SnakeListener接口中部分方法以及游戏框架构建代码来自于“我赢职场\n"
						+ "java教程”曹雪松老师;Timer,OperatorPanel类以及Food,Snake,Global,\n"
						+ "SnakeGameTest,Controller类中部分方法为 11(2) 吴元培原创！\n\n"));

				okay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						help.setEnabled(true);
						setVisible(false);
					}
				});
			}
		}.setVisible(true);

	}
	// public void rank(){
	// if(!rank.isEnabled()) return;
	// JFrame rankFrame = new JFrame();
	// JTextArea inf = new JTextArea();
	//
	// Container c =rankFrame.getContentPane();
	// JScrollPane sp =new JScrollPane(inf);
	// sp.doLayout();
	//
	// rankFrame.setVisible(true);
	// rankFrame.setSize(405,600);
	// rankFrame.setLocation(10, 75);
	// rankFrame.setTitle("历史记录");
	// rankFrame.setResizable(false);
	// rank.setEnabled(false);
	// rankFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	//
	// JButton okay = new JButton("确定并退出");
	//
	// rankFrame.setLayout(new BorderLayout());
	// rankFrame.add(sp, BorderLayout.CENTER);
	// c.add(okay, BorderLayout.SOUTH);
	// inf.setFocusable(false);
	//
	// try {
	// // 创建文件夹
	// File f2 = new File("D:\\Program Files\\贪吃蛇2.2");
	// String information="";
	// // 如果不存在创造文件夹
	// if (!f2.exists()) {
	// f2.mkdirs();
	// }
	// // 在文件夹下创建文件
	// File f = new File("D:\\Program Files\\贪吃蛇2.2\\GreedySnake.dat");
	// if(!f.exists()){
	// inf.setText("还没有历史记录！\tfor贪吃蛇2.1");
	// }
	// if(f.exists()){
	// // 创建输入流
	// FileInputStream fis = new FileInputStream(f);
	// // 读取文件
	// byte[] b = new byte[1024];
	// int bytes;
	// while ((bytes = fis.read(b, 0, b.length)) != -1) {
	// information = information.concat(new String(b, 0, bytes));
	// }
	// fis.close();
	// // 将扫描内容放到主板
	// inf.setText("历史记录：\tfor贪吃蛇2.0\n"+information);
	// }
	// } catch (Exception e) {
	// // 记得处理错误
	// inf.setText("警告：数据找不到或无法正常读写！\n请立刻联系11（2）吴元培");
	// }
	//
	// okay.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// rank.setEnabled(true);
	// rankFrame.setVisible(false);
	// }
	// });
	// }
}
