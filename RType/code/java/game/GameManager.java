package game;

import java.util.Stack;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

import model.World;
import model.World.State;

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
    	world = new World();
    	worldViews = new LinkedList<view.WorldView>();
    	
    	players = new LinkedList<PlayerController>();
    	
    	gameModes = new HashMap<String, GameMode>();
    	gameModeStack = new Stack<GameMode>();
    	
    	// Init gamemodes
    	gameModes.put(MainMenuMode.ID, new MainMenuMode(this));
    	gameModes.put(IngameMode.ID, new IngameMode(this));    	
    	gameModes.put(GameOverMode.ID, new GameOverMode(this));
    }

    
    /**
     * 
     */
    public void dispose()
    {
    	world = null;
    	
    	// release views
    	for (view.WorldView worldView: worldViews)
    	{
    		worldView.dispose();
    	}
    	
    	worldViews.clear();
    }
    
    /**
     * 
     * @return
     */
    public World getWorld()
    {
    	return world;
    }
    
    /**
     * 
     */
    public void addWorldView(view.WorldView worldView)
    {
    	worldView.init(world);
    	worldViews.add(worldView);
    }
    
    /**
     * 
     */
    public void deleteWorldView(view.WorldView worldView)
    {
    	worldView.dispose();
    	worldViews.remove(worldView);
    }
    
    /**
     * 
     */
    public void loadLevel(String levelDefPath)
    {
    	world.loadLevel(levelDefPath);
    }
    
    /**
     * 
     */
    public void addPlayerController(PlayerController playerController)
    {
    	players.add(playerController);
    }
    
    
    /**
     * 
     */
    public void popAllGameModes()
    {
    	while (!gameModeStack.isEmpty())
    	{
    		GameMode gameMode = gameModeStack.pop();
    		assert gameMode != null;
    		
    		gameMode.onDeactivated();
    		gameMode.onPopped();
    	}
    }
    
    /**
     * 
     */
    public void popGameMode()
    {
    	if (!gameModeStack.isEmpty())
    	{
    		GameMode gameMode = gameModeStack.pop();
    		assert gameMode != null;
    		
    		gameMode.onDeactivated();
    		gameMode.onPopped();
    	}
    }
    
    /**
     * 
     * @param Id
     */
    public void pushGameMode(String Id)
    {
    	GameMode gameMode = getGameMode(Id);
    	if (gameMode != null)
    	{
        	assert !gameModeStack.contains(gameMode);
        	
        	if (!gameModeStack.isEmpty())
        	{
        		GameMode currentMode = gameModeStack.peek();
        		currentMode.onDeactivated();
        	}
        	
	    	gameModeStack.push(gameMode);
	    	gameMode.onPushed();
	    	gameMode.onActivated();
    	}
    }
    
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		GameMode currentMode = getCurrentGameMode();
		if (currentMode != null)
		{
			currentMode.update(timeDelta);
		}
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void setPlayerControllersActive(boolean active)
	{
		for (PlayerController player: players)
		{
			player.setActive(active);
		}
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void updatePlayerControllers(final float timeDelta)
	{
		for (PlayerController player: players)
		{
			player.update(timeDelta);
		}
	}
	
	/**
	 * 
	 */
	public void updateWorld(final float timeDelta)
	{
		world.update(timeDelta);
	}
	
	/**
	 * 
	 */
	public void updateWorldViews(final float timeDelta)
	{
		for (view.WorldView view: worldViews)
		{
			view.update(timeDelta);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public GameMode getCurrentGameMode()
	{
		GameMode current = null;
		
		if (!gameModeStack.isEmpty())
		{
			current = gameModeStack.peek();
		}
		
		return current;
	}
	
	/**
	 * 
	 * @param Id
	 * @return
	 */
	public GameMode getGameMode(String Id)
	{
		return gameModes.get(Id);
	}
	
	private World world;
	
	private List<view.WorldView> worldViews;
	
	private Stack<GameMode> gameModeStack;
	private Map<String, GameMode> gameModes;
	
	private List<PlayerController> players;
}
