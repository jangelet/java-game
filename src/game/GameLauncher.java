package game;

//this class launches the game

public class GameLauncher 
{
	public static void main(String[] args)
	{
		CoreGame game = new CoreGame("TEH", 720, 480);
		game.start();
	}
}
