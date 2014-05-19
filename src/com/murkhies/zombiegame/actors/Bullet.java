package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;

public class Bullet extends Thread {

	Image image;
	int dir, x, y, heigth, width;
	int speed = 4;
	public boolean alive = true;
	GameScreen gameScreen;
	Start start;
	List<BasicZombie> basicZombieList;
	Boss boss;
	Player player;

	Rectangle rec;

	public Bullet(Player player, Start start, int dir, int heigth, int width,
			GameScreen gameScreen, List<BasicZombie> basicZombieList, Boss boss) {
		this.player = player;
		this.dir = dir;
		this.heigth = heigth;
		this.width = width;
		this.gameScreen = gameScreen;
		this.start = start;
		this.basicZombieList = basicZombieList;
		if (boss != null) {
			this.boss = boss;
		}
		image = Start.art.getBullet();
		switch (dir) {
		case 0:
			x = player.getX();
			y = player.getY() + 16;
		case 1:
			x = player.getX() + 26;
			y = player.getY() + 16;
			break;
		case 2:
			x = player.getX() + 4;
			y = player.getY() + 16;
			break;
		case 3:
			x = player.getX();
			y = player.getY() + 16;
			break;
		default:
			break;
		}
		rec = new Rectangle(x, y, 4, 4);
	}

	@Override
	public void run() {
		super.run();
		while (alive) {
			for (BasicZombie basicZombie : basicZombieList) {
				if (basicZombie.getRec() == null) {
					return;
				}
				if (rec.intersects(basicZombie.getRec())) {
					basicZombie.hurt();
					alive = false;
					gameScreen.endShoot(this);
					return;
				}
			}
			if (boss != null) {
				if (dir == boss.getDir()) {
					if (rec.intersects(boss.getRec2())) {
						boss.hurt();
						alive = false;
						gameScreen.endShoot(this);
						return;
					}
				} else {
					if (rec.intersects(boss.getRec())) {
						alive = false;
						gameScreen.endShoot(this);
						return;
					}
				}
			} else {

			}
			switch (dir) {
			case 0:
				y -= speed;
				if (y < 37) {
					alive = false;
				}
				break;
			case 1:
				x += speed;
				if (x > width - 25) {
					alive = false;
				}
				break;
			case 2:
				y += speed;
				if (y > heigth - 22) {
					alive = false;
				}
				break;
			case 3:
				x -= speed;
				if (x < 19) {
					alive = false;
				}
				break;
			default:
				break;
			}
			rec = new Rectangle(x, y, 4, 4);
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gameScreen.endShoot(this);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void paint(Graphics g) {
		g.drawImage(image, x, y, 4, 4, gameScreen);
	}

}
