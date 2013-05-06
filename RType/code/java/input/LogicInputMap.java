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
		
		// Register keyListenerAdaptor
		JFrame appMainFrame = App.getInstance().getMainFrame();
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
		
		appMainFrame.addKeyListener(keyListenerAdaptor);
	}

	/**
	 * 
	 */
	public void dispose()
	{
		if (keyListenerAdaptor != null)
		{
			JFrame appMainFrame = App.getInstance().getMainFrame();
			appMainFrame.removeKeyListener(keyListenerAdaptor);
			keyListenerAdaptor = null;
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
}
