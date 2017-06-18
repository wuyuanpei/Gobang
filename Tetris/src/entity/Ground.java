package entity;

import java.awt.Color;

import test.TetrisTest;
import util.Param;

public class Ground {
	
	private boolean moving = false;
	
	private boolean[][] background= new boolean[Param.LENGTH][Param.WIDTH];
	private Color[][] colorGround= new Color[Param.LENGTH][Param.WIDTH];
	
	public boolean[][] getBackground() {
		return background;
	}

	public void setBackground(boolean[][] background) {
		this.background = background;
	}

	public Ground() {
		super();
	}
	public void dealNodes(Block block,boolean dealing){
		int[][] nodes = block.getNodes();
		for(int i = 0;i<nodes.length;i++){
			int x = nodes[i][0];
			int y = nodes[i][1];
			if(x<0||x>=Param.LENGTH||y<0||y>=Param.WIDTH) return;
			background[x][y] = dealing;
			if(dealing)
				colorGround[x][y] = block.getColor();
			else
				colorGround[x][y] = null;
		}
		
	}
	public void addBlock(Block block){
		dealNodes(block,true);
	}
	public void downMove(Block block){
		int[][] nodes = block.getNodes();
		//如果碰到底,直接返回
		for(int i = 0;i<nodes.length;i++){
			if(nodes[i][0]==Param.LENGTH-1){
				this.moving = false;
				dealNodes(block,true);	
				block.toOrigin();
				return;
			}
		}
		//原画删除
		dealNodes(block,false);		
		//向下移
		for(int i = 0;i<nodes.length;i++){
			nodes[i][0]++;
		}		
		//如果新位置已被占用，向上移
		for(int j = 0;j<nodes.length;j++){
			int x = nodes[j][0];
			int y = nodes[j][1];
			if(background[x][y]){
				for(int i = 0;i<nodes.length;i++){
					nodes[i][0]--;
				}
				dealNodes(block,true);
				this.moving = false;
				block.toOrigin();
				return;
			}
		}
		//画出
		dealNodes(block,true);
	}
	private void deleteOneLine(int x){
		if(x<0||x>=Param.LENGTH) return;
		for(int i =0; i<background[x].length;i++){
			background[x][i] = false;
			colorGround[x][i] = null;
		}
	}
	private void downMoveLine(int x){
		if(x<1||x>=Param.LENGTH) return;
		for(int i =x; i>0;i--){
			for(int j=0;j<background[i].length;j++){
				background[i][j] = background[i-1][j];
				colorGround[i][j] = colorGround[i-1][j];
			}
		}
	}
	public void delete(){
		//i 对应行 j 对应列
		for(int i = background.length-1; i>=0;i--){
			for(int j = 0; j<background[i].length; j++){
				if(!background[i][j]){
					break;
				}
				if(j==background[i].length-1){
					deleteOneLine(i);
					TetrisTest.score+=100;
					downMoveLine(i++);
					break;
				}
			}
		}
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public void identifyLiving(){
		for(int i = 0;i<background[3].length;i++){
			if(background[3][i]){
				TetrisTest.isAlive = false;
				break;
			}
		}
	}
	public void clearBackground(){
		for(int i = 0;i<background.length;i++){
			for(int j = 0;j<background[i].length;j++){
				background[i][j] = false;
			}
		}
	}

	public Color[][] getColorGround() {
		return colorGround;
	}

	public void setColorGround(Color[][] colorGround) {
		this.colorGround = colorGround;
	}
}
