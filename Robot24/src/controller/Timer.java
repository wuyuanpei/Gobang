package controller;

public class Timer {
	/**
	 * time为创建计时器的currentTimeMillis时间
	 */
	private long time = 0;
	private int restrictTime;
	public Timer() {
	}
	public int getSeconds(){
		return restrictTime-(int)(System.currentTimeMillis()-time)%60000/1000;
	}
	public void init(){
		time=System.currentTimeMillis();
	}
	public int getRestrictTime() {
		return restrictTime;
	}
	public void setRestrictTime(int restrictTime) {
		this.restrictTime = restrictTime;
	}
}
