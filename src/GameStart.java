
import javax.swing.JFrame;

public class GameStart extends JFrame {

	private static final long serialVersionUID = -5160279951693047635L;

	public static void main(String[] args) {
		Screen game = new Screen();
		game.run();
		System.exit(0);
	}

}