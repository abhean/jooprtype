package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleLifeComponentDef extends AbstractComponentDef
{

	public SimpleLifeComponentDef()
	{
		super(SimpleLifeComponent.class);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * @return
	 */
	public float getMaxLife()
	{
		return maxLife;
	}
	
	@XmlAttribute
	private float maxLife;
}
