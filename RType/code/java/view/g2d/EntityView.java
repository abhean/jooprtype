/**
 * 
 */
package view.g2d;

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
	public EntityView(model.Entity entity, EntityViewDef viewDef)
	{
		this.viewDef = viewDef;
		this.entity  = entity;
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		if (viewDef != null)
		{
			app.App.getInstance().getResourceManager().releaseResourceRef(viewDef);
			viewDef = null;
		}
		
		entity = null;
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
	}
	
	model.Entity getEntity()
	{
		return entity;
	}
	
	private EntityViewDef viewDef;
	private model.Entity  entity;
}
