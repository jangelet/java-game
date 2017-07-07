package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//this class loads graphics

public class GraphicsLauncher 
{
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(GraphicsLauncher.class.getResource(path));     //returns buffered image object of loaded image
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}	
}
