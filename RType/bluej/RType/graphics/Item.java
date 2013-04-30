/**
 * 
 */
package graphics;

import java.awt.Graphics2D;

import resource.Resource;

/**
 * @author kique
 *
 */
public interface Item
{
	public void SetResource(Resource resource);

	public void Draw	   (Graphics2D graphics2d); 
}
