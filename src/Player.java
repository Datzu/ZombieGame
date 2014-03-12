
public class Player {
	
	int x = 0, y = 0;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void up() {
		y -= 5;
	}
	
	public void down() {
		y += 5;
	}
	
	public void left() {
		x -= 5;
	}
	
	public void right() {
		x += 5;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void shoot() {
		
	}

}
