package com.murkhies.zombiegame.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Art {

	Image generalBackground;
	Image levelBackground;
	
	Image[] player = new Image[4];
	Image[] basicZombie = new Image[4];
	Image[] boss = new Image[3];
	
	Image heart;
	Image explosion;
	Image explosionMissile;
	Image bullet;
	Image ammoBox;
	
	Image[] missile = new Image[4];

	public Art() {
		generalBackground = new ImageIcon(getClass().getResource("/background.png")).getImage();
		levelBackground = new ImageIcon(getClass().getResource("/levelBackground.jpg")).getImage();
		
		player[0] = new ImageIcon(getClass().getResource("/player0.png")).getImage();
		player[1] = new ImageIcon(getClass().getResource("/player1.png")).getImage();
		player[2] = new ImageIcon(getClass().getResource("/player2.png")).getImage();
		player[3] = new ImageIcon(getClass().getResource("/player3.png")).getImage();
				
		basicZombie[0] = new ImageIcon(getClass().getResource("/basicZombie0.png")).getImage();
		basicZombie[1] = new ImageIcon(getClass().getResource("/basicZombie1.png")).getImage();
		basicZombie[2] = new ImageIcon(getClass().getResource("/basicZombie2.png")).getImage();
		basicZombie[3] = new ImageIcon(getClass().getResource("/basicZombie3.png")).getImage();
		
		boss[0] = new ImageIcon(getClass().getResource("/boss0.png")).getImage();
		boss[2] = new ImageIcon(getClass().getResource("/boss2.png")).getImage();
		
		heart = new ImageIcon(getClass().getResource("/heart.png")).getImage();
		explosion = new ImageIcon(getClass().getResource("/explosion.png")).getImage();
		explosionMissile = new ImageIcon(getClass().getResource("/explosionMissile.png")).getImage();
		bullet = new ImageIcon(getClass().getResource("/bullet.png")).getImage();
		ammoBox = new ImageIcon(getClass().getResource("/ammoBox.png")).getImage();
		
		missile[0] = new ImageIcon(getClass().getResource("/missile0.png")).getImage();
		missile[1] = new ImageIcon(getClass().getResource("/missile1.png")).getImage();
		missile[2] = new ImageIcon(getClass().getResource("/missile2.png")).getImage();
		missile[3] = new ImageIcon(getClass().getResource("/missile3.png")).getImage();
		
	}

	public Image getGeneralBackground() {
		return generalBackground;
	}
	
	public Image getLevelBackground() {
		return levelBackground;
	}
	
	public Image getPlayer(int i) {
		return player[i];
	}
	
	public Image getBasicZombie(int i) {
		return basicZombie[i];
	}

	public Image getExplosion() {
		return explosion;
	}

	public Image getBullet() {
		return bullet;
	}
	
	public Image getHeart() {
		return heart;
	}

	public Image getAmmoBox() {
		return ammoBox;
	}
	
	public Image getBoss(int i) {
		return boss[i];
	}
	
	public Image getMissile(int i) {
		return missile[i];
	}

	public Image getExplosionMissile() {
		return explosionMissile;
	}

}
