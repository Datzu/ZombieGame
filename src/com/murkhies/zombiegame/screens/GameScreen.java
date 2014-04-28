package com.murkhies.zombiegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.actors.AmmoBox;
import com.murkhies.zombiegame.actors.BasicZombie;
import com.murkhies.zombiegame.actors.Boss;
import com.murkhies.zombiegame.actors.Bullet;
import com.murkhies.zombiegame.actors.ExplosionOnCoord;
import com.murkhies.zombiegame.actors.ExplosionOnCoordMissile;
import com.murkhies.zombiegame.actors.Heart;
import com.murkhies.zombiegame.actors.Missile;
import com.murkhies.zombiegame.actors.Player;
import com.murkhies.zombiegame.utils.Generate;
import com.murkhies.zombiegame.utils.InputHandler;
import com.murkhies.zombiegame.utils.UI;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	Start start;
	Player player;
	Boss boss;
	List<BasicZombie> basicZombieList = new ArrayList<BasicZombie>();
	List<Bullet> bulletList = new ArrayList<Bullet>();
	List<Missile> missileList = new ArrayList<Missile>();
	public static List<Heart> heartList = new ArrayList<Heart>();
	public static List<AmmoBox> ammoBoxList = new ArrayList<AmmoBox>();
	InputHandler inputHandler;
	
	UI ui;

	boolean running = true;

	public GameScreen(Start start, InputHandler inputHandler) {

		this.start = start;
		setDoubleBuffered(true);
		player = new Player(start, this, inputHandler);
		addKeyListener(inputHandler);
		setOpaque(false);
		this.inputHandler = inputHandler;
		player.start();
		ui = new UI(player, start, this);
		new Generate(start, this).start();
		for (int i = 0; i < 3; i++) {
			basicZombieList.add(new BasicZombie(start, this, player));
		}
		
		boss = new Boss(start, this, player);

		setLayout(null);
	}

	public void paint(Graphics g) {
		if (running = true) {
			g.setColor(Color.blue);
			for (AmmoBox ammoBox : ammoBoxList) {
				ammoBox.paint(g);
			}
			for (Heart heart : heartList) {
				heart.paint(g);
			}
			for (BasicZombie basicZombie : basicZombieList) {
				if (basicZombie != null) {
					basicZombie.paint(g);
				}
			}
			if (boss != null) {
				boss.paint(g);
			}
			player.paint(g);
			if (boss != null && boss.getY() > player.getY()-70) {
				boss.paint(g);
			}
			for (BasicZombie basicZombie : basicZombieList) {
				if (basicZombie.getY() > player.getY()) {
					basicZombie.paint(g);
				}
			}
			for (Bullet bullet : bulletList) {
				if (bullet != null) {
					
				}bullet.paint(g);
			}
			for (Missile missile : missileList) {
				if (missile != null) {
					missile.paint(g);
				}
			}
			ui.paint(g);
		}
	}
	
	public void newShoot() {
		Bullet bullet = new Bullet(player, start, player.getDir(), start.HEIGHT, start.WIDTH, this, basicZombieList, boss);
		new Thread(bullet).start();
		bulletList.add(bullet);
	}
	
	public void endShoot(Bullet bullet) {
		for (int i = 0; i < 5; i++) {
			new ExplosionOnCoord(bullet.getX(), bullet.getY()-1, this).paint(start.getGraphics());
		}
		bulletList.remove(bullet);
	}
	
	public void newAmmoBox(AmmoBox ammoBox) {
		if (ammoBoxList.size() < 5) {
			ammoBoxList.add(ammoBox);
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void removeZombie(BasicZombie basicZombie) {
		basicZombie.die();
		basicZombieList.remove(basicZombie);
		ui.setPoints(10);
	}

	public void removeAmmoBox(AmmoBox ammoBox) {
		ammoBoxList.remove(ammoBox);
	}

	public void newZombie() {
		if (basicZombieList.size() > 5) {
			basicZombieList.add(new BasicZombie(start, this, player));
		} else if (basicZombieList.size() < 3) {
			for (int i = 0; i < 20; i++) {
				basicZombieList.add(new BasicZombie(start, this, player));
			}
		}
		
	}

	public void newHeart(Heart heart) {
		if (heartList.size() < 3) {
			heartList.add(heart);
		}
	}
	
	public void removeHeart(Heart heart) {
		heartList.remove(heart);
	}

	public void missileExplosion(Missile missile) {
		for (int i = 0; i < 5; i++) {
			new ExplosionOnCoordMissile(missile.getX()-18, missile.getY()-20, this).paint(start.getGraphics());
		}
		missileList.remove(missile);
	}

	public void newMissile() {
		Missile missile = new Missile(player, start, boss.getDir(), start.HEIGHT, start.WIDTH, this, basicZombieList, boss);
		new Thread(missile).start();
		missileList.add(missile);
	}
	
	public void end() {
		running = false;
		for (BasicZombie zombie : basicZombieList) {
			zombie.die();
		}
		basicZombieList = new ArrayList<BasicZombie>();
		for (Bullet bullet : bulletList) {
			bullet.alive = false;
		}
		bulletList = new ArrayList<Bullet>();
		for (Missile missile : missileList) {
			missile.alive = false;
		}
		missileList = new ArrayList<Missile>();
		boss = null;
	}

}
