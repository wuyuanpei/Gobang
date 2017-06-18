package view;

import javax.swing.JFrame;

import controller.Listener;
import test.GobangTest;

public class MainFrame extends JFrame{
	private static final MainFrame MAIN_FRAME;
	static{
		MAIN_FRAME = new MainFrame();
	}
	public static MainFrame getInstance(){
		return MAIN_FRAME;
	}
	private static final long serialVersionUID = 1L;
	private MainFrame() {
		super("Îå×ÓÆå1.0");
		this.setIconImage(GobangTest.icon);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(693,693);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(MainPanel.getInstance());
		this.setUndecorated(true);
		this.addMouseListener(new Listener());
	}
}
