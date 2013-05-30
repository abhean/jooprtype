package input;

import java.awt.event.KeyEvent;


public class InputSourceKeyPressed implements InputSource 
{
	public InputSourceKeyPressed(char keyChar)
	{
		this.keyChar = keyChar;
		this.bPressed = false;
	}
	
	@Override
	public void onInputConsumed()
	{
		
	}
	
	@Override
	public void reset()
	{
		this.bPressed = false;
	}

	@Override
	public float getValue() 
	{
		return bPressed ? 1.0f : 0.0f;
	}

	@Override
	public void keyReleased(final KeyEvent keyEvent)
	{
		if (keyEvent.getKeyChar() == keyChar)
		{
			bPressed = false;
		}
	}
	
	@Override
	public void keyPressed (final KeyEvent keyEvent)
	{
		if (keyEvent.getKeyChar() == keyChar)
		{
			bPressed = true;
		}	
	}
	
	private char keyChar;
	private boolean bPressed;
}
