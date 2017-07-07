package states;

import java.awt.Graphics;

import game.*;
import graphics.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;


//class for main menu

public class MenuState extends State
{
	private UIManager uiManager;
	
	public MenuState(final Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);							
		handler.getMouseManager().setUIManager(uiManager);			
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 115, Assets.start_btn, new ClickListener(){     //adds start button to game

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);				//buttons in UI no longer receive any input
				StateManager.setState(handler.getGame().gameState);
			}}));
	}
	
	@Override
	public void tick() 
	{
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		uiManager.render(g);
	}
}
