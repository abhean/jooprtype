package model;
/**
 * 
 */

/**
 * @author kique
 *
 */
public interface Component
{
	/**
	 * 
	 * @param componentDef
	 */
	public void init(Entity entity, ComponentDef componentDef);
	
	
	/**
	 * 
	 */
	public void dispose();
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta);
	
	/**
	 * 
	 */
	public Entity getEntity();
	
	
	/**
	 * 
	 * @return
	 */
	public Class<? extends Component> getComponentType();
}
