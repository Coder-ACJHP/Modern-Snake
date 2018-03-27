package com.coder.snake.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.apple.eawt.Application;

public class MainBoard extends JFrame {

	
	/**
	 * 
	 */
	private final static int SCREEN_WIDTH = 1280;
	private final static int SCREEN_HEIGHT = 720;
	private JPanel gamePanel;
	private JPanel container;
	private Dimension frameSize;
	private JPanel controlPanel;
	private static final long serialVersionUID = 1L;


	public MainBoard(){}
	
	public void initialize() {
		//add screen size to variable to can be used after.
		frameSize = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		//Create the main frame for game.
		this.setTitle("Modern Snake");
		this.setPreferredSize(frameSize);
		this.setMaximumSize(frameSize);
		this.setResizable(false);
		this.setFont(new Font("Helvetica", Font.PLAIN, 18));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String version = System.getProperty("os.name").toLowerCase();
		if (version.contains("windows") || version.contains("nux")) {
			this.setIconImage(
					Toolkit.getDefaultToolkit().getImage(getClass().
							getResource("/com/coder/snake/icons/snake.png")));
        } else {
            Application.getApplication()
                    .setDockIconImage(new ImageIcon(getClass().getResource(
                    		"/com/coder/snake/icons/snake.png")).getImage());
        }
		
		container = new JPanel();
		container.setBackground(Color.DARK_GRAY);
		final BorderLayout containerLayout = new BorderLayout();
		container.setLayout(containerLayout);
		
		container.add(gamePanel, BorderLayout.CENTER);
		container.add(controlPanel, BorderLayout.EAST);
		
		this.add(container, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void setControlPanel(JPanel cntrlPanel) {
		if(cntrlPanel == null) {
			return;
		}
		this.controlPanel = cntrlPanel;
	}
	
	
	public void setGamePanel(JPanel gmPanel) {
		if(gmPanel == null) {
			return;
		}
		this.gamePanel = gmPanel;
	}

}
