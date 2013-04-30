package resource;

import java.io.InputStream;

public interface ResourceLoader<T extends Resource>
{
	Class<T> getResourceType ();
	T 		 load			 (final InputStream inputStream);
}
