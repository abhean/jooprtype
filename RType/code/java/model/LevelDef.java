package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import resource.AbstractResource;

@XmlRootElement
public class LevelDef extends AbstractResource
{
	public LevelDef()
	{
		numAliensToSpawn = 10;
		numAliensToDestroy = 10;
		minTimeBetweenSpawns = 1.0f;
		maxActiveAliens = 10;
		hardShipsRatio = 0.0f;
		alienShipsSpeedFactor = 1.0f;
	}
	
	
	public int getNumAliensToSpawn()
	{
		return numAliensToSpawn;
	}
	
	public int getNumAliensToDestroy()
	{
		return numAliensToDestroy;
	}
	
	public float getMinTimeBetweenSpawns()
	{
		return minTimeBetweenSpawns;
	}
	
	public float getMaxActiveAliens()
	{
		return maxActiveAliens;
	}

	public float getHardShipsRatio()
	{
		return hardShipsRatio;
	}
	

	public float getAlienShipsSpeedFactor()
	{
		return alienShipsSpeedFactor;
	}
	
	@Override
	public void onLoaded()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
	
	@XmlAttribute
	private float hardShipsRatio;
	
	@XmlAttribute
	private int maxActiveAliens;
	
	@XmlAttribute
	private int numAliensToSpawn;
	
	@XmlAttribute
	private float minTimeBetweenSpawns;
	
	@XmlAttribute
	private int numAliensToDestroy;

	@XmlAttribute
	private float alienShipsSpeedFactor;
}
