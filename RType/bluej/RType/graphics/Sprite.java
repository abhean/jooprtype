package graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import resource.Resource;
import resource.SpriteSheet;


public class Sprite implements Item
{
	/**
	 * 
	 */
	public Sprite()
	{
		spriteSheet = null;
		spriteSheetId = null;
		transform = new AffineTransform();
	}
	

	/**
	 * 
	 */
	@Override public void SetResource(Resource resource)
	{
		assert resource instanceof SpriteSheet;
		spriteSheet = (SpriteSheet)resource;
	}
	
	/**
	 * 
	 * @param graphics2d
	 */
	@Override public void Draw(Graphics2D graphics2d)
	{
		assert spriteSheet != null;
		

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
	// Instance variables
	//------------------------------------------------
	private SpriteSheet spriteSheet;
	private String spriteSheetId;
	
	private AffineTransform transform;
	
}
