package com.coder.snake.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.coder.snake.icons.ImagePathes;
import com.coder.snake.model.Direction;
import com.coder.snake.model.Food;
import com.coder.snake.model.Snake;
import com.coder.snake.model.SoundPlayer;

public class GamePanel extends JPanel implements ActionListener {
	
	
	private static final int CELL_SIZE = 15;
	public static final int WIDTH = 65;
	public static final int HEIGHT = 46;
	final Color topColor = Color.decode("#000000");
	final Color bottomColor = Color.decode("#53346d");

	private Food food;
	private Snake snake;
	private Timer timer;
	private int score;
	private int highScore = 0;
	public int difficult = 42;
	private String snailImagePath = ImagePathes.SNAIL;
	private String ratImagePath = ImagePathes.RAT;
	private String headImagePath = ImagePathes.HEAD_RIGHT;
	private String bodyImagePath = ImagePathes.BODY_RIGHT;
	private String tailImagePath = ImagePathes.TAIL_RIGHT;
	/* Game level (speed) */
	private boolean mute = false;
	private boolean guideLine = false;
	private SoundPlayer mediaPlayer;
	private Color flexColor = Color.WHITE;
	private ControlPanel currentControlPanel;
	private static final long serialVersionUID = 1L;
	private String statusMessage = "Press start button!";

	public GamePanel(ControlPanel controlPanel) {
		
		this.currentControlPanel = controlPanel;
		
		this.setFocusable(true);
	    this.requestFocusInWindow(true);
		this.setPreferredSize(new Dimension(900, 720));
	    this.addFocusListener(customGetFocus());
	    this.addKeyListener(customKeyAdapter());
	    controlPanel.addActionToButtons(this);
	    	    
	    highScore = Integer.valueOf(currentControlPanel.highScoreBoard.getText());
		snake = new Snake();
		food = new Food();
		mediaPlayer = new SoundPlayer();
		timer = new Timer(difficult, e -> {
	    	initialize();
	    });
	}
	
	public void initialize() {
		snake.move();

		
		if ((snake.positionX[0] == food.randomX) && (snake.positionY[0] == food.randomY)) {
			if (!mute) {
				mediaPlayer.foodEaten();
			}

			food.addFood();
			food.eatenCounter++;
			snake.length++;
			changeScore(difficult);

			if (food.eatenCounter % 5 == 0) {
				food.addBonusFood();
			}

		} else if((snake.positionX[0] == food.masterPositionX) && (snake.positionY[0] == food.masterPositionY)) {
			if (!mute) {
				mediaPlayer.foodEaten();
			}
			food.deleteBonusFood();
			changeScore(20);
			snake.length = snake.length + 4;
		}

		/* If user earn new high score show it live */
		if (score > highScore)
			highScore = score;


		if (snake.gameIsOver) {
			gameOver();
			if(!mute) {
				mediaPlayer.gameOver();
			}
		}

		drawScore();
		refresh();
	}

	public void start() {
		/* Make snake head turned to right because snake body is reseted */
		this.headImagePath = ImagePathes.HEAD_RIGHT;
		this.food.addFood();
		/* Initialize the game to start from the scratch */
		this.snake.initialize();
		prepareButtonsForStart();
		this.score = 0;
		timer.start();
		this.statusMessage = "";
		this.snake.gameIsOver = false;
		this.food.eatenCounter = 0;
		refresh();
	}
	
	public void pause() {
		timer.stop();
		statusMessage = "Paused!";
		currentControlPanel.pauseButton.setText("Resume");
		currentControlPanel.pauseButton.setActionCommand("resume");
		refresh();
	}
	
	public void resume() {
		timer.restart();
		statusMessage = "";
		currentControlPanel.pauseButton.setText("Pause");
		currentControlPanel.pauseButton.setActionCommand("pause");
		refresh();
	}

	public void gameOver() {
		timer.stop();
		statusMessage = "Game over!";
		prepareButtonsForGameOver();
		
		if(score > highScore) {

			try (final FileWriter fileWriter = 
					new FileWriter(new File("src/com/coder/snake/files/scoreBoard.txt"))){
				
				fileWriter.write(String.valueOf(score));
				fileWriter.flush();
				
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
			}
		}
		refresh();
	}
	
	private void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	private void prepareButtonsForStart() {
		currentControlPanel.startButton.setEnabled(false);
		currentControlPanel.pauseButton.setEnabled(true);
		currentControlPanel.easyRdBtn.setEnabled(false);
		currentControlPanel.mediumRdBtn.setEnabled(false);
		currentControlPanel.hardRdBtn.setEnabled(false);
	}
	
	private void prepareButtonsForGameOver() {
		currentControlPanel.startButton.setEnabled(true);
		currentControlPanel.pauseButton.setEnabled(false);
		currentControlPanel.easyRdBtn.setEnabled(true);
		currentControlPanel.mediumRdBtn.setEnabled(true);
		currentControlPanel.hardRdBtn.setEnabled(true);
	}

