package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.Player;
import test.FightingGameTest;

public class Listener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (FightingGameTest.operatingComputer) {
			switch (code) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_D:
				FightingGameTest.gamePanel.infLiu = "正在右移...";
				Player.LIU.rightMove = true;
				Player.LIU.direction = 1;
				break;
			case KeyEvent.VK_A:
				FightingGameTest.gamePanel.infLiu = "正在左移...";
				Player.LIU.leftMove = true;
				Player.LIU.direction = -1;
				break;
			case KeyEvent.VK_SEMICOLON:
				FightingGameTest.gamePanel.infFu = "正在右移...";
				Player.FU.rightMove = true;
				Player.FU.direction = 1;
				break;
			case KeyEvent.VK_K:
				FightingGameTest.gamePanel.infFu = "正在左移...";
				Player.FU.leftMove = true;
				Player.FU.direction = -1;
				break;
			case KeyEvent.VK_W:
				FightingGameTest.gamePanel.infLiu = "正在跳跃...";
				Player.LIU.jumping();
				break;
			case KeyEvent.VK_O:
				FightingGameTest.gamePanel.infFu = "正在跳跃...";
				Player.FU.jumping();
				break;
			case KeyEvent.VK_S:
				FightingGameTest.gamePanel.infLiu = "正在格挡...";
				Player.LIU.shieldOn = true;
				break;
			case KeyEvent.VK_L:
				FightingGameTest.gamePanel.infFu = "正在格挡...";
				Player.FU.shieldOn = true;
				break;
			case KeyEvent.VK_Z:
				if (Player.LIU.skill1Used)
					return;
				if (Player.LIU.energy < 75)
					return;
				FightingGameTest.gamePanel.infLiu = "释放一技能...";
				if (!Player.LIU.skill1On) {
					Player.LIU.skill1On = true;
					Player.LIU.skill1(Player.FU);
				}
				break;
			case KeyEvent.VK_COMMA:
				if (Player.FU.skill1Used)
					return;
				if (Player.FU.energy < 75)
					return;
				FightingGameTest.gamePanel.infFu = "释放一技能...";
				if (!Player.FU.skill1On) {
					Player.FU.skill1On = true;
					Player.FU.skill1(Player.LIU);
				}
				break;
			case KeyEvent.VK_C:
				if (Player.LIU.energy < 150 || Player.LIU.skill3Used)
					return;
				Player.LIU.energy -= 150;
				FightingGameTest.gamePanel.infLiu = "释放大招...";
				Player.LIU.skill3();
				break;
			case KeyEvent.VK_SLASH:
				if (Player.FU.energy < 150 || Player.LIU.skill3Used)
					return;
				Player.FU.energy -= 150;
				FightingGameTest.gamePanel.infFu = "释放大招...";
				Player.FU.skill3();
				break;
			}
		}else{
			switch (code) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_D:
				FightingGameTest.gamePanel.infLiu = "正在右移...";
				Player.LIU.rightMove = true;
				Player.LIU.direction = 1;
				break;
			case KeyEvent.VK_A:
				FightingGameTest.gamePanel.infLiu = "正在左移...";
				Player.LIU.leftMove = true;
				Player.LIU.direction = -1;
				break;
			case KeyEvent.VK_RIGHT:
				FightingGameTest.gamePanel.infFu = "正在右移...";
				Player.FU.rightMove = true;
				Player.FU.direction = 1;
				break;
			case KeyEvent.VK_LEFT:
				FightingGameTest.gamePanel.infFu = "正在左移...";
				Player.FU.leftMove = true;
				Player.FU.direction = -1;
				break;
			case KeyEvent.VK_W:
				FightingGameTest.gamePanel.infLiu = "正在跳跃...";
				Player.LIU.jumping();
				break;
			case KeyEvent.VK_UP:
				FightingGameTest.gamePanel.infFu = "正在跳跃...";
				Player.FU.jumping();
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_U:
				FightingGameTest.gamePanel.infLiu = "正在格挡...";
				Player.LIU.shieldOn = true;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_NUMPAD4:
				FightingGameTest.gamePanel.infFu = "正在格挡...";
				Player.FU.shieldOn = true;
				break;
			case KeyEvent.VK_J:
				if (Player.LIU.skill1Used)
					return;
				if (Player.LIU.energy < 75)
					return;
				FightingGameTest.gamePanel.infLiu = "释放一技能...";
				if (!Player.LIU.skill1On) {
					Player.LIU.skill1On = true;
					Player.LIU.skill1(Player.FU);
				}
				break;
			case KeyEvent.VK_NUMPAD1:
				if (Player.FU.skill1Used)
					return;
				if (Player.FU.energy < 75)
					return;
				FightingGameTest.gamePanel.infFu = "释放一技能...";
				if (!Player.FU.skill1On) {
					Player.FU.skill1On = true;
					Player.FU.skill1(Player.LIU);
				}
				break;
			case KeyEvent.VK_L:
				if (Player.LIU.energy < 150 || Player.LIU.skill3Used)
					return;
				Player.LIU.energy -= 150;
				FightingGameTest.gamePanel.infLiu = "释放大招...";
				Player.LIU.skill3();
				break;
			case KeyEvent.VK_NUMPAD3:
				if (Player.FU.energy < 150 || Player.LIU.skill3Used)
					return;
				Player.FU.energy -= 150;
				FightingGameTest.gamePanel.infFu = "释放大招...";
				Player.FU.skill3();
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				int code = e.getKeyCode();
				if (FightingGameTest.operatingComputer) {
					switch (code) {
					case KeyEvent.VK_ESCAPE:
						System.exit(0);
						break;
					case KeyEvent.VK_D:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.rightMove = false;
						break;
					case KeyEvent.VK_A:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.leftMove = false;
						break;
					case KeyEvent.VK_SEMICOLON:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.rightMove = false;
						break;
					case KeyEvent.VK_K:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.leftMove = false;
						break;
					case KeyEvent.VK_Q:
						FightingGameTest.gamePanel.infLiu = "正在拾雪球...";
						Player.LIU.addBall();
						break;
					case KeyEvent.VK_I:
						FightingGameTest.gamePanel.infFu = "正在拾雪球...";
						Player.FU.addBall();
						break;
					case KeyEvent.VK_E:
						FightingGameTest.gamePanel.infLiu = "正在扔雪球...";
						Player.LIU.throwBall(Player.FU);
						break;
					case KeyEvent.VK_P:
						FightingGameTest.gamePanel.infFu = "正在扔雪球...";
						Player.FU.throwBall(Player.LIU);
						break;
					case KeyEvent.VK_S:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.shieldOn = false;
						break;
					case KeyEvent.VK_L:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.shieldOn = false;
						break;
					case KeyEvent.VK_Z:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.skill1On = false;
						break;
					case KeyEvent.VK_COMMA:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.skill1On = false;
						break;
					case KeyEvent.VK_X:
						if (Player.LIU.energy < 100 || Player.LIU.skill2Time != 0)
							return;
						Player.LIU.energy -= 100;
						FightingGameTest.gamePanel.infLiu = "释放二技能...";
						Player.LIU.skill2();
						break;
					case KeyEvent.VK_PERIOD:
						if (Player.FU.energy < 100 || Player.FU.skill2Time != 0)
							return;
						Player.FU.energy -= 100;
						FightingGameTest.gamePanel.infFu = "释放二技能...";
						Player.FU.skill2();
						break;
					}
				} else {
					switch (code) {
					case KeyEvent.VK_ESCAPE:
						System.exit(0);
						break;
					case KeyEvent.VK_D:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.rightMove = false;
						break;
					case KeyEvent.VK_A:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.leftMove = false;
						break;
					case KeyEvent.VK_RIGHT:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.rightMove = false;
						break;
					case KeyEvent.VK_LEFT:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.leftMove = false;
						break;
					case KeyEvent.VK_I:
						FightingGameTest.gamePanel.infLiu = "正在拾雪球...";
						Player.LIU.addBall();
						break;
					case KeyEvent.VK_NUMPAD5:
						FightingGameTest.gamePanel.infFu = "正在拾雪球...";
						Player.FU.addBall();
						break;
					case KeyEvent.VK_O:
						FightingGameTest.gamePanel.infLiu = "正在扔雪球...";
						Player.LIU.throwBall(Player.FU);
						break;
					case KeyEvent.VK_NUMPAD6:
						FightingGameTest.gamePanel.infFu = "正在扔雪球...";
						Player.FU.throwBall(Player.LIU);
						break;
					case KeyEvent.VK_S:
					case KeyEvent.VK_U:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.shieldOn = false;
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_NUMPAD4:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.shieldOn = false;
						break;
					case KeyEvent.VK_J:
						FightingGameTest.gamePanel.infLiu = "不动了...";
						Player.LIU.skill1On = false;
						break;
					case KeyEvent.VK_NUMPAD1:
						FightingGameTest.gamePanel.infFu = "不动了...";
						Player.FU.skill1On = false;
						break;
					case KeyEvent.VK_K:
						if (Player.LIU.energy < 100 || Player.LIU.skill2Time != 0)
							return;
						Player.LIU.energy -= 100;
						FightingGameTest.gamePanel.infLiu = "释放二技能...";
						Player.LIU.skill2();
						break;
					case KeyEvent.VK_NUMPAD2:
						if (Player.FU.energy < 100 || Player.FU.skill2Time != 0)
							return;
						Player.FU.energy -= 100;
						FightingGameTest.gamePanel.infFu = "释放二技能...";
						Player.FU.skill2();
						break;
					}
				}
			}
		}).start();

	}
}
