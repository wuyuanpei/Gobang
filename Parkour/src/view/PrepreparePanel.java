package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PrepreparePanel extends JPanel{

	private static final long serialVersionUID = -8584081843963119225L;
	
	URL url = this.getClass().getResource("preprepareBG.jpg");
	Image icon = Toolkit.getDefaultToolkit().getImage(url);
	ImageIcon img = new ImageIcon(url);
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(icon, 0, 0, img.getIconWidth(),img.getIconHeight(),null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString("游戏准备中...", 330,25);
	}
}
