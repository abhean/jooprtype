package graphics;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlRootElement(name="item")
public class SpriteSheetItem
{
	/**
	 * 
	 * @author kique
	 *
	 */
	@XmlRootElement(name="frame")
	public static class Frame
	{
		@XmlAttribute
		public int u;
		
		@XmlAttribute
		public int v;
		
		@XmlAttribute
		public int w;
		
		@XmlAttribute
		public int h;
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
	 * @param index
	 * @return
	 */
	public Frame getFrame(int index)
	{
		assert index < frameList.size();
		return frameList.get(index);
	}

	/**
	 * 
	 */
	@XmlAttribute
	private String name;
	
	/**
	 * 
	 */
	@XmlElement(name="frame")
	private List<Frame> frameList;
}
