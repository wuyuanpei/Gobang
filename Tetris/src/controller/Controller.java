package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.Block;
import entity.Ground;
import test.TetrisTest;
import util.Param;

public class Controller extends KeyAdapter {

	private Block currentBlock;
	private Ground ground;
	public static double DISPLAY_EACH_TIME = Param.STANDAD_DISPLAY_EACH_TIME;

	public Controller(Ground ground) {
		this.ground = ground;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			leftMove();
			TetrisTest.panel.repaint();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			rightMove();
			TetrisTest.panel.repaint();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			downMove();
			break;
		case KeyEvent.VK_SPACE:
			rotate();
			TetrisTest.panel.repaint();
			break;
		case KeyEvent.VK_C:
			TetrisTest.restartGame();
			break;
		case KeyEvent.VK_E:
			TetrisTest.exitGame();
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			originMove();
			break;
		case KeyEvent.VK_Q:
			TetrisTest.openHelpFrame();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			originMove();
		}
	}

	public void leftMove() {
		int[][] nodes = currentBlock.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i][1] == 0)
				return;
		}
		ground.dealNodes(currentBlock, false);
		for (int i = 0; i < nodes.length; i++) {
			nodes[i][1]--;
		}
		for (int i = 0; i < nodes.length; i++) {
			int x = nodes[i][0];
			int y = nodes[i][1];
			if (x < 0 || y < 0 || x >= ground.getBackground().length || y >= ground.getBackground()[x].length
					|| ground.getBackground()[x][y]) {
				for (int j = 0; j < nodes.length; j++) {
					nodes[j][1]++;
				}
				return;
			}
		}
	}

	public void rightMove() {
		int[][] nodes = currentBlock.getNodes();
		// ����Ѿ����������
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i][1] == Param.WIDTH - 1)
				return;
		}
		// ɾ��ԭ��
		ground.dealNodes(currentBlock, false);
		// ������
		for (int i = 0; i < nodes.length; i++) {
			nodes[i][1]++;
		}
		// �Ѿ���ռ�õĻ����ƻ���
		for (int i = 0; i < nodes.length; i++) {
			int x = nodes[i][0];
			int y = nodes[i][1];
			if (x < 0 || y < 0 || x >= ground.getBackground().length || y >= ground.getBackground()[x].length
					|| ground.getBackground()[x][y]) {
				for (int j = 0; j < nodes.length; j++) {
					nodes[j][1]--;
				}
				return;
			}
		}
	}

	public void rotate() {
		int[][] nodes = currentBlock.getNodes();
		// ɾ��ԭ��
		ground.dealNodes(currentBlock, false);
		rotateOnce();
		for (int i = 0; i < nodes.length; i++) {
			int x = nodes[i][0];
			int y = nodes[i][1];
			if (x < 0 || y < 0 || x >= ground.getBackground().length || y >= ground.getBackground()[x].length
					|| ground.getBackground()[x][y]) {
				rotateOnce();
				rotateOnce();
				rotateOnce();
				return;
			}
		}
	}

	private void rotateOnce() {
		//ֻ��O_BLOCK�ǲ���Ҫ��ת��
		if(this.currentBlock == Block.O_BLOCK) return;
		int[][] nodes = currentBlock.getNodes();
		// ��ȡ��һ��
		int[] firstNode = { nodes[0][0], nodes[0][1] };
		// ʹ���е��ȥ��������
		for (int i = 0; i < nodes.length; i++) {
			nodes[i][0] -= firstNode[0];
			nodes[i][1] -= firstNode[1];
		}
		// ��x��y����
		for (int i = 0; i < nodes.length; i++) {
			int temp = nodes[i][0];
			nodes[i][0] = nodes[i][1];
			nodes[i][1] = temp;
		}
		//���е�x��ɸ��� ����I_BLOCK
		if(this.currentBlock!=Block.I_BLOCK){
			for (int i = 0; i < nodes.length; i++) {
				nodes[i][0] =0-nodes[i][0];
			}
		}
		// ʹ���е���Ͼ�������
		for (int i = 0; i < nodes.length; i++) {
			nodes[i][0] += firstNode[0];
			nodes[i][1] += firstNode[1];
		}
	}

	public void originMove() {
		Controller.DISPLAY_EACH_TIME = Param.STANDAD_DISPLAY_EACH_TIME;
	}

	public void downMove() {
		Controller.DISPLAY_EACH_TIME = Param.FAST_DISPLAY_EACH_TIME;
	}

	public Block getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(Block currentBlock) {
		this.currentBlock = currentBlock;
	}

}
