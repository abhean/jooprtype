package resource;

/**
 * @author kique
 *
 */
public interface Resource extends foundation.RefCounted
{
	void onLoaded();
	void dispose();
}
