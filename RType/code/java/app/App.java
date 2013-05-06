package app;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import game.GameManager;

/**
 * 
 * @author kique
 *
 */
public class App 
{
	// managers getters
	public graphics.GraphicsManager getGraphicsManager() { return graphicsManager; }
	public resource.ResourceManager getResourceManager() { return resourceManager; }
	public input.LogicInputMap		getInputManager   () { return inputManager; }
	public game.GameManager			getGameManager	  () { return gameManager; }
	
	//---------------------------------------
	// Singleton
	//---------------------------------------
	public static boolean init() { assert instance == null; instance = new App(); instance.instanceInit(); return true; }
	public static void 	  done() { assert instance != null; instance.instanceDone(); instance = null; }
	
	public static App getInstance() { return instance; }
	
	private static App instance;
		
	/**
	 * 
	 */
	private App()
	{

	}
	
	/**
	 * 
	 */
	public void instanceInit()
	{
		// Main Window
		createMainWindow();
		
    	// Resource Manager
		resourceManager = new resource.ResourceManager();
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

    	// Graphics Manager
		graphicsManager = new graphics.GraphicsManager();

		// Input Manager
		inputManager 	= new input.LogicInputMap();
		
		// Game Manager
    	gameManager = new GameManager();      
    	gameManager.addWorldView(new view.WorldView());		
	}
	
	/**
	 * 
	 */
	private void instanceDone()
	{
		if (gameManager != null)
		{
			gameManager.dispose();
			gameManager = null;
		}
		
		if (inputManager != null)
		{
			inputManager.dispose();
			inputManager = null;
		}
		
		if (graphicsManager != null)
		{
			graphicsManager.dispose();
			graphicsManager = null;
		}
		
		if (resourceManager != null)
		{
			resourceManager.dispose();
			resourceManager = null;
		}		
	}
	

	/**
	 * 
	 */
	public void update(final float timeDelta)
	{
		// update managers
		gameManager.update(timeDelta);
		
		inputManager.onLogicTickFinished();
		
		graphicsManager.update(timeDelta);
		
		// draw
    	mainFrame.repaint();
	}
	
	/**
	 * 
	 */
	public JFrame getMainFrame()
	{
		return mainFrame;
	}
	
	
	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private void createMainWindow()
	{
    	mainFrame = new JFrame("Main Frame");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(new Dimension(1024, 768));
    	mainFrame.addWindowListener
    	(
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
    	
    	mainFrame.setVisible(true);		
	}
	
	//
	private JFrame mainFrame;
	
	private input.LogicInputMap		 inputManager;
	private graphics.GraphicsManager graphicsManager;
	private resource.ResourceManager resourceManager;
	private game.GameManager		 gameManager;
}
