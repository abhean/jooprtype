/**
 * 
 */
package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * @author kique
 *
 */
public class Screen extends JComponent
{
	  public Screen()
	  {
		  
	  }
	
	  public void paint(Graphics graphics)
	  {
		    Graphics2D graphics2d = (Graphics2D) graphics;

	        //resource.SpriteSheet spriteSheet = ResourceManager.GetInstance().GetResource(resource.SpriteSheet.class, "assets/r-9aarrowhead.png");
		    graphics2d.finalize();
	  }

}
