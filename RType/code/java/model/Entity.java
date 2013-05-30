package model;


import java.util.HashMap;
import java.util.Collection;


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
    public Entity(World world, String name, EntityDef entityDef)
    {
    	this.world = world;
    	this.name = name;
    	this.entityDef = entityDef;

    	// instantiate / initialize components from entityDef
    	components = new HashMap<Class<? extends Component>, Component>();
    	for (ComponentDef componentDef: entityDef.getComponentDefs())
    	{
    		Component component = componentDef.newComponent(this);
    		addComponent(component.getComponentType(), component);
    	}
    }

    
    /**
     * 
     */
    public void dispose()
    {
		Collection<Component> componentValues = components.values();
		for (Component component: componentValues)
		{
			component.dispose();
		}
		
    	if (entityDef != null)
    	{
    		app.App.getInstance().getResourceManager().releaseResourceRef(entityDef);
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
     * @return
     */
    public World getWorld()
    {
    	return world;
    }
    
    /**
     * 
     * @return
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * 
     * @param componentType
     * @param component
     */
    public <TComponentType extends Component> 
    void addComponent(Class<TComponentType> componentType, Component component)
    {
    	assert Components.isValidComponentType(componentType) && componentType.isInstance(component);
    	components.put(componentType, component);
    }
    
    /**
     * 
     * @param typeName
     * @param componentClass
     * @return
     */
    public <TComponentType extends Component, TComponent extends TComponentType> 
    TComponent getComponent(Class<TComponentType> componentType, Class<TComponent> componentClass)
    {
    	TComponent componentFound = null;
    	
    	TComponentType componentCandidate = getComponentByType(componentType);
		if ((componentCandidate != null) && componentClass.isInstance(componentCandidate))
		{
			componentFound = componentClass.cast(componentCandidate);
		}
		
		return componentFound;
    }
    
    /**
     * 
     */
    // @NOTE[egarcia]: addComponent guarantees that the type is valid
    @SuppressWarnings("unchecked")
	public <TComponentType extends Component> TComponentType getComponentByType(Class<TComponentType> componentType)
    {
    	assert Components.isValidComponentType(componentType);
    	return (TComponentType)components.get(componentType);
    }
    
	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		Collection<Component> componentValues = components.values();
		for (Component component: componentValues)
		{
			component.update(timeDelta);
		}
	}
    
	//--------------------------------------
    // instance variables
	//--------------------------------------
	private World world;
	private String name;
	private EntityDef entityDef;
    private HashMap<Class<? extends Component>, Component> components;
}
