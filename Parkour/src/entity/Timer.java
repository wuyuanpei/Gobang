package entity;

public class Timer {
	/**
	 * timeΪ������ʱ����currentTimeMillisʱ��
	 */
	private long time = 0;
	/**
	 * �����ʱ����ͣ��currentTimeMillisʱ��
	 */
	public Timer() {
		time = System.currentTimeMillis();
	}
	public int getMinutes() {
		return (int)(System.currentTimeMillis()-time)/60000;
	}
	public int getSeconds(){
		return (int)(System.currentTimeMillis()-time)%60000/1000;
	}
	public void init(){
		time=System.currentTimeMillis();
	}
}
