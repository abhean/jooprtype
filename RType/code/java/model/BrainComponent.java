package model;

/**
 * 
 * @author kique
 *
 */
public interface BrainComponent extends Component
{
	public void onCollision(Entity collidedEntity);
	public void onLifeStateChanged(LifeComponent.State prevState, LifeComponent.State newState);
	public void onDamagedByYou(Entity entity, float damage);
	public void onKilledByYou(Entity entity);
}
