package model;

/**
 * 
 * @author kique
 *
 */
public abstract class AbstractComponent<TComponentDef extends ComponentDef> implements Component
{
	protected AbstractComponent(Class<? extends Component> componentType, Class<TComponentDef> componentDefClass)
	{
		assert Components.isValidComponentType(componentType);
		
		this.componentType = componentType;
		this.componentDefClass = componentDefClass;
	}

	@Override
	public void init(Entity entity, ComponentDef componentDef)
	{
		this.entity = entity;
		this.componentDef = componentDefClass.cast(componentDef);	
	}
	
	
	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	public Entity getEntity() 
	{
		return entity;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public Class<? extends Component> getComponentType()
	{
		return componentType;
	}

	/**
	 * 
	 * @return
	 */
	public TComponentDef getComponentDef()
	{
		return componentDef;
	}
	
	/**
	 * 
	 */
	private Entity entity;
	private TComponentDef componentDef;
	
	private Class<TComponentDef> componentDefClass;
	private Class<? extends Component> componentType;
}
