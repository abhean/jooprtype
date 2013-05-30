package view;

public interface WorldView extends model.WorldListener
{
	void init(model.World world);
	void dispose();
	
	void update(final float timeDelta);
}
