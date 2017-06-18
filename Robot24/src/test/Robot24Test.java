package test;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import calculator.NumProducer;
import controller.DataController;
import controller.NumActionListener;
import controller.Timer;
import global.Param;
import view.GamePanel;
import view.NumButton;
import view.PreparePanel;

public class Robot24Test {

	public static JFrame frame = new JFrame(Param.FRAME_TITLE);

	public static GamePanel panel = new GamePanel();

	public static Timer timer = new Timer();

	public static boolean pause = false;
	
	public static JFrame prepareFrame = new JFrame();
	
	public static String prepareText ="游戏准备中...";
	
	public static PreparePanel preparePanel = new PreparePanel();
	
	public static void main(String[] args) throws Exception{
		prepareFrame();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		initNums();
		initFrame();
		prepareFrame.setVisible(false);
		Level.levelInit();
		frame.setVisible(true);
		// 每秒重画一次以显示时间的线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
						try {
							if (timer.getSeconds() == 0) {
								endFrame();
							}
							Thread.sleep(50);
							panel.repaint();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					while(pause){
						Thread.yield();
					}
				}
			}
		}).start();
	}

	public static void prepareFrame(){
		prepareFrame.setSize(450,275);
		prepareFrame.setLocationRelativeTo(null);
//		URL url = Robot24Test.class.getResource("bg2.png");
//		Image icon = Toolkit.getDefaultToolkit().getImage(url);
//		prepareFrame.setIconImage(icon);
		prepareFrame.setResizable(false);
		prepareFrame.add(preparePanel);
		prepareFrame.setUndecorated(true);
		prepareFrame.setVisible(true);
	}
	public static void endFrame() {
		pause = true;
		frame.setEnabled(false);
		JFrame endFrame = new JFrame("游戏结束");
		URL url = Robot24Test.class.getResource("icon.png");
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		endFrame.setIconImage(img);
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
		p2.add(new JLabel("您打到： 关卡-" + Level.level) {

			private static final long serialVersionUID = 9170252757037303732L;

			{
				this.setForeground(Param.FONT_COLOR);
			}
		});
		JPanel p3 = new JPanel();
		p3.setBackground(Param.BACKGROUND_COLOR);
		endFrame.add(p3);
		int rank = Level.level * Level.level / 2;
		if (rank > 99)
			rank = 99;
		p3.add(new JLabel("您击败了:" + rank + "%的玩家！") {

			private static final long serialVersionUID = 9170252757037303732L;

			{
				this.setForeground(Param.FONT_COLOR);
			}
		});
		JPanel p4 = new JPanel();
		p4.setBackground(Param.MARGIN_COLOR);
		endFrame.add(p4);
		p4.add(new JButton("重新开始") {
			private static final long serialVersionUID = 1702110863261643654L;
			{
				this.setContentAreaFilled(false);
				this.setFocusable(false);
				this.setForeground(Param.CMD_COLOR);
				this.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pause = false;
						restart();
						frame.setEnabled(true);
						endFrame.setVisible(false);
					}
				});
			}
		});
		endFrame.setVisible(true);
	}

	public static void initNums() {
		int[] nums = NumProducer.getNums(99);
		NumButton.num1.setNum(nums[0]);
		NumButton.num2.setNum(nums[1]);
		NumButton.num3.setNum(nums[2]);
		NumButton.num4.setNum(nums[3]);
	}

	public static void initFrame() {
		URL url = Robot24Test.class.getResource("icon.png");
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		frame.setIconImage(img);
		frame.setSize(292, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
	}

	public static void nextLevel() {
		initKit();
		Level.nextLevel();
	}

	public static void restart() {
		initKit();
		Level.levelInit();
	}

	public static void initKit() {
		Robot24Test.initNums();
		DataController.clear();
		NumActionListener.button1 = null;
		NumActionListener.button2 = null;
		NumButton.allVisible();
	}
}
