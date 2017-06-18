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
		start = new JButton("��ʼ(B)");
		pause = new JButton("��ͣ(P)");
		restart = new JButton("���¿�ʼ(R)");
		// rank = new JButton("��¼(J)");
		exit = new JButton("�˳�(E)");
		help = new JButton("����(H)");

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
		// ��һ��������ô����������
		if (!snake1.getLife()) {
			timer.startTiming();
			snake1.setLife(true);
			snake2.setLife(true);
		}
	}

	public void pause() {
		// ��һ��������ô����������
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
				this.setTitle("����");
				this.setSize(405, 600);
				this.setLocation(950, 75);
				this.setResizable(false);
				this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

				JTextArea inf = new JTextArea();
				JButton okay = new JButton("ȷ�����˳�");

				this.setLayout(new BorderLayout());
				this.add(inf, BorderLayout.CENTER);
				this.add(okay, BorderLayout.SOUTH);

				inf.setFocusable(false);
				inf.setText(new String("��Ϸ����\t" + "    ��ӭ����̰�����޾��棡\n"
						+ "            ���������Ҫ����Ķ��ֶ��Ƕ��£�ֻ��ײ����������Ż�������\n    wsad\"���Կ��ƻ�ɫ�ߵ����������ƶ�\n    \"ikjl\"���Կ��Ʒۺ�ɫ�ߵ����������ƶ�"
						+ "\n    ��Ϊ�ۺ�ͻ�ɫ��ʳ��Ϊ��ɫ������Ϊ����ɫ\n" + "    ������������ʱ����Ϸ����\n"
						+ "    ��ͣ(P)��ֹͣ��Ϸ��ʱ    ��ʼ(B)���ָ���Ϸ\n    ���¿�ʼ(R)����ʼ����Ϸ    �˳�(E)���˳���Ϸ\n    ����(H)���򿪱�����\n\n"
						+ "bug����\tfor̰����2.3\n" + "���¿�ʼ��Ϸ������Ϸ���������¿�ʼ��Ϸ��һ�����ʻᵼ���޷��ٿ���Ϸ\n�������Ѿ��õ��޸�����"
						+ "�������Ϸ���ڼ��ɲ�����Ϸ����������bug����\n��ϵ 11(2) ��Ԫ��\n\n" + "����Ȩ��\tfor̰����2.3\n"
						+ "Food,Ground,Snake,Global,GamePanel,SnakeGameTest,Controller����\n"
						+ "SnakeListener�ӿ��в��ַ����Լ���Ϸ��ܹ������������ڡ���Ӯְ��\n"
						+ "java�̡̳���ѩ����ʦ;Timer,OperatorPanel���Լ�Food,Snake,Global,\n"
						+ "SnakeGameTest,Controller���в��ַ���Ϊ 11(2) ��Ԫ��ԭ����\n\n"));

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
	// rankFrame.setTitle("��ʷ��¼");
	// rankFrame.setResizable(false);
	// rank.setEnabled(false);
	// rankFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	//
	// JButton okay = new JButton("ȷ�����˳�");
	//
	// rankFrame.setLayout(new BorderLayout());
	// rankFrame.add(sp, BorderLayout.CENTER);
	// c.add(okay, BorderLayout.SOUTH);
	// inf.setFocusable(false);
	//
	// try {
	// // �����ļ���
	// File f2 = new File("D:\\Program Files\\̰����2.2");
	// String information="";
	// // ��������ڴ����ļ���
	// if (!f2.exists()) {
	// f2.mkdirs();
	// }
	// // ���ļ����´����ļ�
	// File f = new File("D:\\Program Files\\̰����2.2\\GreedySnake.dat");
	// if(!f.exists()){
	// inf.setText("��û����ʷ��¼��\tfor̰����2.1");
	// }
	// if(f.exists()){
	// // ����������
	// FileInputStream fis = new FileInputStream(f);
	// // ��ȡ�ļ�
	// byte[] b = new byte[1024];
	// int bytes;
	// while ((bytes = fis.read(b, 0, b.length)) != -1) {
	// information = information.concat(new String(b, 0, bytes));
	// }
	// fis.close();
	// // ��ɨ�����ݷŵ�����
	// inf.setText("��ʷ��¼��\tfor̰����2.0\n"+information);
	// }
	// } catch (Exception e) {
	// // �ǵô������
	// inf.setText("���棺�����Ҳ������޷�������д��\n��������ϵ11��2����Ԫ��");
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
