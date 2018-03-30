package com.coder.snake.model;

import com.coder.snake.view.GamePanel;

public class Food {

	public int positionX;
	public int positionY;
	public int eatenCounter;

	public Food(int startX, int startY) {
		positionX = startX;
		positionY = startY;
		eatenCounter = 0;
	}

	public void addFood() {
		positionX = (int) (Math.random() * GamePanel.WIDTH);
		positionY = (int) (Math.random() * GamePanel.HEIGHT);
		eatenCounter++;
	}
	
	public void addMasterFood() {

	}
}
