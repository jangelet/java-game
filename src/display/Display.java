package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//this class establishes the game's display

public class Display 
{
	private JFrame frame;							//frame holds canvas
	private Canvas canvas;  						//graphics will be drawn to canvas
	
	private String name;
	
	private int w, h;
	
	public Display(String name, int w, int h)
	{
		this.name = name;
		this.w = w;
		this.h = h;
		
		createDisplay();
	}

	private void createDisplay()
	{
		frame = new JFrame(name);
		frame.setSize(w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);            //puts frame in middle of screen
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(w, h)); //size canvas is to be
		canvas.setMaximumSize(new Dimension(w, h));   //ensures canvas isn't resized
		canvas.setMinimumSize(new Dimension(w,h));	  //see above comment
		canvas.setFocusable(false);                   //allows application to focus on itself
		
		frame.add(canvas);                            //adds canvas to frame
		frame.pack();								  //ensures all of canvas is visible
	}

	public Canvas getCanvas()
	{
		return canvas;
	}

	public JFrame getFrame()
	{
		return frame;
	}
}
