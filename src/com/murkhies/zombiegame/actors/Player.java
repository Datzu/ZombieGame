package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.screens.TitleScreen;
import com.murkhies.zombiegame.utils.InputHandler;

public class Player extends Thread {

	int dir;

	Start start;
	GameScreen gameScreen;
	Image image;
	InputHandler inputHandler;

	int x = 0, y = 0;
	int speed = 2;
	int maxHealth = 5;
	int health = maxHealth;
	int maxBullets = 50;
	int bullets = maxBullets;
	
	Rectangle rec;

	boolean shoot = true;

	public Player(Start start, GameScreen gameScreen, InputHandler inputHandler) {
		this.start = start;
		this.gameScreen = gameScreen;
		this.x = start.WIDTH / 2;
		this.y = start.HEIGHT / 2;
		rec = new Rectangle(x, y, 32, 32);
		image = Start.art.getPlayer(0);
		this.inputHandler = inputHandler;
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
		start.stopAll();
		start.changePanel(new TitleScreen(start));
	}
	
	public void hurt() {
		health -= 2;
	}

	public void up() {
		image = Start.art.getPlayer(0);
		if (y + speed > 0+30) {
			y -= speed;
		}
		dir = 0;
	}

	public void down() {
		image = Start.art.getPlayer(2);
		if (y - speed < start.HEIGHT-60) {
			y += speed;
		}
		dir = 2;
	}

	public void left() {
		image = Start.art.getPlayer(3);
		if (x + speed > 0+25) {
			x -= speed;
		}
		dir = 3;
	}

	public void right() {
		image = Start.art.getPlayer(1);
		if (x - speed < start.WIDTH-60) {
			x += speed;
		}
		dir = 1;
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
		return this.shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	public int getBullets() {
		return bullets;
	}

	public void paint(Graphics g) {
		int tmp = x-6;
		for (int i = 0; i < health; i++) {
			g.drawImage(Start.art.getHeart(), tmp+i*10, y-10, gameScreen);
		}
		g.drawImage(image, x, y, 32, 32, gameScreen);
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
		if (inputHandler.isKeyDown(KeyEvent.VK_SPACE)) {
			if (bullets > 0) {
				new Explosion(this).paint(start.getGraphics());
				bullets--;
				gameScreen.newShoot();
				inputHandler.setKeys(KeyEvent.VK_SPACE, false);
			}
		}
		rec.setBounds(x, y, 32, 32);
		for (AmmoBox ammoBox : gameScreen.ammoBoxList) {
			if (rec.intersects(ammoBox.rec)) {
				if (bullets == maxBullets) {
					return;
				}
				if (bullets + 20 <= maxBullets) {
					bullets += 20;
				} else if (bullets + 20 > maxBullets) {
					bullets = maxBullets;
				}
				gameScreen.removeAmmoBox(ammoBox);
				return;
			}
		}
		for (Heart heart : gameScreen.heartList) {
			if (rec.intersects(heart.rec)) {
				if (health == maxHealth) {
					return;
				}
				if (health + 1 <= maxHealth) {
					health++;
				} else if (bullets + 1 > maxBullets) {
					health = maxHealth;
				}
				gameScreen.removeHeart(heart);
				return;
			}
		}
	}
	
	public Rectangle getRec() {
		return rec;
	}

	public int getMaxBullets() {
		return maxBullets;
	}

}
