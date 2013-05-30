/**
 * 
 */
package game;

/**
 * @author kique
 *
 */
public interface GameMode
{
	void update  (float timeDelta);
	
	GameManager getGameManager();
	
	void onActivated    ();
	void onDeactivated  ();
	
	void onPushed 		();
	void onPopped 		();
}
