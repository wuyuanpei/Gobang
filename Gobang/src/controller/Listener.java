package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import test.GobangAI;
import test.GobangTest;
import view.MainFrame;
import view.MainPanel;

public class Listener extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		y = (y - 9) / 45;
		x = (x - 10) / 45;
		if (y > 14)
			y = 14;
		if (x > 14)
			x = 14;
		if (y < 0)
			y = 0;
		if (x < 0)
			x = 0;
		if (GobangTest.GameMode == 1) {
			if (MainPanel.board[y][x] == 0) {
				if (MainPanel.whom)
					MainPanel.board[y][x] = 1;
				else
					MainPanel.board[y][x] = 2;
				MainPanel.whom = !MainPanel.whom;
				doWinning();
			}
		} else {
			if (MainPanel.board[y][x] == 0) {
				if (GobangTest.Player == 1 && MainPanel.whom) {
					// 玩家执黑
					MainPanel.board[y][x] = 1;
					MainPanel.whom = !MainPanel.whom;
					doWinning();
					if (MainPanel.winning == 0) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								MainPanel.add(GobangAI.go(MainPanel.board, false), false);
								MainPanel.whom = !MainPanel.whom;
								MainPanel.getInstance().repaint();
								doWinning();
							}
						}).start();
					}
				} else if (GobangTest.Player == 2 && !MainPanel.whom) {
					// 玩家执白
					MainPanel.board[y][x] = 2;
					MainPanel.whom = !MainPanel.whom;
					doWinning();
					if (MainPanel.winning == 0) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								MainPanel.add(GobangAI.go(MainPanel.board, true), true);
								MainPanel.whom = !MainPanel.whom;
								MainPanel.getInstance().repaint();
								doWinning();
							}
						}).start();
					}
				}
			}
		}
	}

	private void doWinning() {
		boolean win = identifyWinning(!MainPanel.whom);
		if (win) {
			if (MainPanel.whom)
				MainPanel.winning = 2;
			else
				MainPanel.winning = 1;
			MainFrame.getInstance().setEnabled(false);
			GobangTest.prepare();
			new Thread(new Runnable() {
				@Override
				public void run() {
					GobangTest.setGame(GobangTest.s.accept());
					MainFrame.getInstance().setEnabled(true);
					MainPanel.getInstance().repaint();
				}

			}).start();
		}
		MainPanel.getInstance().repaint();
	}

	private boolean identifyWinning(boolean aim) {
		int theAim;
		if (aim) {
			theAim = 1;
		} else {
			theAim = 2;
		}
		int[][] board = MainPanel.board;
		// 已经连五
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 11; i++) {
				if (board[j][i] == theAim && board[j][i + 1] == theAim && board[j][i + 2] == theAim
						&& board[j][i + 3] == theAim && board[j][i + 4] == theAim) {
					return true;
				}
			}
		}
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 11; i++) {
				if (board[i][j] == theAim && board[i + 1][j] == theAim && board[i + 2][j] == theAim
						&& board[i + 3][j] == theAim && board[i + 4][j] == theAim) {
					return true;
				}
			}
		}
		// /方向
		for (int j = 4; j < 15; j++) {
			for (int i = 0; i < 11; i++) {
				if (board[i][j] == theAim && board[i + 1][j - 1] == theAim && board[i + 2][j - 2] == theAim
						&& board[i + 3][j - 3] == theAim && board[i + 4][j - 4] == theAim) {
					return true;
				}
			}
		}
		// \方向
		for (int j = 0; j < 11; j++) {
			for (int i = 0; i < 11; i++) {
				if (board[i][j] == theAim && board[i + 1][j + 1] == theAim && board[i + 2][j + 2] == theAim
						&& board[i + 3][j + 3] == theAim && board[i + 4][j + 4] == theAim) {
					return true;
				}
			}
		}
		// 连四两边无挡
		// 横的判定
		// for (int j = 0; j < 15; j++) {
		// for (int i = 0; i < 10; i++) {
		// if (board[j][i] == 0 && board[j][i + 1] == theAim && board[j][i + 2]
		// == theAim
		// && board[j][i + 3] == theAim && board[j][i + 4] == theAim &&
		// board[j][i + 5] == 0) {
		// return true;
		// }
		// }
		// }
		// 竖的判定
		// for (int j = 0; j < 15; j++) {
		// for (int i = 0; i < 10; i++) {
		// if (board[i][j] == 0 && board[i + 1][j] == theAim && board[i + 2][j]
		// == theAim
		// && board[i + 3][j] == theAim && board[i + 4][j] == theAim && board[i
		// + 5][j] == 0) {
		// return true;
		// }
		// }
		// }
		// /方向的判定
		// for (int j = 5; j < 15; j++) {
		// for (int i = 0; i < 10; i++) {
		// if (board[i][j] == 0 && board[i + 1][j - 1] == theAim && board[i +
		// 2][j - 2] == theAim
		// && board[i + 3][j - 3] == theAim && board[i + 4][j - 4] == theAim &&
		// board[i + 5][j - 5] == 0) {
		// return true;
		// }
		// }
		// }
		// // \方向的判定
		// for (int j = 0; j < 10; j++) {
		// for (int i = 0; i < 10; i++) {
		// if (board[i][j] == 0 && board[i + 1][j + 1] == theAim && board[i +
		// 2][j + 2] == theAim
		// && board[i + 3][j + 3] == theAim && board[i + 4][j + 4] == theAim &&
		// board[i + 5][j + 5] == 0) {
		// return true;
		// }
		// }
		// }
		return false;
	}
}
