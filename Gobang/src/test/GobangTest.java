package test;

import java.awt.Image;
import java.awt.Toolkit;

import s_GUI_Richard.SFrame;
import s_GUI_Richard.SRadioLine;
import view.MainFrame;
import view.MainPanel;

public class GobangTest {
	public static int GameMode, Player;
	public static final Image icon = Toolkit.getDefaultToolkit().getImage(GobangTest.class.getResource("gobang.png"));

	public static void main(String[] args) {
		MainFrame f = MainFrame.getInstance();
		f.setVisible(true);
		f.setEnabled(false);
		prepare();
		setGame(s.accept());
		f.setEnabled(true);
	}

	public static SFrame s;

	public static void setGame(String arg[]) {
		MainPanel.winning = 0;
		MainPanel.whom = true;
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				MainPanel.board[i][j] = 0;
		if (arg[0].equals("˫��")) {
			GameMode = 1;
		} else {
			GameMode = 2;
			if (arg[1].equals("ִ��")) {
				Player = 1;
			} else {
				MainPanel.add(GobangAI.go(MainPanel.board, true),true);
				MainPanel.whom = false;
				Player = 2;
				MainPanel.getInstance().repaint();
			}
		}
	}

	public static void prepare() {
		s = new SFrame("������1.0", "��Ϸ����-��ʼ", 325, icon);
		s.addLine(new SRadioLine("��Ϸģʽ��", "�˻�", "˫��"));
		s.addLine(new SRadioLine("�˻��Ⱥ�", "ִ��", "ִ��"));
		s.setLocation(1000, 450);
		s.shw();

	}
}
