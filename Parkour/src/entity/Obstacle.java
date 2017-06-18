package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import global.Param;
import test.ParkourTest;
import view.GamePanel;

/**
 * 障碍物枚举
 * 
 * @author WYP
 *
 */
public enum Obstacle {
	/*
	 * 突出物
	 */
	THORN() {
		@Override
		public void paintObstacle(Graphics g) {
			if(img==null){
				setImage("tree1.png", 75);
				randomEach = 0;
			}
			paintImage(g);
			if(randomEach!=NO_OBSTACLE) return;
			if(GamePanel.imageAfter!=GamePanel.imageBefore){
				setImage("lightHouse.png", 60);
				randomEach = 100;
				return;
			}
			if (ParkourTest.gamePanel.getGround() == GamePanel.ground1) {
				randomEach = new Random().nextInt(8);
				switch (randomEach) {
				case 0:
					setImage("tree1.png", 75);
					break;
				case 1:
					setImage("fire.png", 65);
					break;
				case 2:
					setImage("rock.png", 50);
					break;
				case 3:
					setImage("tree2.png", 70);
					break;
				case 4:
					setImage("tree3.png", 70);
					break;
				case 5:
					setImage("tree4.png", 70);
					break;
				case 6:
					setImage("tree5.png", 70);
					break;
				case 7:
					setImage("wood.png", 60);
					break;
				}
			} else {
				randomEach = new Random().nextInt(5);
				switch (randomEach) {
				case 0:
					setImage("ship1.png", 50);
					break;
				case 1:
					setImage("ship2.png", 75);
					break;
				case 2:
					setImage("ship3.png", 75);
					break;
				case 3:
					setImage("ship4.png", 75);
					break;
				case 4:
					setImage("ship5.png", 75);
					break;
				}
			}
		}

		@Override
		public boolean identifyDeath() {
			return !(getDistance() == 40 && GamePanel.getHEIGHT() > Param.GROUND_LOCATION - 80);
		}
	},
	/*
	 * 鸟
	 */
	BIRD() {
		@Override
		public void paintObstacle(Graphics g) {
			if(img==null){
				setImage("bird1.png", 90);
			    randomEach = 0;
			}
			g.setColor(Color.RED);
			g.drawRect(getDistance() + 50, Param.GROUND_LOCATION - 100, 30, 7);
			paintImage(g);
			if(randomEach!=NO_OBSTACLE) return;
			randomEach = new Random().nextInt(4);
			switch (randomEach) {
			case 0:
				setImage("bird1.png", 90);
				break;
			case 1:
				setImage("bird2.png", 90);
				break;
			case 2:
				setImage("bat.png", 80);
				break;
			case 3:
				setImage("bat2.png", 90);
				break;
			}	
		}

		@Override
		public boolean identifyDeath() {
			return !(getDistance() == 40
					&& (GamePanel.getHEIGHT() < Param.GROUND_LOCATION - 50 || !GamePanel.isStooping()));
		}
	},
	/*
	 * 野兽
	 */
	MONSTER() {
		@Override
		public void paintObstacle(Graphics g) {
			if(img==null) {
				setImage("monster1.png", 75);
				randomEach = 0;
			}
			paintImage(g);
			g.setColor(Color.RED);
			g.drawRect(getDistance() + 50, Param.GROUND_LOCATION - 100, 30, 7);
			if(randomEach!=NO_OBSTACLE) return;
			if(GamePanel.imageAfter!=GamePanel.imageBefore){
				setImage("frog.png", 75);
				randomEach = 101;
				return;
			}
			if (ParkourTest.gamePanel.getGround() == GamePanel.ground1) {
				randomEach = new Random().nextInt(6);
				switch (randomEach) {
				case 0:
					setImage("monster1.png", 90);
					break;
				case 1:
					setImage("monster2.png", 90);
					break;
				case 2:
					setImage("monster3.png", 90);
					break;
				case 3:
					setImage("monster4.png", 90);
					break;
				case 4:
					setImage("monster5.png", 90);
					break;
				case 5:
					setImage("monster6.png", 90);
					break;
				}
			} else {
				randomEach = new Random().nextInt(5);
				switch (randomEach) {
				case 0:
					setImage("fish1.png", 90);
					break;
				case 1:
					setImage("fish2.png", 90);
					break;
				case 2:
					setImage("fish3.png", 90);
					break;
				case 3:
					setImage("fish4.png", 90);
					break;
				case 4:
					setImage("fish5.png", 90);
					break;
				}
			}
		}

		@Override
		public boolean identifyDeath() {
			return getDistance() != 40;
		}
	};

	/*
	 * 当前障碍物的位置，默认最右边
	 */
	private static int distance = Param.WIDTH;

	private static final int NO_OBSTACLE = 407;
	private static int randomEach = NO_OBSTACLE;

	private Obstacle() {
		putIntoList();
	}

	private void putIntoList() {
		ParkourTest.OBSTACLES.add(this);
	}

	public static int getDistance() {
		return distance;
	}

	public static void setDistance(int distance) {
		Obstacle.distance = distance;
	}

	public static void decreaseDistance() {
		setDistance(getDistance() - Param.EACH_MOVE);
	}

	public static void distanceToOrigin() {
		setDistance(Param.WIDTH);
	}

	public static Obstacle randomObstacle() {
		randomEach = NO_OBSTACLE;
		int random = new Random().nextInt(3);
		return ParkourTest.OBSTACLES.get(random);
	}
	private static ImageIcon img;
	private static int location;
	private static void setImage(String urlString, int location) {
		URL url = Obstacle.class.getResource(urlString);
		img = new ImageIcon(url);
		Obstacle.location = location;
	}
	public static void paintImage(Graphics g){
		img.paintIcon(null, g, getDistance(), Param.GROUND_LOCATION - location);
	}
	/**
	 * 每种障碍物的绘画不一样 每种障碍物的死亡判断不一样
	 * 
	 * @param g
	 */
	abstract public void paintObstacle(Graphics g);

	abstract public boolean identifyDeath();

}
