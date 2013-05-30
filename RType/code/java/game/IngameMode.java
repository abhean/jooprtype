package game;

import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class IngameMode extends AbstractGameMode
{
	public static final String ID = "INGAME"; 

	public IngameMode(GameManager gameManager)
	{
		super(gameManager);
		
	}
	
	@Override
	public void update(float timeDelta)
	{
		GameManager gameManager = getGameManager();
		
		gameManager.updatePlayerControllers(timeDelta);
		gameManager.updateWorld(timeDelta);
		gameManager.updateWorldViews(timeDelta);
		
		if (gameManager.getWorld().getState() == model.World.State.GAME_OVER)
		{
			gameManager.pushGameMode(GameOverMode.ID);
		}
	}
	
	@Override
	public void onActivated()
	{
		getGameManager().setPlayerControllersActive(true);
	}
	
	@Override
	public void onDeactivated()
	{
		getGameManager().setPlayerControllersActive(false);		
	}
	
	@Override
	public void onPushed()
	{
		JFrame mainFrame = app.App.getInstance().getMainFrame();
		GameManager gameManager = getGameManager();
		model.World world = gameManager.getWorld();
    	// Player1
    	{
	    	model.Player player = world.getPlayer("PLAYER1");

	    	model.Entity entity = world.newEntity("Player1", "assets/entitydefs/guardianship-player1.xml");
    		model.LocationComponent locationComponent = entity.getComponentByType(model.LocationComponent.class);
    		locationComponent.setPosition(new Point2D.Float(30.0f, mainFrame.getHeight() * 0.3f));
	    	player.setEntity(entity);
    	}
		
    	// Player2
    	{
	    	model.Player player = world.getPlayer("PLAYER2");

	    	model.Entity entity = world.newEntity("Player2", "assets/entitydefs/guardianship-player2.xml");
    		model.LocationComponent locationComponent = entity.getComponentByType(model.LocationComponent.class);
    		locationComponent.setPosition(new Point2D.Float(30.0f, mainFrame.getHeight() * 0.6f));
    		player.setEntity(entity);
    	}
    	
    	gameView = new view.g2d.WorldView();
		getGameManager().addWorldView(gameView);	
		
		hudView = new view.hud.WorldView();
		getGameManager().addWorldView(hudView);	
	}
	
	@Override
	public void onPopped()
	{
		model.World world = getGameManager().getWorld();
		if (world != null)
		{
			world.deleteAllEntities(true);
		}
		
		if (gameView != null)
		{
			getGameManager().deleteWorldView(gameView);
			gameView = null;
		}
		
		if (hudView != null)
		{
			getGameManager().deleteWorldView(hudView);
			hudView = null;
		}
	}
	
	
	view.g2d.WorldView gameView;
	view.hud.WorldView hudView;
}
