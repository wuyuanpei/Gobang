package entity;

public class Timer {
	/**
	 * time为创建计时器的currentTimeMillis时间
	 */
	private long time = 0;
	/**
	 * 储存计时器暂停的currentTimeMillis时间
	 */
	private long stopPoint = 0;
	public Timer() {
		time = System.currentTimeMillis();
	}
	public int getMinutes() {
		return (int)(System.currentTimeMillis()-time)/60000;
	}
	public int getSeconds(){
		return (int)(System.currentTimeMillis()-time)%60000/1000;
	}
	public void stopTiming(){
		stopPoint = System.currentTimeMillis();
	}
	public void startTiming(){
		time+=(System.currentTimeMillis()-stopPoint);
	}
	public void init(){
		time=System.currentTimeMillis();
	}
}
