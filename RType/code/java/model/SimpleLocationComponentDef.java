package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleLocationComponentDef extends AbstractComponentDef 
{
	public SimpleLocationComponentDef()
	{
		super(SimpleLocationComponent.class);
		
		//...
	}


}