	private void changeScore(int theDifficult) {
		switch (theDifficult) {
		case 64:
			score = score + 3;
			break;
		case 42:
			score = score + 5;
			break;
		case 32:
			score = score + 10;
			break;
		case 20:
			score = score + 20;
			break;
		default:
			refresh();
			break;
		}
	}
	
	/* Change game difficulty with changing delay of timer.
	 * Then add the new difficulty value to variable to can
	 * set the score based on difficulty.	  
	 */
	public void changeDifficulty(int difficulty) {
		this.timer.setDelay(difficulty);
		difficult = difficulty;
		refresh();
	}
	
	public void drawMessage(Graphics2D g) {
		g.setColor(Color.BLACK);
		Font font = new Font("Lucida Grande", Font.BOLD, 50);
		FontMetrics fontMetrics = getFontMetrics(font);
		g.setFont(font);
		g.drawString(this.statusMessage, (getWidth() - fontMetrics.stringWidth(statusMessage)) / 2, getHeight() / 2);

		if (this.food.showCounter) {
			
			int initialWidth = 163;
			int drawingWidth = this.food.interval * initialWidth;
			g.setPaint(new GradientPaint(0, 10, topColor, initialWidth, 10, bottomColor));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));
			g.fillRect(0, getHeight()-10, drawingWidth, 10);
			
		}
	}
	
	public void drawScore() {
		currentControlPanel.scoreBoard.setText(String.valueOf(this.score));
	}
	
	// This method drawing guide lines (cells)
	public void drawlines(Graphics2D g) {

		for (int x = 0; x <= WIDTH * CELL_SIZE; x += CELL_SIZE) {
			for (int y = 0; y <= HEIGHT * CELL_SIZE; y += CELL_SIZE) {
				g.setStroke(new BasicStroke(0.1f));
				g.setColor(Color.GRAY.brighter());
				g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
	}


	public void drawHead(int index, Graphics2D g2D) {

		final Image head = new ImageIcon(this.getClass().getResource(headImagePath)).getImage();
		g2D.drawImage(head, snake.positionX[index] * CELL_SIZE, snake.positionY[index] * CELL_SIZE, CELL_SIZE,
				CELL_SIZE, null);
	}

	public void drawTail(int index, Graphics2D g2D) {

		final Image tail = new ImageIcon(this.getClass().getResource(tailImagePath)).getImage();
		g2D.drawImage(tail, snake.positionX[index] * CELL_SIZE, snake.positionY[index] * CELL_SIZE, CELL_SIZE,
				CELL_SIZE, null);
	}

	public void drawBody(int index, Graphics2D g2D) {

		final Image body = new ImageIcon(this.getClass().getResource(bodyImagePath)).getImage();
		g2D.drawImage(body, snake.positionX[index] * CELL_SIZE, snake.positionY[index] * CELL_SIZE, CELL_SIZE,
				CELL_SIZE, null);
	}
	
	public void drawFood(int foodPositionX, int foodPositionY, Graphics2D g2D) {
		final Image snail = new ImageIcon(this.getClass().getResource(snailImagePath)).getImage();
		g2D.drawImage(snail, foodPositionX * CELL_SIZE, foodPositionY * CELL_SIZE, 20, 20, null);
	}
	
	public void drawBonusFood(int foodPositionX, int foodPositionY, Graphics2D g2D) {
		final Image rat = new ImageIcon(this.getClass().getResource(ratImagePath)).getImage();
		g2D.drawImage(rat, foodPositionX * CELL_SIZE, foodPositionY * CELL_SIZE, 20, 20, null);
	}
	
	public void changeBackground() {
		flexColor = JColorChooser.showDialog(null, 
				"Choose color to change snake background", Color.WHITE);
		setBackground(flexColor);
		refresh();
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Graphics2D graphics2d = (Graphics2D) g;
		applyQualityRenderingHints(graphics2d);

		if(guideLine) {
			drawlines(graphics2d);
		}
		
		/* Draw snake parts */
		for (int index = 0; index < snake.length; index++) {
			
			if (index == 0) {
				/* head */
				drawHead(index, graphics2d);
				
			} else {
				
				//Save previous part of snake
				int previousPartX = snake.positionX[index - 1];
				int previousPartY = snake.positionY[index - 1];
				int currentPartX = snake.positionX[index];
				int currentPartY = snake.positionY[index];
				int nextPartX = snake.positionX[index + 1];
				int nextPartY = snake.positionY[index + 1];
				
				if (index == snake.length - 1) {
					
					/* tail */

					if (previousPartY < snake.positionY[index]) {
						// Up
						tailImagePath = ImagePathes.TAIL_UP;
					} else if (previousPartX > snake.positionX[index]) {
						// Right
						tailImagePath = ImagePathes.TAIL_RIGHT;
					} else if (previousPartY > snake.positionY[index]) {
						// Down
						tailImagePath = ImagePathes.TAIL_DOWN;
					} else if (previousPartX < snake.positionX[index]) {
						// Left
						tailImagePath = ImagePathes.TAIL_LEFT;
					}

					drawTail(index, graphics2d);
					
				} else {
					
					/* body */
					if (previousPartX > currentPartX && nextPartY < currentPartY || nextPartX > currentPartX && previousPartY < currentPartY) {
						// Left-Up
						bodyImagePath = ImagePathes.BODY_CORNER_LEFT_UP;
					} else if (previousPartY > currentPartY && nextPartX > currentPartX || nextPartY > currentPartY && previousPartX > currentPartX) {
						// Left-Down
						bodyImagePath = ImagePathes.BODY_CORNER_LEFT_DOWN;
					} else if (previousPartY < currentPartY && nextPartX < currentPartX || nextPartY < currentPartY && previousPartX < currentPartX) {
						// Right-Up
						bodyImagePath = ImagePathes.BODY_CORNER_RIGHT_UP;
					} else if (previousPartX < currentPartX && nextPartY > currentPartY || nextPartX < currentPartX && previousPartY > currentPartY) {
						// Right-Down
						bodyImagePath = ImagePathes.BODY_CORNER_RIGHT_DOWN;
					} else if (previousPartY > currentPartY || previousPartY < currentPartY) {
						// Down - Up
						bodyImagePath = ImagePathes.BODY_UP;
					} else if (previousPartX < currentPartX || previousPartX > currentPartX) {
						// Left - Right 
						bodyImagePath = ImagePathes.BODY_RIGHT;
					}
					
					drawBody(index, graphics2d);
				}
			}
				
		}
		
				
		drawFood(food.randomX, food.randomY, graphics2d);
		
		if(food.eatenCounter > 0 && food.eatenCounter % 5 == 0) {
			drawBonusFood(food.masterPositionX, food.masterPositionY, graphics2d);
		}
		
		drawMessage(graphics2d);

		graphics2d.dispose();
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
		if (!snake.gameIsOver) {
			if (snake.direction != Direction.DOWN) {
				snake.direction = Direction.UP;
				headImagePath = ImagePathes.HEAD_UP;
				refresh();
			}
		}
	}
	
	public void moveToDown() {
		if (!snake.gameIsOver) {
			if (snake.direction != Direction.UP) {
				snake.direction = Direction.DOWN;
				headImagePath = ImagePathes.HEAD_DOWN;
				refresh();
			}
		}
	}
	
	public void moveToRight() {
		if (!snake.gameIsOver) {
			if (snake.direction != Direction.LEFT) {
				snake.direction = Direction.RIGHT;
				headImagePath = ImagePathes.HEAD_RIGHT;
				refresh();
			}
		}
	}
	
	public void moveToLeft() {
		if (!snake.gameIsOver) {
			if (snake.direction != Direction.RIGHT) {
				snake.direction = Direction.LEFT;
				headImagePath = ImagePathes.HEAD_LEFT;
				refresh();
			}
		}
	}
	
	private KeyListener customKeyAdapter() {

		final KeyAdapter adapter = new KeyAdapter() {

			@Override
			public synchronized void keyPressed(KeyEvent e) {

				if (!snake.gameIsOver) {
					Integer keyCode = e.getKeyCode();

					if ((keyCode == KeyEvent.VK_LEFT) && snake.direction != Direction.RIGHT) {
						snake.direction = Direction.LEFT;
						headImagePath = ImagePathes.HEAD_LEFT;
						refresh();
					} else if ((keyCode == KeyEvent.VK_RIGHT) && snake.direction != Direction.LEFT) {
						snake.direction = Direction.RIGHT;
						headImagePath = ImagePathes.HEAD_RIGHT;
						refresh();
					} else if ((keyCode == KeyEvent.VK_UP) && snake.direction != Direction.DOWN) {
						snake.direction = Direction.UP;
						headImagePath = ImagePathes.HEAD_UP;
						refresh();
					} else if ((keyCode == KeyEvent.VK_DOWN) && snake.direction != Direction.UP) {
						snake.direction = Direction.DOWN;
						headImagePath = ImagePathes.HEAD_DOWN;
						refresh();
					}

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
				currentControlPanel.soundButton.setIcon(new 
						ImageIcon(ImagePathes.UNMUTE_IMG));
			} else {
				mute = true;
				currentControlPanel.soundButton.setIcon(new 
						ImageIcon(ImagePathes.MUTE_IMG));
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
			changeDifficulty(42);
			break;
		case "hardLevel":
			changeDifficulty(32);
			break;
		case "guideLines":
			if(guideLine) {
				guideLine = false;
			}else {
				guideLine = true;
			}
		default:
			refresh();
			break;
		}

	}
}
