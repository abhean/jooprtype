package physics;

import java.awt.geom.Point2D;

public abstract class AbstractCollider implements Collider
{
	public AbstractCollider()
	{
		position = new Point2D.Float();
	}

	public void onCollision(Collider collider)
	{
		if (owner != null)
		{
			owner.onCollision(collider.getOwner());
		}
	}
	
	public ColliderOwner getOwner()
	{
		return owner;
	}
	
	public void setOwner(ColliderOwner owner)
	{
		this.owner = owner;
	}
	
	
	public Point2D.Float getPosition()
	{
		return (Point2D.Float)position.clone();
	}
	
	/**
	 * 
	 */
	public void setPosition(Point2D.Float position)
	{
		this.position = (Point2D.Float)position.clone();
	}
	
	public float getRotation()
	{
		return rotation;
	}
	
	/**
	 * 
	 * @param rotation
	 */
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}
	
	protected ColliderOwner owner;
	protected Point2D.Float position;
	protected float rotation;
}
