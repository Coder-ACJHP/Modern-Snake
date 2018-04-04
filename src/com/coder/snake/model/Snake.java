package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Snake {

	public int length = 4;
	private int POSIX = 30;
	private final static int POSIY = 30;
	public Enum<Direction> direction;
	public volatile boolean gameIsOver = false;

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
		direction = Direction.RIGHT;
	}

	public void move() {

			for (int index = length; index > 0; index--) {
				positionX[index] = positionX[index - 1];
				positionY[index] = positionY[index - 1];
			}

			if (getDirection() == Direction.RIGHT) {
				positionX[0]++;
			} else if (getDirection() == Direction.DOWN) {
				positionY[0]++;
			} else if (getDirection() == Direction.LEFT) {
				positionX[0]--;
			} else if (getDirection() == Direction.UP) {
				positionY[0]--;
			}

		/* If the snake hits itself (body) finish the game */
		for (int index = length; index > 0; index--) {
			if ((positionX[0] == positionX[index]) && (positionY[0] == positionY[index]))
				this.gameIsOver = true;
		}

		/* Control the snake hits the walls */
		if (positionX[0] > GamePanel.WIDTH || positionX[0] < 0 
				|| positionY[0] > GamePanel.HEIGHT || positionY[0] < 0) {
			this.gameIsOver = true;
		}

		if (length < 4)
			length = 4;
	}

	public Enum<Direction> getDirection() {
		return direction;
	}

	public void setDirection(Enum<Direction> direction) {
		this.direction = direction;
	}
	
}
