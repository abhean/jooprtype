package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JComponent;



/**
 * Write a description of class CEngine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraphicsManager
{	
    /**
     * Constructor for objects of class CEngine
     */
    public GraphicsManager()
    {
    	items = new LinkedList<Item>();
    	
    	// JComponent Adaptor
    	JComponent screen = new JComponent()
		{
      	  public void paint(Graphics graphics)
      	  {
  		    Graphics2D graphics2d = (Graphics2D) graphics;

  		    for (Item item: items)
  		    {
  		    	item.Draw(graphics2d);
  		    }

  		    Toolkit.getDefaultToolkit().sync();
      	  }
		};
		
    	screen.setDoubleBuffered(true);
    			
    	app.App.getInstance().getMainFrame().add(screen);
    }
    
    /**
     * 
     */
    public void dispose()
    {
    	// ...
    	
    }
    
    /**
     * 
     * @param loader
     */
    public void update(final float timeDelta)
    {
    	
    }
    

    /**
     * 
     */
    public <T extends Item> T CreateItem(final Class<T> type)
    {
    	T item = null;
    	
    	try
		{
    		item = type.newInstance();
	    	items.add(item);
		} 
    	catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return item;
    }
    
    /*
     * 
     */
    public void DeleteItem(Item item)
    {
    	//item.dispose();
    	items.remove(item);
    }
    
    //----------------------------------------------
    // Instance variables
    //----------------------------------------------
    private List<Item> items;
}
