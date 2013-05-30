package model;

import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class Level implements WorldListener
{
	/**
	 * 
	 * @param world
	 * @param levelDef
	 */
	public Level(World world, LevelDef levelDef)
	{
		this.world = world;
		world.addListener(this);
		
		this.levelDef = levelDef;
		
		this.numSpawnedAliens = 0;
		this.numActiveAliens = 0;
		
		timeSinceLastAlienGeneration = 0.0f;
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		world.removeListener(this);
		
		if (levelDef != null)
		{
			app.App.getInstance().getResourceManager().releaseResourceRef(levelDef);
			levelDef = null;
		}
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		// Aliens
		// @TODO[egarcia]: Difficulty, level settings
		timeSinceLastAlienGeneration += timeDelta;
		if ((timeSinceLastAlienGeneration > levelDef.getMinTimeBetweenSpawns()) &&
			(numSpawnedAliens < levelDef.getNumAliensToSpawn()) && 
			(numActiveAliens  < levelDef.getMaxActiveAliens()))
		{
			++numActiveAliens;
			++numSpawnedAliens;
			timeSinceLastAlienGeneration = 0.0f;
			
			JFrame mainFrame = app.App.getInstance().getMainFrame();
			
			String entityDefPath;
			if (Math.random() < levelDef.getHardShipsRatio())
			{
				entityDefPath = "assets/entitydefs/alienshiphard.xml";
			}
			else
			{
				entityDefPath = "assets/entitydefs/alienship.xml";				
			}
			
			model.Entity alienShipEntity = world.newEntity("AlienShip", entityDefPath);
			model.LocationComponent locationComponent = alienShipEntity.getComponentByType(model.LocationComponent.class);
			
			
			model.InertialMovementComponent inertialMovementComponent = alienShipEntity.getComponent(model.MovementComponent.class, model.InertialMovementComponent.class);
			inertialMovementComponent.setMaxSpeedFactor(levelDef.getAlienShipsSpeedFactor());
			
			model.GraphicsComponent graphicsComponent = alienShipEntity.getComponentByType(model.GraphicsComponent.class);
			
			final float BORDER = 0.2f;
			float height = (float)(Math.random() * (1.0f - (BORDER * 2.0f))) + BORDER;
			locationComponent.setPosition(new Point2D.Float(
					mainFrame.getWidth()  + graphicsComponent.getDimension().width, 
					mainFrame.getHeight() * height - graphicsComponent.getDimension().height * 0.5f));
		}
	}
	

	@Override
	public void onEntityCreated(Entity entity)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean isFinished()
	{
		return (numDestroyedAliens >= levelDef.getNumAliensToDestroy());
	}

	@Override
	public void onEntityDeleting(Entity entity)
	{
		if (entity.getEntityDef().getType().equals("enemy"))
		{
			--numActiveAliens;

			// If it is not dead, it can be respawned
			LifeComponent lifeComp = entity.getComponentByType(LifeComponent.class);
			if (lifeComp.getState() != LifeComponent.State.DEAD)
			{
				assert numSpawnedAliens > 0;
				--numSpawnedAliens;
			}
			else
			{
				++numDestroyedAliens;
			}
		}
	}

	@Override
	public void onPlayerCreated(Player player)
	{
		// TODO Auto-generated method stub
		
	}

	private World world;
	private LevelDef levelDef;
	
    private float timeSinceLastAlienGeneration;
    private int   numActiveAliens;
    private int   numSpawnedAliens;
    private int   numDestroyedAliens;

}