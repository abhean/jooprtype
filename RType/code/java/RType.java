import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Write a description of class space_invaders here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class RType 
{
    /**
     * Constructor for objects of class space_invaders
     */
    public static void main(String[] _aArgs)
    {
    	try {
            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
    	
    	app.App.init();
    }    
    
    /**
     * Private constructor for objects of class RType
     */
    private RType()
    {
        // initialise instance variables
    }
}   
   

