package tiles;

import java.awt.image.BufferedImage;

import graphics.Assets;

public class WallTile extends Tile
{

	public WallTile(int id)
	{
		super(Assets.cobble, id);
	}

	
	public boolean isSolid()					//prevents walking through
	{
		return true;
	}
}
