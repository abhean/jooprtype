package resource;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBResourceLoader<T extends Resource> implements ResourceLoader<T>
{
	
	public JAXBResourceLoader(final Class<T> resourceType, Class<?>... extraClasses)
	{
		this.resourceType = resourceType; 
		
		try
		{
			Class<?>[] classesToBeBound = new Class<?>[1 + extraClasses.length];
			classesToBeBound[0] = resourceType;
			System.arraycopy(extraClasses, 0, classesToBeBound, 1, extraClasses.length);

			JAXBContext  jaxbContext = JAXBContext.newInstance(classesToBeBound);
			this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} 
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Class<T> getResourceType()
	{
		return resourceType;
	}
	
	@Override
	public T load(final InputStream inputStream)

	{
		T resource = null;
		
		try
		{
			Object unmarshalledObject = jaxbUnmarshaller.unmarshal(inputStream);
			resource = resourceType.cast(unmarshalledObject);
		} 
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resource;
	}
	
	
	private Class<T>     resourceType;
	private Unmarshaller jaxbUnmarshaller;
}
