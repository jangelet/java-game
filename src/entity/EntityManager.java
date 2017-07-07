package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import game.Handler;


//manages all entities in the game
public class EntityManager 
{
	private Handler handler;
	private Player player;
	private Enemy enemy;
	private ArrayList<Entity> entities;						//holds every entity in the game
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>()
	{
		public int compare(Entity a, Entity b) //goes through entities array comparing them to each other, and returning -1 or 1 									   
		{									   //depending on if a should be rendered before or after b
			if(a.getY() < b.getY())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive())
				entities.remove(e);
		}
		Collections.sort(entities,renderSorter);
	
	}

	public void render(Graphics g)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.render(g);
		}
	}

	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	//G&S
	
	public Handler getHandler() 
	{
		return handler;
	}

	public void setHandler(Handler handler) 
	{
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() 
	{
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) 
	{
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy()
	{
		return enemy;
	}
}

