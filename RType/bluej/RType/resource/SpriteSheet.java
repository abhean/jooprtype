//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.03 at 12:01:51 AM CEST 
//


package resource;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpriteSheet implements Resource
{
	public SpriteSheet()
	{
		this("");
	}
	
	public SpriteSheet(String texturePath)
	{
		this.texturePath = texturePath;
	}
	
	public String GetTexturePath()
	{
		return texturePath;
	}
	
	@Override
	public void OnLoaded()
	{
		
	}
	
//	@Override
//	public List<String> GetDependencies()
//	{
//		return new ArrayList<String>();
//	}
	
	@XmlAttribute
	private String texturePath;
}