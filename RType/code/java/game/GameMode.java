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
	void Update  (float timeDelta);
	
	void OnActivated    ();
	void OnDeactivated  ();
	
	void OnPushed 		();
	void OnPopped 		();
}
