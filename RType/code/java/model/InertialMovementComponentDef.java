package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InertialMovementComponentDef extends AbstractComponentDef
{
	public InertialMovementComponentDef()
	{
		super(InertialMovementComponent.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onLoaded()
	{
		if (damping < 0.0f)
		{
			damping = Float.POSITIVE_INFINITY;
		}
		
		if (maxAccel < 0.0f)
		{
			maxAccel = Float.POSITIVE_INFINITY;
		}
	}
	
	/**
	 * 
	 */
	public float getDamping()
	{
		return damping;
	}
	
	/**
	 * 
	 */
	public float getMass()
	{
		return mass;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getMaxSpeed()
	{
		return maxSpeed;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getMaxAccel()
	{
		return maxAccel;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean getWorldLimitsCollision()
	{
		return worldLimitsCollision;
	}
	
	@XmlAttribute
	private float mass;
	
	@XmlAttribute
	private float maxSpeed;

	@XmlAttribute
	private float maxAccel;
	
	@XmlAttribute
	private float damping;
	
	@XmlAttribute
	private boolean worldLimitsCollision;
}
