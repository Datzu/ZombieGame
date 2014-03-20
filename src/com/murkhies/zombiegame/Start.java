package com.murkhies.zombiegame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.murkhies.zombiegame.screens.SettingsScreen;
import com.murkhies.zombiegame.utils.Strings;
import com.murkhies.zombiegame.utils.XMLParser;

public class Start extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public int WIDTH;
	public int HEIGHT;
	public int FPS;
	XMLParser xmlParser;
	
	public Start() {
		getXmlConf();
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(new SettingsScreen(this));
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
			
			System.out.println("Width: " + WIDTH);
			System.out.println("Height: " + HEIGHT);
			System.out.println("FPS: " + FPS);

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

}