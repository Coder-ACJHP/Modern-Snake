package com.coder.snake.view;

import java.awt.BasicStroke;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.coder.snake.model.Apple;
import com.coder.snake.model.Snake;

public class GamePanel extends JPanel implements ActionListener {
	
	/*
	 * https://forum.tutorials7.com/1527/how-to-create-snake-game-in-java-with-
	 * intellij-idea-code
	 */
	public static final int CELL_SIZE = 10;
	public static final int SCALE = 32;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static final int SPEED = 4;

	Apple apple = new Apple((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
	Snake snake = new Snake(10, 10, 9, 10);
	Timer timer = new Timer(1000 / SPEED, this);

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
		timer.start();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawlines(graphics2d);

		graphics2d.setColor(Color.BLACK);
		for (int xx = 0; xx <= WIDTH * SCALE; xx += SCALE) {
			graphics2d.drawLine(xx, 0, xx, HEIGHT * SCALE);
		}

		for (int yy = 0; yy <= HEIGHT * SCALE; yy += SCALE) {
			graphics2d.drawLine(0, yy, WIDTH * SCALE, yy);
		}

		for (int d = 0; d < snake.length; d++) {
			graphics2d.setColor(Color.GREEN);
			graphics2d.fillRect(snake.snakeX[d] * SCALE + 1, snake.snakeY[d] * SCALE + 1, SCALE - 1, SCALE - 1);
		}

		graphics2d.setColor(Color.BLACK);
		graphics2d.fillRect(apple.posX * SCALE + 1, apple.posY * SCALE + 1, SCALE - 1, SCALE - 1);
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

	public void drawlines(Graphics2D g) {

		for (int x = 0; x <= getWidth(); x += CELL_SIZE) {
			for (int y = 0; y <= getHeight(); y += CELL_SIZE) {
				g.setStroke(new BasicStroke(1));
				g.setColor(Color.GRAY.brighter());
				g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
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
			break;
		case "right":
			break;
		case "left":
			break;
		case "up":
			break;
		case "down":
			break;
		default:
			break;
		}
		
	}
}
