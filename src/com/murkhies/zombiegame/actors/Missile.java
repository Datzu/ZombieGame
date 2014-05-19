package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;

public class Missile extends Thread {

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

	public Missile(Player player, Start start, int dir, int heigth, int width,
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
		image = Start.art.getMissile(dir);
		switch (dir) {
		case 0:
			x = boss.getX() + 500;
			y = boss.getY() + 200;
			rec = new Rectangle(x, y, 8, 20);
		case 1:
			x = boss.getX() + 26;
			y = boss.getY() + 16;
			rec = new Rectangle(x, y, 20, 8);
			break;
		case 2:
			x = boss.getX() + 4;
			y = boss.getY() + 16;
			rec = new Rectangle(x, y, 8, 20);
			break;
		case 3:
			x = boss.getX();
			y = boss.getY() + 16;
			rec = new Rectangle(x, y, 20, 8);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		super.run();
		while (alive) {
			
			switch (dir) {
			case 0:
				y -= speed;
				rec = new Rectangle(x, y, 8, 20);
				if (y < 37) {
					alive = false;
				}
				break;
			case 1:
				x += speed;
				rec = new Rectangle(x, y, 20, 8);
				if (x > width - 25) {
					alive = false;
				}
				break;
			case 2:
				y += speed;
				rec = new Rectangle(x, y, 8, 20);
				if (y > heigth - 22) {
					alive = false;
				}
				break;
			case 3:
				x -= speed;
				rec = new Rectangle(x, y, 20, 8);
				if (x < 19) {
					alive = false;
				}
				break;
			default:
				break;
			}
			if (rec.intersects(player.getRec())) {
				player.hurt();
				alive = false;
				break;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gameScreen.missileExplosion(this);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void paint(Graphics g) {
		if (dir == 0 || dir == 2) {
			g.drawImage(image, x, y, 8, 20, gameScreen);
		} else if (dir == 1 || dir == 3) {
			g.drawImage(image, x, y, 20, 8, gameScreen);
		}
	}

}
