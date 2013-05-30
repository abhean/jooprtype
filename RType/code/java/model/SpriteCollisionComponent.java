package model;

import physics.ColliderOwner;

public class SpriteCollisionComponent extends AbstractComponent<SpriteCollisionComponentDef>
		implements CollisionComponent, physics.ColliderOwner
{

	/**
	 * 
	 */
	public SpriteCollisionComponent()
	{
		super(CollisionComponent.class, SpriteCollisionComponentDef.class);
		// TODO Auto-generated constructor stub
		
		physics.PhysicsManager physicsManager = app.App.getInstance().getPhysicsManager();
		collider = physicsManager.createCollider(this, physics.SpriteCollider.class);
	}
	
	/**
	 * 
	 */
	@Override
	public void dispose()
	{
		super.dispose();
		
		if (collider != null)
		{
			app.App.getInstance().getPhysicsManager().deleteCollider(collider);
			collider = null;
		}
		
	}

	/**
	 * 
	 */
	@Override
	public void update(float timeDelta)
	{
		assert collider != null;
		
		SpriteGraphicsComponent spriteComp = getEntity().getComponent(GraphicsComponent.class, SpriteGraphicsComponent.class);
		assert spriteComp != null;
		
		collider.setSpriteSheetItem(spriteComp.getSpriteSheet(), spriteComp.getSpriteSheetId());
		
		LocationComponent locationComp = getEntity().getComponentByType(LocationComponent.class);
		collider.setPosition(locationComp.getPosition());
		collider.setRotation(locationComp.getRotation());
	}
	
	/**
	 * 
	 */
	@Override
	public void onCollision(ColliderOwner colliderOwner)
	{
		BrainComponent brainComponent = getEntity().getComponentByType(BrainComponent.class);
		
		// Notify brain
		if (brainComponent != null)
		{
			Entity entity = ((CollisionComponent)colliderOwner).getEntity();
			brainComponent.onCollision(entity);
		}
		
	}

	private physics.SpriteCollider collider;


}
