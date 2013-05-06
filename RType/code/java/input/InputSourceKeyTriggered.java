package input;

import java.awt.event.KeyEvent;

public class InputSourceKeyTriggered implements InputSource
{
	public InputSourceKeyTriggered(char keyChar)
	{
		this.keyChar = keyChar;
		this.bTriggered = false;
	}

	@Override
	public void onInputConsumed()
	{
		bTriggered = false;
	}
	
	@Override
	public float getValue() 
	{
		return bTriggered ? 1.0f : 0.0f;
	}

	@Override
	public void keyReleased(KeyEvent keyEvent)
	{
		if (keyEvent.getKeyChar() == keyChar)
		{
			bTriggered = true;
		}
	}
	
	@Override
	public void keyPressed (KeyEvent keyEvent)
	{
	}
	
	private char keyChar;
	private boolean bTriggered;
}
