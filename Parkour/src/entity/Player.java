package entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import global.Param;
import test.ParkourTest;
import view.GamePanel;

public enum Player {
	LIU("liu.jpg", "liuStoop.jpg", "head.jpg", "lazer.jpg", "����", 2000, 400, 800, 1000, 75, "liuSkill1.png",
			"liuSkill2.png", "fireSound.wav","LiuCard.png",0,0) {
		@Override
		public boolean skill() {
			if(specialVisit) return false;
			if (LIU.skillUsed)
				return false;
			new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					ParkourTest.gamePanel.drawSkill = true;
					LIU.skillUsed = true;
					LIU.lazerLimit = 0;
					LIU.jumpLimit = 0;
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ParkourTest.gamePanel.drawSkill = false;
					LIU.lazerLimit = 800;
					LIU.jumpLimit = 400;
				}
			}).start();
			return false;
		}
	},
	NIU("Niu.jpg", "NiuStoop.jpg", "NiuHead.jpg", "sword.jpg", "ţţ", 1500, 375, 750, 1500, 50, "NiuSkill1.png",
			"NiuSkill2.png", "swordSound.wav","NiuCard.png",5000,500) {
		@Override
		public boolean skill() {
			if(specialVisit) return false;
			if (NIU.skillUsed)
				return false;
			new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					ParkourTest.gamePanel.drawSkill = true;
					NIU.skillUsed = true;
					for (int i = 0; i < 500; i++) {
						if (i >= 450) {
							ParkourTest.SPEED = Param.SLOW_SPEED;
						} else {
							ParkourTest.SPEED = 1;
						}
						NIU.jumpLimit = 0;
						NIU.lazerLimit = 0;
						GamePanel.jump();
						GamePanel.shoot();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					ParkourTest.gamePanel.drawSkill = false;
					NIU.jumpLimit = 375;
					NIU.lazerLimit = 750;
					ParkourTest.SPEED = Param.SLOW_SPEED;
				}
			}).start();
			return false;
		}
	},
	ZHOU("Zhou.jpg", "ZhouStoop.jpg", "ZhouHead.jpg", "spider.jpg", "�ƺ�", 1600, 400, 800, 1000, 60, "ZhouSkill1.png",
			"ZhouSkill2.png", "spiderSound.wav","ZhouCard.png",3000,300) {
		private boolean reborn = false;

		@Override
		public boolean skill() {
			if(specialVisit) return reborn;
			if (ZHOU.skillUsed)
				return false;
			new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					ParkourTest.gamePanel.drawSkill = true;
					ZHOU.skillUsed = true;
					reborn = true;
					ZHOU.lazerLimit = 400;
					ZHOU.jumpLimit = 200;
					try {
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					reborn = false;
					ZHOU.lazerLimit = 800;
					ZHOU.jumpLimit = 400;
					ParkourTest.gamePanel.drawSkill = false;
				}
			}).start();
			return false;
		}
	},
	FU("Fu.jpg", "FuStoop.jpg", "FuHead.jpg", "pig.jpg", "����", 650, 250, 500, 1500, 50, "FuSkill1.png", "FuSkill2.png",
			"pigSound.wav","FuCard.png",1000,100) {
		@Override
		public boolean skill() {
			if(specialVisit) return false;
			if (FU.skillUsed)
				return false;
			new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					ParkourTest.gamePanel.drawSkill = true;
					FU.skillUsed = true;
					for (int i = 0; i < 1000; i++) {
						ParkourTest.SPEED = 6;
						FU.lazerPower = 5;
						if (ParkourTest.energy < 650)
							ParkourTest.energy += 8;
						ParkourTest.score += 8;
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					FU.lazerPower = 50;
					ParkourTest.SPEED = Param.SLOW_SPEED;
					ParkourTest.gamePanel.drawSkill = false;
				}
			}).start();
			return false;
		}
	};
	private final AudioClip weaponSound;

	public AudioClip getWeaponSound() {
		return weaponSound;
	}

	private final ImageIcon smallHead, stoopHead, head, lazer, skill1, skill2,inf;
	private final String name;
	public int energy, jumpLimit, lazerLimit, lazerTime, lazerPower;
	public final int price,foretryPrice;
	private boolean skillUsed = false;

	public void setSkillUsed(boolean skillUsed) {
		this.skillUsed = skillUsed;
	}

	public boolean isSkillUsed() {
		return skillUsed;
	}

	public ImageIcon getSmallHead() {
		return smallHead;
	}

	public ImageIcon getHead() {
		return head;
	}

	public String getName() {
		return name;
	}

	/**
	 * Ӣ�ۿ����Զ�������ֵ
	 * 
	 * @param smallHeadURL
	 *            Ӣ��Сͷ����ͼ 18*20px
	 * @param stoopHeadURL
	 *            Ӣ��Сͷ���ͼ 20*18px
	 * @param headURL
	 *            Ӣ��ͷ�����Ͻǣ� 60*77px
	 * @param lazerURL
	 *            ����ͼ�� 600*37px
	 * @param name
	 *            ����
	 * @param energy
	 *            ����ֵ����
	 * @param jumpLimit
	 *            ��Ծ��������ֵ
	 * @param lazerLimit
	 *            ������������ֵ
	 * @param lazerTime
	 *            �����ͷ�ʱ�䳤�ȣ���ֵ*�ƶ�һ����ٶ� ���룩
	 * @param lazerPower
	 *            ����ǿ�� ����һ����������Ҫ����ֵ*�ƶ�һ����ٶ� ���룩
	 * @param skill1URL
	 *            1����ͼ��
	 * @param skill2URL
	 *            2����ͼ��
	 * @param weaponSoundURL
	 *            ��������ʱ������
	 * @param price Ӣ�ۼ۸�
	 * @param foretryPrice Ӣ�����ü۸�
	 */
	private Player(String smallHeadURL, String stoopHeadURL, String headURL, String lazerURL, String name, int energy,
			int jumpLimit, int lazerLimit, int lazerTime, int lazerPower, String skill1URL, String skill2URL,
			String weaponSoundURL,String infURL,int price,int foretryPrice) {
		smallHead = new ImageIcon(this.getClass().getResource(smallHeadURL));
		stoopHead = new ImageIcon(this.getClass().getResource(stoopHeadURL));
		head = new ImageIcon(this.getClass().getResource(headURL));
		lazer = new ImageIcon(this.getClass().getResource(lazerURL));
		skill1 = new ImageIcon(this.getClass().getResource(skill1URL));
		skill2 = new ImageIcon(this.getClass().getResource(skill2URL));
		inf = new ImageIcon(this.getClass().getResource(infURL));
		weaponSound = Applet.newAudioClip(this.getClass().getResource(weaponSoundURL));
		this.name = name;
		this.energy = energy;
		this.jumpLimit = jumpLimit;
		this.lazerLimit = lazerLimit;
		this.lazerTime = lazerTime;
		this.lazerPower = lazerPower;
		this.foretryPrice = foretryPrice;
		this.price = price;
		ParkourTest.PLAYERS.add(this);
	}

	public ImageIcon getLazer() {
		return lazer;
	}

	public ImageIcon getStoopHead() {
		return stoopHead;
	}

	public int getEnergy() {
		return energy;
	}

	public int getJumpLimit() {
		return jumpLimit;
	}

	public int getLazerLimit() {
		return lazerLimit;
	}

	public int getLazerTime() {
		return lazerTime;
	}

	public int getLazerPower() {
		return lazerPower;
	}

	public void drawIcon(Graphics g) {
		skill1.paintIcon(null, g, 250, 15);
		skill2.paintIcon(null, g, 345, 15);
	}
	public static boolean specialVisit = false;
	public abstract boolean skill();
	public ImageIcon getInf() {
		return inf;
	}
}
