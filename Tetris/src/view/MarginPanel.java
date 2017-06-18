package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import entity.Block;
import test.TetrisTest;
import util.Param;

public class MarginPanel extends JPanel {

	private static final long serialVersionUID = 5496796533628786624L;

	Controller controller = TetrisTest.controller;
	Block nextBlock = TetrisTest.nextBlock;

	public MarginPanel() {
		this.setBackground(Param.BACKGROUND_COLOR);
		this.setLayout(new GridLayout(15, 1));
		JPanel p1 = new JPanel();
		p1.setBackground(Param.BACKGROUND_COLOR);
		this.add(p1);
		p1.add(new JButton("清除模块(c)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.L_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						TetrisTest.restartGame();
					}
				});
			}
		});
		JPanel p2 = new JPanel();
		p2.setBackground(Param.BACKGROUND_COLOR);
		this.add(p2);
		p2.add(new JButton("向左移动(a)") {

			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.O_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.leftMove();
						TetrisTest.panel.repaint();
					}
				});
			}
		});
		JPanel p3 = new JPanel();
		p3.setBackground(Param.BACKGROUND_COLOR);
		this.add(p3);
		p3.add(new JButton("向右移动(d)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.T_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.rightMove();
						TetrisTest.panel.repaint();
					}
				});
			}
		});
		JPanel p4 = new JPanel();
		p4.setBackground(Param.BACKGROUND_COLOR);
		this.add(p4);
		p4.add(new JButton("加速移动(s)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.I_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.downMove();
					}
				});
			}
		});
		JPanel p5 = new JPanel();
		p5.setBackground(Param.BACKGROUND_COLOR);
		this.add(p5);
		p5.add(new JButton("还原速度(w)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.Z_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.originMove();
					}
				});
			}
		});
		JPanel p6 = new JPanel();
		p6.setBackground(Param.BACKGROUND_COLOR);
		this.add(p6);
		p6.add(new JButton("旋转(space)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.L2_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.rotate();
						TetrisTest.panel.repaint();
					}
				});
			}
		});
		JPanel p7 = new JPanel();
		p7.setBackground(Param.BACKGROUND_COLOR);
		this.add(p7);
		p7.add(TetrisTest.helpButton);
		TetrisTest.helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisTest.openHelpFrame();
			}
		});
		TetrisTest.helpButton.setBackground(Param.Z2_COLOR);
		JPanel p8 = new JPanel();
		p8.setBackground(Param.BACKGROUND_COLOR);
		this.add(p8);
		p8.add(new JButton("退出游戏(e)") {
			private static final long serialVersionUID = 220304015726876825L;

			{
				this.setBackground(Param.L_COLOR);
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						TetrisTest.exitGame();
					}
				});
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		controller = TetrisTest.controller;
		nextBlock = TetrisTest.nextBlock;
		if (controller != null && nextBlock != null && controller.getCurrentBlock() != null) {
			int[][] nodes = controller.getCurrentBlock().getOriginNodes();
			int[][] nodesNext = nextBlock.getOriginNodes();
			for (int i = 0; i < 4; i++) {
				g.setColor(controller.getCurrentBlock().getColor());
				g.fillRect(0 - 10 + nodes[i][1] * 15, 520 + nodes[i][0] * 15, 14, 14);
				g.setColor(nextBlock.getColor());
				g.fillRect(0 - 10 + nodesNext[i][1] * 15, 415 + nodesNext[i][0] * 15, 14, 14);
			}
		}
		g.setColor(Param.BOX_COLOR);
		g.drawString("下一个方块：", 5, 375);
		g.drawString("当前方块：", 5, 485);
	}
}
