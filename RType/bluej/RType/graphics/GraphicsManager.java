package graphics;

import java.awt.Dimension;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JFrame;

import resource.Resource;

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
    	mainFrame = null;
    	items = new LinkedList<Item>();
    }

    /**
     * 
     */
    public <T extends Item> T CreateItem(final Class<T> type, Resource resource) throws IllegalAccessException, InstantiationException
    {
    	T item = type.newInstance();
    	item.SetResource(resource);
    	items.add(item);
    	
    	return item;
    }
    
    /**
     * 
     */
    public void Open()
    {
    	mainFrame = new JFrame("Main Frame");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(new Dimension(640, 480));
    	mainFrame.add(new Screen());
    	
    	mainFrame.setVisible(true);
    }
    
    //----------------------------------------------
    // Instance variables
    //----------------------------------------------
    private JFrame mainFrame;
    private List<Item> items;
}
