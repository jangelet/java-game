package game;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import graphics.Assets;
import graphics.*;
import states.*;
import inputs.*;

//Central class for the actual game, utilizing threads                                               

public class CoreGame implements Runnable
{
	
	private Display display;
	private int w, h;
	public String name;

	private boolean is_running = false;
	private Thread thread;
	
	//GRAPHICS
	private BufferStrategy buffer;
	private Graphics g;
	
	//STATES
	public State gameState;						//declaring game state as a State obj
	@SuppressWarnings("unused")
	private State menuState;						//main menu state declared
	
	//INPUTS
	private InputManager inputmngr;
	private MouseManager mousemngr;
	
	//CAMERA
	private Camera gameCam;
	
	//HANDLER
	private Handler handler;
	
	public CoreGame(String name, int w, int h)
	{
		this.w = w;
		this.h = h;
		this.name = name;
		inputmngr = new InputManager();
		mousemngr = new MouseManager();
	}
	

	
	//initializes graphics, etc. -- prepares things for the game to be run
	private void init()
	{
		display = new Display(name, w, h);
		display.getFrame().addKeyListener(inputmngr);		//adding key listener to JFrame window
		display.getFrame().addMouseListener(mousemngr);
		display.getFrame().addMouseMotionListener(mousemngr);
		display.getCanvas().addMouseListener(mousemngr);
		display.getCanvas().addMouseMotionListener(mousemngr);
		Assets.init();
		
		handler = new Handler(this);
		gameCam = new Camera(handler, 0,0);
		
		gameState = new GameState(handler);					//initialize to GameState obj
		menuState = new MenuState(handler);
		StateManager.setState(menuState);
	
	}
	
	//clock for game
	private void tick()
	{
		inputmngr.tick();
		
		if(StateManager.getState() != null)					//prevents error if state actually exists
		{
			StateManager.getState().tick();
		}
	
			
	}
	
	//graphics rendered in this method
	private void render()
	{
		buffer = display.getCanvas().getBufferStrategy();	//buffer prevents flickering
		if(buffer == null)
		{
			display.getCanvas().createBufferStrategy(3);   //creates buffer if none and returns
			return;
		}
		g = buffer.getDrawGraphics();

		g.clearRect(0,0,w,h);
		
		if(StateManager.getState() != null)					//prevents error if no state
		{
			StateManager.getState().render(g);       
		}
	
		
		buffer.show();
		g.dispose();                                //done drawing, throws out garbage
	}
	
	//contains main game loop, calls init method
	//keeps runtimes consistent across computers
	public void run()
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;        //one billion divided by fps -- converts time to nanoseconds
		double delta = 0;							//tracks how much time until tick and render must be run again
		long currTime;								
		long lastTime = System.nanoTime();          //returns current time of computer in nanosec
		long timer = 0;								//tracks when one second passes
		int ticks = 0;								//tracks number of ticks
		
		
		while(is_running)							//as long as is_running = true, loop tick and render
		{
			currTime = System.nanoTime();			
			delta += (currTime - lastTime)/timePerTick;  //tracks how much time until tick and render methods will be called again
			timer += currTime - lastTime;				 //tracks amount of time since this block of code was called
			lastTime = currTime;
			
			if(delta >= 1)							//must tick and render now in order to maintain 60 FPS
			{
				tick();
				render();
				ticks++;							
				delta--;							//resets delta
			}
		
			if(timer >= 1000000000)					//whenever timer runs for 1 sec, displays how many ticks are run during that time
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}
	
	
	public InputManager getInputManager()
	{
		return inputmngr;
	}
	
	public MouseManager getMouseManager()
	{
		return mousemngr;
	}
	
	public Camera getGameCamera()
	{
		return gameCam;
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	//starts main thread
	public synchronized void start()
	{
		if(is_running) 
			return;				 					//if already running, do nothing
		is_running = true;
		thread = new Thread(this);	
		thread.start();
	}

	
	public synchronized void stop()
	{
		if(!is_running) 
			return; 			 					//if not running, just do nothing
		is_running = false;
		try 
		{
			thread.join(); 							//tells current thread to wait until it's finished
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
