package game;

import java.awt.geom.Point2D;

import foundation.MathUtils;

import model.MovementComponent;
import model.ShooterComponent;

public class PlayerInputController implements PlayerController
{
	/**
	 * 
	 */
	public PlayerInputController(model.Player player)
	{
		this.player = player;
		logicInputMap = new input.LogicInputMap();
		
		player.addPlayerListener(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public input.LogicInputMap getLogicInputMap()
	{
		return logicInputMap;
	}
	
	/**
	 * 
	 * @param active
	 */
	public void setActive(boolean active)
	{
		logicInputMap.setActive(active);
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	@Override
	public void update(final float timeDelta)
	{
		model.Entity playerEntity = player.getEntity();
		
		if (playerEntity != null)
		{
			// Movement
			model.MovementComponent movementComponent = playerEntity.getComponentByType(MovementComponent.class);
			
			Point2D.Float moveInput = new Point2D.Float(
					logicInputMap.getLogicInputValue("Right") - logicInputMap.getLogicInputValue("Left"),
					logicInputMap.getLogicInputValue("Down") - logicInputMap.getLogicInputValue("Up")
			);
		
			if (MathUtils.getVector2DMagnitude(moveInput) > 0.0f)
			{
				movementComponent.move(moveInput);
			}
			
			// Shoot
			if (logicInputMap.getLogicInputValue("Shoot") > 0.0f)
			{
				model.ShooterComponent shooterComponent = playerEntity.getComponentByType(ShooterComponent.class);
				shooterComponent.shoot();
			}
		}
		
		logicInputMap.onLogicTickFinished();
	}
	
	@Override
	public void onPlayerEntityDead()
	{
	}
	
	private input.LogicInputMap logicInputMap;
	private model.Player player;
}
