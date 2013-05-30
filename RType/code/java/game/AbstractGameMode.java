package game;

public class AbstractGameMode implements GameMode
{

	public AbstractGameMode(GameManager gameManager)
	{
		this.gameManager = gameManager;
	}
	
	
	@Override
	public GameManager getGameManager()
	{
		return gameManager;
	}
	
	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivated()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeactivated()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPushed()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPopped()
	{
		// TODO Auto-generated method stub
		
	}

	private GameManager gameManager;
}
