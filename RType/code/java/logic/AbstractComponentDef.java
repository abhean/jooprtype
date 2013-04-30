/**
 * 
 */
package logic;

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
	public AbstractComponentDef(String type, Class<? extends Component> componentClass)
	{
		this.type = type;
		this.componentClass = componentClass;
	}
	
	@Override
	public String getType()
	{
		return type;
	}

	/**
	 * 
	 * @return
	 */
	public Component newComponent()
	{
		Component newComponent = null;
		
		try
		{
			newComponent = componentClass.newInstance();
			newComponent.init(this);
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
	 * @return
	 */
//	public <TComponentDef extends AbstractComponentDef> Component<TComponentDef> newComponentT()
//	{
//		Component<TComponentDef> newComponent = null;
//		
//		try
//		{
//			newComponent = componentClass.newInstance();
//			newComponent.init(this);
//		}
//		catch (InstantiationException | IllegalAccessException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return newComponent;
//	}
	
	private String type;
	private Class<? extends Component> componentClass;

}
