package com.coder.snake.model;

import java.util.Timer;
import java.util.TimerTask;

import com.coder.snake.view.GamePanel;

public class Food {

	private Snake snake;
	public int positionX;
	public int positionY;
	public int randomX;
	public int randomY;
	public int masterPositionX;
	public int masterPositionY;
	public int eatenCounter = 0;
	private CountDown countDown;
	public int interval = 6;
	private Timer timer;
	private boolean isValidXY = false;
	public boolean showCounter = false;

	public Food() {
		snake = new Snake();
	}

	public void addFood() {
		
		isValidXY = false;
		while (!isValidXY) {
			
			randomX = (int) (Math.random() * GamePanel.WIDTH-1);
			randomY = (int) (Math.random() * GamePanel.HEIGHT-1);
						
			for (int i = 0; i < snake.length; i++) {
				positionX = snake.positionX[i];
				positionY = snake.positionY[i];
				
				if(positionX != randomX && positionY != randomY) {
					isValidXY = true;
					break;
				}
			}
			
		} 

	}

	public void addBonusFood() {
		
		showCounter = true;
		isValidXY = false;
		
		while (!isValidXY) {
			
			masterPositionX = (int) (Math.random() * GamePanel.WIDTH);
			masterPositionY = (int) (Math.random() * GamePanel.HEIGHT);
						
			for (int i = 0; i < snake.length; i++) {
				randomX = snake.positionX[i];
				randomY = snake.positionY[i];
				
				if(masterPositionX != randomX && masterPositionY != randomY) {
					isValidXY = true;
					break;
				}
			}
			
		} 
		
		countDown = new CountDown(6);
		countDown.count();
	}

	public void deleteBonusFood() {
		
		showCounter = false;
		timer.cancel();
		this.interval = 1;
		masterPositionX = GamePanel.WIDTH +2;
		masterPositionY = GamePanel.HEIGHT +2;
	}

	public class CountDown {

		private static final int DELAY = 1000;
		private static final int PERIOD = 1000;

		public CountDown(int theInterval) {
			interval = theInterval;
		}

		public void count() {
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					--interval;
					control(timer, interval);
				}

			}, DELAY, PERIOD);
		}

		private void control(Timer theTimer, int interval) {
			if (interval > 1) {
				return;
			} else {
				deleteBonusFood();
				theTimer.cancel();
			}
		}

	}

}
