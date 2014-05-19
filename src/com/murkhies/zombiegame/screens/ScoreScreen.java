package com.murkhies.zombiegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.murkhies.zombiegame.Start;

public class ScoreScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Start start;
	Image image;
	
	private JTextField txtName;
	private JLabel lblState;
	public int points;
	
	final Color foregroundColor = Color.white;
	final Color backgroundColor = Color.black;

	public ScoreScreen(final Start start, int points, boolean state) {

		this.start = start;
		
		this.points = points;
		
		setLayout(null);
		
		if (state) {
			lblState = new JLabel("You win! Points: " + points);
		} else {
			lblState = new JLabel("You loose! Points: " + points);
		}
		lblState.setBounds((start.WIDTH*42)/100, (start.HEIGHT*40)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		lblState.setBackground(backgroundColor);
		lblState.setForeground(foregroundColor);
		add(lblState);
		
		txtName = new JTextField("Insert your name");
		txtName.setBounds((start.WIDTH*40)/100, (start.HEIGHT*60)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		txtName.setBackground(backgroundColor);
		txtName.setForeground(foregroundColor);
		add(txtName);
				
		JButton btnSave = new JButton("Save");
		btnSave.setBounds((start.WIDTH*40)/100, (start.HEIGHT*82)/100, (start.WIDTH*20)/100, (start.HEIGHT*5)/100);
		btnSave.setBackground(backgroundColor);
		btnSave.setForeground(foregroundColor);
		btnSave.addMouseListener(new MouseListener() {
			
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
				start.saveHighScore(txtName.getText().toString(), getPoints());
				start.changePanel(new TitleScreen(start));
			}
		});
		add(btnSave);
				
	}
	
	public int getPoints() {
		return points;
	}

	public void paint(Graphics g) {
		setOpaque(false);
		g.drawImage(Start.art.getGeneralBackground(), 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}

}
