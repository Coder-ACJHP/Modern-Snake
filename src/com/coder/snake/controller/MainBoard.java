package com.coder.snake.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.apple.eawt.Application;
import com.coder.snake.files.FilePaths;
import com.coder.snake.icons.ImagePaths;

public class MainBoard extends JFrame {

	
	/**
	 * 
	 */
	private final static int SCREEN_WIDTH = 1280;
	private final static int SCREEN_HEIGHT = 745;
	private JPanel gamePanel;
	private JPanel container;
	private Dimension frameSize;
	private JPanel controlPanel;
	
	private JMenuBar menuBar;
	private JMenu tools, help;
	public JMenuItem restart, exit, sourceCode, howToPlay;
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
							getResource(ImagePaths.GAME_ICON)));
        } else {
            Application.getApplication()
					.setDockIconImage(new ImageIcon(getClass().getResource(ImagePaths.GAME_ICON)).getImage());
        }
		
		/* Initialize menu bar and add items to it */

		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		tools = new JMenu("Tools");
		tools.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		tools.setMnemonic(KeyEvent.VK_T);

		help = new JMenu("Help");
		help.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		help.setMnemonic(KeyEvent.VK_H);

		restart = new JMenuItem("Restart");
		restart.setIcon(new ImageIcon(this.getClass().getResource(ImagePaths.MENU_RESTART)));

		exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon(this.getClass().getResource(ImagePaths.MENU_EXIT)));
		exit.addActionListener(e -> {
			System.exit(0);
		});

		tools.add(restart);
		tools.addSeparator();
		tools.add(exit);

		sourceCode = new JMenuItem("Source code");
		sourceCode.setIcon(new ImageIcon(this.getClass().getResource(ImagePaths.MENU_SOURCE_CODE)));
		sourceCode.addActionListener(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/Coder-ACJHP/Modern-Snake"));
				} catch (IOException | URISyntaxException ex) {
					JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Open browser error!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		help.add(sourceCode);

		howToPlay = new JMenuItem("How to play");
		howToPlay.setIcon(new ImageIcon(this.getClass().getResource(ImagePaths.MENU_HOW_TO_PLAY)));
		howToPlay.addActionListener(e -> {

			final JEditorPane editorPane = new JEditorPane();
			editorPane.setEditable(false);
			final URL helpURL = MainBoard.class.getResource(FilePaths.HOW_TO_HTML);

			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException ex) {
					System.err.println(ex.getLocalizedMessage());
				}
			}

			// Put the editor pane in a scroll pane.
			final JScrollPane editorScrollPane = new JScrollPane(editorPane);
			editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			final JDialog dialog = new JDialog();
			dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImagePaths.GAME_ICON)));
			dialog.add(editorScrollPane, BorderLayout.CENTER);
			dialog.setPreferredSize(new Dimension(650, 500));
			dialog.setAlwaysOnTop(true);
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		});

		help.addSeparator();
		help.add(howToPlay);

		menuBar.add(tools);
		menuBar.add(help);
		/* Menu bar end */

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

		this.controlPanel = cntrlPanel;
	}
	
	public JPanel getControlPanel() {
		return this.controlPanel;
	}
	
	public void setGamePanel(JPanel gmPanel) {

		this.gamePanel = gmPanel;
	}

	public JPanel getGamePanel() {
		return this.gamePanel;
	}

}
