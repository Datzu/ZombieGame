package com.murkhies.zombiegame.actors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BulletSound {

	public BulletSound() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Bite.class
							.getResourceAsStream("/skorpion.au"));
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
