package listener;

import entity.Snake;
/**
 * 蛇的监听器 监听蛇的移动
 * @author lenovo
 *
 */
public interface SnakeListener {
	/**
	 * 监听是否撞到东西
	 * @param snake
	 */
	public void snakeMoved(Snake snake1,Snake snake2);
}
