package controller;

public class Timer {
	private long time = 0;
	public Timer(){
		time = System.currentTimeMillis();
	}
	public int getMinutes(){
		return (int)(System.currentTimeMillis()-time)/60000;
	}
	public int getSeconds(){
		return (int)(System.currentTimeMillis()-time)%60000/1000;
	}
	public void init(){
		time = System.currentTimeMillis();
	}
}
