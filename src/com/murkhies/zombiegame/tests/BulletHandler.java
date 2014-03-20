package com.murkhies.zombiegame.tests;

public class BulletHandler extends Thread {
	
	Player player;
	
	public BulletHandler(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			player.setShoot(false);
			sleep(5000);
			player.setShoot(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
