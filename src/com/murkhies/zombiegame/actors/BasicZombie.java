package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.utils.Art;

public class BasicZombie extends Thread {

	int dir;

	Start start;
	GameScreen gameScreen;
	Image image;
	Player player;

	int x = 0, y = 0;
	int speed = new Random().nextInt(1)+2;
	int health = 2;

	boolean shoot = true;

	public BasicZombie(Start start, GameScreen gameScreen, Player player) {
		this.start = start;
		this.gameScreen = gameScreen;
		this.x = start.WIDTH / 2;
		this.y = start.HEIGHT / 2;
		image = new Art().getBasicZombie(0);
		this.player = player;
		new Thread(this).start();
	}

	@Override
	public void run() {
		super.run();
		while (health > 0) {
			long time = System.currentTimeMillis();
			
			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					update();
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}
	}

	public void up() {
		image = Start.art.getBasicZombie(0);
		y -= speed;
		dir = 0;
	}

	public void down() {
		image = Start.art.getBasicZombie(2);
		y += speed;
		dir = 1;
	}

	public void left() {
		image = Start.art.getBasicZombie(3);
		x -= speed;
		dir = 2;
	}

	public void right() {
		image = Start.art.getBasicZombie(1);
		x += speed;
		dir = 3;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public int getDir() {
		return this.dir;
	}

	public boolean canShoot() {
		return this.shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public void paint(Graphics g) {
		int tmp = x+8;
		for (int i = 0; i < health; i++) {
			g.drawImage(Start.art.getHeart(), tmp+i*10, y-10, gameScreen);
		}
		g.drawImage(image, x, y, 32, 32, gameScreen);
	}
	
	void update() {
		int tempSpeed = speed;
		speed /= 2;
		if (y < player.getY() && x > player.getX()) {
			up();
			left();
		}
		if (y > player.getY() && x < player.getX()) {
			up();
			right();
		}
		if (y < player.getY() && x < player.getX()) {
			down();
			right();
		}
		if (y < player.getY() && x > player.getX()) {
			down();
			left();
		}
		speed = tempSpeed;
		if (y > player.getY() + 20) {
			up();
		}
		if (x < player.getX() - 20) {
			right();
		}
		if (y < player.getY() - 10) {
			down();
		}
		if (x > player.getX() + 20) {
			left();
		}
		
	}

}
