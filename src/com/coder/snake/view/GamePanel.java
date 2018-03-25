package com.coder.snake.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	

	/**
	 * 
	 */
	private int x,y = 10;
	private boolean mute = false;
	private ControlPanel controlPanel;
	private Color flexColor = Color.WHITE;
	private String text = "Coming soon...";
	private static final long serialVersionUID = 1L;

	public GamePanel() {
		
		this.setFocusable(true);
	    this.requestFocusInWindow(true);
	    
	    this.addFocusListener(customGetFocus());
	    this.addKeyListener(customKeyAdapter());
	    
	    controlPanel = new ControlPanel();
	    controlPanel.startButton.addActionListener(e->{
	    	System.out.println("Coming soon...");
	    });
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.setColor(flexColor);
		graphics2d.fillRect(0, 0, getWidth(), getHeight());
		
		final Font TEXT_FONT = new Font("Helvetica", Font.BOLD, 60);
		graphics2d.setColor(Color.black);
		graphics2d.setFont(TEXT_FONT);
		
//		graphics2d.drawString(text, getWidth() / 3, getHeight() / 2);
		graphics2d.drawString(text, x, y);
		
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
					y -= 100;
					repaint();
				} 
				if(keyCode == KeyEvent.VK_DOWN) {
					y += 100;
					repaint();
				} 
				if(keyCode == KeyEvent.VK_LEFT) {
					x -= 100;
					repaint();
				}
				if(keyCode == KeyEvent.VK_RIGHT) {
					x += 100;
					repaint();	
				}		
			}
		};
		return adapter;
	}
	

	//Implement these methods to apply them on this panel.
	public void startGame() {
		System.out.println("Game started!");
	}


	public void muteGame() {
		System.out.println("Game sound muted!");
		
	}


	public void unMuteGame() {
		System.out.println("Game sound unmuted!");
		
	}
	
	public void changeBackground(Color color) {
		setBackground(color);
	}


	public void moveToLeft() {
		System.out.println("Snake moving to left!");
		y -= 100;
		repaint();
		
	}


	public void moveToRight() {
		System.out.println("Snake moving to right!");
		y += 100;
		repaint();
		
	}


	public void moveToUp() {
		System.out.println("Snake moving to up!");
		x += 100;
		repaint();
	}


	public void moveToDown() {
		System.out.println("Snake moving to down!");
		x -= 200;
		repaint();
	}

	
	public ActionListener buttonActions() {
		final ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
//				switch (e.getActionCommand()) {
//				case "sound":
//					if(mute) {
//						mute = false;
//						unMuteGame();
//						controlPanel.soundButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/spiker.png")));
//						
//					} else {
//						mute = true;
//						muteGame();
//						controlPanel.soundButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/mute-spiker.png")));
//					}
//					break;
//				case "colors":
//					flexColor = JColorChooser.showDialog(null, "Choose color to change snake background", Color.WHITE);
//					changeBackground(flexColor);
//					break;
//				case "start":
//					startGame();
//					break;
//				case "right":
//					moveToRight();
//					break;
//				case "left":
//					moveToLeft();
//					break;
//				case "up":
//					moveToUp();
//					break;
//				case "down":
//					moveToDown();
//					break;
//				default:
//					break;
//				}
				
			}
		};
		
		return actionListener;
	}
}
