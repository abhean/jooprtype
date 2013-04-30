package logic;

import java.util.LinkedList;

public class EntityManager
{
	public EntityManager()
	{
        // initialise instance variables
        entities = new LinkedList<Entity>();
	}
	
	/**
	 * 
	 */
	public void Update(final float timeDelta)
	{
		for (Entity entity: entities)
		{
			entity.Update(timeDelta);
		}
	}
	
    // instance variables - replace the example below with your own
    private LinkedList<Entity> entities;

}
