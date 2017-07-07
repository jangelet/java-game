package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//handles key inputs
public class InputManager implements KeyListener
{

	private boolean[] keys;
	
	public boolean dnStill = false; 
	public boolean upStill = false;
	public boolean ltStill = false;
	public boolean rtStill = false; 
	public boolean up, dn, lt, rt, esc;
	public boolean atkU, atkL, atkR, atkD;
	
	public InputManager()
	{
		keys = new boolean[256];				
	}
	
	
	
	public boolean[] getKeys() 
	{
		return keys;
	}



	public void tick()
	{
		up = keys[KeyEvent.VK_W];
		lt = keys[KeyEvent.VK_A];
		rt = keys[KeyEvent.VK_D];
		dn = keys[KeyEvent.VK_S];
		
		atkU = keys[KeyEvent.VK_UP];
		atkL = keys[KeyEvent.VK_LEFT];
		atkR = keys[KeyEvent.VK_RIGHT];
		atkD = keys[KeyEvent.VK_DOWN];
		esc = keys[KeyEvent.VK_ESCAPE];
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		keys[e.getKeyCode()] = true;
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		keys[e.getKeyCode()] = false;
		if(keys[KeyEvent.VK_W] == true)
		{
			upStill = true;
			dnStill = false;
			ltStill = false;
			rtStill = false;
			
		}else if(keys[KeyEvent.VK_A] == true){
			upStill = false;
			dnStill = false;
			ltStill = true;
			rtStill = false;
		}else if(keys[KeyEvent.VK_D] == true){
			upStill = false;
			dnStill = false;
			ltStill = false;
			rtStill = true;
		}else if(keys[KeyEvent.VK_D] == true)
			upStill = false;
			dnStill = true;
			ltStill = false;
			rtStill = false;
	}

}
