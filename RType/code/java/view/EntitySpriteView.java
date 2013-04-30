/**
 * 
 */
package view;

import logic.GraphicsComponentDef;

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
	public EntitySpriteView(logic.Entity entity, EntitySpriteViewDef viewDef)
	{
		super(entity, viewDef);
		
		sprite = graphics.GraphicsManager.getInstance().CreateItem(graphics.Sprite.class);
	}
	
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		logic.SpriteGraphicsComponent spriteComponent = getEntity().getComponent(GraphicsComponentDef.TYPE, logic.SpriteGraphicsComponent.class);
		if (spriteComponent != null)
		{
			sprite.SetSpriteSheetItem(spriteComponent.getSpriteSheet(), spriteComponent.getSpriteSheetId());
		}
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		if (sprite != null)
		{
			graphics.GraphicsManager.getInstance().DeleteItem(sprite);
			sprite = null;
		}
	}
	
	/**
	 * 
	 */
	private graphics.Sprite sprite;
}
