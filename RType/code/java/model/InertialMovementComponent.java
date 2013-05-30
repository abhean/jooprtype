package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import foundation.MathUtils;

/**
 * 
 * @author kique
 *
 */
public class InertialMovementComponent extends	AbstractComponent<InertialMovementComponentDef> implements MovementComponent
{
	/**
	 * 
	 */
	public InertialMovementComponent()
	{
		super(MovementComponent.class, InertialMovementComponentDef.class);
		
		mass = 1.0f;
		maxSpeedFactor = 1.0f;
		impulse  = new Point2D.Float(0.0f, 0.0f);
		velocity = new Point2D.Float(0.0f, 0.0f);
	}
	
	@Override
	public void init(Entity entity, ComponentDef componentDef)
	{
		super.init(entity, componentDef);
		
		mass = getComponentDef().getMass();
	}

	/**
	 * 
	 * @param speed
	 */
	public void setMaxSpeedFactor(float maxSpeedFactor)
	{
		this.maxSpeedFactor = maxSpeedFactor;
	}
	
	/**
	 * 
	 */
	@Override
	public void update(float timeDelta)
	{
		LocationComponent locationComponent = getEntity().getComponentByType(LocationComponent.class);
		assert locationComponent != null;
		
		// Apply speed damping
		float damping = getComponentDef().getDamping();
		if   (damping > 0.0f)
		{
			Point2D.Float dampingImpulse = new Point2D.Float(
					Math.min(damping, mass / timeDelta) * -velocity.x,
					Math.min(damping, mass / timeDelta) * -velocity.y);
			
			applyImpulse(dampingImpulse);		
		}
		
		// Calculate acceleration
		assert mass > 0.0f;
		Point2D.Float acceleration = new Point2D.Float((float)impulse.x / mass, (float)impulse.y / mass);
		
		// Update velocity
		final float maxSpeed = getComponentDef().getMaxSpeed() * maxSpeedFactor;
		velocity.x = Math.min(maxSpeed, Math.max(-maxSpeed, velocity.x + acceleration.x * timeDelta));
		velocity.y = Math.min(maxSpeed, Math.max(-maxSpeed, velocity.y + acceleration.y * timeDelta));
				
		// Update position
		Point2D.Float position = locationComponent.getPosition();
		position.x += velocity.x * timeDelta;
		position.y += velocity.y * timeDelta;
		
		if (getComponentDef().getWorldLimitsCollision())
		{
			Rectangle2D.Float worldLimits = getEntity().getWorld().getLimits();
			
			// Clamp x
			if (position.x < worldLimits.getMinX())
			{
				position.x = (float)worldLimits.getMinX();
				velocity.x = Math.max(0.0f,  velocity.x);
			}
			else if (position.x > worldLimits.getMaxX())
			{
				position.x = (float)worldLimits.getMaxX();
				velocity.x = Math.min(0.0f, velocity.x);
			}

			// Clamp y
			if (position.y < worldLimits.getMinY())
			{
				position.y = (float)worldLimits.getMinY();
				velocity.y = Math.max(0.0f,  velocity.y);
			}
			else if (position.y > worldLimits.getMaxY())
			{
				position.y = (float)worldLimits.getMaxY();
				velocity.y = Math.min(0.0f, velocity.y);
			}
		}
		
		locationComponent.setPosition(position);
		
		// Clean impulse
		impulse.setLocation(0.0f, 0.0f);	
	}

	/**
	 * 
	 */
	@Override
	public void move(Point2D.Float ratio)
	{
		applyImpulse(new Point2D.Float(ratio.x * mass * getComponentDef().getMaxAccel(), ratio.y * mass * getComponentDef().getMaxAccel()));
	}
	
	/**
	 * 
	 * @param impulse
	 */
	public void applyImpulse(Point2D.Float impulse)
	{
		this.impulse.x += impulse.x;
		this.impulse.y += impulse.y;
	}

	
	private float maxSpeedFactor;
	private float mass;
	private Point2D.Float impulse;
	private Point2D.Float velocity;

}
