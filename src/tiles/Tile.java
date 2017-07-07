package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;

//base class for all tiles in the game
public class Tile 
{

	protected static BufferedImage wall1 = Assets.walls[0];
	public static Tile[] tiles = new Tile[128];		  //array of tiles
	public static Tile grassTile = new GroundTile(0);  //assigning integer IDs for easier implementation
	public static Tile wallTile1= new WallTile(1);
//	public static Tile wallTile2= new WallTile(Assets.walls[1],2);
//	public static Tile wallTile3= new WallTile(Assets.walls[2],3);
//	public static Tile wallTile4= new WallTile(Assets.walls[3],4);
//	public static Tile wallTile5= new WallTile(Assets.walls[4],5);
	
	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;						//will never change this, hence finality
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		tiles[id] = this;						//id serves as index for tile array
	}

	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture,x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid()					 //determines if tile can be walked through or not												
	{
		return false;
	}
	
	public int getId()
	{
		return id;
	}
}
