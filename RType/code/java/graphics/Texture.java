package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import resource.AbstractResource;

public class Texture extends AbstractResource
{
	@Override
	public void onLoaded()
	{
		
	}

	@Override
	public void dispose()
	{
		image = null;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	public Image getImage()
	{
		return image;
	}
	
	public Image getSubImage(int u, int v, int w, int h)
	{
		return image.getSubimage(u, v, w, h);
	}
	
	private BufferedImage image = null;
}
