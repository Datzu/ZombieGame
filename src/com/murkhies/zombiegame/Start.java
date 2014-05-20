package com.murkhies.zombiegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.murkhies.zombiegame.actors.BackgroundSound;
import com.murkhies.zombiegame.screens.GameScreen;
import com.murkhies.zombiegame.screens.TitleScreen;
import com.murkhies.zombiegame.utils.Art;
import com.murkhies.zombiegame.utils.InputHandler;
import com.murkhies.zombiegame.utils.Score;
import com.murkhies.zombiegame.utils.Strings;
import com.murkhies.zombiegame.utils.XMLParser;

public class Start extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public int WIDTH;
	public int HEIGHT;
	public int FPS;
	XMLParser xmlParser;
	GameScreen gameScreen;
	Image background;
	public static Art art = new Art();
	BufferStrategy bs;
	public InputHandler inputHandler;
	
	public Start() {
		getXmlConf();
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		background = art.getLevelBackground();
		createBufferStrategy(2);
		bs = getBufferStrategy();
		inputHandler = new InputHandler(this);
		addKeyListener(inputHandler);
		setFocusable(true);
		add(new TitleScreen(this));
		new BackgroundSound();
		repaint();
	}
	
	@Override
	public void run() {
		while (gameScreen != null && gameScreen.isRunning()) {
			long time = System.currentTimeMillis();
			time = (1000 / FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					repaint();
					Thread.sleep(time*3);
				} catch (Exception e) {
				}
			}
		}
	}
	
	public void paintBackground(Graphics g) {
		g.drawImage(background, 0, 20, WIDTH, HEIGHT-20, this);
	}
	
	@Override
	public void paint(Graphics g) {
		if (gameScreen != null) {
			do {
				try {
					g = bs.getDrawGraphics();
					paintBackground(g);
					gameScreen.paint(g);
				} catch (Exception e) {
					e.printStackTrace();
				}
				bs.show();
				g.dispose();
			} while (bs.contentsLost());
		}
	}

	public void getXmlConf() {
		xmlParser = new XMLParser();
		xmlParser.setPath(new Strings().FILE_TO_XML_SETTINGS);
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
	}

	public void saveXML() {
		getXmlConf();
	}
	
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public void stopAll() {
		gameScreen = null;
	}

	public void saveHighScore(String cad, int points) {
		xmlParser.saveScore(cad, points);
	}
	
	public ArrayList<Score> getScores() {
		
		ArrayList<Score> scoreList = new ArrayList<Score>();
		xmlParser.setPath(new Strings().FILE_TO_XML_HIGHSCORE);
		xmlParser.build();
		NodeList nodeList = xmlParser.getAllValue("player");
		
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				scoreList.add(new Score(eElement.getElementsByTagName("name").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("score").item(0).getTextContent())));
			}
		}
		
		return scoreList;
		
	}

}