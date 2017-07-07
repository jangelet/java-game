package states; 

import java.awt.Graphics;
import maps.*;

import game.Handler;


public class GameState extends State
{
	
	private Map testMap;
	
	public GameState(Handler handler)
	{
		super(handler);
		testMap = new Map(handler, "res/maps/level1.txt");
		handler.setMap(testMap);
	
	}
	
	@Override
	public void tick() 
	{
		testMap.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		testMap.render(g);						//before player, so that player is rendered on top
	}

}
