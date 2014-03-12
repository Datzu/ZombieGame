import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Screen extends JFrame {

	private static final long serialVersionUID = 5280294122605773228L;
	
	boolean isRunning = true;
	int fps = 30;
	int windowWidth = 800;
	int windowHeight = 600;
	
	Player player;
	List<Enemy> enemyList = new ArrayList<Enemy>();

	BufferedImage backBuffer;
	Insets insets;
	InputHandler input;

	public void run() {
		initialize();

		while (isRunning) {
			long time = System.currentTimeMillis();

			update();
			draw();

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
		setTitle("Tic Tac Toe");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		this.player = new Player(windowWidth/2, windowHeight/2);

		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top
				+ windowHeight + insets.bottom);

		backBuffer = new BufferedImage(windowWidth, windowHeight,
				BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
	}

	void update() {
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			player.right();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			player.left();
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			player.down();
		}
		if (input.isKeyDown(KeyEvent.VK_UP)) {
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
			player.shoot();
		}
	}

	void draw() {
		Graphics g = getGraphics();

		Graphics bbg = backBuffer.getGraphics();

		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);

		bbg.setColor(Color.BLACK);
		bbg.drawOval(player.getX(), player.getY(), 20, 20);
		
		bbg.setColor(Color.RED);
		for (Enemy enemy : enemyList) {
			enemy.update();
			bbg.drawOval(enemy.getX(), enemy.getY(), 20, 20);
		}

		g.drawImage(backBuffer, insets.left, insets.top, this);
	}

}
