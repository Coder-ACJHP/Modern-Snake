package com.coder.snake.model;

public class Cell {

	final static int CELL_TYPE_EMPTY = 0;
	public static final int CELL_TYPE_FOOD = 10;
	static final int CELL_TYPE_SNAKE_NODE = 20;
	public final int row, col;
	public int type;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
