package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;

public class ExplosionOnCoord {

	Image image;
	GameScreen gameScreen;
	int x, y;

	public ExplosionOnCoord(int x, int y, GameScreen gameScreen) {
		this.x = x;
		this.y = y;
		image = Start.art.getExplosion();
	}

	public void paint(Graphics g) {
		if (gameScreen != null) {
			g.drawImage(image, x, y, gameScreen);
		}
	}

}
