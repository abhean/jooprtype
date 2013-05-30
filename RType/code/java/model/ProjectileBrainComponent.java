package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import model.LifeComponent.State;

public class ProjectileBrainComponent extends AbstractComponent<ProjectileBrainComponentDef>
		implements BrainComponent
{

	protected ProjectileBrainComponent()
	{
		super(BrainComponent.class, ProjectileBrainComponentDef.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		LocationComponent locationComp = getEntity().getComponentByType(LocationComponent.class);
		if (locationComp != null)
		{
			World world = getEntity().getWorld();
			Rectangle2D.Float worldLimits = world.getLimits();
			
			float minX = locationComp.getPosition().x;
			
			// We want it to be visually outside
			GraphicsComponent graphicsComp = getEntity().getComponentByType(GraphicsComponent.class);
			if (graphicsComp != null)
			{
				minX -= graphicsComp.getDimension().width;
			}
			
			// Is it outside the right border?
			if (minX > worldLimits.getMaxX())
			{
				world.deleteEntity(getEntity());
			}
		}
	}
	
	/**
	 * 
	 * @param owner
	 */
	public void setOwner(Entity owner)
	{
		this.owner = owner;
	}

	@Override
	public void onCollision(Entity collidedEntity)
	{
		if (collidedEntity != owner) 
		{
			LifeComponent lifeComponent = collidedEntity.getComponentByType(LifeComponent.class);
			if ((lifeComponent != null) && (lifeComponent.getState() == LifeComponent.State.ALIVE))
			{
				float damage = getComponentDef().getDamageOnCollision();
				lifeComponent.onDamage(damage, getEntity());
				
				app.App.getInstance().getGameManager().getWorld().deleteEntity(getEntity());
			}
		}
	}

	@Override
	public void onLifeStateChanged(State prevState, State newState)
	{
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void onKilledByYou(Entity entity)
	{
		if (owner != null)
		{
			BrainComponent ownerBrainComponent = owner.getComponentByType(BrainComponent.class);
			if (ownerBrainComponent != null)
			{
				ownerBrainComponent.onKilledByYou(entity);
			}
		}
	}

	@Override
	public void onDamagedByYou(Entity entity, float damage)
	{
		if (owner != null)
		{
			BrainComponent ownerBrainComponent = owner.getComponentByType(BrainComponent.class);
			if (ownerBrainComponent != null)
			{
				ownerBrainComponent.onDamagedByYou(entity, damage);
			}			
		}
	}
	
	private Entity owner;
}
