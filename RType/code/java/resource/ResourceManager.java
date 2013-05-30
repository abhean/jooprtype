package resource;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * @author kique
 *
 */
public class ResourceManager 
{
	public ResourceManager()
	{
		rootPath  = "";
		loaders   = new HashMap<Class<?>, ResourceLoader<?>>();
		resources = new HashMap<String, Resource>();
	}
	
	public void dispose()
	{
		// Log alive references
		for (Map.Entry<String, Resource> resourceMapEntry: resources.entrySet())
		{
			System.err.format("Resource %s has %d alive references!\n", resourceMapEntry.getKey(), resourceMapEntry.getValue().getRefs());
		}
	}
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	public <T extends Resource> void addLoader(ResourceLoader<T> loader)
	{
		loaders.put(loader.getResourceType(), loader);
	}
	
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	public <T extends Resource> T getResourceRef(final Class<T> type, final String path)
	{
		T resource = null;
		
		Resource genericResource = resources.get(path);
		
		if (genericResource != null)
		{
			if (type.isInstance(genericResource))
			{
				resource = type.cast(genericResource);
			}
			else
			{
				System.err.println("Resource '"+path+":"+genericResource.getClass().toString()+"' is not of type '"+type.getName());
			}
		}
		else
		{
			resource = loadResource(type, path);

			if (resource != null)
			{
				resources.put(path, resource);
			}
		}
		
		if (resource != null)
		{
			addResourceRef(resource);
		}

		return resource;
	}
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	public void addResourceRef(Resource resource)
	{
		assert resource != null;
		resource.incRefs();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public void releaseResourceRef(Resource resource)
	{
		assert resource != null && resource.getRefs() > 0;
		resource.decRefs();
		
		if (resource.getRefs() == 0)
		{
			resources.values().remove(resource);
		}
	}
	
	
	/**
	 * 
	 */
	public void log()
	{
		System.out.println("ResourceManager.log");
		for (String path: resources.keySet())
		{
			Resource resource = resources.get(path);
			System.out.format("%s->%d\n", path, resource.getRefs());
		}
	}
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	private <T extends Resource> T loadResource(final Class<T> type, final String resourcePath)
	{
		T resource = null;
		
		ResourceLoader<?> loader = loaders.get(type);
		if (loader != null)
		{
			InputStream inputStream;
			
			String fullQualifiedPath = rootPath + resourcePath;
			
			//inputStream = new FileInputStream(fullQualifiedPath);
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fullQualifiedPath);
			if (inputStream != null)
			{
				//@SuppressWarnings("unchecked")
				resource = (T)loader.load(inputStream);
				resource.onLoaded();
			}
			else
			{
				System.err.printf("No file for path '%s'", fullQualifiedPath);
			}
		}
		else
		{
			System.err.printf("No resource loader for class '%s'", type.getName());
		}
		
		return resource;
	}
	
	
	//
	private String rootPath;
	private AbstractMap<Class<?>, ResourceLoader<?>> loaders;
	private AbstractMap<String, Resource> 		  	 resources;
}
