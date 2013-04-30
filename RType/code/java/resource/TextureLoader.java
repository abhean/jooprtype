package resource;

import graphics.Texture;

import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TextureLoader implements ResourceLoader<Texture>
{
	/**
	 * 
	 */
	public Class<Texture> getResourceType ()
	{
		return Texture.class;
	}
	
	/**
	 * 
	 */
	public Texture load(final InputStream inputStream)
	{
		Texture texture = new Texture();
		
		BufferedImage img = null;
		try
		{
		    img = ImageIO.read(inputStream);
		    texture.setImage(img);
		} 
		catch (IOException e) 
		{
		}
		
		return texture;
	}
}
