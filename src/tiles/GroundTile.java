package tiles;

import java.awt.image.BufferedImage;

import graphics.Assets;

public class GroundTile extends Tile
{
	public GroundTile(int id)
	{
		super(Assets.ground, id);               //don't take in a buffered image, already know what asset to assign
	}
}
