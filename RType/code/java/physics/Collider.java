package physics;

public interface Collider
{
	void dispose();
	
	boolean checkCollisions(final Collider collider);
	
	// Concrete collison detection
	boolean 	  collides(final SpriteCollider collider);
	
	ColliderOwner getOwner ();
	void 		  setOwner (ColliderOwner owner);
	
	//
	void onCollision(Collider collider);
	
	
}
