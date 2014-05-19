package com.murkhies.zombiegame.utils;

import java.awt.Color;
import java.awt.Graphics;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.actors.Player;
import com.murkhies.zombiegame.screens.GameScreen;

public class UI {
	
	Player player;
	Start start;
	GameScreen gameScreen;
	
	int points = 0;
	
	public UI(Player player, Start start, GameScreen gameScreen) {
		this.player = player;
		this.start = start;
		this.gameScreen = gameScreen;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.drawString("Bullets: " + player.getBullets() + " / " + player.getMaxBullets(), start.WIDTH*3/100, start.HEIGHT*9/100);
		g.drawString("Points: " + points, start.WIDTH*3/100, start.HEIGHT*12/100);
	}

	public void setPoints(int i) {
		points += i;
	}

	public int getPoints() {
		return points;
	}

}
