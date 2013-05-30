package view.g2d;

import java.util.List;
import java.util.LinkedList;

import model.Entity;
import model.Player;
import model.World;

public class WorldView implements view.WorldView
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
	 * @param world
	 */
	@Override
	public void init(World world)
	{
		dispose();
		
		this.world = world;
		this.world.addListener(this);
		
		graphics.GraphicsManager graphicsManager = app.App.getInstance().getGraphicsManager();
		starField = graphicsManager.CreateItem(graphics.StarsField.class);
		starField.init(graphicsManager.getScreen().getSize(), 100, 1.0f);
		
		// Simulate already created entities events
		world.forEachEntity(new model.World.EntityAction() {
			@Override
			public void applyTo(Entity entity, Object param)
			{
				onEntityCreated(entity);
			}
		}, null);
	}	
	
	/**
	 * 
	 */
	public void dispose()
	{
		if (starField != null)
		{
			app.App.getInstance().getGraphicsManager().deleteItem(starField);
			starField = null;
		}
		
		releaseWorld();
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	@Override
	public void update(final float timeDelta)
	{
		starField.update(timeDelta);
		
		for (EntityView entityView: entityViews)
		{
			entityView.update(timeDelta);
		}
	}
	
	/**
	 * 
	 */
	private void releaseWorld()
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

	
	@Override
	public void onPlayerCreated(Player player)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEntityCreated(Entity entity)
	{
		model.EntityDef entityDef = entity.getEntityDef();
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
	public void onEntityDeleting(Entity entity)
	{
		// TODO destroy view
		EntityView entityView = findEntityView(entity);
		if (entityView != null)
		{
			entityView.dispose();
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
	private graphics.StarsField starField;
    private List<EntityView> entityViews;
    private World world;
}
