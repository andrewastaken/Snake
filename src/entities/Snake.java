package entities;

import display.GamePanel;

public class Snake 
{
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private int[] x, y;
	private int bodyLength;
	private int direction;
	private int applesEaten;
	
	public Snake()
	{
		x = new int[GamePanel.NUM_CELLS];
		y = new int[GamePanel.NUM_CELLS];
		bodyLength = 0;
		direction = UP;
		applesEaten = 0;
	}
	
	public Snake(int startX, int startY)
	{
		x = new int[startX];
		y = new int[startY];
		bodyLength = 3;
		direction = RIGHT;
		applesEaten = 0;
	}

	public void move()
	{
		for(int i = bodyLength; i > 0; i--)
		{
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		switch(direction)
		{
		case UP:
			y[0] =- GamePanel.CELL_SIZE;
			break;
		case DOWN:
			y[0] =+ GamePanel.CELL_SIZE;
			break;
		case LEFT:
			x[0] =- GamePanel.CELL_SIZE;
			break;
		case RIGHT:
			x[0] =+ GamePanel.CELL_SIZE;
			break;
		}
	}
	
	public int getX(int index) { return x[index]; }
	public int getY(int index) { return y[index]; }
	
	public int getBodyLength() { return bodyLength; }
	public void incBodyLength() { bodyLength++; }
	
	public int getDirection() { return direction; }
	public void setDirection(int direction) { this.direction = direction; }

	public int getApplesEaten() { return applesEaten; }
	public void incAppleEaten() { applesEaten++; }
}
