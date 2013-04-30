package resource;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBResourceLoader<T extends Resource> implements ResourceLoader<T>
{
	
	public JAXBResourceLoader(final Class<T> resourceType)
	{
		this.resourceType = resourceType; 
		
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(resourceType);
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
	public T Load(final InputStream inputStream)

	{
		T resource = null;
		
		try
		{
			resource = resourceType.cast(jaxbUnmarshaller.unmarshal(inputStream));
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
