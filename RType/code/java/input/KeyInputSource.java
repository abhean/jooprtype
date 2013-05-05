package input;


public class KeyInputSource implements InputSource 
{
	public KeyInputSource(char key)
	{
		this.key = key;
	}

	@Override
	public float getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	private char key;
}
