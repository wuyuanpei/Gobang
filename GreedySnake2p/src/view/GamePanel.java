package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entity.*;
import util.Global;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Snake snake1,snake2;
	private Food food;
	private Ground ground;
	private Timer timer;
	
	public void display(Snake snake1,Snake snake2, Food food, Ground ground, Timer timer) {
		//System.out.println("�����ʾ...");
//		System.out.println(snake.getLife());
		this.snake1=snake1;
		this.snake2=snake2;
		this.food=food;
		this.ground=ground;
		this.timer=timer;
		if(snake1.getLife()||snake2.getLife()){
//			this.requestFocus(true);
		}
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (snake1 != null&&snake2 !=null && food != null && ground != null) {
			snake1.drawMe(g);
			snake2.drawMe(g);
			food.drawMe(g);
			ground.drawMe(g);// �����Ĺ���
		}
		g.setColor(Color.BLACK);
//		if(Global.SPEED ==100){
//			g.drawString("��ǰ�ٶȣ���",Global.CEIL_SIZE*Global.WIDTH-430,Global.CEIL_SIZE*Global.HEIGHT-27);
//		}
//		if(Global.SPEED ==80){
//			g.drawString("��ǰ�ٶȣ���",Global.CEIL_SIZE*Global.WIDTH-430,Global.CEIL_SIZE*Global.HEIGHT-27);
//		}
//		if(Global.SPEED ==60){
//			g.drawString("��ǰ�ٶȣ���",Global.CEIL_SIZE*Global.WIDTH-430,Global.CEIL_SIZE*Global.HEIGHT-27);
//		}
//		g.drawString("Z��X��C�л��ٶ�",Global.CEIL_SIZE*Global.WIDTH-270,Global.CEIL_SIZE*Global.HEIGHT-27);
		g.drawString("С���߳���:  "+food.scoreA+" ��ʳ��      С���߳���: "+food.scoreB+" ��ʳ��",30,Global.CEIL_SIZE*Global.HEIGHT-7);
//		if(timer.getSeconds()>=54) g.setColor(Color.RED);
		g.drawString("  ʱ�䣺" + timer.getMinutes() + "��" +timer.getSeconds() + "��",Global.CEIL_SIZE*Global.WIDTH-130,Global.CEIL_SIZE*Global.HEIGHT-7);
		if(snake1.oldDirection == Snake.LEFT||snake1.oldDirection == Snake.RIGHT){
		g.setColor(Color.WHITE);
		g.fillOval(snake1.getBody().getFirst().x*Global.CEIL_SIZE+7,snake1.getBody().getFirst().y*Global.CEIL_SIZE+2, 5,5);
		g.fillOval(snake1.getBody().getFirst().x*Global.CEIL_SIZE+7,snake1.getBody().getFirst().y*Global.CEIL_SIZE+12, 5,5);
		}else{
			g.setColor(Color.WHITE);
			g.fillOval(snake1.getBody().getFirst().x*Global.CEIL_SIZE+2,snake1.getBody().getFirst().y*Global.CEIL_SIZE+7, 5,5);
			g.fillOval(snake1.getBody().getFirst().x*Global.CEIL_SIZE+12,snake1.getBody().getFirst().y*Global.CEIL_SIZE+7, 5,5);
		}
		if(snake2.oldDirection == Snake.LEFT||snake2.oldDirection == Snake.RIGHT){
			g.setColor(Color.WHITE);
			g.fillOval(snake2.getBody().getFirst().x*Global.CEIL_SIZE+7,snake2.getBody().getFirst().y*Global.CEIL_SIZE+2, 5,5);
			g.fillOval(snake2.getBody().getFirst().x*Global.CEIL_SIZE+7,snake2.getBody().getFirst().y*Global.CEIL_SIZE+12, 5,5);
			}else{
				g.setColor(Color.WHITE);
				g.fillOval(snake2.getBody().getFirst().x*Global.CEIL_SIZE+2,snake2.getBody().getFirst().y*Global.CEIL_SIZE+7, 5,5);
				g.fillOval(snake2.getBody().getFirst().x*Global.CEIL_SIZE+12,snake2.getBody().getFirst().y*Global.CEIL_SIZE+7, 5,5);
			}
	}
}

