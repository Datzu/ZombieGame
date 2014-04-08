package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.utils.Art;

public class Bullet extends Thread {
	
	Image image;
	int dir, x, y, heigth, width;
	int speed = 4;
	boolean alive = true;
	GameScreen gameScreen;
	Start start;
	
	public Bullet(Player player, Start start, int dir, int heigth,int width, GameScreen gameScreen) {
		this.dir = dir;
		this.heigth = heigth;
		this.width = width;
		this.gameScreen = gameScreen;
		this.start = start;
		image = Start.art.getBullet();
		switch (dir) {
			case 0:
				x = player.getX();
				y = player.getY()+16;
			case 1:
				x = player.getX()+26;
				y = player.getY()+16;
				break;
			case 2:
				x = player.getX()+4;
				y = player.getY()+16;
				break;
			case 3:
				x = player.getX();
				y = player.getY()+16;
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
					if (y < 37) {
						alive = false;
					}
					break;
				case 1:
					x += speed;
					if (x > width-25) {
						alive = false;
					}
					break;
				case 2:
					y += speed;
					if (y > heigth-22) {
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
