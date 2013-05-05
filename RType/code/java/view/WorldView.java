package view;

import java.util.List;
import java.util.LinkedList;

import logic.Entity;
import logic.World;

public class WorldView implements logic.WorldListener
{
	/**
	 * 
	 */
	public WorldView()
	{
		entityViews = new LinkedList<EntityView>();
	}	
	
	/**
	 * 
	 */
	public void dispose()
	{
		releaseWorld();
	}
	
	/**
	 * 
	 * @param world
	 */
	public void setWorld(World world)
	{
		releaseWorld();
		
		this.world = world;
		this.world.addListener(this);
	}
	
	/**
	 * 
	 */
	public void releaseWorld()
	{
		if (world != null)
		{		
			world.removeListener(this);
			world = null;
		}		
		
		for (EntityView entityView: entityViews)
		{
			entityView.dispose();
		}
		entityViews.clear();
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		for (EntityView entityView: entityViews)
		{
			entityView.update(timeDelta);
		}
	}
	
	@Override
	public void onEntityCreated(Entity entity)
	{
		logic.EntityDef entityDef = entity.getEntityDef();
		String entityDefId = entityDef.getName();
		
		// Create view
		EntityViewDef viewDef = app.App.getInstance().getResourceManager().getResourceRef(EntityViewDef.class, "assets/entityviewdefs/"+entityDefId+".xml");
		if (viewDef != null)
		{
			EntityView entityView = viewDef.newEntityView(entity);
			entityViews.add(entityView);
		}
	}

	@Override
	public void onEntityDestroying(Entity entity)
	{
		// TODO destroy view
		EntityView entityView = findEntityView(entity);
		if (entityView != null)
		{
			entityViews.remove(entityView);
		}
		
	}	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	private EntityView findEntityView(Entity entity)
	{
		EntityView entityViewFound = null;

		for (EntityView entityView: entityViews)
		{
			if (entityView.getEntity() == entity)
			{
				entityViewFound = entityView;
				break;
			}
		}
		
		return entityViewFound;
	}
	
    // instance variables - replace the example below with your own
    private List<EntityView> entityViews;
    private World world;
}
