package com.murkhies.zombiegame.actors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundSound extends Thread {

	public BackgroundSound() {
		start();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Bite.class
							.getResourceAsStream("/egg_nebula_-_06_-_totem.au"));
			clip.open(inputStream);
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
