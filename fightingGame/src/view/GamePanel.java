package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 1860979716621182121L;
	public ImageIcon LiuBody = new ImageIcon(this.getClass().getResource("LiuBody.png"));
	public ImageIcon FuBody = new ImageIcon(this.getClass().getResource("FuBody.png"));
//	public ImageIcon LiuHead = new ImageIcon(this.getClass().getResource("LiuHead.jpg"));
//	public ImageIcon FuHead = new ImageIcon(this.getClass().getResource("FuHead.jpg"));
	public ImageIcon Ground = new ImageIcon(this.getClass().getResource("ground.jpg"));
	public ImageIcon LiuBodyRight = new ImageIcon(this.getClass().getResource("LiuBody2.png"));
	public ImageIcon FuBodyRight = new ImageIcon(this.getClass().getResource("FuBody2.png"));
	public ImageIcon Snowman = new ImageIcon(this.getClass().getResource("snowman.png"));
	public ImageIcon Liu = new ImageIcon(this.getClass().getResource("Liu.jpg"));
	public ImageIcon Fu = new ImageIcon(this.getClass().getResource("Fu.jpg"));
	public ImageIcon SnowIcon = new ImageIcon(this.getClass().getResource("snowIcon.png"));
	public ImageIcon pickingIcon = new ImageIcon(this.getClass().getResource("pickingIcon.png"));
	public ImageIcon attackingIcon = new ImageIcon(this.getClass().getResource("attacking.png"));
	public ImageIcon shieldIcon = new ImageIcon(this.getClass().getResource("shield.png"));
	public ImageIcon snowPatch = new ImageIcon(this.getClass().getResource("snowPatch.png"));
	public ImageIcon target = new ImageIcon(this.getClass().getResource("target.png"));
	public ImageIcon fire = new ImageIcon(this.getClass().getResource("fire.gif"));
	public ImageIcon timeBG = new ImageIcon(this.getClass().getResource("timeBG.png"));
	public ImageIcon bulletR = new ImageIcon(this.getClass().getResource("bulletR.png"));
	public ImageIcon bulletL = new ImageIcon(this.getClass().getResource("bulletL.png"));
	public ImageIcon explode = new ImageIcon(this.getClass().getResource("explode.gif"));
	public ImageIcon ash = new ImageIcon(this.getClass().getResource("ash.gif"));
	public ImageIcon win = new ImageIcon(this.getClass().getResource("win.png"));
	public ImageIcon lose = new ImageIcon(this.getClass().getResource("lose.png"));
	public String infLiu = new String("不动了...");
	public String infFu = new String("不动了...");
	public GamePanel(){
		this.setBackground(Color.WHITE);
	}
	public boolean drawResult;
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGround(g);
		drawPlayer(g);
		drawInf(g);
		drawSkill1(g);
		if(drawResult)
			drawResult(g);
	}
	public boolean whoWins;//true刘以洲胜利，false豪哥胜利
	public void drawResult(Graphics g){
		if(whoWins){
			win.paintIcon(null, g, 100, 200);
			lose.paintIcon(null, g, 700, 200);
		}else{
			win.paintIcon(null, g, 700, 200);
			lose.paintIcon(null, g, 100, 200);
		}
	}
	public void drawPlayer(Graphics g){
//		LiuHead.paintIcon(null, g, Player.LIU.location.x+50,Player.LIU.location.y-35);
//		FuHead.paintIcon(null, g, Player.FU.location.x+50,Player.FU.location.y-35);
		if(Player.LIU.direction<0)
			LiuBody.paintIcon(null, g, Player.LIU.location.x,Player.LIU.location.y);
		else
			LiuBodyRight.paintIcon(null, g, Player.LIU.location.x,Player.LIU.location.y);
		if(Player.FU.direction<0)
			FuBody.paintIcon(null, g, Player.FU.location.x,Player.FU.location.y);
		else
			FuBodyRight.paintIcon(null, g, Player.FU.location.x,Player.FU.location.y);
		if(Player.LIU.picking)
			pickingIcon.paintIcon(null, g, Player.LIU.location.x+25,Player.LIU.location.y-90);
		if(Player.FU.picking)
			pickingIcon.paintIcon(null, g, Player.FU.location.x+25,Player.FU.location.y-90);
		if(Player.LIU.isAttacking)
			attackingIcon.paintIcon(null, g, Player.LIU.location.x+25,Player.LIU.location.y-90);
		if(Player.FU.isAttacking)
			attackingIcon.paintIcon(null, g, Player.FU.location.x+25,Player.FU.location.y-90);
		if(Player.LIU.shieldOn)
			shieldIcon.paintIcon(null, g, Player.LIU.location.x+25,Player.LIU.location.y-90);
		if(Player.FU.shieldOn)
			shieldIcon.paintIcon(null, g, Player.FU.location.x+25,Player.FU.location.y-90);
		
	}
	public void drawGround(Graphics g){
		Ground.paintIcon(null, g, 0,0);
		Snowman.paintIcon(null, g, 430,140);
	}
	public void drawInf(Graphics g){
		Liu.paintIcon(null, g, 10,10);
		Fu.paintIcon(null, g, 1231,10);
		g.setFont(new Font("华文新魏",Font.PLAIN,20));
		g.drawString("p1   洲神", 150, 35);
		g.drawString("p2   豪哥", 1140, 35);
		g.setColor(Color.RED);
		g.drawString(infLiu, 150,125);
		g.drawString(infFu, 985,125);
		g.setFont(new Font("华文新魏",Font.PLAIN,45));
		g.drawString("Esc快速退出", 570, 90);
		g.fillRect(150,50,Player.LIU.blood,25);
		g.fillRect(815-Player.FU.blood+400,50, Player.FU.blood,25);
		g.setColor(Color.MAGENTA);
		g.fillRect(150,80, Player.LIU.energy,25);
		g.fillRect(815-Player.FU.energy+400,80, Player.FU.energy,25);
		for(int i =0;i<Player.LIU.numOfBalls;i++)
			SnowIcon.paintIcon(null, g, 15+35*i, 150);
		for(int i =0;i<Player.FU.numOfBalls;i++)
			SnowIcon.paintIcon(null, g, 1306-35*i, 150);
		for(int i =0;i<3;i++)
			timeBG.paintIcon(null, g, 150+80*i, 130);
		for(int i =0;i<3;i++)
			timeBG.paintIcon(null, g, 1145-80*i, 130);
		if(Player.LIU.skill1Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,40));
		if(Player.LIU.skill1Time==0)
			g.drawString("√", 165,178);
		else
			g.drawString(""+Player.LIU.skill1Time, 175,178);
		if(Player.FU.skill1Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		if(Player.FU.skill1Time==0)
			g.drawString("√", 1000,178);
		else
			g.drawString(""+Player.FU.skill1Time, 1010,178);
		
		if(Player.LIU.skill2Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,40));
		if(Player.LIU.skill2Time==0)
			g.drawString("√", 245,178);
		else
			g.drawString(""+Player.LIU.skill2Time, 255,178);
		if(Player.FU.skill2Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		if(Player.FU.skill2Time==0)
			g.drawString("√", 1080,178);
		else
			g.drawString(""+Player.FU.skill2Time, 1090,178);
		
		if(Player.LIU.skill3Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,40));
		if(Player.LIU.skill3Time==0)
			g.drawString("√", 325,178);
		else
			g.drawString(""+Player.LIU.skill3Time, 335,178);
		if(Player.FU.skill3Time == 0)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.RED);
		if(Player.FU.skill3Time==0)
			g.drawString("√", 1160,178);
		else
			g.drawString(""+Player.FU.skill3Time, 1170,178);
	}
	public void drawSkill1(Graphics g){
		if(Player.FU.skill1On)
			target.paintIcon(null, g,Player.FU.targetLocation,500);
		if(Player.LIU.skill1On)
			target.paintIcon(null, g,Player.LIU.targetLocation,500);
	}
}
