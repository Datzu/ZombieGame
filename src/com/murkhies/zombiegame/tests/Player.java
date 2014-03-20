package com.murkhies.zombiegame.tests;


public class Player extends Thread {
	
	int dir;
	
	int x = 0, y = 0;
	int speed = 10;

	boolean shoot = true;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void up() {
		y -= speed;
		dir = 0;
	}
	
	public void down() {
		y += speed;
		dir = 1;
	}
	
	public void left() {
		x -= speed;
		dir = 2;
	}
	
	public void right() {
		x += speed;
		dir = 3;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getDir() {
		return this.dir;
	}

	public boolean canShoot() {
		return this.shoot ;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

}
