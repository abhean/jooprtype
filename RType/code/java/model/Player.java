package model;

import java.awt.Color;
import java.util.List;
import java.util.LinkedList;

public class Player
{
	public Player(String name)
	{
		this.playerListeners = new LinkedList<PlayerListener>();
		this.name = name;
		this.score = 0;
		this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	public void setEntity(Entity entity)
	{
		this.entity = entity;
		
		PlayerBrainComponent playerBrainComp = entity.getComponent(BrainComponent.class, PlayerBrainComponent.class);
		assert playerBrainComp != null;
		
		playerBrainComp.setPlayer(this);
	}
	
	public Entity getEntity()
	{
		return entity;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
	

	/**
	 * 
	 * @param playerListener
	 */
	public void addPlayerListener(PlayerListener playerListener)
	{
		assert !playerListeners.contains(playerListener);
		playerListeners.add(playerListener);
	}
	
	/**
	 * 
	 * @param playerListener
	 */
	public void removePlayerListener(PlayerListener playerListener)
	{
		playerListeners.remove(playerListener);
	}
	
	/**
	 * 
	 */
	public void onPlayerEntityDead()
	{
		for (PlayerListener playerListener: playerListeners)
		{
			playerListener.onPlayerEntityDead();
		}
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void addPoints(int points)
	{
		score += points;
	}
	
	private String name;
	private int score;
	private Entity entity;
	
	private Color color;
	private List<PlayerListener> playerListeners;

}
