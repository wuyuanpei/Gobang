package entity;

import test.FightingGameTest;

public enum Player {
	LIU(100, 475, 1), FU(1125, 475, -1);
	public int direction;
	public int blood = 400;
	public int energy = 400;
	public int targetLocation = 625;

	private Player(int x, int y, int direction) {
		location = new Location(x, y);
		this.direction = direction;
	}

	public static class Location {
		public int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public Location location;

	public static void reset() {
		LIU.leftMove = false;
		LIU.rightMove = false;
		FU.rightMove = false;
		FU.leftMove = false;
		FU.blood = 400;
		LIU.blood = 400;
		FU.energy = 400;
		LIU.energy = 400;
		FU.location.x = 1125;
		FU.location.y = 475;
		FU.direction = -1;
		LIU.location.x = 100;
		LIU.location.y = 475;
		LIU.direction = 1;
		FU.numOfBalls = 3;
		LIU.numOfBalls = 3;
		FU.shieldOn = false;
		LIU.shieldOn = false;
		
		LIU.skill1On = false;
		LIU.skill1Time = 0;
		LIU.skill1Used = false;
		LIU.skill2Time = 0;
		LIU.skill2Used = false;
		LIU.skill3On = false;
		LIU.skill3Time = 0;
		LIU.skill3Used = false;
		FU.skill1On = false;
		FU.skill1Time = 0;
		FU.skill1Used = false;
		FU.skill2Time = 0;
		FU.skill2Used = false;
		FU.skill3On = false;
		FU.skill3Time = 0;
		FU.skill3Used = false;
	}

	public boolean leftMove, rightMove;

