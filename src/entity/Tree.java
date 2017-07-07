package entity;

import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Tree extends Statics
{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
		
	}

	@Override
	public void tick() 
	{
		
	}

	public void die()
	{
		
	}
	
	@Override
	public void render(Graphics g) 
	{
//		g.draw/mage(Assets.bush, (int)(x - handler.getGameCamera().getDx()), (int)(y - handler.getGameCamera().getDy()), w, h, null);
	}

}
