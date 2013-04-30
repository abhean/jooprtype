package resource;

import java.io.InputStream;

public interface ResourceLoader<T extends Resource>
{
	Class<T> getResourceType ();
	T 		 Load			 (final InputStream inputStream);
}
