package logic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author kique
 *
 */
@XmlRootElement
public class SpriteGraphicsComponentDef extends AbstractComponentDef implements GraphicsComponentDef
{
	/**
	 * 
	 */
	public SpriteGraphicsComponentDef()
	{
		super(GraphicsComponentDef.TYPE, SpriteGraphicsComponent.class);
		
		// ...
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSpriteSheet()
	{
		return spriteSheet;
	}

	/**
	 * 
	 * @return
	 */
	public String getItemId()
	{
		return itemId;
	}
	
	@XmlAttribute
	private String spriteSheet;

	@XmlAttribute
	private String itemId;
}
