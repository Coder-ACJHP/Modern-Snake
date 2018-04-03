package com.coder.snake.start;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.coder.snake.controller.InitializeSettings;

public class Modern_Snake {

	public static void main(String[] args) {
			
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
		}

		SwingUtilities.invokeLater(() -> {
			
			InitializeSettings defaultSettings = new InitializeSettings();
			defaultSettings.injectDependencies();
		});
	}

}
