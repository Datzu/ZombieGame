import java.util.Random;

public class Enemy {

	Player player;
	boolean alive = true;
	int x, y;

	public Enemy(Player player) {
		this.player = player;
		x = new Random().nextInt(800);
		y = new Random().nextInt(600);
	}

	public void update() {
		if (x > player.getX()) {
			x--;
		}
		if (x < player.getX()) {
			x++;
		}
		if (y > player.getY()) {
			y--;
		}
		if (y < player.getY()) {
			y++;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
