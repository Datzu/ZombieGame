package com.murkhies.zombiegame.actors;

import java.awt.Image;
import java.awt.event.KeyEvent;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.utils.Art;
import com.murkhies.zombiegame.utils.InputHandler;

public class Player extends Thread {

	int dir;

	Start start;
	GameScreen gameScreen;
	Image image;
	public InputHandler inputHandler;

	float x = 0, y = 0;
	float speed = 5f;
	int health = 10;

	boolean shoot = true;

	public Player(Start start, GameScreen gameScreen) {
		this.start = start;
		this.gameScreen = gameScreen;
		this.x = start.WIDTH / 2;
		this.y = start.HEIGHT / 2;
		image = new Art().getPlayer(0);
		inputHandler = new InputHandler(gameScreen);
	}

	@Override
	public void run() {
		super.run();
		while (health > 0) {
			long time = System.currentTimeMillis();

			update();
			
			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}
	}

	public void up() {
		image = new Art().getPlayer(0);
		y -= speed;
		dir = 0;
	}

	public void down() {
		image = new Art().getPlayer(2);
		y += speed;
		dir = 1;
	}

	public void left() {
		image = new Art().getPlayer(3);
		x -= speed;
		dir = 2;
	}

	public void right() {
		image = new Art().getPlayer(1);
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

	public void paint() {
		start.getGraphics().drawImage(image, (int) x, (int) y, 32, 32, gameScreen);
	}
	
	void update() {
		if (inputHandler.isKeyDown(KeyEvent.VK_D)) {
			right();
		}
		if (inputHandler.isKeyDown(KeyEvent.VK_A)) {
			left();
		}
		if (inputHandler.isKeyDown(KeyEvent.VK_S)) {
			down();
		}
		if (inputHandler.isKeyDown(KeyEvent.VK_W)) {
			up();
		}
	}

}
