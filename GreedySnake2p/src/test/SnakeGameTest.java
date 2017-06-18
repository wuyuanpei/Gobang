package test;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import control.Controller;
import entity.*;
import util.Global;
import view.GamePanel;
import view.OperatorPanel;

public class SnakeGameTest {
private static Controller c;
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("GreedySnake2.3 双人无尽版");
		//实体对象
		Snake snake1 = new Snake(1);
		Snake snake2 = new Snake(2);
		
		snake1.setSnakeOther(snake2);
		snake2.setSnakeOther(snake1);
		
		Food food = new Food();
		Ground ground = new Ground();
		Timer timer = new Timer();
		//视图对象
		GamePanel gamePanel = new GamePanel();
		OperatorPanel operatorPanel = new OperatorPanel(snake1,snake2,food,timer,gamePanel);
		gamePanel.setBackground(new Color(250,250,210));
		operatorPanel.setBackground(Color.BLACK);
		
		gamePanel.addKeyListener(c);
		
		
		//控制器的创建  既是蛇的监听器，也是键盘的监听器
		c = new Controller(snake1,snake2,food,ground,timer,gamePanel,frame,operatorPanel);
		
		snake1.addSnakeListener(c);
		snake2.addSnakeListener(c);
		gamePanel.addKeyListener(c);
		frame.setSize(Global.CEIL_SIZE*Global.WIDTH+5,Global.CEIL_SIZE*Global.HEIGHT+66);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		
		//let panel get focus
		gamePanel.setFocusable(true);
		frame.add(gamePanel,BorderLayout.CENTER);
		frame.add(operatorPanel,BorderLayout.SOUTH);
		//start the game
		c.startGame();
		
		//manifest
		frame.setVisible(true);
	}

}
