package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class StarsField implements Item
{

	/**
	 * 
	 * @param dimension
	 * @param numStars
	 * @param speed
	 */
	public void init(final Dimension dimension, final int numStars, final float speed)
	{
		this.speed = speed;
		this.dimension = dimension;
    	stars = new Star[numStars];
    	for (int i = 0; i < numStars; ++i)
    	{
    		Star newStar = new Star();
    		newStar.maxIntensity = (float)Math.random();
    		newStar.curIntensity = newStar.maxIntensity;
    		newStar.position = new Point2D.Float((float)Math.random() * dimension.width, (float)Math.random() * dimension.height);
    		stars[i] = newStar;
    	}
	}
	
	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
    	for (int i = 0; i < stars.length; ++i)
    	{
    		Star star = stars[i];
    		
    		star.position.x -= star.maxIntensity * speed;
    		if (star.position.x < 0.0f)
    		{
    			star.position.x += dimension.width;
    		}
    		
    		star.curIntensity = (float)(star.maxIntensity * (0.8f + Math.random() * 0.2f));
    	}
	} 
	
	@Override
	public void draw(Graphics2D graphics2d)
	{
    	for (int i = 0; i < stars.length; ++i)
    	{
    		final Star star = stars[i];
  		    graphics2d.setColor(new Color(star.curIntensity, star.curIntensity, star.curIntensity));
	    	graphics2d.drawRect((int)star.position.x, (int)star.position.y, 1, 1);
    	}
	}
	
	
    private static class Star
    {
    	public Point2D.Float position;
    	float curIntensity;
    	float maxIntensity;
    }
    
    private Dimension dimension;
    private float speed;
    private Star[] stars;
}
