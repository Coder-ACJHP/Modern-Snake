package com.coder.snake.model;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SoundPlayer {

	Player mediaPlayer;
	private FileInputStream fileInputStream;
	private static final File EAT_FOOD_CLIP_PATH = new File("src/com/coder/snake/sounds/snake-eat.mp3");
	private static final File GAME_OVER_CLIP_PATH = new File("src/com/coder/snake/sounds/game-over.mp3");
	
	public SoundPlayer() {
		
		
	}
	
	public void foodEaten() {
		new Thread(()-> {
			try {
				fileInputStream = new FileInputStream(EAT_FOOD_CLIP_PATH);
				mediaPlayer = new Player(fileInputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}).start();
	}
	
	public void gameOver() {
		new Thread(()-> {
			try {
				fileInputStream = new FileInputStream(GAME_OVER_CLIP_PATH);
				mediaPlayer = new Player(fileInputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}).start();
	}
}
