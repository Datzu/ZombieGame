package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;

import com.murkhies.zombiegame.Start;

public class Explosion {
	
	Image image;
	Player player;
	
	public Explosion(Player player) {
		this.player = player;
		image = Start.art.getExplosion();
	}
	
	public void paint(Graphics g) {
		switch (player.dir) {
			case 1:
				g.drawImage(image, player.getX()+30, player.getY()+15, player.gameScreen);
				break;
			case 2:
				g.drawImage(image, player.getX()+3, player.getY()+15, player.gameScreen);
				break;
			case 3:
				g.drawImage(image, player.getX()-5, player.getY()+15, player.gameScreen);
				break;
			default:
				break;
		}
	}

}
