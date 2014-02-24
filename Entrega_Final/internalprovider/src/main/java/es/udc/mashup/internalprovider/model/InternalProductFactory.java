package es.udc.mashup.internalprovider.model;

import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.ServiceException;


public class InternalProductFactory {
	
	private final static String CLASS_NAME_PARAMETER =
	        "ProductFactory/className";

	private static InternalProductService instance;
	    
	static {
		try {
			String implClassName = ConfigurationParametersManager.getParameter(
					CLASS_NAME_PARAMETER);
			Class<?> implClass = Class.forName(implClassName);
			instance = (InternalProductService) implClass.newInstance();
		} catch (Exception e) {
		}
	}
	    
	private InternalProductFactory() {}
	    
	public static InternalProductService getInternalProviderService()
			throws ServiceException {
	        
		if (instance == null) {
			throw new ServiceException("Can not create instance of " +
					InternalProductService.class);
		} else {
			return instance;
		}
	        
	}    

}
