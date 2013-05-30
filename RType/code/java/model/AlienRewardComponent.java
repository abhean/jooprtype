package model;

public class AlienRewardComponent extends AbstractComponent<AlienRewardComponentDef>
		implements RewardComponent
{

	protected AlienRewardComponent()
	{
		super(RewardComponent.class, AlienRewardComponentDef.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOnDamagedPoints()
	{
		// TODO Auto-generated method stub
		return getComponentDef().getOnDamagedPoints();
	}

	@Override
	public int getOnKilledPoints()
	{
		// TODO Auto-generated method stub
		return getComponentDef().getOnKilledPoints();
	}

}
