package com.murkhies.zombiegame.screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.actors.BasicZombie;
import com.murkhies.zombiegame.actors.Player;
import com.murkhies.zombiegame.utils.Art;

public class GameScreen extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	Start start;
	Player player;
	List<BasicZombie> basicZombieList = new ArrayList<BasicZombie>();
	
	boolean running = true;

	public GameScreen(Start start) {

		this.start = start;
		setDoubleBuffered(true);
		player = new Player(start, this);
		addKeyListener(player.inputHandler);
		player.paint();
		player.start();
		
		basicZombieList.add(new BasicZombie(start, this, player));
		
		new Thread(this).start();

		setLayout(null);
	}
	
	@Override
	public void run() {
		while (running) {
			
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setOpaque(false);
		g.drawImage(new Art().getLevelBackground(), 0, 0, getWidth(),
				getHeight(), this);
		player.paint();
		if (player.inputHandler.isKeyDown(KeyEvent.VK_E)) {
			basicZombieList.add(new BasicZombie(start, this, player));
		}
		for (BasicZombie basicZombie : basicZombieList) {
			basicZombie.paint(start.getGraphics());
		}

	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
