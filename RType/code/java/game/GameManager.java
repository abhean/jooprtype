package game;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import logic.World;

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
    	
    	players = new LinkedList<Player>();
    	
    	gameModes = new HashMap<String, GameMode>();
    	gameModeStack = new Stack<GameMode>();
    	
    	// Init gamemodes
    	gameModes.put(MainMenuMode.ID, new MainMenuMode());
    	gameModes.put(IngameMode.ID, new IngameMode());
    	
    	timer = new Timer(33, new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			update(0.033f);
    		}
    	});
    	
    	timer.start();
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
    public World getEntityManager()
    {
    	return world;
    }
    
    /**
     * 
     */
    public void addWorldView(view.WorldView worldView)
    {
    	worldView.setWorld(world);
    	worldViews.add(worldView);
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
	    	gameModeStack.push(gameMode);
    	}
    }
    
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		for (Player player: players)
		{
			player.update(timeDelta);
		}
		
		world.update(timeDelta);
		
		for (view.WorldView view: worldViews)
		{
			view.update(timeDelta);
		}
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
	
	private Timer timer;
	private World world;
	
	private List<view.WorldView> worldViews;
	
	private Stack<GameMode> gameModeStack;
	private Map<String, GameMode> gameModes;
	
	private List<Player> players;
}
