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

	float x = 0, y = 0;
	float speed = (float) (new Random().nextDouble()+0.3);
	int health = 10;

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
		image = new Art().getBasicZombie(0);
		y -= speed;
		dir = 0;
	}

	public void down() {
		image = new Art().getBasicZombie(2);
		y += speed;
		dir = 1;
	}

	public void left() {
		image = new Art().getBasicZombie(3);
		x -= speed;
		dir = 2;
	}

	public void right() {
		image = new Art().getBasicZombie(1);
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
		g.drawImage(image, (int) x, (int) y, 32, 32, gameScreen);
	}
	
	void update() {
		if (y > player.getY()) {
			up();
		}else if (y < player.getY()) {
			down();
		}
		if (x < player.getX()) {
			right();
		} else if (x > player.getX()) {
			left();
		}
	}

}
