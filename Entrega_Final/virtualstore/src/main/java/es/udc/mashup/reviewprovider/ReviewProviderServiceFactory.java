package es.udc.mashup.reviewprovider;

import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.ServiceException;

public class ReviewProviderServiceFactory {
	
	private final static String CLASS_NAME_PARAMETER =
			"ReviewProviderServiceFactory/className";
	 
	private static ReviewProviderService instance;
	 
	static {
		try {
			String implClassName = ConfigurationParametersManager.getParameter(
					CLASS_NAME_PARAMETER);
			Class<?> implClass = Class.forName(implClassName);
			instance = (ReviewProviderService) implClass.newInstance();
		} catch (Exception e) {
		}
	}
	 
	private ReviewProviderServiceFactory() {}
	 
	public static ReviewProviderService getReviewProviderService()
			throws ServiceException {
		        
		if (instance == null) {
			throw new ServiceException("Can not create instance of " +
					ReviewProviderService.class);
		} else {
			return instance;
		}
	    
	}  

}
