package view;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EntitySpriteViewDef extends EntityViewDef
{
	@Override
	public EntityView newEntityView(logic.Entity entity)
	{
		EntitySpriteView spriteView = new EntitySpriteView(entity, this);
		return spriteView;
	}

}
