package model;

public interface ComponentDef
{
	void onLoaded();
	Component newComponent(Entity entity);
}
