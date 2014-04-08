package com.murkhies.zombiegame.utils;

import java.util.Random;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.actors.AmmoBox;
import com.murkhies.zombiegame.actors.Heart;
import com.murkhies.zombiegame.screens.GameScreen;

public class Generate extends Thread {
	
	Start start;
	GameScreen gameScreen;
	
	public Generate(Start start, GameScreen gameScreen) {
		this.start = start;
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void run() {
		super.run();
		while (gameScreen.isRunning()) {
			if (new Random().nextFloat() > 0.50f) {
				gameScreen.newAmmoBox(new AmmoBox(gameScreen, start));
			}
			if (new Random().nextFloat() > 0.50f) {
				gameScreen.newHeart(new Heart(gameScreen, start));
			}
			if (new Random().nextFloat() > 0.30f) {
				gameScreen.newZombie();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
