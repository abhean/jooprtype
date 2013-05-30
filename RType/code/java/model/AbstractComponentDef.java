/**
 * 
 */
package model;

/**
 * @author kique
 *
 */
public class AbstractComponentDef implements ComponentDef
{
	/**
	 * 
	 * @param componentClass
	 */
	public AbstractComponentDef(Class<? extends Component> componentClass)
	{
		this.componentClass = componentClass;
	}
	
	
	@Override
	public void onLoaded()
	{
		// Nothing to do by default
	}

	/**
	 * 
	 * @return
	 */
	public Component newComponent(Entity entity)
	{
		Component newComponent = null;
		
		try
		{
			newComponent = componentClass.newInstance();
			newComponent.init(entity, this);
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newComponent;
	}
	
	/**
	 * 
	 */
	public Class<? extends Component> getComponentClass()
	{
		return componentClass;
	}
	
	
	private Class<? extends Component> componentClass;

}
