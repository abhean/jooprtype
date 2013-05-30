package model;

import model.LifeComponent.State;

/**
 * 
 * @author kique
 *
 */
public class PlayerBrainComponent extends AbstractComponent<PlayerBrainComponentDef> implements BrainComponent
{

	protected PlayerBrainComponent()
	{
		super(BrainComponent.class, PlayerBrainComponentDef.class);
		
	}

	@Override
	public void init(Entity entity, ComponentDef componentDef)
	{
		super.init(entity, componentDef);
		
		
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void onCollision(Entity collidedEntity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLifeStateChanged(State prevState, State newState)
	{
		// TODO Auto-generated method stub
		if (newState == State.DEAD)
		{
			player.onPlayerEntityDead();
		}
	}
	

	@Override
	public void onDamagedByYou(Entity entity, float damage)
	{
		RewardComponent rewardComponent = entity.getComponentByType(RewardComponent.class);
		if (rewardComponent != null)
		{
			player.addPoints(rewardComponent.getOnDamagedPoints());
		}
	}

	@Override
	public void onKilledByYou(Entity entity)
	{
		RewardComponent rewardComponent = entity.getComponentByType(RewardComponent.class);
		if (rewardComponent != null)
		{
			player.addPoints(rewardComponent.getOnKilledPoints());
		}	
	}
	
	private Player player;

}
