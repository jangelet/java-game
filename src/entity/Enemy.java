package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import graphics.Animation;
import graphics.Assets;
import entity.*;
public class Enemy extends Character
{

	private Animation all;

	private boolean changeDy = false;
	
	private Rectangle boundary = new Rectangle(65, 69, 69, 620);
	private Rectangle boundary2 = new Rectangle(65, 340, 69, 620);
	
	public Enemy(Handler handler, float x, float y, int w, int h) 
	{
		super(handler, x, y, w, h);
	
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 48;
		bounds.height = 48;
	
		all = new Animation(50, Assets.evilorb);
		
	}

	@Override
	public void die() 
	{
		
	}

	
	private void checkAttacks()
	{
		//establishes timer to prevent spamming of attacks
//		atkTimer += System.currentTimeMillis() - lastAtkTimer;
//		lastAtkTimer = System.currentTimeMillis();
//		if(atkTimer < atkCD)
//			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle au = new Rectangle();
		Rectangle al = new Rectangle();
		Rectangle ar = new Rectangle();
		Rectangle ad = new Rectangle();
		
		int arSize = 20;
//		ar.width = arSize;
//		ar.height = arSize;
//		au.width = arSize;
//		au.height = arSize;
//		ad.width = arSize;
//		ad.height = arSize;
//		al.width = arSize;
//		al.height = arSize;
//	
//		au.x = cb.x + cb.width/2 - arSize/2;
//		au.y = cb.y - arSize;
//		
//		ad.x = cb.x + cb.width/2 - arSize/2;
//		ad.y = cb.y + cb.height;
//	
//		ar.x = cb.x + cb.width;
//		ar.y = cb.y + cb.height/2 - arSize/2;
//	
//		al.x = cb.x - arSize;
//		al.y = cb.y + cb.height/2 - arSize/2;
		
//		atkTimer = 0; 					//resets attack timer
		//if current entity looping through isn't player, and it's valid, check and see if it intercepts attack hitbox
		for(Entity e:handler.getMap().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar) || e.getCollisionBounds(0, 0).intersects(au) || e.getCollisionBounds(0, 0).intersects(ad) || e.getCollisionBounds(0, 0).intersects(al)) //if intercepts, damage by 1
			{
				e.hurt(5);
				return;
			}
		}
	}
	
	@Override
	public void tick() 
	{
//		System.out.println("y = " + y);
//	System.out.println(checkEntityCollisions(x, 0f));
//		System.out.println(checkEntityCollisions(0f, y));
		all.tick();
		move();
		
//		if(changeDy){
//			dy =speed;
//		}else if(changeDy){
//			dy = -speed;
//		}
			
		
		System.out.println(changeDy);
//		System.out.println(this.y);
		
//		for(Entity e:handler.getMap().getEntityManager().getEntities())
//		{
//			if(e.equals(this))
//				continue;
//			if(e.getCollisionBounds(0, 0).intersects(boundary)) //if intercepts, damage by 1
//			{
//				changeDy = true;
//			}else if(e.getCollisionBounds(0, 0).intersects(boundary2)){
//				changeDy = false;
//			}
//	}
//		if((int)this.y == 462.0)
//		{
//			dy = -3*speed;
//		}else{
//			dy = -3*speed;
//		}

//		checkAttacks();	
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getDx()), (int)(y - handler.getGameCamera().getDy()), w, h, null);
		g.drawRect(0, 800, 10, 10);
	}

	private BufferedImage getCurrentAnimationFrame()
	{
		
		if(dx < 0) //if moving to the left
		{
			return all.getCurrentFrame();
		}else if(dx > 0){ //if moving right
			return all.getCurrentFrame();
		}else if(dy < 0){ //if moving up
			return all.getCurrentFrame();
		}else{
			return all.getCurrentFrame();
		}
	}
	
}
