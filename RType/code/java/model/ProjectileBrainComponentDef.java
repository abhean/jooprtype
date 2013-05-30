package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectileBrainComponentDef extends AbstractComponentDef
{

	public ProjectileBrainComponentDef()
	{
		super(ProjectileBrainComponent.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public float getDamageOnCollision()
	{
		return damageOnCollision;
	}
	
	@XmlAttribute
	private float damageOnCollision;
}
