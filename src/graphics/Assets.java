package graphics;

import java.awt.image.BufferedImage;

public class Assets 
{
	
	//constants representing width and height of grid spaces in spritesheet
	private static final int W = 64, H = 64;
	
	public static BufferedImage cobble, ground, stall;
	

	public static BufferedImage[] player_up, player_dn, player_rt, player_lt, 
									player_stillUp, player_stillDn, player_stillRt, player_stillLt;       //array of buffered images for down movement animation
	public static BufferedImage[] start_btn;
	public static BufferedImage[] evilorb, walls;
	public static BufferedImage[] atkUp, atkDn, atkRt, atkLt;
	
	//initializes images, stores them as static objects above
	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(GraphicsLauncher.loadImage("/test/dunge.png"));
		SpriteSheet goblin = new SpriteSheet(GraphicsLauncher.loadImage("/test/goblin.png"));
		SpriteSheet btns = new SpriteSheet(GraphicsLauncher.loadImage("/test/btns.png"));
		SpriteSheet sword = new SpriteSheet(GraphicsLauncher.loadImage("/test/sword.png"));
		SpriteSheet orb = new SpriteSheet(GraphicsLauncher.loadImage("/test/orb.png"));
		SpriteSheet wall = new SpriteSheet(GraphicsLauncher.loadImage("/test/dunge.png"));
		
		//menu buttons
		start_btn = new BufferedImage[2];
		start_btn[0] = btns.crop(0, 0, 156, 110);
		start_btn[1] = btns.crop(159, 0, 156, 110);
		
		//orb
		evilorb = new BufferedImage[12];
		evilorb[0] = orb.crop(0, 0, W, H);
		evilorb[1] = orb.crop(67, 0, W, H);
		evilorb[2] = orb.crop(67*2, 0, W, H);
		evilorb[3] = orb.crop(67*3, 0, W, H);
		evilorb[4] = orb.crop(67*4, 0, W, H);
		evilorb[5] = orb.crop(67*5, 0, W, H);
		evilorb[6] = orb.crop(67*5, 0, W, H);
		evilorb[7] = orb.crop(67*4, 0, W, H);
		evilorb[8] = orb.crop(67*3, 0, W, H);
		evilorb[9] = orb.crop(67*2, 0, W, H);
		evilorb[10] = orb.crop(67, 0, W, H);
		evilorb[11] = orb.crop(0, 0, W, H);
		
		//walls
		walls = new BufferedImage[6];
		walls[0] = sheet.crop(0, H, W, H);
		walls[1] = sheet.crop(W, H, W, H);
		walls[2] = sheet.crop(W*2, H, W, H);
		walls[3] = sheet.crop(W*3, H, W, H);
		walls[4] = sheet.crop(W*4, H, W, H);
		walls[5] = sheet.crop(W*5, H, W, H);

		cobble = sheet.crop(0, H, W, H);
		ground = sheet.crop(0,H*2,W,H);
		
		//buffered image arrays containing movement sprites for player character
		player_up = new BufferedImage[6];
		player_dn = new BufferedImage[6];
		player_rt = new BufferedImage[6];
		player_lt = new BufferedImage[6];
		player_stillUp = new BufferedImage[1];
		player_stillDn = new BufferedImage[1];
		player_stillRt = new BufferedImage[1];
		player_stillLt = new BufferedImage[1];
		walls = new BufferedImage[9];
		atkUp = new BufferedImage[1];
		
		//cutting up sprite sheet by 64x64 pixels
		player_up[0] = goblin.crop(W*4, H*2, W, H);
		player_up[1] = goblin.crop(W*3, H*2, W, H);
		player_up[2] = goblin.crop(W*2, H*2, W, H);
		player_up[3] = goblin.crop(W, H*2, W, H);
		player_up[4] = goblin.crop(W*2, H*2, W, H);
		player_up[5] = goblin.crop(W*3, H*2, W, H);	
		
		//down animation
		player_dn[0] = goblin.crop(W*4,    0, W, H);
		player_dn[1] = goblin.crop(W*3,  0, W, H);
		player_dn[2] = goblin.crop(W*2,    0, W, H);
		player_dn[3] = goblin.crop(W ,  0, W, H);
		player_dn[4] = goblin.crop(W*2,    0, W, H);
		player_dn[5] = goblin.crop(W*3,  0, W, H);

		//right animation
		player_rt[0] = goblin.crop(W*4,  H, W, H);
		player_rt[1] = goblin.crop(W*3,  H, W, H);
		player_rt[2] = goblin.crop(W*2,  H, W, H);
		player_rt[3] = goblin.crop(W,  H, W, H);
		player_rt[4] = goblin.crop(W*2,  H, W, H);
		player_rt[5] = goblin.crop(W*3,  H, W, H);
	
		
		//left animation
		player_lt[0] = goblin.crop(W*4,   H*3, W, H);
		player_lt[1] = goblin.crop(W*3, H*3, W, H);
		player_lt[2] = goblin.crop(W*2,   H*3, W, H);
		player_lt[3] = goblin.crop(W, H*3, W, H);
		player_lt[4] = goblin.crop(W*2,   H*3, W, H);
		player_lt[5] = goblin.crop(W*3, H*3, W, H);

		//still animation
		player_stillUp[0] = goblin.crop(W*6, H*2, W, H);   //up still
		player_stillDn[0] = goblin.crop(W*6,  0, W, H);
		player_stillRt[0] = goblin.crop(W*6,  H, W, H);
		player_stillLt[0] = goblin.crop(W*6,  H*3, W, H);
		
		//attack animations
		
		atkUp[0] = goblin.crop(W*7, H*2, W, H);

	}


}
