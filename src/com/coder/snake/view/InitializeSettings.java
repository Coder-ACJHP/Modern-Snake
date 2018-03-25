package com.coder.snake.view;

import java.awt.Dimension;
import java.awt.Toolkit;

public class InitializeSettings {

	
	private static MainBoard mainBoard;
	private static GamePanel gamePanel;
	private static ControlPanel controlPanel;
	
	public void injectDependencies() {
		
		InitializeSettings.controlPanel = new ControlPanel();
		InitializeSettings.gamePanel = new GamePanel();
		InitializeSettings.mainBoard = new MainBoard();
		
		final int controlPanelWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
		final int gamePanelWidth = controlPanelWidth * 3;
		final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		final Dimension gamePanelDimension = new Dimension(gamePanelWidth, screenHeight);
		final Dimension controlPanelDimension = new Dimension(controlPanelWidth, screenHeight);
		
		gamePanel.setPreferredSize(gamePanelDimension);
		controlPanel.setPreferredSize(controlPanelDimension);
		
		mainBoard.setControlPanel(controlPanel);
		mainBoard.setGamePanel(gamePanel);
		mainBoard.initialize();
		
	}
	

}
