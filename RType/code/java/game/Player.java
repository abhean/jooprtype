package game;

import input.InputSourceKeyPressed;
import input.InputSourceKeyTriggered;

public class Player
{
	/**
	 * 
	 */
	public Player()
	{
		logicInputMap = new input.LogicInputMap();
		
		// Default logic input map
		// @TODO[egarcia]: Load from XML
		logicInputMap.addLogicInput("Up", new InputSourceKeyPressed('q'));
		logicInputMap.addLogicInput("Down", new InputSourceKeyPressed('a'));
		logicInputMap.addLogicInput("Left", new InputSourceKeyPressed('o'));
		logicInputMap.addLogicInput("Left", new InputSourceKeyPressed('p'));
		logicInputMap.addLogicInput("Fire", new InputSourceKeyTriggered(' '));
		
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		entityController.update(timeDelta);
		
		logicInputMap.onLogicTickFinished();
	}
	
	private input.LogicInputMap logicInputMap;
	private EntityController entityController;
}
