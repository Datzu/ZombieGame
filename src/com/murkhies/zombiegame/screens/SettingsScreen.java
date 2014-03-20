package com.murkhies.zombiegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.murkhies.zombiegame.Start;
import com.murkhies.zombiegame.utils.Art;

public class SettingsScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	Start start;
	Image image;
	
	private JTextField txtHeigth;
	private JTextField txtFPS;
	private JTextField txtWidth;
	
	final int LEFT_MARGING_LABEL = 36;
	final int LEFT_MARGING_TXT = 42;
	final Color foregroundColor = Color.white;
	final Color backgroundColor = Color.black;
	
	public SettingsScreen(final Start start) {
		
		this.start = start;
		
		setLayout(null);
		
		//WIDTH
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds((800*LEFT_MARGING_LABEL)/100, (600*50)/100, (800*20)/100, (600*5)/100);
		lblWidth.setForeground(foregroundColor);
		add(lblWidth);
		
		txtWidth = new JTextField();
		txtWidth.setBounds((800*LEFT_MARGING_TXT)/100, (600*50)/100, (800*20)/100, (600*5)/100);
		txtWidth.setBackground(backgroundColor);
		txtWidth.setForeground(foregroundColor);
		txtWidth.setHorizontalAlignment(SwingConstants.CENTER);
		txtWidth.setText("" + start.WIDTH);
		txtWidth.setBorder(null);
		add(txtWidth);
		
		
		//HEIGTH
		JLabel lblHeigth = new JLabel("Heigth:");
		lblHeigth.setBounds((800*LEFT_MARGING_LABEL)/100, (600*60)/100, (800*20)/100, (600*5)/100);
		lblHeigth.setForeground(foregroundColor);
		add(lblHeigth);
		
		txtHeigth = new JTextField();
		txtHeigth.setBounds((800*LEFT_MARGING_TXT)/100, (600*60)/100, (800*20)/100, (600*5)/100);
		txtHeigth.setBackground(backgroundColor);
		txtHeigth.setForeground(foregroundColor);
		txtHeigth.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeigth.setText("" + start.HEIGHT);
		txtHeigth.setBorder(null);
		add(txtHeigth);
		
		
		// FPS
		JLabel lblFps = new JLabel("FPS:");
		lblFps.setBounds((800*LEFT_MARGING_LABEL)/100, (600*70)/100, (800*20)/100, (600*5)/100);
		lblFps.setForeground(foregroundColor);
		add(lblFps);
		
		txtFPS = new JTextField();
		txtFPS.setBounds((800*LEFT_MARGING_TXT)/100, (600*70)/100, (800*20)/100, (600*5)/100);
		txtFPS.setBackground(backgroundColor);
		txtFPS.setForeground(foregroundColor);
		txtFPS.setHorizontalAlignment(SwingConstants.CENTER);
		txtFPS.setText("" + start.FPS);
		txtFPS.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				txtFPS.setForeground(foregroundColor);
			}
		});
		txtFPS.setBorder(null);
		add(txtFPS);

		// LAUNCH
		JButton btnLaunch = new JButton("Save");
		btnLaunch.setBounds((800*LEFT_MARGING_TXT)/100, (600*80)/100, (800*20)/100, (600*5)/100);
		btnLaunch.setBackground(backgroundColor);
		btnLaunch.setForeground(foregroundColor);
		btnLaunch.setHorizontalAlignment(SwingConstants.CENTER);
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
				if (Integer.parseInt(txtFPS.getText()) < 0 ) {
					txtFPS.setForeground(Color.red);
					return;
				}
				start.saveXML();
				start.setDefault();
				start.changePanel(new TitleScreen(start));
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
