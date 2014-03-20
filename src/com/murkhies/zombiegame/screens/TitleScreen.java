package com.murkhies.zombiegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.utils.Art;

public class TitleScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Start start;
	Image image;

	public TitleScreen(final Start start) {

		this.start = start;
		
		setLayout(null);
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setBounds((start.WIDTH*40)/100, (600*80)/100, (800*20)/100, (600*5)/100);
		btnLaunch.setBackground(Color.black);
		btnLaunch.setForeground(Color.red);
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
				start.changePanel(new GameScreen(start));
			}
		});
		add(btnLaunch);
		
		
		
		

	}

	public void paint(Graphics g) {
		setOpaque(false);
		g.drawImage(new Art().getGeneralBackground(), 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}

}
