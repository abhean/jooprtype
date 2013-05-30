package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JFrame;

public class World
{
	public enum State
	{
		PLAYING,
		GAME_OVER
	}
	
	public World()
	{
        // initialise instance variables
		// @TODO: Assign from outside
        limits = new Rectangle2D.Float(0.0f, 0.0f, 1024.0f, 768.0f);

        entities = new LinkedList<Entity>();
        entitiesToAdd = new HashSet<Entity>();
        entitiesToDelete = new HashSet<Entity>();
        
        players = new LinkedList<Player>();
        
        worldListeners = new LinkedList<WorldListener>();
        
        setState(State.PLAYING);
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		deleteAllEntities(true);
		deleteCurrentLevel();
	}
	
	/**
	 * 
	 */
	public State getState()
	{
		return state;
	}
	
	/**
	 * 
	 */
	public void setState(State state)
	{
		if (this.state != state)
		{
			this.state = state;
		}
	}
	
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		// State specific behaviour
		switch (state)
		{
			case PLAYING:
			{
				// Update
				if (level != null)
				{
					level.update(timeDelta);
				}
				
				for (Entity entity: entities)
				{
					entity.update(timeDelta);
				}

				// State transitions
				
				// -> GameOver?
				boolean goToGameOver = false;
				
				// Level Finished?
				if (!goToGameOver)
				{
					goToGameOver = level.isFinished();
				}
				
				// Player dead?
				if (!goToGameOver)
				{
					for (Player player: players)
					{
						LifeComponent playerLifeComp = player.getEntity().getComponentByType(LifeComponent.class);
						if (playerLifeComp.getState() == LifeComponent.State.DEAD)
						{
							goToGameOver = true;
							break;
						}
					}
				}
				
				if (goToGameOver)
				{
					setState(State.GAME_OVER);
				}
			}
			break;
			
			case GAME_OVER:
			{
				// Nothing to do
			}
			break;		
		}
		
		// Common behaviour
		processPendingAddDeleteEntites();
	}
	
	
	/**
	 * 
	 */
	public void loadLevel(final String levelDefPath)
	{
		deleteCurrentLevel();
		
		LevelDef levelDef = app.App.getInstance().getResourceManager().getResourceRef(LevelDef.class, levelDefPath);
		level = new Level(this, levelDef);
		
    	setState(State.PLAYING);
	}

	
	/**
	 * 
	 */
	private void deleteCurrentLevel()
	{
		if (level != null)
		{
			level.dispose();
			level = null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public final Rectangle2D.Float getLimits()
	{
		return limits;
	}
	
	/**
	 * 
	 * @author kique
	 *
	 */
	public interface EntityAction
	{
		void applyTo(Entity entity, Object param);
	}
	
	/**
	 * 
	 */
	public void forEachEntity(EntityAction action, Object param)
	{
		for (Entity entity: entities)
		{
			action.applyTo(entity,  param);
		}
	}
	
	/**
	 * 
	 * @author kique
	 *
	 */
	public interface PlayerAction
	{
		void applyTo(Player player, Object param);
	}
	
	/**
	 * 
	 */
	public void forEachPlayer(PlayerAction action, Object param)
	{
		for (Player player: players)
		{
			action.applyTo(player,  param);
		}
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Player newPlayer(String name)
	{
		assert getPlayer(name) == null;
		
		Player newPlayer = new Player(name);
		players.add(newPlayer);
		
		return newPlayer;
	}
	

	/**
	 * 
	 * @return
	 */
	public List<Player> getPlayers()
	{
		return players;
	}

	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Player getPlayer(String name)
	{
		Player playerFound = null;
		
		for (Player playerCandidate: players)
		{
			if (playerCandidate.getName().equals(name))
			{
				playerFound = playerCandidate;
				break;
			}
		}
		
		return playerFound;
	}
	
	/**
	 * 
	 * @param name
	 * @param entityDefPath
	 * @return
	 */
	public Entity newEntity(String name, String entityDefName)
	{
		Entity newEntity = null;
		
		EntityDef entityDef = app.App.getInstance().getResourceManager().getResourceRef(EntityDef.class, entityDefName);
		if (entityDef != null)
		{
			newEntity = new Entity(this, name, entityDef);
			entitiesToAdd.add(newEntity);
		}
		
		return newEntity;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void deleteEntity(Entity entity)
	{
		notifyOnEntityDeleting(entity);
		
		entitiesToAdd.remove(entity);
		entitiesToDelete.add(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void deleteAllEntities(boolean flush)
	{
		for (Entity entity: entities)
		{
			deleteEntity(entity);
		}
		
		if (flush)
		{
			processPendingAddDeleteEntites();
		}
	}
	
	
	/**
	 * 
	 * @param worldListener
	 */
	public void addListener(WorldListener worldListener)
	{
		assert !worldListeners.contains(worldListener);
		worldListeners.add(worldListener);
	}

	/**
	 * 
	 * @param worldListener
	 */
	public void removeListener(WorldListener worldListener)
	{
		worldListeners.remove(worldListener);
	}
	
	/**
	 * 
	 */
	private void notifyOnEntityCreated(Entity entity)
	{
		for (WorldListener listener: worldListeners)
		{
			listener.onEntityCreated(entity);
		}
	}
	
	/**
	 * 
	 */
	private void notifyOnEntityDeleting(Entity entity)
	{
		for (WorldListener listener: worldListeners)
		{
			listener.onEntityDeleting(entity);
		}
	}
	
	/**
	 * 
	 */
	private void processPendingAddDeleteEntites()
	{
		for (Entity entity: entitiesToDelete)
		{
			entity.dispose();
			entities.remove(entity);
		}
		entitiesToDelete.clear();
		
		for (Entity entity: entitiesToAdd)
		{
			entities.add(entity);
			notifyOnEntityCreated(entity);
		}
		entitiesToAdd.clear();
	}

	
    // instance variables - replace the example below with your own
    private List<Entity> entities;
    private Set<Entity> entitiesToAdd;
    private Set<Entity> entitiesToDelete;
    
    private List<Player> players;
    
    private List<WorldListener> worldListeners;
    private Rectangle2D.Float limits;
    
    private Level level;
    
    private State state;
}