	public static void moving() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				while (!FightingGameTest.pause) {
					if (FU.leftMove && FU.location.x >= 750) {
						FU.location.x -= 3;
					}
					if (FU.rightMove && FU.location.x <= 1250) {
						FU.location.x += 3;
					}
					if (LIU.leftMove && LIU.location.x >= -10) {
						LIU.location.x -= 3;
					}
					if (LIU.rightMove && LIU.location.x <= 490) {
						LIU.location.x += 3;
					}
					FightingGameTest.gamePanel.repaint();
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private boolean isJumping;

	public void jumping() {
		if (isJumping)
			return;
		isJumping = true;
		Player thisPlayer = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 25; i++) {
					location.y -= (25 - i);
					FightingGameTest.gamePanel.repaint();
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 25; i++) {
					location.y += i + 1;
					FightingGameTest.gamePanel.repaint();
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (thisPlayer == LIU)
					FightingGameTest.gamePanel.infLiu = "不动了...";
				else
					FightingGameTest.gamePanel.infFu = "不动了...";
				isJumping = false;
			}
		}).start();
	}

	public int numOfBalls = 3;

	public boolean picking;

	// synchronized is important
	public synchronized void addBall() {
		if (numOfBalls == 3) {
			if (this == LIU)
				FightingGameTest.gamePanel.infLiu = "雪球满了，不能拾！！！！！";
			else
				FightingGameTest.gamePanel.infFu = "雪球满了，不能拾！！！！！";
			return;
		}
		if (numOfBalls < 3) {
			picking = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			numOfBalls++;
			picking = false;
		}
		if (this == LIU)
			FightingGameTest.gamePanel.infLiu = "不动了...";
		else
			FightingGameTest.gamePanel.infFu = "不动了...";
		FightingGameTest.gamePanel.repaint();
	}

	public boolean isAttacking;

	public void throwBall(Player enermy) {
		if (picking) {
			if (enermy == FU)
				FightingGameTest.gamePanel.infLiu = "正在拾雪，不能扔！！！！！";
			else
				FightingGameTest.gamePanel.infFu = "正在拾雪，不能扔！！！！！";
			FightingGameTest.gamePanel.repaint();
			return;
		}
		if (numOfBalls < 1) {
			if (enermy == FU)
				FightingGameTest.gamePanel.infLiu = "雪球不足，不能扔！！！！！";
			else
				FightingGameTest.gamePanel.infFu = "雪球不足，不能扔！！！！！";
			FightingGameTest.gamePanel.repaint();
			return;
		}
		if (numOfBalls > 0) {
			numOfBalls--;
			if (this.energy <= 380)
				this.energy += 20;
			isAttacking = true;
			new Thread(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					Ball ball = new Ball(location.x, location.y, enermy);
					for (int i = 0; i < 1500; i++) {
						if (i == 250)
							isAttacking = false;
						if (i % 4 == 0) {
							if (enermy == LIU) {
								ball.x -= 3;
							} else {
								ball.x += 3;
							}
							int index = i / 20;
							if (index < 25 && i % 20 == 0)
								ball.y -= (25 - index);
							else if (i % 20 == 0)
								ball.y += index - 25;
						}
						ball.drawBall(FightingGameTest.gamePanel.getGraphics());
						if (ball.inTheAttackRange()) {
							if (enermy.shieldOn){
									enermy.blood -= 20;
								if(skill3On)
									enermy.blood-=20;
							}
							else{
								enermy.blood -= 40;// 普通攻击伤害
								if(skill3On)
									enermy.blood-=40;
							}
							for (int j = 0; j < 250; j++) {
								if(skill3On)
									FightingGameTest.gamePanel.ash.paintIcon(null,
											FightingGameTest.gamePanel.getGraphics(), ball.x, ball.y);
								else
									FightingGameTest.gamePanel.snowPatch.paintIcon(null,
										FightingGameTest.gamePanel.getGraphics(), ball.x, ball.y);
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							break;
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					isAttacking = false;
					if (enermy == FU)
						FightingGameTest.gamePanel.infLiu = "不动了...";
					else
						FightingGameTest.gamePanel.infFu = "不动了...";
					FightingGameTest.gamePanel.repaint();
				}
			}).start();
		}
	}

	public boolean shieldOn;
	public boolean skill1On;
	public boolean skill1Used;
	public int skill1Time = 0;

	public void skill1(Player enermy) {
		this.energy -= 75;
		new Thread(new Runnable() {
			@Override
			public void run() {
				skill1Used = true;
				boolean direction = true;// true左移， false右移
				while (skill1On) {
					if (direction)
						targetLocation -= 2;
					else
						targetLocation += 2;
					if (enermy == FU && targetLocation > 1300)
						direction = true;
					if (enermy == FU && targetLocation < 625)
						direction = false;
					if (enermy == LIU && targetLocation < -50)
						direction = false;
					if (enermy == LIU && targetLocation > 625)
						direction = true;
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				new Thread(new Runnable() {
					@Override
					public void run() {
						skill1Time = 3;
						sleep1Sec();
						skill1Time = 2;
						sleep1Sec();
						skill1Time = 1;
						sleep1Sec();
						skill1Time = 0;
						skill1Used = false;
					}
				}).start();
				boolean alreadyAttacked = false;
				for (int i = 0; i < 500; i++) {
					FightingGameTest.gamePanel.fire.paintIcon(null, FightingGameTest.gamePanel.getGraphics(),
							targetLocation, 350);
					if (!alreadyAttacked && targetLocation > enermy.location.x - 75
							&& targetLocation < enermy.location.x + 50) {
						enermy.blood -= 60;
						if (enermy.shieldOn)
							enermy.blood += 30;// 1技能伤害
						alreadyAttacked = true;
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public boolean skill2Used;
	public int skill2Time;
	public void skill2() {
		Player thisP = this;
		skill2Used = true;
		skill2Time = 3;
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (thisP == Player.LIU) {
					int y = Player.LIU.location.y;
					boolean alreadyAttacked = false;
					int x = 0;
					for (int i = Player.LIU.location.x; i < 1500; i++) {
						FightingGameTest.gamePanel.bulletR.paintIcon(null, FightingGameTest.gamePanel.getGraphics(), i,
								y);
						if(!alreadyAttacked&&i>Player.FU.location.x-96&&i<Player.FU.location.x&&y>Player.FU.location.y-15&&y<Player.FU.location.y+50){
							Player.FU.blood-=60;
							if(FU.shieldOn)
								Player.FU.blood+=30;
							alreadyAttacked = true;
							x=i;
						}
						if(alreadyAttacked){
							FightingGameTest.gamePanel.explode.paintIcon(null, FightingGameTest.gamePanel.getGraphics(), x+50, y-75);
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					skill2Time = 2;
					sleep1Sec();
					skill2Time = 1;
					sleep1Sec();
					skill2Time = 0;
				} else {
					int y = Player.FU.location.y;
					boolean alreadyAttacked = false;
					int x = 0;
					for (int i = Player.FU.location.x; i > -100; i--) {
						FightingGameTest.gamePanel.bulletL.paintIcon(null, FightingGameTest.gamePanel.getGraphics(), i,
								y);
						if(!alreadyAttacked&&i>Player.LIU.location.x&&i<Player.LIU.location.x+96&&y>Player.LIU.location.y-15&&y<Player.LIU.location.y+50){
							Player.LIU.blood-=60;
							if(LIU.shieldOn)
								Player.LIU.blood+=30;
							alreadyAttacked = true;
							x=i;
						}
						if(alreadyAttacked){
							FightingGameTest.gamePanel.explode.paintIcon(null, FightingGameTest.gamePanel.getGraphics(), x-75, y-75);
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					skill2Time = 2;
					sleep1Sec();
					skill2Time = 1;
					sleep1Sec();
					skill2Time = 0;
				}

			}

		}).start();
	}
	public boolean skill3On,skill3Used;
	public int skill3Time;
	public void skill3(){
		skill3On = true;
		skill3Used = true;
		new Thread(new Runnable(){
			@Override
			public void run() {
				skill3Time = 9;
				sleep1Sec();
				skill3Time = 8;
				sleep1Sec();
				skill3Time = 7;
				sleep1Sec();
				skill3Time = 6;
				sleep1Sec();
				skill3Time = 5;
				sleep1Sec();
				skill3Time = 4;
				sleep1Sec();
				skill3Time = 3;
				sleep1Sec();
				skill3Time = 2;
				sleep1Sec();
				skill3Time = 1;
				sleep1Sec();
				skill3Time = 0;
				skill3On = false;
				skill3Used = false;
			}
			
		}).start();
	}
	public static void sleep1Sec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
