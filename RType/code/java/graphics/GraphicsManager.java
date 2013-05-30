package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    	screen = new JComponent()
		{
    	  @Override
      	  protected void paintComponent(Graphics graphics)
      	  {
  		    Graphics2D graphics2d = (Graphics2D) graphics;

  		    graphics2d.setColor(new Color(0.0f, 0.0f, 0.0f));
  		    graphics2d.fillRect(0, 0, getWidth(), getHeight());

  		    for (Item item: items)
  		    {
  		    	item.draw(graphics2d);
  		    }
  		    
  		    graphics2d.dispose();
      	  }
		};
		
    	javax.swing.JFrame mainFrame = app.App.getInstance().getMainFrame();

    	screen.setFocusable(false);
    	screen.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
    	screen.setDoubleBuffered(true);
    	
    	mainFrame.add(screen);

    	mainFrame.validate();
    	mainFrame.repaint();
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
    	screen.repaint();
    }
    

    /**
     * 
     * @return
     */
    public JComponent getScreen()
    {
    	return screen;
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
    public void deleteItem(Item item)
    {
    	//item.dispose();
    	items.remove(item);
    }
    
    //----------------------------------------------
    // Instance variables
    //----------------------------------------------
    private List<Item> items;
    private JComponent screen;
}
