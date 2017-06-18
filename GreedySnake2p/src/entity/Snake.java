package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import listener.SnakeListener;
import util.Global;

/**
 * Snake实体类，包涵所有蛇的方法与参数
 * 
 * @author lenovo
 *
 */
public class Snake {
	private SnakeListener snakeListener;
	/**
	 * 保存蛇的生命状态
	 */
	private boolean life = true;
	/**
	 * 以LinkedList储存蛇的每个身体的点位置
	 */
	private LinkedList<Point> body = new LinkedList<>();
	/**
	 * 每个方向对应的int值
	 */
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/**
	 * 新与旧的方向，分别修改以确保在一次移动时不会有两次位置修改
	 */
	public int oldDirection, newDirection;
	/**
	 * 储存当前尾巴位置
	 */
	private Point tail;
	/**
	 * 记录玩家的编号
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
	 * 构造函数
	 */
	public Snake(int player) {
		this.player = player;
		init(3);// 初始化蛇的对象
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
	 * 初始化蛇的对象
	 */
	public synchronized void init(int originLength) {
		/*
		 * 初始化状态下头点为中间的格子
		 */
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		/**
		 * 3为初始长度 蛇身包含头点左侧3个点
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
		 * 初始状态下新旧移动状态都为向右
		 */
		
	}

	/**
	 * 在重新开始的情况下因为修改了食物状态， init蛇后会自动吃一个食物导致长度变为4 因此SpecialInit提供初始长度为2的init方法
	 */
	// public void specialInit() {
	// /*
	// * 初始化状态下头点为中间的格子
	// */
	// int x = Global.WIDTH / 2;
	// int y = Global.HEIGHT / 2;
	// /**
	// * 3为初始长度 蛇身包含头点左侧3个点
	// */
	// for (int i = 0; i < 2; i++) {
	// body.add(new Point(x - i, y));
	// }
	// /**
	// * 初始状态下新旧移动状态都为向右
	// */
	// this.oldDirection = RIGHT;
	// this.newDirection = RIGHT;
	// }
	/**
	 * 根据已经修改了的Direction进行body的修改 可以视为蛇的移动 基于参数层面
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

		// System.out.println("蛇正在移动...");
	}

	/**
	 * 蛇吃食物，该方法将原来的tail加上 add the tail which is previously deleted
	 * 
	 * @param food
	 */
	public synchronized void eatFood(Food food) {
		body.addLast(tail);
		// System.out.println("蛇正在吃食物...");
	}

	/**
	 * 改变蛇的方向，赋值于newDirection
	 */
	public synchronized void changeDirection(int direction) {
		// //insure the input direction is not the opposite direction
		// if(this.direction+direction!=0)
		// this.direction = direction;
		this.newDirection = direction;
		// System.out.println("蛇正在改变方向...");
	}

	/**
	 * 绘画蛇自己
	 * 
	 * @param g
	 *            画笔，由GamePanel 得到
	 */
	public synchronized void drawMe(Graphics g) {
		// System.out.println("蛇正在画出自己...");

		/*
		 * 将body里面所有的点画出来
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
	 * 判断蛇是否会吃到自己
	 * 
	 * @return true即碰到自己 false即没有碰到自己
	 */
	public synchronized boolean isEatSelf() {
		/*
		 * 不包含第一个蛇头
		 */
//		for (int i = 1; i < body.size(); i++) {
//			if (body.get(i).equals(getHead()))
//				return true;
//		}
		return false;
	}

	/**
	 * 判断一个点是否是蛇的一部分 用于判断随机生成的食物的位置是否是蛇的一部分
	 * 
	 * @param p
	 *            输入一个点
	 * @return true这个点是蛇的一部分 false这个点不是蛇的一部分
	 */
	public synchronized boolean filterBody(Point p) {
		for (int i = 0; i < body.size(); i++) {
			if (body.get(i).equals(p))
				return true;
		}
		return false;
	}

	/**
	 * 添加监听器 SnakeListener为接口
	 * 
	 * @param sl
	 *            输入监听器应为 Controller类
	 */
	public synchronized void addSnakeListener(SnakeListener sl) {
		// if (sl != null)
		snakeListener = sl;
	}

	/**
	 * start a thread to make snake move
	 */
	public synchronized void start() {
		// System.out.println("建立新线程...");
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
				// System.out.println("蛇移动...");
				snakeListener.snakeMoved(Snake.this, snakeOther);
				// System.out.println("Driver线程开始休息0.1秒");
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
