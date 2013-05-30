package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlienBrainComponentDef extends AbstractComponentDef
{

	public AlienBrainComponentDef()
	{
		super(AlienBrainComponent.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public float getVerticalMovementAmplitude()
	{
		return verticalMovementAmplitude;
	}
	
	/**
	 * 
	 */
	public float getVerticalMovementFrequency()
	{
		return verticalMovementFrequency;
	}
	
	
	@XmlAttribute
	private float verticalMovementAmplitude;

	@XmlAttribute
	private float verticalMovementFrequency;
}
