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
        GameManager gameManager = new GameManager();
        
        gameManager.add

    	GraphicsManager.init();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        
        ResourceManager.init();
        ResourceManager resourceManager = ResourceManager.getInstance();
        
        resourceManager.addLoader(new resource.JAXBResourceLoader<graphics.SpriteSheet>(graphics.SpriteSheet.class));
        resourceManager.addLoader(new resource.JAXBResourceLoader<logic.EntityDef>(logic.EntityDef.class, 
        		// serializable component defs list
        		logic.SpriteGraphicsComponentDef.class
        ));
        resourceManager.addLoader(new resource.JAXBResourceLoader<view.EntityViewDef>(view.EntityViewDef.class, 
        		// serializable component defs list
        		view.EntitySpriteViewDef.class
        ));
        resourceManager.addLoader(new resource.TextureLoader());
        
        logic.Entity entity = gameManager.getEntityManager().newEntity("guardianship", "assets/entitydefs/guardianship.xml");
        
//        graphics.SpriteSheet spriteSheet = resourceManager.getResourceRef(graphics.SpriteSheet.class, "assets/spritesheets/guardianship.xml");
//        System.out.println(spriteSheet.getTexturePath());
//
//        logic.EntityDef entityDef = resourceManager.getResourceRef(logic.EntityDef.class, "assets/entitydefs/guardianship.xml");
//        System.out.println(entityDef.getName());
//        
//        graphics.Sprite sprite = graphicsManager.CreateItem(graphics.Sprite.class);
//        sprite.SetSpriteSheetItem(spriteSheet, "idle");
        
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
   

