package model;

import java.awt.geom.Point2D;

public class SimpleLocationComponent extends AbstractComponent<SimpleLocationComponentDef> implements LocationComponent
{
	public SimpleLocationComponent()
	{
		super(LocationComponent.class, SimpleLocationComponentDef.class);
		
		position = new Point2D.Float(0.0f, 0.0f);
		rotation = 0.0f;
		
	}
	
	@Override
	public void update(final float timeDelta)
	{
		
	}

	@Override
	public Point2D.Float getPosition()
	{
		return (Point2D.Float)position.clone();
	}

	@Override
	public void setPosition(Point2D.Float position)
	{
		this.position = (Point2D.Float)position.clone();
	}

	@Override
	public float getRotation()
	{
		return rotation;
	}

	@Override
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}
	
	
	private Point2D.Float position;
	private float		  rotation;

}
