package com.coder.snake.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class ControlPanel extends JPanel {
	
	/**
	 * 
	 */
	protected JPanel contentpanel;
	final Color topColor = Color.decode("#000000");
	final Color bottomColor = Color.decode("#53346d");
	private JSeparator whiteSeparator_1, whiteSeparator_2, whiteSeparator_3;
	public JLabel panelIcon, titleLabel, soundLabel, backgroundColorLabel, titleLabelTwo, scoreBoard;
	public JButton startButton, pauseButton, colorChooserButton, upButton, downButton, leftButton, rightButton,
			soundButton;
	private static final long serialVersionUID = 1L;
	
	public ControlPanel() {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GRAY, null, null, null));
		setLayout(new BorderLayout(0, 0));
	
		contentpanel = new JPanel();
		contentpanel.setAutoscrolls(true);
		contentpanel.setOpaque(false);
		contentpanel.setPreferredSize(new Dimension(300, 800));
		contentpanel.setLayout(null);
				
		titleLabel = new JLabel("Basic configurations : ");
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setBounds(10, 305, 276, 36);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		contentpanel.add(titleLabel);
		
		soundLabel = new JLabel("Sound : ");
		soundLabel.setBounds(12, 367, 202, 24);
		soundLabel.setForeground(Color.WHITE);
		soundLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		contentpanel.add(soundLabel);
		
		backgroundColorLabel = new JLabel("Background color : ");
		backgroundColorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		backgroundColorLabel.setBounds(12, 399, 202, 24);
		backgroundColorLabel.setForeground(Color.WHITE);
		contentpanel.add(backgroundColorLabel);
		
		colorChooserButton = new JButton("");
		colorChooserButton.setActionCommand("colors");
		colorChooserButton.setOpaque(true);
		colorChooserButton.setBackground(new Color(255, 255, 255));
		colorChooserButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		colorChooserButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/color-palette.png")));
		colorChooserButton.setBounds(254, 395, 34, 29);
		colorChooserButton.setPreferredSize(new Dimension(50, 29));
		contentpanel.add(colorChooserButton);
		
		titleLabelTwo = new JLabel("Controllers : ");
		titleLabelTwo.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabelTwo.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabelTwo.setBounds(10, 451, 276, 36);
		titleLabelTwo.setForeground(Color.WHITE);
		titleLabelTwo.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		titleLabelTwo.setPreferredSize(new Dimension(140, 16));
		contentpanel.add(titleLabelTwo);
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(0, 506, 300, 103);
		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(null);
		contentpanel.add(buttonsPanel);
		
		upButton = new JButton("");
		upButton.setActionCommand("up");
		upButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/up.png")));
		upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		upButton.setBounds(104, 10, 89, 38);
		buttonsPanel.add(upButton);
		
		leftButton = new JButton("");
		leftButton.setActionCommand("left");
		leftButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/left.png")));
		leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftButton.setBounds(12, 52, 89, 38);
		buttonsPanel.add(leftButton);
		
		downButton = new JButton("");
		downButton.setActionCommand("down");
		downButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/down.png")));
		downButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		downButton.setBounds(104, 52, 89, 38);
		buttonsPanel.add(downButton);
		
		rightButton = new JButton("");
		rightButton.setActionCommand("right");
		rightButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/right.png")));
		rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightButton.setBounds(196, 52, 91, 38);
		buttonsPanel.add(rightButton);

		
		whiteSeparator_1 = new JSeparator();
		whiteSeparator_1.setBackground(new Color(245, 245, 245));
		whiteSeparator_1.setPreferredSize(new Dimension(100, 0));
		whiteSeparator_1.setBounds(10, 341, 276, 12);
		contentpanel.add(whiteSeparator_1);
		
		whiteSeparator_2 = new JSeparator();
		whiteSeparator_2.setPreferredSize(new Dimension(100, 0));
		whiteSeparator_2.setBackground(new Color(245, 245, 245));
		whiteSeparator_2.setBounds(10, 488, 275, 12);
		contentpanel.add(whiteSeparator_2);
		
		final JLabel lblYourScoreIs = new JLabel("Your score is");
		lblYourScoreIs.setForeground(Color.WHITE);
		lblYourScoreIs.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYourScoreIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScoreIs.setBounds(88, 184, 111, 24);
		contentpanel.add(lblYourScoreIs);

		scoreBoard = new JLabel("0");
		scoreBoard.setForeground(Color.WHITE);
		scoreBoard.setHorizontalAlignment(SwingConstants.CENTER);
		scoreBoard.setVerticalAlignment(SwingConstants.CENTER);
		scoreBoard.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		scoreBoard.setBackground(new Color(255, 255, 255));
		scoreBoard.setBorder(null);
		scoreBoard.setBounds(65, 209, 159, 47);
		contentpanel.add(scoreBoard);
		
		whiteSeparator_3 = new JSeparator();
		whiteSeparator_3.setPreferredSize(new Dimension(100, 0));
		whiteSeparator_3.setBackground(new Color(245, 245, 245));
		whiteSeparator_3.setBounds(11, 624, 276, 12);
		contentpanel.add(whiteSeparator_3);
		
		panelIcon = new JLabel("");
		panelIcon.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		panelIcon.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/snake.png")));
		panelIcon.setBounds(31, 33, 229, 90);
		contentpanel.add(panelIcon);
		
		soundButton = new JButton("");
		soundButton.setOpaque(true);
		soundButton.setActionCommand("sound");
		soundButton.setBackground(new Color(255, 255, 255));
		soundButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		soundButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/com/coder/snake/icons/spiker.png")));
		soundButton.setPreferredSize(new Dimension(50, 29));
		soundButton.setBounds(254, 364, 34, 29);
		contentpanel.add(soundButton);
		
		startButton = new JButton("Start");
		startButton.setActionCommand("start");
		startButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		startButton.setBounds(153, 637, 137, 47);
		contentpanel.add(startButton);

		pauseButton = new JButton("Pause");
		pauseButton.setActionCommand("pause");
		pauseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pauseButton.setBounds(10, 637, 137, 47);
		pauseButton.setEnabled(false);
		contentpanel.add(pauseButton);

		this.add(contentpanel);
	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		final GradientPaint gradient = new GradientPaint(0, 0, topColor, 0, getHeight(), bottomColor);
		graphics2d.setPaint(gradient);
		
		graphics2d.fillRect(0, 0, getWidth(), getHeight());
		
		final Font TEXT_FONT = new Font("Helvetica", Font.BOLD, 35);
		graphics2d.setColor(Color.WHITE);
		graphics2d.setFont(TEXT_FONT);
		
		graphics2d.drawString("Modern Snake", 30, 165);
		
	}

	public void addActionToButtons(ActionListener actionListener) {
		startButton.addActionListener(actionListener);
		pauseButton.addActionListener(actionListener);
		soundButton.addActionListener(actionListener);
		rightButton.addActionListener(actionListener);
		leftButton.addActionListener(actionListener);
		upButton.addActionListener(actionListener);
		downButton.addActionListener(actionListener);
		colorChooserButton.addActionListener(actionListener);
	}
}





