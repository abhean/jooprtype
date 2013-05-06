package input;

import java.awt.event.KeyEvent;

public interface InputSource 
{
	float getValue();
	
	void onInputConsumed();
	
	// Events
	void keyReleased(final KeyEvent keyEvent);
	void keyPressed (final KeyEvent keyEvent);
}
