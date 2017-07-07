package entity;

import game.Handler;

//for unmoving entities such as trees, etc.
public abstract class Statics extends Entity
{
	public Statics(Handler handler, float x, float y, int w, int h)
	{
		super(handler, x, y, w, h);
	}
}
