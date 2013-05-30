package physics;

import graphics.SpriteSheet;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class SpriteCollider extends AbstractCollider
{
	/**
	 * 
	 * @param owner
	 */
	public SpriteCollider()
	{
		spriteSheet = null;
		imageCache = null;
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		releaseSpriteSheetItem();
	}

	
	@Override
	public final boolean checkCollisions(final Collider collider)
	{
		// Double dispatch
		return collider.collides(this);
	}

	/**
	 * 
	 * @param collider
	 * @return
	 */
	@Override
	public boolean collides(final SpriteCollider spriteCollider)
	{
		return getAABB().intersects(spriteCollider.getAABB());
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Rectangle2D.Float getAABB()
	{
		 Rectangle2D.Float rect = null;
		 if (imageCache != null)
		 {
			 rect = new Rectangle2D.Float(position.x, position.y, imageCache.getWidth(null), imageCache.getHeight(null));
		 }
		 else
		 {
			 rect = new Rectangle2D.Float(position.x, position.y, -1.0f, -1.0f);
		 }
		 
		 return rect;
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getImage()
	{
		return imageCache;
	}
	
	/**
	 * 
	 */
	public void setSpriteSheetItem(SpriteSheet spriteSheet, String itemId)
	{
		releaseSpriteSheetItem();

		if (spriteSheet != null)
		{
			app.App.getInstance().getResourceManager().addResourceRef(spriteSheet);

			this.spriteSheet = spriteSheet;
			this.imageCache = spriteSheet.getImage(itemId, 0);
		}
	}


	/**
	 * 
	 */
	private void releaseSpriteSheetItem()
	{
		if (spriteSheet != null)
		{
			imageCache = null;
			
			app.App.getInstance().getResourceManager().releaseResourceRef(spriteSheet);
			spriteSheet = null;
		}
	}
	
	//------------------------------------------------
	// Instance variables
	//------------------------------------------------
	private SpriteSheet spriteSheet;
	private Image imageCache; 	
}
