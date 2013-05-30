package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;

import app.App;

/**
 * Write a description of class CInputManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LogicInputMap
{
	/**
	 * 
	 */
	public LogicInputMap()
	{
		logicInputMap = new HashMap<String, InputSource>();
		
		// Create keyListenerAdaptor
		keyListenerAdaptor = new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent keyEvent) 
			{
				// Nothing to do
			}
			
			@Override
			public void keyReleased(KeyEvent keyEvent) 
			{
				for (InputSource inputSource: logicInputMap.values())
				{
					inputSource.keyReleased(keyEvent);
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent keyEvent) 
			{
				for (InputSource inputSource: logicInputMap.values())
				{
					inputSource.keyPressed(keyEvent);
				}
			}
		};		
	}

	/**
	 * 
	 */
	public void dispose()
	{
		setActive(false);
		keyListenerAdaptor = null;
	}
	
	
	/**
	 * 
	 * @param active
	 */
	public void setActive(boolean active)
	{
		JFrame appMainFrame = App.getInstance().getMainFrame();
		
		if (active != this.active)
		{
			this.active = active;

			if (this.active)
			{
				resetInputSources();
				appMainFrame.addKeyListener(keyListenerAdaptor);
			}
			else
			{
				appMainFrame.removeKeyListener(keyListenerAdaptor);
			}
		}
	}
	
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void resetInputSources()
	{
		for (InputSource inputSource: logicInputMap.values())
		{
			inputSource.reset();
		}
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void onLogicTickFinished()
	{
		for (InputSource inputSource: logicInputMap.values())
		{
			inputSource.onInputConsumed();
		}
	}
	
	/**
	 * 
	 * @param logicInputId
	 * @param inputSource
	 */
	public void addLogicInput(String logicInputId, InputSource inputSource)
	{
		logicInputMap.put(logicInputId, inputSource);
	}
	
	/**
	 * 
	 * @param logicInputId
	 * @return
	 */
	public float getLogicInputValue(String logicInputId)
	{
		float value = 0.0f;
		
		InputSource inputSource = logicInputMap.get(logicInputId);
		if (inputSource != null)
		{
			value = inputSource.getValue();
		}
		
		return value;
	}
	   
    // Logic input map
    private HashMap<String, InputSource> logicInputMap;
    private KeyListener keyListenerAdaptor;
    private boolean active;
}
