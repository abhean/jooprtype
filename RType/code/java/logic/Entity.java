package logic;


import java.util.HashMap;
import java.util.Set;


/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity
{

    /**
     * Constructor for objects of class Entity
     */
    public Entity(String name, EntityDef entityDef)
    {
    	this.name = name;
    	this.entityDef = entityDef;

    	// instantiate / initialize components from entityDef
    	components = new HashMap<String, Component>();
    	for (ComponentDef componentDef: entityDef.getComponentDefs())
    	{
    		Component component = componentDef.newComponent();
    		components.put(componentDef.getType(), component);
    	}
    }

    
    /**
     * 
     */
    public void dispose()
    {
    	if (entityDef != null)
    	{
    		resource.ResourceManager.getInstance().releaseResourceRef(entityDef);
    	}
    }
    
    
    /**
     * 
     * @return
     */
    public EntityDef getEntityDef()
    {
    	return entityDef;
    }
    

    /**
     * 
     * @param typeName
     * @param componentClass
     * @return
     */
    public <TComponent extends Component> TComponent getComponent(String typeName, Class<TComponent> componentClass)
    {
    	TComponent componentFound = null;
    	
		Component component = getComponent(typeName);
		if ((component != null) && componentClass.isInstance(component))
		{
			componentFound = componentClass.cast(component);
		}
		
		return componentFound;
    }
    
    /**
     * 
     */
    public Component getComponent(String typeName)
    {
    	return components.get(name);
    }
    
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		Set<String> componentIds = components.keySet();
		for (String componentId: componentIds)
		{
			Component component = components.get(componentId);
			component.update(timeDelta);
		}
	}
    
	//--------------------------------------
    // instance variables
	//--------------------------------------
	private String name;
	private EntityDef entityDef;
    private HashMap<String, Component> components;
}
