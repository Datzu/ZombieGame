package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
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
	int speed = 0;
	int lastSpeed = 0;
	int health = 30;
	int maxDamage = 0;

	long time = System.currentTimeMillis();

	boolean shoot = true;

	public Boss(Start start, GameScreen gameScreen, Player player) {
		this.start = start;
		this.gameScreen = gameScreen;
		x = start.WIDTH / 2 - 120;
		y = -90;
		speed = start.WIDTH*3/100;
		lastSpeed = speed;
		image = new Art().getBoss(2);
		this.player = player;
		rec = new Rectangle(x + 15, y, 150, 100);
		rec2 = new Rectangle(x + 50, y + 5, 85, 62);
		new Thread(this).start();
	}

	@Override
	public void run() {
		super.run();
		
		int i = 0, iTmp = new Random().nextInt(20)+1;
		
		firtsFase();

		while (health > 0) {
			
			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					move();
					if (y > player.getY()-70) {
						lookUp();
					} else {
						lookDown();
					}
					shoot();
					rec = new Rectangle(x + 15, y, 150, 100);
					rec2 = new Rectangle(x + 50, y + 5, 85, 62);
					i++;
					if (i == iTmp) {
						i = 0;
						iTmp = new Random().nextInt(20)+1;
						changeDir();
					}
					Thread.sleep(200);
				} catch (Exception e) {
				}
			}
		}
		player.endGame(true);
	}
	
	private void changeDir() {
		speed = 30;
		maxDamage = 0;
		int iTmp;
		switch (dir) {
			case 0:
				iTmp = new Random().nextInt(start.HEIGHT*50)/100;
				while (y > iTmp) {
					
					time = (1000 / start.FPS) - (System.currentTimeMillis() - time);
					
					if (time > 0) {
						try {
							up();
							if (rec.intersects(player.getRec())) {
								player.hurt();
							}
							Thread.sleep(200);
						} catch (Exception e) {
						}
					}
				}
				
				speed = lastSpeed;
				break;
			case 2:
				iTmp = start.HEIGHT - (new Random().nextInt(200)+200); 
				while (y < iTmp) {
					
					time = (1000 / start.FPS) - (System.currentTimeMillis() - time);
					
					if (time > 0) {
						try {
							down();
							if (rec.intersects(player.getRec())) {
								player.hurt();
							}
							Thread.sleep(200);
						} catch (Exception e) {
						}
					}
				}
				
				speed = lastSpeed;
				break;
			default:
				break;
		}
	}

	private void shoot() {
		if (x-40 < player.getX() && x+50 > player.getX()) {
			gameScreen.newMissile();
		}
	}
	
	private void move() {
		switch (dir) {
			case 0:
				if (x > player.getX()) {
					left();
				}
				if (x < player.getX()-20) {
					right();
				}
				break;
			case 2:
				if (x > player.getX()) {
					left();
				}
				if (x < player.getX()) {
					right();
				}
				break;
			default:
				break;
		}
	}

	private void firtsFase() {
		
		while (y < start.HEIGHT*80/100) {
			
			time = (1000 / start.FPS) - (System.currentTimeMillis() - time);
			
			if (time > 0) {
				try {
					down();
					Thread.sleep(200);
				} catch (Exception e) {
				}
			}
		}
				
	}

	public void up() {
		image = Start.art.getBoss(0);
		y -= speed;
		dir = 0;
		rec = new Rectangle(x + 15, y, 150, 100);
		rec2 = new Rectangle(x + 50, y + 5, 85, 62);
	}
	
	private void lookUp() {
		image = Start.art.getBoss(0);
		dir = 0;
	}

	public void down() {
		image = Start.art.getBoss(2);
		y += speed;
		dir = 2;
		rec = new Rectangle(x + 15, y, 150, 100);
		rec2 = new Rectangle(x + 50, y + 5, 85, 62);
	}
	
	private void lookDown() {
		image = Start.art.getBoss(2);
		dir = 2;
	}

	public void left() {
		x -= speed;
		dir = 3;
	}

	public void right() {
		x += speed;
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

	public void paint(Graphics g) {
		for (int i = -8; i < health; i++) {
			g.drawImage(Start.art.getHeart(), x + i * 8, y - 10, gameScreen);
		}
		g.drawImage(image, x, y, 180, 100, gameScreen);
	}

	public Rectangle getRec() {
		return rec;
	}

	public Rectangle getRec2() {
		return rec2;
	}

	@SuppressWarnings("deprecation")
	public void die() {
		stop();
	}

	public void hurt() {
		if (maxDamage < 3) {
			maxDamage++;
			health--;
		}
	}

}
