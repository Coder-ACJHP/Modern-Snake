package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Food {

	public int positionX;
	public int positionY;

	public Food(int startX, int startY) {
		positionX = startX;
		positionY = startY;

	}

	public void addFood() {
		/* Give some spaces like 5 px for border */
		positionX = (int) (Math.random() * GamePanel.WIDTH);
		positionY = (int) (Math.random() * GamePanel.HEIGHT);
	}
	
}
