package com.murkhies.zombiegame.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.font.TextAttribute;

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
		g.drawString(player.getBullets() + " / " + player.getMaxBullets(), 25, 50);
		g.drawString("Points: " + points, 80, 50);
	}

	public void setPoints(int i) {
		points += i;
	}

}
