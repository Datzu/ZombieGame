package com.murkhies.zombiegame.screens;

import java.awt.Graphics;
import java.awt.ImageCapabilities;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.murkhies.zombiegame.utils.Art;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JFrame start;
	
	public GameScreen(JFrame start) {
		
		this.start = start;
		
		setLayout(null);
	}
	
	@Override
	public void paint(Graphics g) {
		setOpaque(false);
		g.drawImage(new Art().getLevelBackground(), 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}

}
