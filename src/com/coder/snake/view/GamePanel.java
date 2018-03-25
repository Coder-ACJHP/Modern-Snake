package com.coder.snake.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import com.coder.snake.model.Direction;

public class GamePanel extends JPanel implements ActionListener {
	
	
	private boolean mute = false;
	private Color flexColor = Color.WHITE;
	private ControlPanel currentControlPanel;
	private static final long serialVersionUID = 1L;

	public GamePanel(ControlPanel controlPanel) {
		
		this.currentControlPanel = controlPanel;
		
		this.setFocusable(true);
	    this.requestFocusInWindow(true);
	    this.addFocusListener(customGetFocus());
	    this.addKeyListener(customKeyAdapter());
	    controlPanel.addActionToButtons(this);

	}
	
	public void startGame() {
		
	}

	public void muteGame() {
		System.out.println("Game sound muted!");
		
	}


	public void unMuteGame() {
		System.out.println("Game sound unmuted!");
		
	}
	
	public void changeBackground(Color color) {
		setBackground(color);
		repaint();
	}


	public Enum<Direction> moveToLeft() {
		return Direction.LEFT;
		
	}


	public Enum<Direction> moveToRight() {
		return Direction.RIGHT;
		
	}


	public Enum<Direction> moveToUp() {
		return Direction.UP;
	}


	public Enum<Direction> moveToDown() {
		return Direction.DOWN;
	}


	//When this panel lost focus focus on again. 
	private FocusListener customGetFocus() {
		final FocusAdapter focusAdapter = new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				requestFocusInWindow(true);
				super.focusLost(e);
			}
		};
		return focusAdapter;
	}
	
	//Listen to arrow keys
	private KeyListener customKeyAdapter() {
		final KeyAdapter adapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_UP) {
				
					repaint();
				} 
				if(keyCode == KeyEvent.VK_DOWN) {
					
					repaint();
				} 
				if(keyCode == KeyEvent.VK_LEFT) {
					
					repaint();
				}
				if(keyCode == KeyEvent.VK_RIGHT) {
					
					repaint();	
				}
				
				if(keyCode == KeyEvent.VK_P) {
					
				}
			}
		};
		return adapter;
	}
	
	//Get action commands from control panel and trigger the action event.
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "sound":
			if(mute) {
				mute = false;
				unMuteGame();

				currentControlPanel.soundButton.setIcon(
						new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/spiker.png")));
			} else {
				mute = true;
				muteGame();
				currentControlPanel.soundButton.setIcon(
						new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/mute-spiker.png")));
			}
			break;
		case "colors":
			flexColor = JColorChooser.showDialog(null, "Choose color to change snake background", Color.WHITE);
			changeBackground(flexColor);
			break;
		case "start":
			startGame();
			break;
		case "right":
			moveToRight();
			break;
		case "left":
			moveToLeft();
			break;
		case "up":
			moveToUp();
			break;
		case "down":
			moveToDown();
			break;
		default:
			break;
		}
		
	}
}
