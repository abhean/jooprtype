package model;
import java.awt.Dimension;
import graphics.SpriteSheet;
import graphics.SpriteSheetItem;

public class SpriteGraphicsComponent extends AbstractComponent<SpriteGraphicsComponentDef> implements GraphicsComponent
{
	public SpriteGraphicsComponent()
	{
		super(GraphicsComponent.class, SpriteGraphicsComponentDef.class);

		//...
	}

	@Override
	public void init(Entity entity, ComponentDef componentDef)
	{
		super.init(entity, componentDef);
		
		spriteSheet = app.App.getInstance().getResourceManager().getResourceRef(SpriteSheet.class, getComponentDef().getSpriteSheet());
	}

	
	@Override
	public void update(final float timeDelta)
	{
		// TODO Auto-generated method stub
		

	}

	@Override
	public Dimension getDimension()
	{
		Dimension dimension = new Dimension();
		
		if (spriteSheet != null)
		{
			SpriteSheetItem item = spriteSheet.getItem(getSpriteSheetId());
			SpriteSheetItem.Frame frame0 = item.getFrame(0);
			dimension.width  = frame0.w;
			dimension.height = frame0.h;
		}

		return dimension;
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
