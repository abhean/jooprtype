package logic;
import graphics.SpriteSheet;

public class SpriteGraphicsComponent extends AbstractComponent<SpriteGraphicsComponentDef> implements Component
{
	public SpriteGraphicsComponent()
	{
		super(SpriteGraphicsComponentDef.class);

		//...
	}

	@Override
	public void init(ComponentDef componentDef)
	{
		super.init(componentDef);
		
		spriteSheet = app.App.getInstance().getResourceManager().getResourceRef(SpriteSheet.class, getComponentDef().getSpriteSheet());
	}
	
	@Override
	public void update(final float timeDelta)
	{
		// TODO Auto-generated method stub

	}
	

	/**
	 * 
	 * @return
	 */
	public SpriteSheet getSpriteSheet()
	{
		return spriteSheet;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSpriteSheetId()
	{
		return getComponentDef().getItemId();
	}

	/**
	 * 
	 */
	private SpriteSheet spriteSheet;
}
