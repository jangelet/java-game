package entity;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Handler;
import graphics.Animation;
import graphics.Assets;

public class Player extends Character
{
	
	//Animations
	private Animation dn, up, lt, rt, stillUp, stillDn, stillRt, stillLt;
	
	//Attack timer
	private long lastAtkTimer, atkCD = 500, atkTimer = atkCD;

	
	public Player(Handler handler, float x, float y) 
	{
		super(handler, x, y, Character.DEFAULT_CHAR_WIDTH, Character.DEFAULT_CHAR_HEIGHT);
		
		
		//to get collision box that fits body of player instead of entire tile
		bounds.x = 16;
		bounds.y = 25;
		bounds.width = 28;
		bounds.height = 28;
		health = 1;
	
		dn = new Animation(150, Assets.player_dn);
		up = new Animation(150, Assets.player_up);
		rt = new Animation(150, Assets.player_rt);
		lt = new Animation(150, Assets.player_lt);
		stillUp = new Animation(0, Assets.player_stillUp);
		stillDn = new Animation(0, Assets.player_stillDn);
		stillRt = new Animation(0, Assets.player_stillRt);
		stillLt = new Animation(0, Assets.player_stillLt);
	}

	
	
	
	@Override
	public void tick() 
	{
		
		dn.tick();								//animation			
		up.tick();
		rt.tick();
		lt.tick();
		
		System.out.println(health);
		
		getInput();									//input
		move();											//movement
		handler.getGameCamera().centerOnEntity(this);   //centers camera on player   
	
		checkAttacks();						  //attacks
	
	}
	
	
	private void checkAttacks()
	{
		//establishes timer to prevent spamming of attacks
		atkTimer += System.currentTimeMillis() - lastAtkTimer;
		lastAtkTimer = System.currentTimeMillis();
		if(atkTimer < atkCD)
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle au = new Rectangle();
		Rectangle al = new Rectangle();
		Rectangle ar = new Rectangle();
//		Rectangle ad = new Rectangle();
//		
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		
		if(handler.getInputManager().atkU){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getInputManager().atkD){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getInputManager().atkL){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getInputManager().atkR){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
}
//		au.width = arSize;
//		au.height = arSize;
//		ad.width = arSize;
//		ad.height = arSize;
//		al.width = arSize;
//		al.height = arSize;
//	
//			au.x = cb.x + cb.width/2 - arSize/2;
//			au.y = cb.y - arSize;
//		
//			ad.x = cb.x + cb.width/2 - arSize/2;
//			ad.y = cb.y + cb.height;
//	
//			ar.x = cb.x + cb.width;
//			ar.y = cb.y + cb.height/2 - arSize/2;
//	
//			al.x = cb.x - arSize;
//			al.y = cb.y + cb.height/2 - arSize/2;
	
		
		atkTimer = 0; 					//resets attack timer
		//if current entity looping through isn't player, and it's valid, check and see if it intercepts attack hitbox
		for(Entity e:handler.getMap().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) //if intercepts, damage by 1
			{
				e.hurt(2);
				return;
			}
		}
	}
	
	
	public void die()
	{
		
		System.out.print("You Lose");
	}
	
	private void getInput()
	{
		dx = 0;
		dy = 0;
		
	
		
		if(handler.getInputManager().up)
			dy = -speed;

		if(handler.getInputManager().dn)
			dy =  speed;

		if(handler.getInputManager().lt)
			dx = -speed;

		if(handler.getInputManager().rt)
			dx = speed;

	}
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getDx()), (int)(y - handler.getGameCamera().getDy()), 
				w, h, null);
		
//		for rendering hitbox
//		g.setColor(Color.red);
//		g.fillRect((int)(x + bounds.x - handler.getGameCamera().getDx()), (int)(y + bounds.y - handler.getGameCamera().getDy()), 
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame()
	{
		
		if(dx < 0) //if moving to the left
		{
			return lt.getCurrentFrame();
		}else if(dx > 0){ //if moving right
			return rt.getCurrentFrame();
		}else if(dy < 0){ //if moving up
			return up.getCurrentFrame();
		}else if(dy > 0){
			return dn.getCurrentFrame();
		}else if(handler.getInputManager().up && dx == 0 && dy == 0){
			return stillDn.getCurrentFrame();
		}else if(handler.getInputManager().upStill && dx == 0 && dy == 0){
			return stillUp.getCurrentFrame();
		}else if(handler.getInputManager().ltStill && dx == 0 && dy == 0){
			return stillLt.getCurrentFrame();
		}else{
			return stillRt.getCurrentFrame();
		}
	
		
	}
}
