package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import global.Param;
import test.ParkourTest;
import view.GamePanel;

/**
 * ¼üÅÌ¼àÌýÆ÷
 * @author WYP
 *
 */
public class Controller extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			jump();
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			stoop();
			break;
		case KeyEvent.VK_J:
		case KeyEvent.VK_SPACE:
			shoot();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:
			ParkourTest.SPEED = Param.FAST_SPEED;
			break;
		case KeyEvent.VK_K:
		case KeyEvent.VK_B:
			ParkourTest.player.skill();
		}
	}
	@Override
	public void keyReleased(KeyEvent e){
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			GamePanel.notStoop();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:
			ParkourTest.SPEED = Param.SLOW_SPEED;
			break;
		}
	}
	public void jump(){
		GamePanel.jump();
	}
	public void stoop(){
		GamePanel.stoop();
	}
	public void shoot(){
		GamePanel.shoot();
	}
}
