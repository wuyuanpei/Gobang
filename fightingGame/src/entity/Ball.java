package entity;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import view.GamePanel;

public class Ball {
	public ImageIcon img = new ImageIcon(GamePanel.class.getResource("snowBall.png"));
	public ImageIcon bomb = new ImageIcon(GamePanel.class.getResource("bomb.png"));
	public int x,y;
	public Player enermy;
	public Ball(int x,int y,Player enermy){
		this.x = x;
		this.y = y;
		this.enermy = enermy;
	}
	public boolean inTheAttackRange(){
		return enermy.location.x>x-96&&enermy.location.x<x+32&&enermy.location.y<y+32&&enermy.location.y>y-96;
	}
	public void drawBall(Graphics g){
		if((enermy == Player.FU&&Player.LIU.skill3On)||(enermy == Player.LIU&&Player.FU.skill3On))
			bomb.paintIcon(null, g, x, y);
		else
			img.paintIcon(null, g, x, y);
	}
}
