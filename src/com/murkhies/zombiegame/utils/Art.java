package com.murkhies.zombiegame.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Art {

	Image generalBackground;
	Image levelBackground;

	public Art() {
		generalBackground = new ImageIcon(getClass().getResource("/background.png")).getImage();
		levelBackground = new ImageIcon(getClass().getResource("/levelBackground.jpg")).getImage();
	}

	public Image getGeneralBackground() {
		return generalBackground;
	}
	
	public Image getLevelBackground() {
		return levelBackground;
	}

}
