package serialization;

import java.io.Serializable;

import test.ParkourTest;

public class Identity implements Serializable{
	private static final long serialVersionUID = -5872800730935213482L;
	private String name;
	private int power,coins,highestScore,lastDay;
	public boolean[] ownHeros = new boolean[ParkourTest.PLAYERS.size()];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getLastDay() {
		return lastDay;
	}
	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}
	public Identity(String name, int power, int coins, int highestScore, int lastDay) {
		super();
		this.name = name;
		this.power = power;
		this.coins = coins;
		this.highestScore = highestScore;
		this.lastDay = lastDay;
		ownHeros[0] = true;
	}
	public boolean[] getOwnHeros() {
		return ownHeros;
	}
	public void setOwnHeros(boolean[] ownHeros) {
		this.ownHeros = ownHeros;
	}
	
}
