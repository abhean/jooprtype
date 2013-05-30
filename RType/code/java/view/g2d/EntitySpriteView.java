/**
 * 
 */
package view.g2d;

/**
 * @author kique
 *
 */
public class EntitySpriteView extends EntityView
{
	/**
	 * 
	 * @param entity
	 * @param viewDef
	 */
	public EntitySpriteView(model.Entity entity, EntitySpriteViewDef viewDef)
	{
		super(entity, viewDef);
		
		sprite = app.App.getInstance().getGraphicsManager().CreateItem(graphics.Sprite.class);
	}
	
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		// @NOTE[egarcia]: We need the concrete component class
		model.SpriteGraphicsComponent spriteComponent = getEntity().getComponent(model.GraphicsComponent.class, model.SpriteGraphicsComponent.class);
		if (spriteComponent != null)
		{
			sprite.setSpriteSheetItem(spriteComponent.getSpriteSheet(), spriteComponent.getSpriteSheetId());
			
			// @NOTE[egarcia]: Wee need just the base component type
			model.LocationComponent locationComponent = getEntity().getComponentByType(model.LocationComponent.class);
			if (locationComponent != null)
			{
				sprite.setPosition(locationComponent.getPosition());
				sprite.setRotation(locationComponent.getRotation());
			}
		}
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		if (sprite != null)
		{
			app.App.getInstance().getGraphicsManager().deleteItem(sprite);
			sprite = null;
		}
	}
	
	/**
	 * 
	 */
	private graphics.Sprite sprite;
}
