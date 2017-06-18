package test;

public class Level {
	
	public static int level;
	
	public static int time;
	
	public static void levelInit(){
		level = 1;
		time = 59;
		Robot24Test.timer.init();
		Robot24Test.timer.setRestrictTime(time);
	}
	public static void nextLevel(){
		level++;
		if(time>44){
			time-=1;
		}
		Robot24Test.timer.init();
		Robot24Test.timer.setRestrictTime(time);
	}
}
