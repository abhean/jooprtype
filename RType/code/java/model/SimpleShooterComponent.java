package model;

import java.awt.Dimension;
import java.awt.geom.Point2D;

public class SimpleShooterComponent extends AbstractComponent<SimpleShooterComponentDef> implements ShooterComponent
{

	protected SimpleShooterComponent()
	{
		super(ShooterComponent.class, SimpleShooterComponentDef.class);
		
		timeSinceLastShot = 0.0f;
	}

	@Override
	public void update(float timeDelta)
	{
		timeSinceLastShot += timeDelta;
	}

	@Override
	public void shoot()
	{
		if (timeSinceLastShot >= getComponentDef().getMinTimeBetweenShots())
		{
			// TODO Auto-generated method stub
			LocationComponent locationComponent = getEntity().getComponentByType(LocationComponent.class);
			assert locationComponent != null;
			
			Point2D.Float position = locationComponent.getPosition();
			float rotation = locationComponent.getRotation();
			
			GraphicsComponent graphicsComponent = getEntity().getComponentByType(GraphicsComponent.class);
			Dimension entityVisualSize = graphicsComponent.getDimension();
			position.x += entityVisualSize.width;
			position.y += 0.0f;
			
			Entity projectile = getEntity().getWorld().newEntity(getEntity().getName() + "projectile", getComponentDef().getProjectileEntityDef());
			
			LocationComponent bulletLocationComponent = projectile.getComponentByType(LocationComponent.class);
			bulletLocationComponent.setPosition(position);
			bulletLocationComponent.setRotation(rotation);
			
			MovementComponent projectileMovementComponent = projectile.getComponentByType(MovementComponent.class);
			projectileMovementComponent.move(new Point2D.Float(1.0f, 0.0f));
			
			ProjectileBrainComponent projectileBrainComponent = projectile.getComponent(BrainComponent.class, ProjectileBrainComponent.class);
			projectileBrainComponent.setOwner(getEntity());
			
			timeSinceLastShot = 0.0f;
		}
	}	
	
	
	private float timeSinceLastShot;
}
