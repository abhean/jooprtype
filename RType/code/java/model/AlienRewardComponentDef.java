package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlienRewardComponentDef extends AbstractComponentDef
{

	public AlienRewardComponentDef()
	{
		super(AlienRewardComponent.class);
		// TODO Auto-generated constructor stub
	}
	
	
	public int getOnDamagedPoints()
	{
		return onDamagedPoints;
	}
	
	public int getOnKilledPoints()
	{
		return onKilledPoints;
	}

	@XmlAttribute
	private int onDamagedPoints;
	
	@XmlAttribute
	private int onKilledPoints;
}
