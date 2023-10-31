package entities;

public class Apple 
{
	private int x, y;
	
	public Apple()
	{
		x = 0;
		y = 0;
	}
	
	public Apple(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	public int getX() { return x; }
	public int getY() { return y; }
}
