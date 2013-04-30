package logic;

/**
 * 
 * @author kique
 *
 */
public abstract class AbstractComponent<TComponentDef extends ComponentDef> implements Component
{
	AbstractComponent(Class<TComponentDef> componentDefType)
	{
		this.componentDefType = componentDefType;
	}

	@Override
	public void init(ComponentDef componentDef)
	{
		this.componentDef = componentDefType.cast(componentDef);
		
		//...
	}

	/**
	 * 
	 * @return
	 */
	TComponentDef getComponentDef()
	{
		return componentDef;
	}
	
	/**
	 * 
	 */
	private TComponentDef componentDef;
	Class<TComponentDef> componentDefType;
}
