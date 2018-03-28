package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Snake {

	public int length = 4;
	public Enum<Direction> direction;
	public static final int WIDTH = 96;
	public static final int HEIGHT = 70;
	public volatile boolean gameIsOver = false;

	public int positionX[] = new int[WIDTH * HEIGHT];
	public int positionY[] = new int[WIDTH * HEIGHT];

	public Snake(int point0X, int point0Y, int point1X, int point1Y) {
		
	    /* define X and Y coordinate of snake */
		positionX[0] = point0X;
		positionY[0] = point0Y;
		positionX[1] = point1X;
		positionY[1] = point1Y;
		
		if(point0X - point1X == 1) {
			positionX[2] = point0X -2;
			positionY[2] = point0Y;
			positionX[3] = point0X -3;
			positionY[3] = point0Y;
		}

		direction = Direction.RIGHT;
	}

	public void move() {

			for (int index = length; index > 0; index--) {
				positionX[index] = positionX[index - 1];
				positionY[index] = positionY[index - 1];
			}

			if (direction == Direction.RIGHT)
				positionX[0]++;
			if (direction == Direction.DOWN)
				positionY[0]++;
			if (direction == Direction.LEFT)
				positionX[0]--;
			if (direction == Direction.UP)
				positionY[0]--;

		/* If the snake hits itself (body) finish the game */
			for (int index = length - 1; index > 0; index--) {
			if ((positionX[0] == positionX[index]) && (positionY[0] == positionY[index]))
				this.gameIsOver = true;
			}

			if (positionX[0] > GamePanel.WIDTH)
				positionX[0] = 0;
			if (positionX[0] < 0)
				positionX[0] = GamePanel.WIDTH - 1;
			if (positionY[0] > GamePanel.HEIGHT - 1)
				positionY[0] = 0;
			if (positionY[0] < 0)
				positionY[0] = GamePanel.HEIGHT - 1;

			if (length < 4)
				length = 4;
	}
	
}
