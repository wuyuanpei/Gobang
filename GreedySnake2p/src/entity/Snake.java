package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import listener.SnakeListener;
import util.Global;

/**
 * Snakeʵ���࣬���������ߵķ��������
 * 
 * @author lenovo
 *
 */
public class Snake {
	private SnakeListener snakeListener;
	/**
	 * �����ߵ�����״̬
	 */
	private boolean life = true;
	/**
	 * ��LinkedList�����ߵ�ÿ������ĵ�λ��
	 */
	private LinkedList<Point> body = new LinkedList<>();
	/**
	 * ÿ�������Ӧ��intֵ
	 */
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/**
	 * ����ɵķ��򣬷ֱ��޸���ȷ����һ���ƶ�ʱ����������λ���޸�
	 */
	public int oldDirection, newDirection;
	/**
	 * ���浱ǰβ��λ��
	 */
	private Point tail;
	/**
	 * ��¼��ҵı��
	 */
	private int player;

	Snake snakeOther;

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	/**
	 * ���캯��
	 */
	public Snake(int player) {
		this.player = player;
		init(3);// ��ʼ���ߵĶ���
	}

	public void setSnakeOther(Snake snakeOther) {
		this.snakeOther = snakeOther;
	}

	public synchronized void setLife(boolean life) {
		this.life = life;
	}

	public synchronized boolean getLife() {
		return life;
	}

	public synchronized void clearOrigin() {
		body.clear();
	}

	/**
	 * ��ʼ���ߵĶ���
	 */
	public synchronized void init(int originLength) {
		/*
		 * ��ʼ��״̬��ͷ��Ϊ�м�ĸ���
		 */
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		/**
		 * 3Ϊ��ʼ���� �������ͷ�����3����
		 */
		if (player == 1) {
			for (int i = 0; i < originLength; i++) {
				body.add(new Point(x-4,y+6+i));
			}
			this.oldDirection = UP;
			this.newDirection = UP;
		} else {
			for (int i = 0; i < originLength; i++) {
				body.add(new Point(x+3,y+6+i));
			}
			this.oldDirection = UP;
			this.newDirection = UP;
		}
		/**
		 * ��ʼ״̬���¾��ƶ�״̬��Ϊ����
		 */
		
	}

	/**
	 * �����¿�ʼ���������Ϊ�޸���ʳ��״̬�� init�ߺ���Զ���һ��ʳ�ﵼ�³��ȱ�Ϊ4 ���SpecialInit�ṩ��ʼ����Ϊ2��init����
	 */
	// public void specialInit() {
	// /*
	// * ��ʼ��״̬��ͷ��Ϊ�м�ĸ���
	// */
	// int x = Global.WIDTH / 2;
	// int y = Global.HEIGHT / 2;
	// /**
	// * 3Ϊ��ʼ���� �������ͷ�����3����
	// */
	// for (int i = 0; i < 2; i++) {
	// body.add(new Point(x - i, y));
	// }
	// /**
	// * ��ʼ״̬���¾��ƶ�״̬��Ϊ����
	// */
	// this.oldDirection = RIGHT;
	// this.newDirection = RIGHT;
	// }
	/**
	 * �����Ѿ��޸��˵�Direction����body���޸� ������Ϊ�ߵ��ƶ� ���ڲ�������
	 */
	public synchronized void move() {
		// delete tail
		tail = body.removeLast();
		// get old head
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		// set new head
		if (this.newDirection + this.oldDirection != 0)
			this.oldDirection = this.newDirection;
		switch (oldDirection) {
		case UP:
			y--;
			if (y < 0)
				y = Global.HEIGHT - 1;
			break;
		case DOWN:
			y++;
			if (y >= Global.HEIGHT)
				y = 0;
			break;
		case RIGHT:
			x++;
			if (x >= Global.WIDTH)
				x = 0;
			break;
		case LEFT:
			x--;
			if (x < 0)
				x = Global.WIDTH - 1;
			break;
		}
		body.addFirst(new Point(x, y));

		// System.out.println("�������ƶ�...");
	}

	/**
	 * �߳�ʳ��÷�����ԭ����tail���� add the tail which is previously deleted
	 * 
	 * @param food
	 */
	public synchronized void eatFood(Food food) {
		body.addLast(tail);
		// System.out.println("�����ڳ�ʳ��...");
	}

