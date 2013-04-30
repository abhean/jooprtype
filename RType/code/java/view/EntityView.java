/**
 * 
 */
package view;

/**
 * @author kique
 *
 */
public class EntityView
{
	/**
	 * 
	 * @param entity
	 * @param viewDef
	 */
	public EntityView(logic.Entity entity, EntityViewDef viewDef)
	{
		this.viewDef = viewDef;
		this.entity  = entity;
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
	}
	
	logic.Entity getEntity()
	{
		return entity;
	}
	
	private EntityViewDef viewDef;
	private logic.Entity  entity;
}
