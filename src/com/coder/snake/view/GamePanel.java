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

import com.coder.snake.model.Direction;
import com.coder.snake.model.Food;
import com.coder.snake.model.Snake;

public class GamePanel extends JPanel implements ActionListener {
	
	
	public static final int CELL_SIZE = 10;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 70;
    public static final int FPS = 32;

	private Food food;
	private Snake snake;
	private Timer timer;
	private int score = 0;

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
	    
	    snake = new Snake(100, 100, 100, 100);
	    food = new Food((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
	    timer = new Timer(FPS, e-> {
	    	initialize();
	    });
	}
	
	public void start() {
		timer.start();
	}
	
	public void pause() {
		timer.setDelay(2000);
	}
	
	public void gameOver() {
		timer.stop();
		snake.length = 2;
	}
	
	public void increaseScore() {
		score = score + 5;
		currentControlPanel.scoreBoard.setText(String.valueOf(this.score));
	}
	
	
	public void muteGame() {
		currentControlPanel.soundButton.setIcon(new 
				ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/mute-spiker.png")));
		
	}

	public void unMuteGame() {
		currentControlPanel.soundButton.setIcon(new 
				ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/spiker.png")));
		
	}
	
	public void changeBackground() {
		flexColor = JColorChooser.showDialog(null, 
				"Choose color to change snake background", Color.WHITE);
		setBackground(flexColor);
		repaint();
	}

	//This method drawing guide lines (cells)
	public void drawlines(Graphics2D g) {

		for (int x = 0; x <= getWidth(); x += CELL_SIZE) {
			for (int y = 0; y <= getHeight(); y += CELL_SIZE) {
				g.setStroke(new BasicStroke(1));
				g.setColor(Color.GRAY.brighter());
				g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//Draw guide lines
//		drawlines(graphics2d);


		for (int index = 0; index < snake.length; index++) {
			graphics2d.setColor(Color.GREEN);
			graphics2d.fillRect(snake.positionX[index] * CELL_SIZE + 1, snake.positionY[index] * CELL_SIZE + 1, CELL_SIZE, CELL_SIZE);
		}

		graphics2d.setColor(Color.BLACK);
		graphics2d.fillRect(food.positionX * CELL_SIZE + 1, food.positionY * CELL_SIZE + 1, CELL_SIZE, CELL_SIZE);
	}

	
	public void initialize() {
		snake.move();
		
        if ((snake.positionX[0] == food.positionX) & (snake.positionY[0] == food.positionY)) {
            food.addFood();
            snake.length++;
            increaseScore();
        }
 
//		if(snake.length > 2) {
//			for (int index = snake.length; index > 0; index--) {
//				if ((snake.positionX[0] == snake.positionX[index]) & (snake.positionX[0] == snake.positionY[index]))
//					gameOver();
//				}
//		}
 
        repaint();
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
	
	/* Create movement methods and listen to keyboard keys
	 * then we can control the game from control panel and
	 * from arrow keys. 
	 */
	public void moveToUp() {
		if (snake.direction != Direction.DOWN) {
			snake.direction = Direction.UP;
		}
	}
	
	public void moveToDown() {
		if (snake.direction != Direction.UP) {
			snake.direction = Direction.DOWN;
		}
	}
	
	public void moveToRight() {
		if (snake.direction != Direction.LEFT) {
			snake.direction = Direction.RIGHT;
		}
	}
	
	public void moveToLeft() {
		if (snake.direction != Direction.RIGHT) {
			snake.direction = Direction.LEFT;
		}
	}
	
	private KeyListener customKeyAdapter() {
		final KeyAdapter adapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();
				if ((keyCode == KeyEvent.VK_LEFT) & snake.direction != Direction.RIGHT) {
					snake.direction = Direction.LEFT;
				}
				if ((keyCode == KeyEvent.VK_RIGHT) & snake.direction != Direction.LEFT) {
					snake.direction = Direction.RIGHT;
				}
				if ((keyCode == KeyEvent.VK_UP) & snake.direction != Direction.DOWN) {
					snake.direction = Direction.UP;
				}
	            if ((keyCode == KeyEvent.VK_DOWN) & snake.direction != Direction.UP) {
	            	snake.direction = Direction.DOWN;
	            }
			}
		};
		return adapter;
	}
	
	
	/*Get action commands from control panel and trigger the action event.*/
	@Override
	public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "sound":
				if(mute) { mute = false; unMuteGame(); } 
				else { mute = true; muteGame(); }
				break;
			case "colors":
				changeBackground();
				break;
			case "start":
				start();
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
				repaint();
				break;
			}
		
	}
}
