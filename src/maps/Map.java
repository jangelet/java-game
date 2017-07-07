package maps;

import java.awt.Graphics;

import entity.Enemy;
import entity.EntityManager;
import entity.Player;
import entity.Tree;
import game.Handler;
import tiles.Tile;
import util.*;

//for creating levels for game
public class Map
{
	private Handler handler;
	private int w, h;
	

	private int spawn_x, spawn_y;
	private int[][] map;      				//array for locations of tiles forming map
	//Entities
	private EntityManager entityManager;
	
	public Map(Handler handler, String path)
	{
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
//		entityManager.addEntity(new Tree(handler, 100, 250));
//		entityManager.addEntity(new Tree(handler, 100, 350));
//		entityManager.addEntity(new Tree(handler, 100, 450));
//		entityManager.addEntity(new Enemy(handler, 110, 300, 67, 67));
		entityManager.addEntity(new Enemy(handler, 150, 100, 67, 67));
		entityManager.addEntity(new Enemy(handler, 250, 150, 67, 67));
		entityManager.addEntity(new Enemy(handler, 350, 200, 67, 67));
		entityManager.addEntity(new Enemy(handler, 450, 250, 67, 67));
		entityManager.addEntity(new Enemy(handler, 550, 100, 67, 67));
		entityManager.addEntity(new Enemy(handler, 650, 76, 67, 67));

		entityManager.addEntity(new Enemy(handler, 750, 100, 67, 67));

		
//		entityManager.addEntity(new Enemy(handler, 450, 150, 67, 67));
//		entityManager.addEntity(new Enemy(handler, 510, 200, 67, 67));
//		entityManager.addEntity(new Enemy(handler, 570, 250, 67, 67));

		loadMap(path);
	
		entityManager.getPlayer().setX(80);
		entityManager.getPlayer().setY(80);
	
	}

	public EntityManager getEntityManager() 
	{
		return entityManager;
	}
	
	//for updating
	public void tick()
	{
		entityManager.tick();
	}

	public void render(Graphics g)
	{
		/**
		 for inits:
		 getDX or DY returns a negative number for some reason, will render 0 tile instead
		 max chooses the greater of two values passed in
		 if greater than 0, then player is moving right etc. and tiles to left that shouldn't rendered 
		 how much camera moved in pixels divided by tile width to get in terms of tiles
		 
		 for finals:
		 similar to inits, but
		 get mins instead, add width or height to offset in order to prevent 
		 rendering of tiles to the right when moving right
		*/
		int x_init = (int)Math.max(0, handler.getGameCamera().getDx()/Tile.TILEWIDTH);			
		int x_final = (int)Math.min(w, (handler.getGameCamera().getDx() + handler.getWidth())/Tile.TILEWIDTH + 1);	//add games width 
		int y_init = (int)Math.max(0,  handler.getGameCamera().getDy()/Tile.TILEHEIGHT);
		int y_final = (int)Math.min(h,  (handler.getGameCamera().getDy() + handler.getHeight())/Tile.TILEHEIGHT + 1);
		for(int j = y_init; j < y_final; j++)
		{
			for(int i = x_init; i < x_final; i++)
			{
				//convert coords from tiles into pixels so that they are rendered properly
				getTile(i, j).render(g, (int)(i*Tile.TILEWIDTH - handler.getGameCamera().getDx()), 
						(int)(j*Tile.TILEHEIGHT - handler.getGameCamera().getDy()));						
			}
		}
		//Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= w || y >= h)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[map[x][y]];         //adding to tiles array based on x, y passed into map array
		if(t == null)
			return Tile.grassTile;				  //return default tile if null
		return t;								  
	}
	
	private void loadMap(String path)
	{
		String file = Util.loadFileAsString(path);
		String[] tokens = file.split("\\s+");	  //take in .txt file and split at any whitespace
		w = Util.parseInt(tokens[0]);			  //sets width of level
		h = Util.parseInt(tokens[1]);			  //sets height of level
		spawn_x = Util.parseInt(tokens[2]);		  //spawn coordinates for player character
		spawn_y = Util.parseInt(tokens[3]);		  //see above -- this is y coord
	
		map = new int[w][h];					 
		for(int j = 0; j < h; j++)
		{
			for(int i = 0; i < w; i++)
			{
				map[i][j] = Util.parseInt(tokens[(i + j * w) + 4]); //need to add 4 to everything since we already set first 4 (0-3) variables 
			}
		}
	}

	public int getWidth()
	{
		return w;
	}

	public int getHeight()
	{
		return h;
	}
}
