package game;

public interface PlayerController extends model.PlayerListener
{
	public void update(final float timeDelta);
	public void setActive(boolean active);
}
