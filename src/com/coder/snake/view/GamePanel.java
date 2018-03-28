package com.coder.snake.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.coder.snake.model.Direction;
import com.coder.snake.model.Food;
import com.coder.snake.model.Snake;
import com.coder.snake.model.SoundPlayer;

public class GamePanel extends JPanel implements ActionListener {
	
	
	private static final int CELL_SIZE = 10;
	public static final int WIDTH = 96;
	public static final int HEIGHT = 70;

	private Food food;
	private Snake snake;
	private Timer timer;
	private int score = 0;
	/* Game level (speed) */
	public int DEFAULT_GAME_DIFFICULTY = 32;
	private String statusMessage = "";
	private boolean mute = false;
	private Color flexColor = Color.WHITE;
	private ControlPanel currentControlPanel;
	private static final long serialVersionUID = 1L;

	public GamePanel(ControlPanel controlPanel) {
		
		this.currentControlPanel = controlPanel;
		
		this.setFocusable(true);
	    this.requestFocusInWindow(true);
		this.setPreferredSize(new Dimension(910, 720));
	    this.addFocusListener(customGetFocus());
	    this.addKeyListener(customKeyAdapter());
	    controlPanel.addActionToButtons(this);
	    	    
	    /* define X and Y coordinate of snake */
	    snake = new Snake(30, 30, 29, 30);
	    food = new Food((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
	    timer = new Timer(DEFAULT_GAME_DIFFICULTY , e-> {
	    	initialize();
	    });
	}
	
	public void start() {
		timer.start();
		statusMessage = "";
		snake.gameIsOver = false;
		currentControlPanel.startButton.setEnabled(false);
		currentControlPanel.pauseButton.setEnabled(true);
		currentControlPanel.easyRdBtn.setEnabled(false);
		currentControlPanel.mediumRdBtn.setEnabled(false);
		currentControlPanel.hardRdBtn.setEnabled(false);
		score = 0;
		repaint();
	}
	
	public void pause() {
		timer.stop();
		statusMessage = "Paused!";
		currentControlPanel.pauseButton.setText("Resume");
		currentControlPanel.pauseButton.setActionCommand("resume");
		repaint();
	}
	
	public void resume() {
		timer.restart();
		statusMessage = "";
		currentControlPanel.pauseButton.setText("Pause");
		currentControlPanel.pauseButton.setActionCommand("pause");
		repaint();
	}

	public void gameOver() {
		timer.stop();
		statusMessage = "Game over!";
		snake.gameIsOver = true;
		currentControlPanel.startButton.setEnabled(true);
		currentControlPanel.pauseButton.setEnabled(false);
		currentControlPanel.easyRdBtn.setEnabled(true);
		currentControlPanel.mediumRdBtn.setEnabled(true);
		currentControlPanel.hardRdBtn.setEnabled(true);
		snake.length = 4;
		repaint();
	}
	
	/* Change game difficulty with changing delay of timer.
	 * Then add the new difficulty value to variable to can
	 * set the score based on difficulty.	  
	 */
	public void changeDifficulty(int difficulty) {
		this.timer.setDelay(difficulty);
		DEFAULT_GAME_DIFFICULTY = difficulty;
		repaint();
	}
	
	public void drawMessage(Graphics2D g) {
		g.setColor(Color.BLACK);
		final Font font = new Font("Lucida Grande", Font.BOLD, 80);
		g.setFont(font);
		g.drawString(this.statusMessage, 300, 370);
	}
	
	public void drawScore() {
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

		Graphics2D graphics2d = (Graphics2D) g;
		applyQualityRenderingHints(graphics2d);

		//Draw guide lines
//		drawlines(graphics2d);


		for (int index = 0; index < snake.length; index++) {
			makeUpSnake(graphics2d);
			graphics2d.fillRect(snake.positionX[index] * CELL_SIZE, snake.positionY[index] * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}

		drawFood(food.positionX, food.positionY, CELL_SIZE, graphics2d);
		
		drawMessage(graphics2d);

	}

	public void makeUpSnake(Graphics2D g2D) {
		
		final BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB); 
		final Rectangle r = new Rectangle(0,0,5,5);
		final TexturePaint tp = new TexturePaint(bi,r); 
		final Graphics2D big = bi.createGraphics(); 
		// Render into the BufferedImage graphics to create the texture 
		big.setColor(Color.GREEN.darker()); 
		big.fillRect(0,0,5,5); 
		big.setColor(Color.lightGray); 
		big.fillOval(0,0,3,3);
		 
		// Add the texture paint to the graphics context. 
		g2D.setPaint(tp); 
	}
	
	public void drawFood(int foodPositionX, int foodPositionY, int cellSize, Graphics2D g2D) {

			final Image snail = Toolkit.getDefaultToolkit().getImage("src/com/coder/snake/icons/snail.png");
			g2D.drawImage(snail, foodPositionX * cellSize, foodPositionY * cellSize, null);
	}
	
	 protected void applyQualityRenderingHints(Graphics2D g2d) {
         g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
         g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
     }
	 
	public void initialize() {
		snake.move();
		
        if ((snake.positionX[0] == food.positionX) & (snake.positionY[0] == food.positionY)) {
        	if(!mute) {
        		new SoundPlayer();
        	}
        	
        	food.addFood();
            snake.length++;
            
            switch (DEFAULT_GAME_DIFFICULTY) {
			case 64:
				score = score + 3;
				break;
			case 32:
				score = score + 5;
				break;
			case 16:
				score = score + 10;
				break;
			default:
				repaint();
				break;
			}
            drawScore();
        }
 
        if(snake.gameIsOver) {
        	gameOver();
		}

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
	
	
	/* Get action commands from control panel and trigger the action event. */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "sound":
			if (mute) {
				mute = false;
				unMuteGame();
			} else {
				mute = true;
				muteGame();
			}
			break;
		case "colors":
			changeBackground();
			break;
		case "start":
			start();
			break;
		case "pause":
			pause();
			break;
		case "resume":
			resume();
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
		case "easyLevel":
			changeDifficulty(64);
			break;
		case "mediumLevel":
			changeDifficulty(32);
			break;
		case "hardLevel":
			changeDifficulty(16);
			break;
		default:
			repaint();
			break;
		}

	}
}
