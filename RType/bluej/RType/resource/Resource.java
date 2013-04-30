package resource;

import java.util.List;

/**
 * @author kique
 *
 */
public interface Resource 
{
	// @NOTE[egarcia]: Dependencies can be implicitly loaded in OnLoaded body or unmarshal customization
	//List<String> GetDependencies();
	void 		 OnLoaded();
}
