package graphics;

import entity.Entity;
import game.Handler;
import tiles.Tile;

public class Camera 
{
	private Handler handler;
	private float dx, dy;						//offset in x and y positions
	
	public Camera(Handler handler, float dx, float dy)
	{
		this.handler = handler;
		this.dx = dx;
		this.dy = dy;
	}
	
	//prevents camera from revealing whitespace
	public void checkNoTile()
	{
		if(dx < 0)
		{
			dx = 0;
		}else if(dx > handler.getMap().getWidth() *Tile.TILEWIDTH - handler.getWidth())				//if dx is greater than WIDTH of world times Tile.TILEWIDTH (to convert width of world to pixels) minus the width of the game window
		{
			dx = handler.getMap().getWidth() *Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(dy < 0)
		{
			dy = 0;
		}else if(dy > handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight())			//if dy is greater than HEIGHT of world times Tile.TILEHEIGHT(to convert height of world to pixels) minus the HEIGHT of the game window
		{
			dy = handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	//takes in entity to center camera on
	//sets dx and dy to x and y position of entity minus the screen's dimensions
	//then divide by 2 so that the player is in the center of the screen instead of the edge
	public void centerOnEntity(Entity e)		
	{
		dx = e.getX() - handler.getWidth()/2 + e.getW()/2;    
		dy = e.getY() - handler.getHeight()/2 + e.getH()/2;
		checkNoTile();
	}
	
	public void move(float x, float y)			//adds to dx and dy
	{
		dx += x;
		dy += y;
		checkNoTile();
	}
	
	public float getDx() 
	{
		return dx;
	}

	public void setDx(float dx) 
	{
		this.dx = dx;
	}

	public float getDy() 
	{
		return dy;
	}

	public void setDy(float dy) 
	{
		this.dy = dy;
	}
}
