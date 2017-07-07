package graphics;

import java.awt.image.BufferedImage;

//for processing sprite sheets

public class SpriteSheet 
{
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int w, int h)
	{
		return sheet.getSubimage(x, y, w, h);
	}
}
