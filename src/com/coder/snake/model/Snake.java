package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Snake {

	public int router;
	public int length = 4;
	private int POSIX = 10;
	private final static int POSIY = 10;
	public volatile boolean gameIsOver = true;

	public int positionX[] = new int[GamePanel.WIDTH * GamePanel.HEIGHT];
	public int positionY[] = new int[GamePanel.WIDTH * GamePanel.HEIGHT];

	public Snake() {
		initialize();
	}

	public void initialize() {
		/* define X and Y coordinate of snake */
		positionX[0] = POSIX;
		positionY[0] = POSIY;
		positionX[1] = POSIX - 1;
		positionY[1] = POSIY;
		positionX[2] = POSIX - 2;
		positionY[2] = POSIY;
		positionX[3] = POSIX - 3;
		positionY[3] = POSIY;
		length = 4;
		setRouter(Directions.RIGHT);
	}

	public void move() {

		gameIsOver = false;
		
			for (int index = length; index > 0; index--) {
				positionX[index] = positionX[index - 1];
				positionY[index] = positionY[index - 1];
			}

			if (getRouter() == Directions.RIGHT) {
				positionX[0]++;
			} else if (getRouter() == Directions.DOWN) {
				positionY[0]++;
			} else if (getRouter() == Directions.LEFT) {
				positionX[0]--;
			} else if (getRouter() == Directions.UP) {
				positionY[0]--;
			}


		/* If the snake hits itself (body) finish the game */
		for (int index = length; index > 0; index--) {
				if((length > 4) && (positionX[0] == positionX[index]) && (positionY[0] == positionY[index])) {
					setRouter(Directions.NONE);
					this.gameIsOver = true;
			}
		}

		/* Control the snake hits the walls */
		if (positionX[0] > GamePanel.WIDTH || positionX[0] < 0 
				|| positionY[0] > GamePanel.HEIGHT || positionY[0] < 0) {
			setRouter(Directions.NONE);
			this.gameIsOver = true;
		}

		if (length < 4)
			length = 4;
	}

	public int getRouter() {
		return router;
	}

	public void setRouter(int snakeDirection) {
		this.router = snakeDirection;
	}
	
}
