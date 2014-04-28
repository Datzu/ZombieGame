package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;

public class ExplosionOnCoordMissile {

	Image image;
	GameScreen gameScreen;
	int x, y;

	public ExplosionOnCoordMissile(int x, int y, GameScreen gameScreen) {
		this.x = x;
		this.y = y;
		image = Start.art.getExplosionMissile();
	}

	public void paint(Graphics g) {
		if (gameScreen != null) {
			g.drawImage(image, x, y, gameScreen);
		}
	}

}
