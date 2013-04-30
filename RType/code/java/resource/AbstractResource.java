package resource;

public abstract class AbstractResource implements Resource
{
	public AbstractResource()
	{
		references = 0;
	}

	public void finalize()
	{
		assert references == 0;
	}

	public void incRefs()
	{
		++references;
	}
	
	public void decRefs()
	{
		assert references > 0;
		--references;
	}
	
	public int  getRefs()
	{
		return references;
	}
	
	private int references;
}
