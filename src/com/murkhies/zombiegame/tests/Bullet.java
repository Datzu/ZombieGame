package com.murkhies.zombiegame.tests;

import com.murkhies.zombiegame.actors.Player;


public class Bullet {
	
	Player player;
	boolean alive = true;
	int x, y, dir;
	int speed = 20;

	public Bullet(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void update() {
		switch (dir) {
			case 0:
				y -= speed;
				break;
			case 1:
				y += speed;
				break;
			case 2:
				x -= speed;
				break;
			case 3:
				x += speed;
				break;
			default:
				break;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
