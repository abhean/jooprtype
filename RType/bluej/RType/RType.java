import game.GameManager;
import graphics.GraphicsManager;
import resource.ResourceManager;


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
        GraphicsManager graphicsManager	= new GraphicsManager();
        GameManager gameManager = new GameManager();
        
        ResourceManager.Init();
        ResourceManager.GetInstance().AddLoader(new resource.JAXBResourceLoader<resource.SpriteSheet>(resource.SpriteSheet.class));
        
        resource.SpriteSheet spriteSheet = ResourceManager.GetInstance().GetResource(resource.SpriteSheet.class, "file.xml");
        System.out.println(spriteSheet.GetTexturePath());
        
        try
        {
        	graphicsManager.Open();
        }
        catch (Exception e)
        {
        	
        }
       
    }
    
    /**
     * Private constructor for objects of class RType
     */
    private RType()
    {
        // initialise instance variables
    }   
}   
   

