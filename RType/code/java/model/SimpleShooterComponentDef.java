package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleShooterComponentDef extends AbstractComponentDef
{

	public SimpleShooterComponentDef()
	{
		super(SimpleShooterComponent.class);
		
		minTimeBetweenShots = 0.0f;
		projectileEntityDef = "";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getProjectileEntityDef()
	{
		return projectileEntityDef;
	}

	/**
	 * 
	 * @return
	 */
	public float getMinTimeBetweenShots()
	{
		return minTimeBetweenShots;
	}
	
	@XmlAttribute
	private float minTimeBetweenShots;
	
	@XmlAttribute
	private String projectileEntityDef;
}
