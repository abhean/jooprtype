package model;

import java.util.Arrays;


public final class Components
{
	// "static" class
	private Components()
	{
		// ...
	}
	
	// @NOTE[egarcia]: Checks TComponentBaseType is a valid interface (direct inheritance from Component interface)
    public static boolean isValidComponentType(Class<? extends Component> componentClass)
    {
    	return componentClass.isInterface() && Arrays.asList(componentClass.getInterfaces()).contains(Component.class);
    }

}
