/**
 * 
 */
package graphics;

import java.awt.Graphics2D;

/**
 * @author kique
 *
 */
public interface Item
{
	public void update	   (final float timeDelta);
	public void draw	   (Graphics2D graphics2d); 
}
