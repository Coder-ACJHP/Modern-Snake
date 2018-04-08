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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.snake.files.FilePaths;
import com.coder.snake.icons.ImagePaths;

public class ControlPanel extends JPanel {
	protected JPanel contentpanel;
	final Color topColor = Color.decode("#000000");
	final Color bottomColor = Color.decode("#53346d");
	private JSeparator whiteSeparator_1, whiteSeparator_2;
	public JRadioButton hardRdBtn, easyRdBtn, mediumRdBtn;
	public JLabel panelIcon, titleLabel, soundLabel, titleLabelTwo, scoreBoard, highScoreBoard;
	public JButton startButton, pauseButton, upButton, downButton, leftButton, rightButton, soundButton;
	private static final long serialVersionUID = 1L;
	
	public ControlPanel() {
		
		setPreferredSize(new Dimension(300, 700));
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
		titleLabel.setBounds(10, 296, 276, 36);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentpanel.add(titleLabel);
		
		soundLabel = new JLabel("Sound : ");
		soundLabel.setBounds(12, 368, 202, 24);
		soundLabel.setForeground(Color.WHITE);
		soundLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		contentpanel.add(soundLabel);
		
		titleLabelTwo = new JLabel("Controllers : ");
		titleLabelTwo.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabelTwo.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabelTwo.setBounds(10, 468, 276, 36);
		titleLabelTwo.setForeground(Color.WHITE);
		titleLabelTwo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabelTwo.setPreferredSize(new Dimension(140, 16));
		contentpanel.add(titleLabelTwo);
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(0, 523, 300, 103);
		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(null);
		contentpanel.add(buttonsPanel);
		
		upButton = new JButton("");
		upButton.setActionCommand("up");
		upButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ImagePaths.ARROW_UP)));
		upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		upButton.setBounds(104, 10, 89, 38);
		buttonsPanel.add(upButton);
		
		leftButton = new JButton("");
		leftButton.setActionCommand("left");
		leftButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ImagePaths.ARROW_LEFT)));
		leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftButton.setBounds(12, 52, 89, 38);
		buttonsPanel.add(leftButton);
		
		downButton = new JButton("");
		downButton.setActionCommand("down");
		downButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ImagePaths.ARROW_DOWN)));
		downButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		downButton.setBounds(104, 52, 89, 38);
		buttonsPanel.add(downButton);
		
		rightButton = new JButton("");
		rightButton.setActionCommand("right");
		rightButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ImagePaths.ARROW_RIGHT)));
		rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightButton.setBounds(196, 52, 91, 38);
		buttonsPanel.add(rightButton);

		
		whiteSeparator_1 = new JSeparator();
		whiteSeparator_1.setBackground(new Color(245, 245, 245));
		whiteSeparator_1.setPreferredSize(new Dimension(100, 0));
		whiteSeparator_1.setBounds(10, 332, 276, 12);
		contentpanel.add(whiteSeparator_1);
		
		whiteSeparator_2 = new JSeparator();
		whiteSeparator_2.setPreferredSize(new Dimension(100, 0));
		whiteSeparator_2.setBackground(new Color(245, 245, 245));
		whiteSeparator_2.setBounds(10, 505, 275, 12);
		contentpanel.add(whiteSeparator_2);
		
		final JLabel lblYourScoreIs = new JLabel("Your score is");
		lblYourScoreIs.setForeground(Color.WHITE);
		lblYourScoreIs.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblYourScoreIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScoreIs.setBounds(88, 181, 111, 24);
		contentpanel.add(lblYourScoreIs);

		scoreBoard = new JLabel("0");
		scoreBoard.setForeground(Color.WHITE);
		scoreBoard.setHorizontalAlignment(SwingConstants.CENTER);
		scoreBoard.setVerticalAlignment(SwingConstants.CENTER);
		scoreBoard.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		scoreBoard.setBackground(new Color(255, 255, 255));
		scoreBoard.setBorder(null);
		scoreBoard.setBounds(65, 206, 159, 47);
		contentpanel.add(scoreBoard);
		
		panelIcon = new JLabel(new ImageIcon(ControlPanel.class.getResource(ImagePaths.GAME_ICON)));
		panelIcon.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		panelIcon.setBounds(31, 18, 229, 115);
		contentpanel.add(panelIcon);
		
		soundButton = new JButton("");
		soundButton.setOpaque(true);
		soundButton.setActionCommand("sound");
		soundButton.setBackground(new Color(255, 255, 255));
		soundButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		soundButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ImagePaths.UNMUTE_IMG)));
		soundButton.setPreferredSize(new Dimension(50, 29));
		soundButton.setBounds(252, 365, 34, 29);
		contentpanel.add(soundButton);
		
		startButton = new JButton("Start");
		startButton.setActionCommand("start");
		startButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		startButton.setBounds(149, 636, 137, 47);
		contentpanel.add(startButton);

		pauseButton = new JButton("Pause");
		pauseButton.setActionCommand("pause");
		pauseButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pauseButton.setBounds(6, 636, 137, 47);
		pauseButton.setEnabled(false);
		contentpanel.add(pauseButton);

		this.add(contentpanel);
		
		final JLabel levelLabel = new JLabel("Difficulty : ");
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		levelLabel.setBounds(12, 408, 90, 24);
		contentpanel.add(levelLabel);
		
		easyRdBtn = new JRadioButton("Easy");
		easyRdBtn.setOpaque(false);
		easyRdBtn.setForeground(Color.WHITE);
		easyRdBtn.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		easyRdBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		easyRdBtn.setHorizontalAlignment(SwingConstants.CENTER);
		easyRdBtn.setActionCommand("easyLevel");
		easyRdBtn.setBounds(105, 411, 58, 23);
		contentpanel.add(easyRdBtn);
		
		mediumRdBtn = new JRadioButton("Mid");
		mediumRdBtn.setOpaque(false);
		mediumRdBtn.setForeground(Color.WHITE);
		mediumRdBtn.setSelected(true);
		mediumRdBtn.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		mediumRdBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		mediumRdBtn.setHorizontalAlignment(SwingConstants.CENTER);
		mediumRdBtn.setActionCommand("mediumLevel");
		mediumRdBtn.setBounds(166, 411, 58, 23);
		contentpanel.add(mediumRdBtn);
		
		hardRdBtn = new JRadioButton("Hard");
		hardRdBtn.setOpaque(false);
		hardRdBtn.setForeground(Color.WHITE);
		hardRdBtn.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		hardRdBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		hardRdBtn.setHorizontalAlignment(SwingConstants.CENTER);
		hardRdBtn.setActionCommand("hardLevel");
		hardRdBtn.setBounds(230, 411, 58, 23);
		contentpanel.add(hardRdBtn);
		
		/* Make these radio buttons group */
		final ButtonGroup group = new ButtonGroup();
		group.add(easyRdBtn);
		group.add(mediumRdBtn);
		group.add(hardRdBtn);
		
		final JLabel lblHighScore = new JLabel("High score : ");
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setForeground(new Color(220, 20, 60));
		lblHighScore.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblHighScore.setBounds(51, 258, 111, 24);
		contentpanel.add(lblHighScore);
		
		highScoreBoard = new JLabel();
		highScoreBoard.setText("0");
		highScoreBoard.setVerticalAlignment(SwingConstants.CENTER);
		highScoreBoard.setHorizontalAlignment(SwingConstants.LEFT);
		highScoreBoard.setForeground(Color.WHITE);
		highScoreBoard.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		highScoreBoard.setBorder(null);
		highScoreBoard.setBackground(Color.WHITE);
		highScoreBoard.setBounds(167, 258, 77, 25);
		contentpanel.add(highScoreBoard);
	
		try  {
			final BufferedReader br = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(FilePaths.SCORE_MEMORY),"UTF-8"));
			highScoreBoard.setText(br.readLine());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Sorry we couldn't find score board file!", "File path error!",
					JOptionPane.WARNING_MESSAGE);
		}
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
		
		graphics2d.drawString("Modern Snake", 30, 170);
		
	}

	public void addActionToButtons(ActionListener actionListener) {
		startButton.addActionListener(actionListener);
		pauseButton.addActionListener(actionListener);
		soundButton.addActionListener(actionListener);
		rightButton.addActionListener(actionListener);
		leftButton.addActionListener(actionListener);
		upButton.addActionListener(actionListener);
		downButton.addActionListener(actionListener);
		easyRdBtn.addActionListener(actionListener);
		mediumRdBtn.addActionListener(actionListener);
		hardRdBtn.addActionListener(actionListener);
	}
}





