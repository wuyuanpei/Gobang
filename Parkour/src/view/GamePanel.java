package view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entity.Obstacle;
import global.Param;
import test.ParkourTest;

public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 3307098431453469682L;
	
	private static double HEIGHT = Param.GROUND_LOCATION-45;
	//绘画状态
	private static boolean isJumping = false;//是否在跳
	private static boolean jumpingDir = false;//跳的状态 true是向上 false是向下
	private static boolean isStooping = false;
	private static boolean lazerOn = false;
	private int groundLocation1 = 0,groundLocation2 = 600;
	
	//不同英雄公用内容
	private ImageIcon ground;
	public ImageIcon getGround() {
		return ground;
	}
	public void setGround(ImageIcon ground) {
		this.ground = ground;
	}
	private final ImageIcon body,cloud,sun,bigSkill;//skillBG;
	public static final ImageIcon ground1 = new ImageIcon(GamePanel.class.getResource("ground.jpg"));
	public static final ImageIcon ground2 = new ImageIcon(GamePanel.class.getResource("sea.jpg"));
	private Obstacle obstacle;
	private static AudioClip jump = Applet.newAudioClip(GamePanel.class.getResource("jump.wav"));
	public GamePanel(){
		this.setBackground(Param.BACKGROUND_COLOR);
		URL url1 = this.getClass().getResource("body.png");
//		URL url2 = this.getClass().getResource("skillBG.png");
		URL url3 = this.getClass().getResource("cloud.jpg");
		URL url4 = this.getClass().getResource("sun.png");
		URL url5 = this.getClass().getResource("bigSkill.png");
		body = new ImageIcon(url1);
//		skillBG = new ImageIcon(url2);
	    cloud = new ImageIcon(url3);
	    sun = new ImageIcon(url4);
	    bigSkill = new ImageIcon(url5);
	    ground = ground1;
	}
	public void changeGround(){
		if(ground == ground1)
			ground = ground2;
		else
			ground = ground1;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGround(g);
		drawCloud(g);
		drawSkillIcon(g);
		drawHead(g);
		drawSun(g);
		drawLazer(g); 
		drawLiuBody(g);
		drawLiuHead(g); 
		drawTime(g);
		drawScore(g);
		obstacle.paintObstacle(g);//只有这个是每个不一样单独实现的
		drawBigSkill(g);
	}
	public boolean drawSkill = false;
	public void drawBigSkill(Graphics g){
		if(drawSkill)
			bigSkill.paintIcon(null, g, 220,100);
	}
	public void drawSkillIcon(Graphics g){
//		skillBG.paintIcon(null, g, 247,17);
//		skillBG.paintIcon(null, g, 337,17);
		ParkourTest.player.drawIcon(g);
		if(ParkourTest.player.isSkillUsed()){
			g.setColor(Color.RED);
			g.fillRect(335,35,60,25);
			g.setColor(Color.BLACK);
			g.setFont(new Font("",Font.BOLD,25));
			g.drawString("Used", 335,55);
		}
	}
	public void drawHead(Graphics g){
		ParkourTest.player.getHead().paintIcon(null, g, 5, 5);
		g.setColor(Color.PINK);
		g.fillRect(70, 50, 150, 15);
		g.setColor(Color.RED);
		g.drawRect(70, 50, 150, 15);
		g.setFont(new Font("",Font.PLAIN,12));
		g.drawString("能量："+ParkourTest.energy/50*50+"/"+ParkourTest.player.getEnergy(), 70,35);
		g.fillRect(70, 50, ParkourTest.energy*150/ParkourTest.player.getEnergy(), 15);
		g.setColor(Param.FONT_COLOR);
		g.drawString(ParkourTest.player.getName(),175, 35);
	}
	public void drawTime(Graphics g){
		g.setColor(Param.FONT_COLOR);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString("时间："+ParkourTest.timer.getMinutes()+"分"+ParkourTest.timer.getSeconds()+"秒", Param.WIDTH-125,Param.LENGTH-45);
	}
	public void drawScore(Graphics g){
		g.setColor(Param.FONT_COLOR);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString("分数："+ParkourTest.score/10,15,Param.LENGTH-45);
	}
	public static int countSec = 0;
	private AudioClip birdDie = Applet.newAudioClip(this.getClass().getResource("birdDie.wav"));
	private AudioClip animalDie = Applet.newAudioClip(this.getClass().getResource("animalDie.wav"));
	public void drawLazer(Graphics g){
		if((!lazerOn)&&(obstacle.equals(Obstacle.MONSTER)||obstacle.equals(Obstacle.BIRD))){
			g.setColor(Color.RED);
			g.fillRect(Obstacle.getDistance()+50, Param.GROUND_LOCATION-100,30,7);
		}//如果不开火一定是满的
		if(!lazerOn) return;
		if(countSec==0){
			ParkourTest.player.getWeaponSound().play();
		}
		if(countSec<200){
			g.setColor(Color.RED);
			g.fillRect(245,35,60,25);
			g.setColor(Color.BLACK);
			g.setFont(new Font("",Font.BOLD,25));
			g.drawString("Used", 245,55);
		}
		ParkourTest.player.getLazer().paintIcon(null, g, 65, (int)getHEIGHT());
		countSec++;
		if(countSec ==ParkourTest.player.getLazerTime()){//火炮正常速度下持续 （次数*时间/次）秒钟
			lazerOn = false;
			countSec = 0;
		}
		if((obstacle.equals(Obstacle.MONSTER)||obstacle.equals(Obstacle.BIRD))&&countSec%ParkourTest.player.getLazerPower()==0){
			if(obstacle.equals(Obstacle.MONSTER)){
				animalDie.play();
			}else{
				birdDie.play();
			}
			Obstacle.setDistance(-100);
		}
		if(obstacle.equals(Obstacle.MONSTER)||obstacle.equals(Obstacle.BIRD)){
			g.setColor(Color.RED);
			g.fillRect(Obstacle.getDistance()+50, Param.GROUND_LOCATION-100,(ParkourTest.player.getLazerPower()-countSec%ParkourTest.player.getLazerPower())*30/ParkourTest.player.getLazerPower(),7);
		}
	}
	public static ImageIcon imageBefore = ground1,imageAfter = ground1;
	private boolean alreadyChange = false;
	public void drawGround(Graphics g){
		if(groundLocation1 == -600){
			groundLocation1 =600;
			if(new Random().nextInt(15) == 0){
				changeGround();
				imageBefore = ground;
				alreadyChange = true;
			}
		}
		if(groundLocation2 == -600){
			groundLocation2 = 600;
			if(alreadyChange){
				imageAfter = ground;
				alreadyChange = false;
			}
		}
		imageBefore.paintIcon(null, g, groundLocation1,Param.GROUND_LOCATION);
		imageAfter.paintIcon(null, g, groundLocation2,Param.GROUND_LOCATION);
		groundLocation1-=Param.EACH_MOVE;	
		groundLocation2-=Param.EACH_MOVE;	
	}
	public void drawCloud(Graphics g){
		cloud.paintIcon(null, g, groundLocation1,20);
		cloud.paintIcon(null, g, groundLocation2,20);
	}
	public void drawSun(Graphics g){
		sun.paintIcon(null, g, Param.WIDTH-130,5);
	}
	
	public void drawLiuHead(Graphics g){
		if(isJumping){
			if(jumpingDir){
				setHEIGHT(getHEIGHT() * 120);
				setHEIGHT(getHEIGHT() / 121);
			}else{
				setHEIGHT(getHEIGHT() * 521);
				setHEIGHT(getHEIGHT() / 520);
			}
			if(getHEIGHT() < Param.GROUND_LOCATION-120){
				jumpingDir = false;
			}
			if(getHEIGHT() >=Param.GROUND_LOCATION-45){
				isJumping = false;
			}
			
		}
		if(isStooping())
			ParkourTest.player.getStoopHead().paintIcon(null, g, 60,(int)getHEIGHT()+25);
		else
		    ParkourTest.player.getSmallHead().paintIcon(null, g,60,(int)getHEIGHT());
	}
	public void drawLiuBody(Graphics g){
		if(isStooping())
			body.paintIcon(null, g, 10,(int)getHEIGHT()+23);
		else
		    body.paintIcon(null, g, 10,(int)getHEIGHT()+9);
	}
	public Obstacle getObstacle() {
		return obstacle;
	}
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	public static void jump(){
		if(ParkourTest.energy<ParkourTest.player.getJumpLimit()) return;
		ParkourTest.energy-=ParkourTest.player.getJumpLimit();
		isJumping = true;
		jumpingDir = true;
		jump.play();
	}
	public static void stoop(){
		setStooping(true);
	}
	public static void notStoop(){
		setStooping(false);
	}
	public static void shoot(){
		if(isStooping()||lazerOn||ParkourTest.energy<ParkourTest.player.getLazerLimit()) return;
		ParkourTest.energy-=ParkourTest.player.getLazerLimit();
		lazerOn = true;
	}
	public static double getHEIGHT() {
		return HEIGHT;
	}
	public static void setHEIGHT(double hEIGHT) {
		HEIGHT = hEIGHT;
	}
	public static boolean isStooping() {
		return isStooping;
	}
	public static void setStooping(boolean isStooping) {
		GamePanel.isStooping = isStooping;
	}
}
