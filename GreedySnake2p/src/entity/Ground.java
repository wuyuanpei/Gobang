package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import util.Global;
/**
 * ground
 * @author lenovo
 *
 */
public class Ground {
	//every member in this int[][] corresponds to every grid in gamePanel
	private int[][] rocks = new int[Global.HEIGHT][Global.WIDTH];
	public static final int FILLED = 1;
	public Ground(){
//		for(int y = 0; y<Global.HEIGHT;y++){
//			for(int x = 0;x<Global.WIDTH;x++){
//				if(y==0||y==Global.HEIGHT-1){
//					rocks[y][x] = FILLED;
//				}
//				if(x==0||x==Global.WIDTH-1){
//					rocks[y][x] = FILLED;
//				}
//					
//			}
//		}
	}
	/**
	 * to identify whether the snake touches the ground
	 * @param snake
	 * @return whether the snake touches the ground
	 */
	public boolean isEatBySnake(Snake snake){
		//System.out.println("判断蛇是否碰到障碍物");
		Point head = snake.getHead();
		for(int x = 0;x<Global.WIDTH;x++){
			for(int y = 0;y<Global.HEIGHT;y++){
				if(rocks[y][x]==1&&head.x==x&&head.y==y){
					return true;
				}
			}
		}
		return false;
	}
	public void drawMe(Graphics g){
		g.setColor(new Color(255,200,0));
		for(int y = 0; y<Global.HEIGHT;y++){
			for(int x = 0;x<Global.WIDTH;x++){
				if(rocks[y][x]==FILLED){
					g.fill3DRect(x*Global.CEIL_SIZE, y*Global.CEIL_SIZE, Global.CEIL_SIZE, Global.CEIL_SIZE, true);
				}
			}
		}
		//System.out.println("障碍物正在画出自己...");
	}
	/**
	 * product the food which cannot be on the grounds or snake's body
	 * @return the point of food
	 */
	public Point getPoint(){
		int x,y;
		do{
		x = new Random().nextInt(Global.WIDTH);
		y = new Random().nextInt(Global.HEIGHT);
		}while(rocks[y][x]==FILLED);
		return new Point(x,y);
	}
}
