package model;

public interface WorldListener
{
	void onEntityCreated  (Entity entity);
	void onEntityDeleting (Entity entity);
	
	void onPlayerCreated (Player player);
}
