package com.murkhies.zombiegame.screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.actors.BasicZombie;
import com.murkhies.zombiegame.actors.Bullet;
import com.murkhies.zombiegame.actors.ExplosionOnCoord;
import com.murkhies.zombiegame.actors.Player;
import com.murkhies.zombiegame.utils.InputHandler;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	Start start;
	Player player;
	List<BasicZombie> basicZombieList = new ArrayList<BasicZombie>();
	List<Bullet> bulletList = new ArrayList<Bullet>();
	InputHandler inputHandler;

	boolean running = true;

	public GameScreen(Start start, InputHandler inputHandler) {

		this.start = start;
		setDoubleBuffered(true);
		player = new Player(start, this, inputHandler);
		addKeyListener(inputHandler);
		setOpaque(false);
		this.inputHandler = inputHandler;
		player.start();

		basicZombieList.add(new BasicZombie(start, this, player));

		setLayout(null);
	}

	public void paint(Graphics g) {
		for (BasicZombie basicZombie : basicZombieList) {
			basicZombie.paint(g);
		}
		player.paint(g);
		for (BasicZombie basicZombie : basicZombieList) {
			if (basicZombie.getY() > player.getY()) {
				basicZombie.paint(g);
			}
		}
		for (Bullet bullet : bulletList) {
			bullet.paint(g);
		}

	}
	
	public void newShoot() {
		Bullet bullet = new Bullet(player, start, player.getDir(), start.HEIGHT, start.WIDTH, this);
		new Thread(bullet).start();
		bulletList.add(bullet);
	}
	
	public void endShoot(Bullet bullet) {
		for (int i = 0; i < 5; i++) {
			new ExplosionOnCoord(bullet.getX(), bullet.getY()-1, this).paint(start.getGraphics());
		}
		bulletList.remove(bullet);
	}

}
