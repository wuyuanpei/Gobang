package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Player;
import global.Param;
import serialization.HighestSerializer;
import serialization.Writer;
import test.ParkourTest;

public class PreparePanel extends JPanel {
	private static final long serialVersionUID = -8584081843963119225L;
	private ImageIcon theInf = Player.LIU.getInf();
	private ImageIcon bigBG = new ImageIcon(this.getClass().getResource("bigBG.jpg"));
	public JPanel buttonsPanel = new JPanel();
	public JPanel startPanel = new JPanel();
	public JButton startButton = new JButton("开始游戏") {
		private static final long serialVersionUID = 6870197597829368253L;
		{
			this.setFocusable(false);
			this.setFont(new Font("", Font.BOLD, 25));
			this.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){
						@Override
						public void run() {
							if(ParkourTest.id.getPower()<1) return;
							ParkourTest.prepareFrame.setEnabled(false);
							setEnabled(false);
							drawPrepare = true;
							repaintInf();
							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw3 = true;
							drawPrepare = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw2 = true;
							draw3 = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw1 = true;
							draw2 = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							drawStart = true;
							draw1 = false;
							repaintInf();
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							drawStart = false;
							ParkourTest.prepareBGM.stop();
							ParkourTest.prepareFrame.setEnabled(true);
							setEnabled(true);
							ParkourTest.prepareFrame.setVisible(false);
							ParkourTest.frame.setEnabled(true);
							ParkourTest.frame.setVisible(true);
							ParkourTest.pause = false;
							ParkourTest.energy = ParkourTest.player.getEnergy();
							ParkourTest.playBGM();
							ParkourTest.timer.init();
							ParkourTest.score = 0;
						}
					}).start();
				}
			});
		}
	};
	public JButton foretryButton = new JButton("试玩") {
		private static final long serialVersionUID = -9023803711005755555L;
		{
			this.setFocusable(false);
			this.setEnabled(false);
			this.setFont(new Font("", Font.BOLD, 25));
			this.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){
						@Override
						public void run() {
							if(ParkourTest.player.foretryPrice>ParkourTest.id.getCoins()||ParkourTest.id.getPower()<1) return;
							ParkourTest.id.setCoins(ParkourTest.id.getCoins()-ParkourTest.player.foretryPrice);
							ParkourTest.prepareFrame.setEnabled(false);
							setEnabled(false);
							drawPrepare = true;
							repaintInf();
							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw3 = true;
							drawPrepare = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw2 = true;
							draw3 = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							draw1 = true;
							draw2 = false;
							repaintInf();
							try {
								Thread.sleep(950);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							drawStart = true;
							draw1 = false;
							repaintInf();
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							drawStart = false;
							ParkourTest.prepareBGM.stop();
							ParkourTest.prepareFrame.setEnabled(true);
							setEnabled(true);
							ParkourTest.prepareFrame.setVisible(false);
							ParkourTest.frame.setEnabled(true);
							ParkourTest.frame.setVisible(true);
							ParkourTest.pause = false;
							ParkourTest.energy = ParkourTest.player.getEnergy();
							ParkourTest.playBGM();
							ParkourTest.timer.init();
							ParkourTest.score = 0;
						}
					}).start();
				}
			});
		}
	};
	public JButton buyButton = new JButton("购买") {
		private static final long serialVersionUID = -407324372430029077L;
		{
			this.setEnabled(false);
			this.setFocusable(false);
			this.setFont(new Font("", Font.BOLD, 25));
			this.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(ParkourTest.player.price>ParkourTest.id.getCoins()) return;
					ParkourTest.id.setCoins(ParkourTest.id.getCoins()-ParkourTest.player.price);
					ParkourTest.id.ownHeros[ParkourTest.PLAYERS.indexOf(ParkourTest.player)] = true;
					Writer.recordTheIdentity(ParkourTest.id);
					foretryButton.setText("试玩");
					buyButton.setText("购买");
					startButton.setEnabled(true);
					foretryButton.setEnabled(false);
					buyButton.setEnabled(false);
				}	
			});
		}
	};
	public PreparePanel() {
		this.setLayout(new BorderLayout());
		this.add(buttonsPanel, BorderLayout.WEST);
		this.add(new JPanel() {
			private static final long serialVersionUID = 2858566024788967102L;
			{
				this.setLayout(new BorderLayout());
				this.add(startPanel, BorderLayout.SOUTH);
				this.setOpaque(false);
			}
		}, BorderLayout.EAST);
		initButtons();
		initStart();
	}

	private void initStart() {
		startPanel.setBackground(Color.YELLOW);
		startPanel.add(startButton);
		startPanel.add(foretryButton);
		startPanel.add(buyButton);
	}

	private void initButtons() {
		buttonsPanel.setBackground(Color.DARK_GRAY);
		buttonsPanel.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < ParkourTest.PLAYERS.size(); i++) {
			final int index = i;
			buttonsPanel.add(new JButton(ParkourTest.PLAYERS.get(i).getHead()) {
				private static final long serialVersionUID = 3334248504231319745L;
				{
					this.setContentAreaFilled(false);
					this.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							theInf = ParkourTest.PLAYERS.get(index).getInf();
							ParkourTest.player = ParkourTest.PLAYERS.get(index);
							repaintInf();
							if(!ParkourTest.id.getOwnHeros()[ParkourTest.PLAYERS.indexOf(ParkourTest.player)]){
								startButton.setEnabled(false);
								foretryButton.setText("试玩($"+ParkourTest.player.foretryPrice+")");
								buyButton.setText("购买($"+ParkourTest.player.price+")");
								foretryButton.setEnabled(true);
								buyButton.setEnabled(true);
							}else{
								startButton.setEnabled(true);
								foretryButton.setText("试玩");
								foretryButton.setEnabled(false);
								buyButton.setText("购买");
								buyButton.setEnabled(false);
							}
						}
					});
				}
			});
		}
	}

	public void repaintInf() {
		repaint();
	}
	private boolean draw1,draw2,draw3,drawPrepare,drawStart;
	@Override
	public void paintComponent(Graphics g) {
		bigBG.paintIcon(null, g, 0, 0);
		g.setColor(new Color(205,184,125));
		g.fillRect(150,290,383,50);
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.PLAIN, 18));
		if(ParkourTest.id.getName().equals(Param.ADMIN_CODE))
			g.drawString("用户：DEVELOPER", 155,310);
		else
			g.drawString("用户： "+ParkourTest.id.getName(), 155, 310);
		g.drawString("体力： "+ParkourTest.id.getPower(), 380, 335);
		g.drawString("金币： $"+ParkourTest.id.getCoins(), 155, 335);
		g.drawString("最高分： "+ParkourTest.id.getHighestScore(), 380, 310);
		g.setColor(Color.RED);
//		g.setFont(new Font("华文行楷",Font.BOLD,45));
//		g.drawString("新年好", 115,395);
		g.setFont(new Font("华文行楷",Font.BOLD,27));
		g.drawString(HighestSerializer.identifyHighest(), 150,35);
		theInf.paintIcon(null, g, 150, 50);
		g.setFont(new Font("宋体",Font.BOLD,80));
		g.setColor(Color.BLACK);
		if(drawPrepare||draw1||draw2||draw3||drawStart) g.fillRect(150,120,383,100);
		g.setColor(Color.WHITE);
		if(drawPrepare)  g.drawString("准备中...",150,200);
		if(draw3)  g.drawString("倒计时：3",150,200);
		if(draw2)  g.drawString("倒计时：2",150,200);
		if(draw1)  g.drawString("倒计时：1",150,200);
		if(drawStart)  g.drawString("游戏开始！",150,200);
	}
}
