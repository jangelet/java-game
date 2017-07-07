package graphics;

import java.awt.image.BufferedImage;

public class Animation 
{
	private int speed, index;
	private long prevTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		prevTime = System.currentTimeMillis();
	}
	
	public void tick()
	{
		timer += System.currentTimeMillis() - prevTime;  //adding to timer the time in milliseconds since last tick method was called
		prevTime = System.currentTimeMillis();
	
		if(timer > speed)					//whenever timer exceeds speed, change frame, creating animation
		{
			index++;						//increment index in animation frame array
			timer=0;
			if(index >= frames.length)
				index = 0;					//prevents out of bound error on frames array
		}
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
}
