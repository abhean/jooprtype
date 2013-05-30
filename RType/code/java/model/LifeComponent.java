package model;

public interface LifeComponent extends Component
{
	public enum State
	{
		DEAD,
		ALIVE
	}

	State getState();
	State onDamage(float amount, Entity instigator);
}
