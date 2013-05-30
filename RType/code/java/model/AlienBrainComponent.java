package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import model.LifeComponent.State;

public class AlienBrainComponent extends AbstractComponent<AlienBrainComponentDef> implements BrainComponent
{
	protected AlienBrainComponent()
	{
		super(BrainComponent.class, AlienBrainComponentDef.class);
		// TODO Auto-generated constructor stub
		time = 0.0f;
		if (Math.random() < 0.5f)
		{
			time += (float)Math.PI * 2.0f;
		}
	}

	@Override
	public void update(float timeDelta)
	{
		LocationComponent locationComp = getEntity().getComponentByType(LocationComponent.class);
		if (locationComp != null)
		{
			World world = getEntity().getWorld();
			Rectangle2D.Float worldLimits = world.getLimits();
			
			float maxX = locationComp.getPosition().x;
			
			// We want it to be visually outside
			GraphicsComponent graphicsComp = getEntity().getComponentByType(GraphicsComponent.class);
			if (graphicsComp != null)
			{
				maxX += graphicsComp.getDimension().width;
			}
			
			// Is it outside the left border?
			if (maxX < worldLimits.getMinX())
			{
				world.deleteEntity(getEntity());
			}
			else
			{
				MovementComponent movementComponent = getEntity().getComponentByType(MovementComponent.class);
				assert movementComponent != null;
				
				float yDir = (float) Math.sin(time * getComponentDef().getVerticalMovementFrequency()) * getComponentDef().getVerticalMovementAmplitude();
				time += timeDelta;
				movementComponent.move(new Point2D.Float(-1.0f, yDir));		
			}
		}
	}

	@Override
	public void onCollision(Entity collidedEntity)
	{
		if (collidedEntity.getEntityDef().getType().equals("player"))
		{
			LifeComponent lifeComp = collidedEntity.getComponentByType(LifeComponent.class);
			if (lifeComp != null)
			{
				lifeComp.onDamage(1000.0f, getEntity());
			}
		}
	}

	@Override
	public void onLifeStateChanged(State prevState, State newState)
	{
		if (newState == LifeComponent.State.DEAD)
		{
			getEntity().getWorld().deleteEntity(getEntity());
		}
	}
	

	@Override
	public void onKilledByYou(Entity entity)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onDamagedByYou(Entity entity, float damage)
	{
		// TODO Auto-generated method stub
		
	}
	
	private float time;
}
