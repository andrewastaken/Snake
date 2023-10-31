package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import entities.Apple;
import entities.Snake;

public class GamePanel extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int GAME_WIDTH = 26;
	public static final int GAME_HEIGHT = 15;
	public static final int NUM_CELLS = GAME_WIDTH * GAME_HEIGHT;
	public static final int CELL_SIZE = 50;
	public static final int SCREEN_WIDTH = GAME_WIDTH * CELL_SIZE;
	public static final int SCREEN_HEIGHT = GAME_HEIGHT * CELL_SIZE;

	public static final int DELAY = 175;
	
	private Snake snake;
	private Apple apple;
	
	private boolean running;
	private Random random;
	private Timer timer;

	public GamePanel() 
	{
		setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame()
	{
		snake = new Snake();
		apple = new Apple();
		random = new Random();	
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g)
	{
		if(running)
		{
			g.setColor(Color.red);
			g.fillOval(apple.getX(), apple.getY(), CELL_SIZE, CELL_SIZE);
		
			for(int i = 0; i < snake.getBodyLength(); i++)
			{
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);		
			}
			
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			
			int stringX = (SCREEN_WIDTH - metrics.stringWidth("Score: "+ snake.getApplesEaten())) / 2;
			int stringY = g.getFont().getSize();
			g.drawString("Score: " + snake.getApplesEaten(), stringX, stringY);
		}
		else
			gameOver(g);
	}
	
	public void checkApple()
	{
		if((x[0] == appleX) && (y[0] == appleY)) 
		{
			snake.incBodyLength();
			snake.incAppleEaten();
			newApple();
		}
	}
	
	public void checkCollisions() 
	{
		//checks if head collides with body
		for(int i = bodyParts; i > 0; i--) 
		{
			if((x[0] == x[i])&& (y[0] == y[i]))
				running = false;
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) 
	{
		//Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + snake.getApplesEaten(), (SCREEN_WIDTH - metrics1.stringWidth("Score: " + snake.getApplesEaten()))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(running) 
		{
			snake.move();
			checkApple();
			checkCollisions();
		}
		
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter 
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{
			int dir = snake.getDirection();
			
			switch(e.getKeyCode()) 
			{
				case KeyEvent.VK_UP:
					if(dir != Snake.UP || dir != Snake.DOWN) 
						snake.setDirection(Snake.UP);
					break;
				case KeyEvent.VK_DOWN:
					if(dir != Snake.DOWN || dir != Snake.UP) 
						snake.setDirection(Snake.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					if(dir != Snake.LEFT || dir != Snake.RIGHT) 
						snake.setDirection(Snake.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					if(dir != Snake.RIGHT || dir != Snake.LEFT) 
						snake.setDirection(Snake.RIGHT);
					break;
			}
		}
	}
}

