package com.murkhies.zombiegame.actors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Bite {

	public Bite() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Bite.class
							.getResourceAsStream("/bite.au"));
			clip.open(inputStream);
			clip.loop(1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
