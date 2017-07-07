package states;

import java.awt.Graphics;

import game.Handler;


//abstract class for states of the game
public abstract class State 
{
	protected Handler handler;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}
	
	public abstract void tick();				   //needs tick to run
	
	public abstract void render(Graphics g);       //allows State to draw to screen directly
	
}
