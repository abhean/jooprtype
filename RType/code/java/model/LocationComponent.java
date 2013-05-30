package model;

import java.awt.geom.Point2D;

public interface LocationComponent extends Component
{
	Point2D.Float getPosition();
	void		  setPosition(final Point2D.Float position);
	
	float	getRotation();
	void	setRotation(float rotation);
}
