package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpriteCollisionComponentDef extends AbstractComponentDef
{

	public SpriteCollisionComponentDef()
	{
		super(SpriteCollisionComponent.class);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean checkPixels()
	{
		return checkPixels;
	}
	
	@XmlAttribute
	private boolean checkPixels;
}
