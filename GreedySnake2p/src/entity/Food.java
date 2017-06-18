package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import util.Global;

public class Food extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public int scoreA = 0,scoreB =0;
	
	
	/**
	 *  true����ʳ����� false����ʳ�ﱻ��
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
		//��ʳ���ֻҪ����ǰ�����غ϶����Ա���Ϊ�Ե�--->�̵߳Ĳ�һ����
		// System.out.println("�ж����Ƿ�����ʳ��");
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
		// System.out.println("ʳ�����ڻ����Լ�...");
		g.setColor(new Color(255,30,0));
		g.fill3DRect(x * Global.CEIL_SIZE, y * Global.CEIL_SIZE, Global.CEIL_SIZE, Global.CEIL_SIZE, true);
	}

	public void addFood(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

}
