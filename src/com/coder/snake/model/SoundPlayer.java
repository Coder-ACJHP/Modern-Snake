package com.coder.snake.model;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SoundPlayer {

	Player mediaPlayer;
	private FileInputStream fileInputStream;
	private static final File CLIP_PATH = new File("src/com/coder/snake/sounds/snake-eat.mp3");
	
	public SoundPlayer() {
		new Thread(()-> {
			try {
				fileInputStream = new FileInputStream(CLIP_PATH);
				mediaPlayer = new Player(fileInputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}).start();
		
	}
}
