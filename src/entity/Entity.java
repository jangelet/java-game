package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Entity 
{
	public static final int DEFAULT_HP = 5;
	protected Handler handler;
	protected float x, y;    		//starting coordinates on screen
	protected int w, h;				//size of entity
	protected int health;
	protected boolean active = true;
	
	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health) 
	{
		this.health = health;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int w, int h)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		health = DEFAULT_HP;

		bounds = new Rectangle(0, 0, w, h);
	}

	
	public abstract void die();
	
	public void hurt(int dmg)
	{
		health -= dmg;
		if(health <= 0)
			active = false;
			die();
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(float dx, float dy)
	{
		for(Entity e: handler.getMap().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;		//prevents from checking for collision against itself
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(dx, dy)))
				return true;
		}
		return false; //returns false if there is no collision
	}
	
	public Rectangle getCollisionBounds(float dx, float dy)
	{
		return new Rectangle((int)(x + bounds.x + dx), (int)(y + bounds.y + dy), bounds.width, bounds.height); //x pos of entity + bounding box offset of entity, then same for y, then width and height of bounding box 
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	
}
