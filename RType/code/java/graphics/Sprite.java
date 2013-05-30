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
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		
	} 
	
	/**
	 * 
	 * @param graphics2d
	 */
	@Override public void draw(Graphics2D graphics2d)
	{
		assert imageCache != null;

		AffineTransform transform = new AffineTransform();
		transform.concatenate(AffineTransform.getRotateInstance(rotation));
		transform.concatenate(AffineTransform.getTranslateInstance(position.x, position.y));
		
		graphics2d.drawImage(imageCache, transform, null);
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
	
	//------------------------------------------------
	// Transformations
	//------------------------------------------------
	
	/**
	 * 
	 */
	public void setPosition(Point2D.Float position)
	{
		this.position = (Point2D.Float)position.clone();
	}
	
	/**
	 * 
	 */
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
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
			
			app.App.getInstance().getResourceManager().releaseResourceRef(spriteSheet);
			spriteSheet = null;
		}
	}
	
	//------------------------------------------------
	// Instance variables
	//------------------------------------------------
	private SpriteSheet spriteSheet;
	private Point2D.Float position;
	private float rotation;
	
	private Image imageCache;
}
