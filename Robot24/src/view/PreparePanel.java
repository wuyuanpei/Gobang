package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import test.Robot24Test;

public class PreparePanel extends JPanel{

	private static final long serialVersionUID = -8584081843963119225L;
	
	URL url = Robot24Test.class.getResource("bg2.png");
	Image icon = Toolkit.getDefaultToolkit().getImage(url);
	ImageIcon img = new ImageIcon(url);
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(icon, 0, 0, img.getIconWidth(),img.getIconHeight(),null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString(Robot24Test.prepareText, 20,255);
	}
}
