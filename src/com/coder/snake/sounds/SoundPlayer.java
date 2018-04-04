package com.coder.snake.sounds;

import java.io.InputStream;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class SoundPlayer {

	private Player mediaPlayer;
	private InputStream inputStream;
	
	public SoundPlayer() {
		
		
	}
	
	public void foodEaten() {
		new Thread(()-> {
			try {
				inputStream = SoundPlayer.class.getResourceAsStream(SoundTrackPaths.EAT_FOOD_CLIP_PATH);
				mediaPlayer = new Player(inputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JDialog(), "Sorry we couldn't find sound track file!", "File path error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}).start();
	}
	
	public void gameOver() {
		new Thread(()-> {
			try {
				inputStream = SoundPlayer.class.getResourceAsStream(SoundTrackPaths.GAME_OVER_CLIP_PATH);
				mediaPlayer = new Player(inputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JDialog(), "Sorry we couldn't find sound track file!", "File path error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}).start();
	}
}
