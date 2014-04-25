package com.murkhies.zombiegame.actors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.utils.Art;

public class Boss extends Thread {

	int dir;

	Start start;
	GameScreen gameScreen;
	Image image;
	Player player;

	Rectangle rec;
	Rectangle rec2;

	int x = 0, y = 0;
	int speed = 30;
	int health = 30;

	long time = System.currentTimeMillis();

	boolean shoot = true;

	public Boss(Start start, GameScreen gameScreen, Player player) {
		this.start = start;
		this.gameScreen = gameScreen;
		x = start.WIDTH / 2 - 120;
		y = -90;
		image = new Art().getBoss(2);
		this.player = player;
		rec = new Rectangle(x + 15, y, 150, 100);
		rec2 = new Rectangle(x + 50, y + 5, 85, 62);
		new Thread(this).start();
	}

	@Override
	public void run() {
		super.run();

		while (health > 0) {
			
			firtsFase();
			
			boolean goDown = true;

			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					move();
					if (y > player.getY()) {
						lookUp();
					} else {
						lookDown();
					}
					shoot();
					rec = new Rectangle(x + 15, y, 150, 100);
					rec2 = new Rectangle(x + 50, y + 5, 85, 62);
					Thread.sleep(200);
				} catch (Exception e) {
				}
			}
		}
	}
	
	private void shoot() {
		if (x-50 < player.getX() && x+50 > player.getX()) {
			gameScreen.newMissile();
		}
	}
	
	private void move() {
		if (x > player.getX()-20) {
			left();
		} else {
			right();
		}
	}

	private void firtsFase() {
		
		while (y < 400) {
			
			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);
			
			if (time > 0) {
				try {
					down();
					Thread.sleep(200);
				} catch (Exception e) {
				}
			}
		}
		
		speed = 20;
		
	}

	public void up() {
		image = Start.art.getBoss(0);
		y -= speed;
		dir = 0;
	}
	
	private void lookUp() {
		image = Start.art.getBoss(0);
		dir = 0;
	}

	public void down() {
		image = Start.art.getBoss(2);
		y += speed;
		dir = 1;
	}
	
	private void lookDown() {
		image = Start.art.getBoss(2);
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
		return this.shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < health; i++) {
			g.drawImage(Start.art.getHeart(), x + i * 8, y - 10, gameScreen);
		}
		g.drawImage(image, x, y, 180, 100, gameScreen);
		g.setColor(Color.blue);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(rec);
		g.setColor(Color.green);
		g2d.draw(rec2);
	}

	public Rectangle getRec() {
		return rec;
	}

	public Rectangle getRec2() {
		return rec2;
	}

	public void die() {
		stop();
	}

	public void hurt() {
		health--;
		if (health == 0) {

		}
	}

}
