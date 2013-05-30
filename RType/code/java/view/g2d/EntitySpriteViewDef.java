package view.g2d;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EntitySpriteViewDef extends EntityViewDef
{
	@Override
	public EntityView newEntityView(model.Entity entity)
	{
		EntitySpriteView spriteView = new EntitySpriteView(entity, this);
		return spriteView;
	}

}
