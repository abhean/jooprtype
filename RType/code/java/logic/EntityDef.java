package logic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EntityDef extends resource.AbstractResource
{
	public EntityDef()
	{
		componentDefs = new ArrayList<ComponentDef>();
	}
	
	@Override public void onLoaded()
	{
		
	}
	
	@Override public void dispose()
	{
		// ...
	}
	
	public String getName()
	{
		return name;
	}
	
	@XmlAttribute
	private String name;
	
	@XmlElementWrapper
    @XmlAnyElement(lax=true)
	List<ComponentDef> getComponentDefs()
	{
		return componentDefs;
	}
	
	public void addComponentDef(ComponentDef componentDef)
	{
		componentDefs.add(componentDef);
	}
	
	private List<ComponentDef> componentDefs;
}
