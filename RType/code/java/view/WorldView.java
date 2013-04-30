package view;

import java.util.List;
import java.util.LinkedList;

import logic.Entity;

public class WorldView implements logic.WorldListener
{
	public WorldView(logic.World world)
	{
		entityViews = new LinkedList<EntityView>();
		world.addListener(this);
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
		EntityViewDef viewDef = resource.ResourceManager.getInstance().getResourceRef(EntityViewDef.class, "assets/entityviewdefs/"+entityDefId+".xml");
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
}
