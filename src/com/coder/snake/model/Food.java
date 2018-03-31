package com.coder.snake.model;

import java.util.Timer;
import java.util.TimerTask;

import com.coder.snake.view.GamePanel;

public class Food {

	private Snake snake;
	public int positionX;
	public int positionY;
	public int masterPositionX;
	public int masterPositionY;
	public int eatenCounter = 0;
	private CountDown countDown;
	public int interval;
	private Timer timer;

	public Food() {
		snake = new Snake();
	}

	public void addFood() {
		
		int randomX = (int) (Math.random() * GamePanel.WIDTH);
		int randomY = (int) (Math.random() * GamePanel.HEIGHT);
		for (int i = 0; i < snake.length; i++) {
			if(snake.positionX[i] != randomX && snake.positionY[i] != randomY) {
				positionX = randomX;
				positionY = randomY;
			}
		}
		
		
	}

	public void addMasterFood() {
		int randomX = (int) (Math.random() * GamePanel.WIDTH);
		int randomY = (int) (Math.random() * GamePanel.HEIGHT);
		for (int i = 0; i < snake.length; i++) {
			if(snake.positionX[i] != randomX && snake.positionY[i] != randomY) {
				masterPositionX = randomX;
				masterPositionY = randomY;
			}
		}
		countDown = new CountDown(5);
		countDown.count();
	}

	public void deleteMasterFood() {
		timer.cancel();
		masterPositionX = GamePanel.WIDTH * GamePanel.WIDTH;
		masterPositionY = GamePanel.HEIGHT * GamePanel.HEIGHT;
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
			if (interval > 0) {
				return;
			} else {
				deleteMasterFood();
				theTimer.cancel();
			}
		}

	}

}
