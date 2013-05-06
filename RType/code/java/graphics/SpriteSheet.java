//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.03 at 12:01:51 AM CEST 
//


package graphics;

import java.util.List;
import java.awt.Image;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import resource.AbstractResource;

@XmlRootElement
public class SpriteSheet extends AbstractResource
{
	public SpriteSheet()
	{
		texturePath = "";
		texture = null;
	}
	
	@Override
	public void onLoaded()
	{
		// ...
	}
	
	@Override
	public void dispose()
	{
		releaseTexture();
	}
	
	@XmlAttribute
	public String getTexturePath()
	{
		return texturePath;
	}
	
	public void setTexturePath(String texturePath)
	{
		this.texturePath = texturePath;
		LoadTexture(this.texturePath);
	}
	
	
	/**
	 * 
	 */
	Image getImage(String itemName, int frameIdx)
	{
		Image image = null;
		
		SpriteSheetItem item = getItem(itemName);
		if (item != null)
		{
			SpriteSheetItem.Frame frame = item.getFrame(frameIdx);
			if (frame != null)
			{
				image = texture.getSubImage(frame.u, frame.v, frame.w, frame.h);
			}
		}
		
		return image;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	private SpriteSheetItem getItem(String name)
	{
		SpriteSheetItem itemFound = null;

		for (SpriteSheetItem item: items)
		{
			if (item.getName().equals(name))
			{
				itemFound = item;
				break;
			}
		}
		
		return itemFound;
	}
	
	/**
	 * 
	 * @param texturePath
	 */
	private void LoadTexture(String texturePath)
	{
		releaseTexture();
		
		if (!texturePath.isEmpty())
		{
			texture = app.App.getInstance().getResourceManager().getResourceRef(Texture.class, texturePath);
		}
		else
		{
			texture = null;
		}
	}


	/**
	 * 
	 */
	private void releaseTexture()
	{
		if (texture != null)
		{
			app.App.getInstance().getResourceManager().releaseResourceRef(texture);
			texture = null;
		}
	}
	
	private String 	texturePath;
	private Texture texture;

	// @TODO[egarcia]: optimize: Map (Jaxb map adaptor?)
	@XmlElementWrapper
	@XmlElement(name="item")
	private List<SpriteSheetItem> items;
}
