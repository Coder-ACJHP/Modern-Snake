package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Snake {

	public Enum<Direction> direction;
	public int length = 4;
	public volatile boolean gameIsOver = false;

	public int positionX[] = new int[GamePanel.WIDTH * GamePanel.HEIGHT];
	public int positionY[] = new int[GamePanel.WIDTH * GamePanel.HEIGHT];

	public Snake(int x0, int y0, int x1, int y1) {
		positionX[0] = x0;
		positionY[0] = y0;
		positionX[1] = x1;
		positionY[1] = y1;
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
