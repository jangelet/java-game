package entity;

import game.Handler;
import tiles.Tile;

//abstract class for all characters in the game
//this includes player character, NPCs, enemies
public abstract class Character  extends Entity
{

	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CHAR_WIDTH = 64;
	public static final int DEFAULT_CHAR_HEIGHT = 64;
	
	
	protected float speed;
	protected float dx, dy;    			//change in y and x responsible for movement
	
	public Character(Handler handler, float x, float y, int w, int h) 
	{
		super(handler, x, y, w, h);
		speed = DEFAULT_SPEED;
		dx = 0;
		dy = 0;
	}
	
	
	public void move()
	{
		//only calling entity collisions for entities that actual move (in order to save resources)
		if(!checkEntityCollisions(dx, 0f))  //passes where character moving to in x coord
			moveX();
		if(!checkEntityCollisions(0f, dy)) //same as above, but for y coord
			moveY();
	}
	
	public float getDx() {
		return dx;
	}


	public void setDx(float dx) {
		this.dx = dx;
	}


	public float getDy() {
		return dy;
	}


	public void setDy(float dy) {
		this.dy = dy;
	}

	//character movement
	
	
	
	//COLLSION DETECTION CONTAINED IN THE FOLLOWING TWO METHODS
	public void moveX()
	{
		if(dx > 0)			       //moving right
		{
			int tempX = (int)(x + dx + bounds.x + bounds.width) /Tile.TILEWIDTH;
			
			//gets upper and lower right of bounds box
			//if tile is not "solid" (i.e. false), then allowed to move
			//else no movement occurs
			if(!collisionWithTile(tempX, (int)(y + bounds.y)/Tile.TILEHEIGHT)
				&& !collisionWithTile(tempX, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT))
			{
				x += dx;
			}else{
				x = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}else if(dx < 0){			//moving left
			
			int tempX = (int)(x + dx + bounds.x) /Tile.TILEWIDTH;
			
			//gets upper and lower left of bounds box
			if(!collisionWithTile(tempX, (int)(y + bounds.y)/Tile.TILEHEIGHT)
				&& !collisionWithTile(tempX, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT))
			{
				x += dx;
			}else{
				x = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
		
	}
	
	public void moveY()
	{
		if(dy < 0)
		{
			int tempY = (int) (y + dy + bounds.y)/Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x)/ Tile.TILEWIDTH, tempY) 
					&& !collisionWithTile((int) (x+bounds.x + bounds.width)/ Tile.TILEWIDTH, tempY))
			{
				
				y += dy;
			}else{
				y = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}else if(dy > 0){
			int tempY = (int) (y + dy + bounds.y + bounds.height)/Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x)/ Tile.TILEWIDTH, tempY) 
					&& !collisionWithTile((int) (x+bounds.x + bounds.width)/ Tile.TILEWIDTH, tempY))
			{
				y += dy;
			}else{
				y = tempY * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
		
	}
	
	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getMap().getTile(x,y).isSolid();
	}
	
	//G & S
	
	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
}



