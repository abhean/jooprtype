package model;

public class SimpleLifeComponent extends AbstractComponent<SimpleLifeComponentDef> implements LifeComponent
{
	protected SimpleLifeComponent()
	{
		super(LifeComponent.class, SimpleLifeComponentDef.class);
		
	}


	@Override
	public void init(Entity entity, ComponentDef componentDef)
	{
		super.init(entity,  componentDef);
		
		maxLife = getComponentDef().getMaxLife();
		currentLife = maxLife;
		
		setState(currentLife > 0.0f ? State.ALIVE: State.DEAD);
	}
	
	@Override
	public void update(float timeDelta)
	{
		// TODO Auto-generated method stub
		
	}
	
	public State getState()
	{
		return state;
	}
	
	public void setState(State state)
	{
		if (state != this.state)
		{
			State prevState = this.state;
			
			this.state = state;
			
			BrainComponent brainComponent = getEntity().getComponentByType(BrainComponent.class);
			if (brainComponent != null)
			{
				brainComponent.onLifeStateChanged(prevState, state);
			}
		}
	}
	
	public State onDamage(float damage, Entity instigator)
	{
		if (currentLife > 0.0f)
		{
			BrainComponent instigatorBrainComponent = null;
			if (instigator != null)
			{
				instigatorBrainComponent = instigator.getComponentByType(BrainComponent.class);
			}

   		    currentLife -= damage;
			if (instigatorBrainComponent != null)
			{
				instigatorBrainComponent.onDamagedByYou(getEntity(), damage);
			}

   		    if (currentLife <= 0.0f)
			{
				currentLife = 0.0f;
				setState(State.DEAD);
				
				if (instigatorBrainComponent != null)
				{
					instigatorBrainComponent.onKilledByYou(getEntity());
				}
			}
		}
		
		return state;
	}
	
	
	private float maxLife;
	private float currentLife;
	private State state;
}
