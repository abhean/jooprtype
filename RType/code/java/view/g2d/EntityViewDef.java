package view.g2d;

import resource.AbstractResource;

public abstract class EntityViewDef extends AbstractResource
{

	@Override
	public void onLoaded()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}
	
	public abstract EntityView newEntityView(model.Entity entity);

}
