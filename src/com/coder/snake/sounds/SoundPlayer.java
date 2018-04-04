package com.coder.snake.sounds;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class SoundPlayer {

	private Player mediaPlayer;
	private FileInputStream fileInputStream;
	
	public SoundPlayer() {
		
		
	}
	
	public void foodEaten() {
		new Thread(()-> {
			try {
				fileInputStream = new FileInputStream(new File(getClass().getResource(SoundTrackPaths.EAT_FOOD_CLIP_PATH).getFile()));
				mediaPlayer = new Player(fileInputStream);
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
				fileInputStream = new FileInputStream(new File(getClass().getResource(SoundTrackPaths.GAME_OVER_CLIP_PATH).getFile()));
				mediaPlayer = new Player(fileInputStream);
				mediaPlayer.play();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JDialog(), "Sorry we couldn't find sound track file!", "File path error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}).start();
	}
}