	/**
	 * �ı��ߵķ��򣬸�ֵ��newDirection
	 */
	public synchronized void changeDirection(int direction) {
		// //insure the input direction is not the opposite direction
		// if(this.direction+direction!=0)
		// this.direction = direction;
		this.newDirection = direction;
		// System.out.println("�����ڸı䷽��...");
	}

	/**
	 * �滭���Լ�
	 * 
	 * @param g
	 *            ���ʣ���GamePanel �õ�
	 */
	public synchronized void drawMe(Graphics g) {
		// System.out.println("�����ڻ����Լ�...");

		/*
		 * ��body�������еĵ㻭����
		 */
		if (player == 1) {
			for (int i = 1; i < body.size(); i++) {
				Point p = body.get(i);
				if (i % 2 == 1)
					g.setColor(new Color(240, 120, 0));
				else
					g.setColor(new Color(100, 42, 20));
				g.fillRect((int) p.getX() * Global.CEIL_SIZE, (int) p.getY() * Global.CEIL_SIZE, Global.CEIL_SIZE,
						Global.CEIL_SIZE);
			}
			Point p = body.getFirst();
			g.setColor(new Color(94, 38, 19));
			g.fillRect((int) p.getX() * Global.CEIL_SIZE, (int) p.getY() * Global.CEIL_SIZE, Global.CEIL_SIZE,
					Global.CEIL_SIZE);
		}
		if (player == 2) {
			for (int i = 1; i < body.size(); i++) {
			Point p = body.get(i);
				if (i % 2 == 1)
					g.setColor(new Color(238,99,99));
				else
					g.setColor(new Color(124,42,42));
				g.fillRect((int) p.getX() * Global.CEIL_SIZE, (int) p.getY() * Global.CEIL_SIZE, Global.CEIL_SIZE,
						Global.CEIL_SIZE);
			}
			Point p = body.getFirst();
			g.setColor(new Color(94,38,19));
			g.fillRect((int) p.getX() * Global.CEIL_SIZE, (int) p.getY() * Global.CEIL_SIZE, Global.CEIL_SIZE,
					Global.CEIL_SIZE);
		}
	}

	/**
	 * �ж����Ƿ��Ե��Լ�
	 * 
	 * @return true�������Լ� false��û�������Լ�
	 */
	public synchronized boolean isEatSelf() {
		/*
		 * ��������һ����ͷ
		 */
//		for (int i = 1; i < body.size(); i++) {
//			if (body.get(i).equals(getHead()))
//				return true;
//		}
		return false;
	}

	/**
	 * �ж�һ�����Ƿ����ߵ�һ���� �����ж�������ɵ�ʳ���λ���Ƿ����ߵ�һ����
	 * 
	 * @param p
	 *            ����һ����
	 * @return true��������ߵ�һ���� false����㲻���ߵ�һ����
	 */
	public synchronized boolean filterBody(Point p) {
		for (int i = 0; i < body.size(); i++) {
			if (body.get(i).equals(p))
				return true;
		}
		return false;
	}

	/**
	 * ��Ӽ����� SnakeListenerΪ�ӿ�
	 * 
	 * @param sl
	 *            ���������ӦΪ Controller��
	 */
	public synchronized void addSnakeListener(SnakeListener sl) {
		// if (sl != null)
		snakeListener = sl;
	}

	/**
	 * start a thread to make snake move
	 */
	public synchronized void start() {
		// System.out.println("�������߳�...");
		new SnakeDriver().start();
	}

	/**
	 * get the head of snake
	 * 
	 * @return
	 */
	public synchronized Point getHead() {
		return body.getFirst();
	}

	private class SnakeDriver extends Thread {
		@Override
		public synchronized void run() {
			while (life) {
//				System.out.println(Thread.currentThread().getName());
				move();
				// System.out.println("���ƶ�...");
				snakeListener.snakeMoved(Snake.this, snakeOther);
				// System.out.println("Driver�߳̿�ʼ��Ϣ0.1��");
				try {
					Thread.sleep(Global.SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (!life) {
				}
			}
		}
	}

	public synchronized LinkedList<Point> getBody() {
		return body;
	}
}
