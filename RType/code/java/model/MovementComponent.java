package model;

import java.awt.geom.Point2D;

public interface MovementComponent extends Component
{
	public void move(Point2D.Float ratio);
}
