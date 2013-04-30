package logic;

import java.util.List;
import java.util.LinkedList;

public class World
{
	public World()
	{
        // initialise instance variables
        entities = new LinkedList<Entity>();
        worldListeners = new LinkedList<WorldListener>();
	}
	
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		for (Entity entity: entities)
		{
			entity.update(timeDelta);
		}
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
		
		EntityDef entityDef = resource.ResourceManager.getInstance().getResourceRef(EntityDef.class, entityDefName);
		if (entityDef != null)
		{
			newEntity = new Entity(name, entityDef);
			notifyOnEntityCreated(newEntity);
		}
		
		return newEntity;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void destroyEntity(Entity entity)
	{
		notifyOnEntityDestroying(entity);
		
		entity.dispose();
		entities.remove(entity);
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
	private void notifyOnEntityDestroying(Entity entity)
	{
		for (WorldListener listener: worldListeners)
		{
			listener.onEntityDestroying(entity);
		}
	}
	
	
    // instance variables - replace the example below with your own
    private List<Entity> entities;
    private List<WorldListener> worldListeners;

}
