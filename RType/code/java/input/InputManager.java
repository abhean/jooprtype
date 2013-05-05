package input;

import graphics.GraphicsManager;

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
public class InputManager
{
	/**
	 * 
	 */
	public InputManager()
	{
		logicInputMap = new HashMap<String, InputSource>();
		
		JFrame appMainFrame = App.getInstance().getMainFrame();
		appMainFrame.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent keyEvent) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent keyEvent) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent keyEvent) 
			{
				// TODO Auto-generated method stub
				keyEvent.getKeyChar();
			}
		});

	}

	/**
	 * 
	 */
	public void dispose()
	{
		
	}
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta)
	{
		
	}
	
	/**
	 * 
	 * @param logicInputId
	 * @param inputSource
	 */
	public void addLogicInput(String logicInputId, InputSource inputSource)
	{
		
	}
	
	/**
	 * 
	 * @param logicInputId
	 * @return
	 */
	public float getLogicInputValue(String logicInputId)
	{
		return 0.0f;
	}
	   
    // Logic input map
    private HashMap<String, InputSource> logicInputMap;
}
