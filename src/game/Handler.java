package game;

import graphics.Camera;
import inputs.InputManager;
import inputs.MouseManager;
import maps.Map;

/*
 * Handler class allows a lot of variables to be passed to anything we want to
 * Makes it much easier to add anything to game
 * Replaced GameCore objects in other classes with Handler
 * 
 * Contains the game AND the world
 */

public class Handler 
{

	private CoreGame game;
	private Map map;
	
	public Handler(CoreGame game)
	{
		this.game = game;
	}
	
	public Camera getGameCamera()
	{
		return game.getGameCamera();
	}
	
	public InputManager getInputManager()
	{
		return game.getInputManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	public int getWidth()
	{
		return game.getWidth();
	}
	
	public int getHeight()
	{
		return game.getHeight();
	}
	
	public CoreGame getGame() {
		return game;
	}
	
	public void setGame(CoreGame game) {
		this.game = game;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
