package com.coder.snake.start;

import javax.swing.SwingUtilities;

import com.coder.snake.view.InitializeSettings;

public class ApplicationStart {

	public static void main(String[] args) {
			
		SwingUtilities.invokeLater(() -> {
			
			InitializeSettings defaultSettings = new InitializeSettings();
			defaultSettings.injectDependencies();
		});
	}

}
