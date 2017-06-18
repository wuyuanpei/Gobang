package view;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import entity.Ground;
import test.TetrisTest;
import util.Param;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1860979716621182121L;
	
	private Ground ground;
	
	public GamePanel(Ground ground) {
		this.setGround(ground);
		this.setSize(Param.WIDTH*Param.SIZE,Param.LENGTH*Param.SIZE);
		this.setBackground(Param.BACKGROUND_COLOR);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(int i = 0; i<ground.getBackground().length; i++){
			for(int j = 0; j<ground.getBackground()[i].length; j++){
				if(ground.getBackground()[i][j]){
					g.setColor(ground.getColorGround()[i][j]);
					g.fillRect(j*Param.SIZE+5,i*Param.SIZE,Param.SIZE-2, Param.SIZE-2);
				}
			}
		}
		g.setColor(Param.DEADLINE_COLOR);
		g.drawLine(0, Param.SIZE*4, Param.SIZE*Param.WIDTH+8,Param.SIZE*4);
		g.setColor(Param.FONT_COLOR);
		g.setFont(new Font("", Font.BOLD,15));
		g.drawString("分数："+TetrisTest.score,10,20);
		g.drawString("时间："+TetrisTest.timer.getMinutes()+"分"+TetrisTest.timer.getSeconds()+"秒", Param.SIZE*Param.WIDTH-100, 20);
		g.setColor(Param.DEADLINE_COLOR);
		g.setFont(new Font("",Font.ITALIC,12));
		g.drawString("死亡边界",Param.WIDTH*Param.SIZE-50, Param.SIZE*4-5);
		g.setColor(Param.BOX_COLOR);
		for(int i = 0;i<4;i++){
			g.drawLine(i,Param.SIZE*3,i,Param.SIZE*Param.LENGTH);
			g.drawLine(0,Param.SIZE*Param.LENGTH+i,Param.WIDTH*Param.SIZE+8,Param.SIZE*Param.LENGTH+i);
			g.drawLine(Param.WIDTH*Param.SIZE+i+5,Param.SIZE*3,Param.WIDTH*Param.SIZE+i+5,Param.SIZE*Param.LENGTH);
		}
	}
	public Ground getGround() {
		return ground;
	}
	public void setGround(Ground ground) {
		this.ground = ground;
	}
}
