package game;


import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import logic.EntityManager;

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager
{
    /**
     * Constructor for objects of class GameManager
     */
    public GameManager()
    {
    	entityManager = new EntityManager();
    	
    	gameModes = new HashMap<String, GameMode>();
    	gameModeStack = new Stack<GameMode>();
    	
    	// Init gamemodes
    	gameModes.put(MainMenuMode.ID, new MainMenuMode());
    	gameModes.put(IngameMode.ID, new IngameMode());
    }

    
    public void PushGameMode(String Id)
    {
    	GameMode gameMode = GetGameMode(Id);
    	if (gameMode != null)
    	{
        	assert !gameModeStack.contains(gameMode);
	    	gameModeStack.push(gameMode);
    	}
    }
    
	/**
	 * 
	 */
	public void Update(final float timeDelta)
	{

	}
	
	/**
	 * 
	 * @param Id
	 * @return
	 */
	public GameMode GetGameMode(String Id)
	{
		return gameModes.get(Id);
	}
	
	private EntityManager entityManager;
	private Stack<GameMode> gameModeStack;
	private Map<String, GameMode> gameModes;
}
