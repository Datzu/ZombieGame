package com.murkhies.zombiegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.utils.Art;
import com.murkhies.zombiegame.utils.Score;

public class TitleScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Start start;
	Image image;
	
	JLabel lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4;
	
	final Color foregroundColor = Color.white;
	final Color backgroundColor = Color.black;

	public TitleScreen(final Start start) {

		this.start = start;
		
		setLayout(null);
		
		ArrayList<Score> scoreList = start.getScores();
		
		lblPlayer1 = new JLabel(scoreList.get(0).getName() + " - " + scoreList.get(0).getPoints());
		lblPlayer1.setBounds((start.WIDTH*45)/100, (start.HEIGHT*30)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		lblPlayer1.setBackground(backgroundColor);
		lblPlayer1.setForeground(foregroundColor);
		add(lblPlayer1);
		
		lblPlayer2 = new JLabel(scoreList.get(1).getName() + " - " + scoreList.get(1).getPoints());
		lblPlayer2.setBounds((start.WIDTH*45)/100, (start.HEIGHT*35)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		lblPlayer2.setBackground(backgroundColor);
		lblPlayer2.setForeground(foregroundColor);
		add(lblPlayer2);
		
		lblPlayer3 = new JLabel(scoreList.get(2).getName() + " - " + scoreList.get(2).getPoints());
		lblPlayer3.setBounds((start.WIDTH*45)/100, (start.HEIGHT*40)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		lblPlayer3.setBackground(backgroundColor);
		lblPlayer3.setForeground(foregroundColor);
		add(lblPlayer3);
		
		lblPlayer4 = new JLabel(scoreList.get(3).getName() + " - " + scoreList.get(3).getPoints());
		lblPlayer4.setBounds((start.WIDTH*45)/100, (start.HEIGHT*45)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		lblPlayer4.setBackground(backgroundColor);
		lblPlayer4.setForeground(foregroundColor);
		add(lblPlayer4);
		
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setBounds((start.WIDTH*40)/100, (start.HEIGHT*68)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		btnLaunch.setBackground(backgroundColor);
		btnLaunch.setForeground(foregroundColor);
		btnLaunch.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameScreen gameScreen = new GameScreen(start, start.inputHandler);
				start.setGameScreen(gameScreen);
				start.changePanel(gameScreen);
				new Thread(start).start();
			}
		});
		add(btnLaunch);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds((start.WIDTH*40)/100, (start.HEIGHT*75)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		btnSettings.setBackground(backgroundColor);
		btnSettings.setForeground(foregroundColor);
		btnSettings.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				start.changePanel(new SettingsScreen(start));
			}
		});
		add(btnSettings);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds((start.WIDTH*40)/100, (start.HEIGHT*82)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		btnExit.setBackground(backgroundColor);
		btnExit.setForeground(foregroundColor);
		btnExit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		add(btnExit);
		
	}

	public void paint(Graphics g) {
		setOpaque(false);
		g.drawImage(new Art().getGeneralBackground(), 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}

}
