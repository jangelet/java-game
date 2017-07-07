package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//helper functions to assist in running of the game
public class Util 
{
	public static String loadFileAsString(String path)	//going to take in .txt file as string
	{
		StringBuilder builder = new StringBuilder();    //allows easy adding of characters to string
	
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;								//current line being worked on
			while((line = br.readLine()) != null)	    //use string builder as long as haven't reached end of line
				builder.append(line + "\n");
			br.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	
		return builder.toString();
	}

	public static int parseInt(String number)
	{
		try{
			return Integer.parseInt(number);		//parses character as int
		}catch(NumberFormatException e)				//prints error if non-number character is passed in 
		{
			e.printStackTrace();
			return 0;								//returns 0 to prevent crash
		}
	}
	
}
