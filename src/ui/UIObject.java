package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject 
{
	protected float x, y;
	protected int w, h;
	protected Rectangle bounds;
	protected boolean hovering = false;

	public UIObject(float x, float y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		bounds = new Rectangle((int) x, (int) y, w, h);
	}

	//Getters and Setters
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();			//recognizes when user clicks on whatever ui element its applied to
	
	public void onMouseMove(MouseEvent e)   //detects whether mouse is over UI item or not
	{
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	
	public void onMouseRelease(MouseEvent e)
	{
		if(hovering)
			onClick();
	}
	
	public float getX() 
	{
		return x;
	}

	public void setX(float x) 
	{
		this.x = x;
	}

	public float getY() 
	{
		return y;
	}

	public void setY(float y) 
	{
		this.y = y;
	}

	public int getW() 
	{
		return w;
	}

	public void setW(int w) 
	{
		this.w = w;
	}

	public int getH() 
	{
		return h;
	}

	public void setH(int h) 
	{
		this.h = h;
	}

	public boolean isHovering() 
	{
		return hovering;
	}

	public void setHovering(boolean hovering) 
	{
		this.hovering = hovering;
	}

}
