package com.coder.snake.controller;

import java.awt.Dimension;

import com.coder.snake.view.ControlPanel;
import com.coder.snake.view.GamePanel;

public class InitializeSettings {

	
	private static Dimension displaySize;
	private static MainBoard mainBoard;
	private static GamePanel gamePanel;
	private static ControlPanel controlPanel;
	
	public void injectDependencies() {
		
		displaySize = new Dimension(965, 690);
		InitializeSettings.controlPanel = new ControlPanel();
		InitializeSettings.gamePanel = new GamePanel(controlPanel, displaySize);
		InitializeSettings.mainBoard = new MainBoard();
		
		mainBoard.setControlPanel(controlPanel);
		mainBoard.setGamePanel(gamePanel);
		mainBoard.initialize();
		
		mainBoard.restart.addActionListener(e -> {
			gamePanel.restart();
			gamePanel.refresh();
		});

	}
	

}
