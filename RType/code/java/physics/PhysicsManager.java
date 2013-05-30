package physics;

import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class PhysicsManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicsManager
{
    /**
     * Constructor for objects of class PhysicsManager
     */
    public PhysicsManager()
    {
    	colliders = new ArrayList<Collider>();
    }
    
    
    /**
     * 
     */
    public void dispose()
    {
    	
    }
    
    
   
    /**
     * 
     */
    public void resolveCollisions()
    {
    	ListIterator<Collider> iterator = colliders.listIterator();
    	while (iterator.hasNext())
    	{
    		Collider collider = iterator.next();
    		
        	ListIterator<Collider> otherIterator = colliders.listIterator(iterator.nextIndex());
        	while (otherIterator.hasNext())
        	{
        		Collider otherCollider = otherIterator.next();
        		if (collider.checkCollisions(otherCollider))
        		{
        			collider.onCollision(otherCollider);
        			otherCollider.onCollision(collider);
        		}
        	}
    	}
    }

    /**
     * 
     */
    public <T extends Collider> T createCollider(ColliderOwner owner, final Class<T> type)
    {
    	T collider = null;
    	
    	try
		{
    		collider = type.newInstance();
    		collider.setOwner(owner);
	    	colliders.add(collider);
		} 
    	catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return collider;
    }
    
    /*
     * 
     */
    public void deleteCollider(Collider collider)
    {
    	//collider.dispose();
    	colliders.remove(collider);
    }
    
    
	private List<Collider> colliders;
}
