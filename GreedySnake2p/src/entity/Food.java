package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import util.Global;

public class Food extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public int scoreA = 0,scoreB =0;
	
	
	/**
	 *  true代表食物存在 false代表食物被吃
	 */
	public boolean foodCondition = true;
	
	public boolean refresh = true;
	
	/**
	 * whether the head of snake touches (overlap) the food need to get the head
	 * 
	 * @param snake
	 * @return
	 */
	public void isEatBySnake(Snake snake,int whichSnake) {
		//该食物点只要与蛇前三格重合都可以被认为吃掉--->线程的不一致性
		// System.out.println("判断蛇是否碰到食物");
		Point head = snake.getHead();
		Point b = snake.getBody().get(1);
		Point c = snake.getBody().get(2);
		if (this.equals(head)||this.equals(b)||this.equals(c)) {
			foodCondition = false;
			if(whichSnake ==1){
				scoreA++;
			}else{
				scoreB++;
			}
		}
	}

	public void drawMe(Graphics g) {
		// System.out.println("食物正在画出自己...");
		g.setColor(new Color(255,30,0));
		g.fill3DRect(x * Global.CEIL_SIZE, y * Global.CEIL_SIZE, Global.CEIL_SIZE, Global.CEIL_SIZE, true);
	}

	public void addFood(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

}
