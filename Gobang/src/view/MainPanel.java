package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import test.GobangAI;

public class MainPanel extends JPanel {
	private static final MainPanel MAIN_PANEL;
	private static final ImageIcon BOARD;
	public static boolean whom = true;// true是黑棋下
	public static final int[][] board = new int[15][15];
	static {
		BOARD = new ImageIcon(MainPanel.class.getResource("board.jpg"));
		MAIN_PANEL = new MainPanel();
	}

	public static MainPanel getInstance() {
		return MAIN_PANEL;
	}

	private static final long serialVersionUID = 1L;

	private MainPanel() {
	}

	public static int winning = 0;

	public void paint(Graphics g) {
		super.paint(g);
		BOARD.paintIcon(null, g, 0, 0);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int a = board[i][j];
				if (a != 0) {
					if (a == 1)
						g.setColor(Color.BLACK);
					else
						g.setColor(new Color(235, 235, 235));
					g.fillOval(10 + j * 45, 9 + i * 45, 43, 43);
				}
				g.setColor(Color.GREEN);
				g.setFont(new Font("华文新魏",Font.BOLD,30));
//				if(GobangAI.scoreC[i][j]!=0)
//				g.drawString(""+GobangAI.scoreC[i][j],20 + j * 45, 40 + i * 45);
			}
		}
		
		if (winning != 0) {
			g.setFont(new Font("华文新魏",Font.BOLD,100));
			g.setColor(Color.RED);
			g.drawString("Game Over", 30,200);
			g.setFont(new Font("华文新魏",Font.BOLD,100));
			if (winning == 1){
				g.setColor(Color.BLACK);
				g.drawString("黑棋胜利", 20, 100);
			}
			else{
				g.setColor(new Color(235, 235, 235));
				g.drawString("白棋胜利", 20, 100);
			}
		}
	}
	public static void add(GobangAI.Point p,boolean who){
		if(who)
			board[p.y][p.x] = 1;
		else
			board[p.y][p.x] = 2;
	}
}
