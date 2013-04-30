package resource;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @author kique
 *
 */
public class ResourceManager 
{
	//---------------------------------------
	// Singleton
	//---------------------------------------
	public static boolean Init() { assert instance == null; instance = new ResourceManager(); return true; }
	public static void 	  Done() { assert instance != null; instance = null; }
	
	public static ResourceManager GetInstance() { return instance; }
	
	private static ResourceManager instance;
	
	private ResourceManager()
	{
		rootPath  = "";
		loaders   = new HashMap<Class<?>, ResourceLoader<?>>();
		resources = new HashMap<String, Resource>();
	}
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	public <T extends Resource> void AddLoader(ResourceLoader<T> loader)
	{
		loaders.put(loader.getResourceType(), loader);
	}
	
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	public <T extends Resource> T GetResource(final Class<T> type, final String path)
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
			resource = LoadResource(type, path);
			
		}

		return resource;
	}
	
	/**
	 * 
	 * @param _sResource
	 * @return
	 */
	private <T extends Resource> T LoadResource(final Class<T> type, final String path)
	{
		T resource = null;
		
		ResourceLoader<?> loader = loaders.get(type);
		if (loader != null)
		{
			try
			{
				FileInputStream inputStream;
				inputStream = new FileInputStream(path);
				
				//@SuppressWarnings("unchecked")
				resource = (T)loader.Load(inputStream);
				
//				List<String> dependencies = resource.GetDependencies();
//				for (String dependency: dependencies)
//				{
//					// @TODO[egarcia]: LoadResource without type or encode type in xml?
//					//LoadResource(Resource.class, dependency);
//				}
				
				resource.OnLoaded();
			} 
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resource;
	}
	
	//
	private String rootPath;
	private AbstractMap<Class<?>, ResourceLoader<?>> loaders;
	private AbstractMap<String, Resource> 		  	 resources;
}
