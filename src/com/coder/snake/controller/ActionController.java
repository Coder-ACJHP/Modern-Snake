package com.coder.snake.controller;

import java.awt.Color;

public interface ActionController {

	public void startGame();
	
	public void muteGame();
	
	public void unMuteGame();
	
	public void changeBackground(Color color);
	
	public void moveToLeft();
	
	public void moveToRight();
	
	public void moveToUp();
	
	public void moveToDown();
}
