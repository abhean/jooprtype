package graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class Sprite implements Item
{
	/**
	 * 
	 */
	public Sprite()
	{
		spriteSheet = null;
		spriteSheetItemId = null;
		transform = new AffineTransform();
		
		imageCache = null;
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		releaseSpriteSheetItem();
	}

	/**
	 * 
	 * @param graphics2d
	 */
	@Override public void Draw(Graphics2D graphics2d)
	{
		assert spriteSheet != null && spriteSheetItemId != null;

		graphics2d.drawImage(imageCache, 0, 0, null);
	}

	/**
	 * 
	 */
	public void SetSpriteSheetItem(SpriteSheet spriteSheet, String itemId)
	{
		releaseSpriteSheetItem();

		if (spriteSheet != null)
		{
			resource.ResourceManager.getInstance().addResourceRef(spriteSheet);

			this.spriteSheet = spriteSheet;
			this.spriteSheetItemId = itemId;
			
			this.imageCache = spriteSheet.getImage(itemId, 0);
		}
	}
	
	//------------------------------------------------
	// Transformations
	//------------------------------------------------
	
	/**
	 * 
	 */
	public void SetPosition(Point2D position)
	{
		// @TODO[egarcia]: 
		Point2D delta = new Point2D.Double(position.getX() - transform.getTranslateX(), position.getY() - transform.getTranslateY());
		transform.translate(delta.getX(), delta.getY());
	}
	
	
	//------------------------------------------------

	/**
	 * 
	 */
	private void releaseSpriteSheetItem()
	{
		if (spriteSheet != null)
		{
			imageCache = null;
			
			resource.ResourceManager.getInstance().releaseResourceRef(spriteSheet);
			spriteSheet = null;
			spriteSheetItemId = null;
		}
	}
	
	//------------------------------------------------
	// Instance variables
	//------------------------------------------------
	private SpriteSheet 	spriteSheet;
	private String			spriteSheetItemId;
	private AffineTransform transform;
	
	private Image imageCache; 
	
}
