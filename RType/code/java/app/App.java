package app;

import input.InputSourceKeyPressed;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import game.GameManager;
import game.MainMenuMode;

/**
 * 
 * @author kique
 *
 */
public class App 
{
	// managers getters
	public graphics.GraphicsManager getGraphicsManager() { return graphicsManager; }
	public physics.PhysicsManager   getPhysicsManager () { return physicsManager; }
	public resource.ResourceManager getResourceManager() { return resourceManager; }
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
        resourceManager.addLoader(new resource.JAXBResourceLoader<model.LevelDef>(model.LevelDef.class));
        resourceManager.addLoader(new resource.JAXBResourceLoader<model.EntityDef>(model.EntityDef.class, 
        		// serializable component defs list
        		model.SpriteGraphicsComponentDef.class,
        		model.SimpleLocationComponentDef.class,
        		model.InertialMovementComponentDef.class,
        		model.PlayerBrainComponentDef.class,
        		model.SimpleShooterComponentDef.class,
        		model.SpriteCollisionComponentDef.class,
        		model.ProjectileBrainComponentDef.class,
        		model.SimpleLifeComponentDef.class,
        		model.AlienBrainComponentDef.class,
        		model.AlienRewardComponentDef.class
        ));
        resourceManager.addLoader(new resource.JAXBResourceLoader<view.g2d.EntityViewDef>(view.g2d.EntityViewDef.class, 
        		// serializable component defs list
        		view.g2d.EntitySpriteViewDef.class
        ));
        resourceManager.addLoader(new resource.TextureLoader());

        // Physics Manager
        physicsManager = new physics.PhysicsManager();
        
    	// Graphics Manager
		graphicsManager = new graphics.GraphicsManager();
				
		// Game Manager
    	gameManager = new GameManager();      
	
    	
    	//
    	model.World world = gameManager.getWorld();

    	// Player1
    	{
	    	model.Player player = world.newPlayer("PLAYER1");
	    	player.setColor(new Color(0.5f, 0.5f, 1.0f, 1.0f));
	    	game.PlayerInputController playerController = new game.PlayerInputController(player);
	    	input.LogicInputMap logicInputMap = playerController.getLogicInputMap();
			// @TODO[egarcia]: Load from XML
	    	logicInputMap.addLogicInput("Up", new InputSourceKeyPressed('w'));
			logicInputMap.addLogicInput("Down", new InputSourceKeyPressed('s'));
			logicInputMap.addLogicInput("Left", new InputSourceKeyPressed('a'));
			logicInputMap.addLogicInput("Right", new InputSourceKeyPressed('d'));
			logicInputMap.addLogicInput("Shoot", new InputSourceKeyPressed('f'));
	
	    	gameManager.addPlayerController(playerController);
    	}
		
    	// Player2
    	{
	    	model.Player player = world.newPlayer("PLAYER2");
	    	player.setColor(new Color(1.0f, 0.5f, 0.5f, 1.0f));
	    	game.PlayerInputController playerController = new game.PlayerInputController(player);
	    	input.LogicInputMap logicInputMap = playerController.getLogicInputMap();
			// @TODO[egarcia]: Load from XML
	    	logicInputMap.addLogicInput("Up", new InputSourceKeyPressed('o'));
			logicInputMap.addLogicInput("Down", new InputSourceKeyPressed('l'));
			logicInputMap.addLogicInput("Left", new InputSourceKeyPressed('k'));
			logicInputMap.addLogicInput("Right", new InputSourceKeyPressed('ñ'));
			logicInputMap.addLogicInput("Shoot", new InputSourceKeyPressed('´'));

			gameManager.addPlayerController(playerController);
    	}
    	
    	final float fFRAMES_PER_SEC = 60;
    	final float fTICK_PERIOD = 1.0f / fFRAMES_PER_SEC;
    	
    	timer = new Timer((int)(fTICK_PERIOD * 1000), new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			update(fTICK_PERIOD);
    		}
    	});
    	
    	timer.start();
    	
    	gameManager.pushGameMode(MainMenuMode.ID);
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
		
		if (graphicsManager != null)
		{
			graphicsManager.dispose();
			graphicsManager = null;
		}
		
		if (physicsManager != null)
		{
			physicsManager.dispose();
			physicsManager = null;
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
		
		graphicsManager.update(timeDelta);
		
		physicsManager.resolveCollisions();
		
		// draw
		mainFrame.repaint();
	    Toolkit.getDefaultToolkit().sync();
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
    	mainFrame.setLayout(null);
		mainFrame.setFocusable(true);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setResizable(false);
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
	
	private physics.PhysicsManager   physicsManager;
	private graphics.GraphicsManager graphicsManager;
	private resource.ResourceManager resourceManager;
	private game.GameManager		 gameManager;
	
	// @TODO[egarcia]: TimeManager
	private Timer timer;
}
