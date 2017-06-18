package entity;

import java.awt.Color;

import util.Global;
import util.Param;

public enum Block {
	
	L_BLOCK(Global.L_SHAPE,new int[][]{{0,0},{-1,0},{1,0},{1,1}},Param.L_COLOR),
	O_BLOCK(Global.O_SHAPE,new int[][]{{0,0},{0,1},{-1,0},{-1,1}},Param.O_COLOR),
	T_BLOCK(Global.T_SHAPE,new int[][]{{0,0},{0,-1},{-1,0},{0,1}},Param.T_COLOR),
	I_BLOCK(Global.I_SHAPE,new int[][]{{0,0},{-1,0},{1,0},{2,0}},Param.I_COLOR),
	Z_BLOCK(Global.Z_SHAPE,new int[][]{{0,0},{-1,0},{1,1},{0,1}},Param.Z_COLOR),
	L2_BLOCK(Global.L2_SHAPE,new int[][]{{0,0},{-1,0},{1,0},{1,-1}},Param.L2_COLOR),
	Z2_BLOCK(Global.Z2_SHAPE,new int[][]{{0,0},{-1,0},{0,-1},{1,-1}},Param.Z2_COLOR);
	
	private int shape;
	
	private Color color;
	
	private final int[][] originNodes;
	
	public int[][] getOriginNodes() {
		return originNodes;
	}

	private int[][] nodes;
	
	private Block(int shape, int[][] nodes,Color color) {
		
		this.color = color;
		
		for(int i=0; i<nodes.length;i++){
			nodes[i][1]+=Param.WIDTH/2-1;
		}
		
		this.originNodes = new int[nodes.length][2];
		for(int i = 0;i<nodes.length;i++){
			for(int j = 0; j<nodes[i].length;j++){
				originNodes[i][j] = nodes[i][j];
			}
		}
		this.shape = shape;
		this.nodes = nodes;
		Global.BLOCKS.add(this);
	}
	public static Block getBlock(int shape){
		if(shape<Global.BLOCKS.size())
			return Global.BLOCKS.get(shape);
		else
			return null;
	}
	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int[][] getNodes() {
		return nodes;
	}

	public void setNodes(int[][] nodes) {
		this.nodes = nodes;
	}
	
	public void toOrigin(){
		for(int i = 0;i<originNodes.length;i++){
			for(int j = 0; j<originNodes[i].length;j++){
				nodes[i][j] = originNodes[i][j];
			}
		}
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
