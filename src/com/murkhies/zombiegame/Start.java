package com.murkhies.zombiegame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.screens.TitleScreen;
import com.murkhies.zombiegame.utils.Strings;
import com.murkhies.zombiegame.utils.XMLParser;

public class Start extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public int WIDTH;
	public int HEIGHT;
	public int FPS;
	XMLParser xmlParser;
	GameScreen gameScreen;
	
	public Start() {
		getXmlConf();
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(new TitleScreen(this));
	}
	
	@Override
	public void run() {
		while (true) {
			long time = System.currentTimeMillis();

			time = (1000 / FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					gameScreen.paint(getGraphics());
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}
	}	


	public void getXmlConf() {
		xmlParser = new XMLParser();
		xmlParser.setPath(new Strings().FILE_TO_XML);
		xmlParser.build();
		xmlParser.setTagName("settings");
		try {

			WIDTH = Integer.parseInt(xmlParser.getValue("width"));
			HEIGHT = Integer.parseInt(xmlParser.getValue("height"));
			FPS = Integer.parseInt(xmlParser.getValue("fps"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Start();
			}
		});
	}
	
	public void setDefault() {
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
	}
	
	public void changePanel(JPanel nextPanel) {
		getContentPane().removeAll();
		getContentPane().add(nextPanel);
		revalidate();
		repaint();
	}

	public void saveXML() {
		getXmlConf();
	}
	
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

}