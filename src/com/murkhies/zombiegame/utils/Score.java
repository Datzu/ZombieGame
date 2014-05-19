package com.murkhies.zombiegame.utils;

public class Score {
	
	String name;
	int points;
	
	public Score(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public String toString() {
		return "Name: " + this.name + ", Points: " + this.points;
	}

}
