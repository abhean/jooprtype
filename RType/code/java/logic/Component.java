package logic;
/**
 * 
 */

/**
 * @author kique
 *
 */
public interface Component
{
	/**
	 * 
	 * @param componentDef
	 */
	public void init(ComponentDef componentDef);
	
	
	/**
	 * 
	 * @param timeDelta
	 */
	public void update(final float timeDelta);
}
