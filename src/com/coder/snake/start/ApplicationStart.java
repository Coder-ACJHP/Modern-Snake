package com.coder.snake.start;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.coder.snake.view.InitializeSettings;

public class ApplicationStart {

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
