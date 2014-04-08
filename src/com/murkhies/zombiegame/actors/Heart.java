package com.murkhies.zombiegame.actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.screens.GameScreen;

public class Heart {
	
	int x, y;
	GameScreen gameScreen;
	Image image;
	
	Rectangle rec;
	
	public Heart(GameScreen gameScreen, Start start) {
		this.gameScreen = gameScreen;
		image = Start.art.getHeart();
		x = new Random().nextInt(start.WIDTH-70)+50;
		y = new Random().nextInt(start.HEIGHT-70)+50;
		rec = new Rectangle(x, y, 16, 16);
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, x, y, 16, 16, gameScreen);
	}

}
