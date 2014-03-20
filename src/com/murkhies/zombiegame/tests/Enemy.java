package com.murkhies.zombiegame.tests;
import java.util.Random;

public class Enemy {

	Player player;
	boolean alive = true;
	int x, y;
	int speed;

	public Enemy(Player player) {
		this.player = player;
		x = new Random().nextInt(800);
		y = new Random().nextInt(600);
//		speed = new Random().nextInt(8)+1;
		speed = 1;
	}

	public void update() {
		if (x > player.getX()) {
			x -= speed;
		}
		if (x < player.getX()) {
			x += speed;
		}
		if (y > player.getY()) {
			y -= speed;
		}
		if (y < player.getY()) {
			y += speed;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
