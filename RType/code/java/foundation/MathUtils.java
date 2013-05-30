package foundation;

import java.awt.geom.Point2D;

public class MathUtils
{
	public static float getVector2DMagnitude(Point2D.Float vector)
	{
		return (float)Math.sqrt(vector.x * vector.x + vector.y * vector.y);
	}
	
	public static Point2D.Float getNormalizedVector2D(Point2D.Float vector)
	{
		return getNormalizedVector2D(vector, 1.0f);
	}
	
	public static Point2D.Float getNormalizedVector2D(Point2D.Float vector, float newMagnitude)
	{
		Point2D.Float normalizedVector = (Point2D.Float)vector.clone();
		
		float magnitude = getVector2DMagnitude(vector);
		if (magnitude > 0.0f)
		{
			normalizedVector.x *= newMagnitude / magnitude;
			normalizedVector.y *= newMagnitude / magnitude;
		}

		return normalizedVector;
	}
	
	public static Point2D.Float getClampedVector2D(Point2D.Float vector, float maxMagnitude)
	{
		Point2D.Float clampedVector;
		
		float magnitude = MathUtils.getVector2DMagnitude(vector);
		if   (magnitude > maxMagnitude)
		{
			clampedVector = MathUtils.getNormalizedVector2D(vector, maxMagnitude);
		}
		else
		{
			clampedVector = (Point2D.Float)vector.clone();
		}
		
		return clampedVector;
	}
}
