package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * Write a description of class CEngine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraphicsManager
{
	//---------------------------------------
	// Singleton
	//---------------------------------------
	public static boolean init() { assert instance == null; instance = new GraphicsManager(); return true; }
	public static void 	  done() { assert instance != null; instance.dispose(); instance = null; }
	
	public static GraphicsManager getInstance() { return instance; }
	
	private static GraphicsManager instance;
	
    /**
     * Constructor for objects of class CEngine
     */
    private GraphicsManager()
    {
    	mainFrame = null;
    	items = new LinkedList<Item>();
    }
    
    private void dispose()
    {
    	// ...
    	
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
    
    /**
     * 
     */
    public void Open()
    {
    	mainFrame = new JFrame("Main Frame");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(new Dimension(1024, 768));
    	
    	// @TODO[egarcia]: Notify application
    	mainFrame.addWindowListener(
			new WindowListener() 
			{
				@Override
				public void windowOpened(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosed(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowActivated(WindowEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
			}
		);
    	
    	// JComponent Adaptor
    	mainFrame.add(
			new JComponent()
			{
	      	  public void paint(Graphics graphics)
	      	  {
	      		    Graphics2D graphics2d = (Graphics2D) graphics;
	
	      		    for (Item item: items)
	      		    {
	      		    	item.Draw(graphics2d);
	      		    }
	
	      		    graphics2d.finalize();
	      	  }
			}
		);
    	
    	mainFrame.setVisible(true);
    }
    
    //----------------------------------------------
    // Instance variables
    //----------------------------------------------
    private JFrame mainFrame;
    private List<Item> items;
}
