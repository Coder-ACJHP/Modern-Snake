package com.coder.snake.view;

public class InitializeSettings {

	
	private static MainBoard mainBoard;
	private static GamePanel gamePanel;
	private static ControlPanel controlPanel;
	
	public void injectDependencies() {
		
		InitializeSettings.controlPanel = new ControlPanel();
		InitializeSettings.gamePanel = new GamePanel(controlPanel);
		InitializeSettings.mainBoard = new MainBoard();
		
		mainBoard.setControlPanel(controlPanel);
		mainBoard.setGamePanel(gamePanel);
		mainBoard.initialize();
		
	}
	

}
