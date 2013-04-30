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
    public Entity()
    {
        // initialise instance variables
    	components = new HashMap<String, Component>();
    }

    
    /**
     * 
     */
    public Component GetComponent(String name)
    {
    	return components.get(name);
    }
    
	/**
	 * 
	 */
	public void Update(final float timeDelta)
	{
		Set<String> componentIds = components.keySet();
		for (String componentId: componentIds)
		{
			Component component = components.get(componentId);
			component.Update(timeDelta);
		}
	}
    
	//--------------------------------------
    // instance variables
	//--------------------------------------
    private HashMap<String, Component> components;
}
