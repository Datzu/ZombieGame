package com.murkhies.zombiegame.tests;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.murkhies.zombiegame.actors.Player;

public class Screen extends JPanel {

	private static final long serialVersionUID = 5280294122605773228L;

	boolean isRunning = true;
	int fps = 30;
	
	int windowWidth = 800;
	int windowHeight = 600;

	Player player;
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Bullet> bulletList = new ArrayList<Bullet>();
	BulletHandler bulletHandler = new BulletHandler(player);

	BufferedImage backBuffer;
	Insets insets;
	InputHandler input;
	ImageIcon ii;
	Image image;
	
	public Screen() {
	}

	public void run() {
		initialize();

		while (isRunning) {
			long time = System.currentTimeMillis();

			update();
			repaint();

			time = (1000 / fps) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}

		setVisible(false);
	}

	void initialize() {

		// this.player = new Player(windowWidth / 2, windowHeight / 2);

		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top
				+ windowHeight + insets.bottom);

		backBuffer = new BufferedImage(windowWidth, windowHeight,
				BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
	}

	void update() {
		if (input.isKeyDown(KeyEvent.VK_D)) {
			player.right();
		}
		if (input.isKeyDown(KeyEvent.VK_A)) {
			player.left();
		}
		if (input.isKeyDown(KeyEvent.VK_S)) {
			player.down();
		}
		if (input.isKeyDown(KeyEvent.VK_W)) {
			player.up();
		}
		if (input.isKeyDown(KeyEvent.VK_E)) {
			Enemy enemy = new Enemy(player);
			enemyList.add(enemy);
		}
		if (input.isKeyDown(KeyEvent.VK_Q)) {
			enemyList.clear();
		}
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			if (player.canShoot()) {
//				Bullet bullet = new Bullet(player.getX() - 4,
//						player.getY() + 10, player.getDir());
//				bulletList.add(bullet);
				try {
					bulletHandler.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void draw(Graphics g) {
		super.paint(g);
		
		ii = new ImageIcon(getClass().getResource("/com/murkhies/zombiegame/background.png"));
		image = ii.getImage();
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, 0, 0, this);
		
		g2d.drawString("Aliens left: " + 0, 5, 15);

		Graphics bbg = backBuffer.getGraphics();

		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);

		bbg.setColor(Color.BLACK);
//		bbg.drawOval(player.getX(), player.getY(), 20, 20);

		bbg.setColor(Color.RED);
		for (Enemy enemy : enemyList) {
			for (Enemy n : enemyList) {
				if (!checkColisions(enemy, n)) {
					System.out.println("Collision no detected.");
					enemy.update();
				} else {
					System.out.println("Collision detected.");
				}
			}
			bbg.drawOval(enemy.getX(), enemy.getY(), 20, 20);
		}
		bbg.setColor(Color.BLUE);
		for (Bullet bullet : bulletList) {
			bullet.update();
			bbg.drawOval(bullet.getX(), bullet.getY(), 5, 5);
		}
		g.drawImage(backBuffer, insets.left, insets.top, this);
		
		g.dispose();
	}

	private boolean checkColisions(Enemy enemy, Enemy n) {
		double dist = dist(enemy.getX() - 5, enemy.getY() - 5, n.getX(),
				n.getY());
		System.out.println("Dist: " + dist);
		if (dist > 50) {
			return false;
		} else {
			return true;
		}
	}

	private double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

}
