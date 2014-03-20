package com.murkhies.zombiegame.tests;

import javax.swing.JFrame;

import org.omg.CORBA.INITIALIZE;

public class GameStart extends JFrame {

	private static final long serialVersionUID = -5160279951693047635L;

	public static void main(String[] args) {
		GameStart start = new GameStart();
		Screen screen = new Screen();
		start.add(screen);
		screen.run();
		start.repaint();
		start.revalidate();
	}
	
	public GameStart() {
		setTitle("Tic Tac Toe");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}