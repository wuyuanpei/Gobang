package listener;

import entity.Snake;
/**
 * �ߵļ����� �����ߵ��ƶ�
 * @author lenovo
 *
 */
public interface SnakeListener {
	/**
	 * �����Ƿ�ײ������
	 * @param snake
	 */
	public void snakeMoved(Snake snake1,Snake snake2);
}
