package es.udc.mashup.productprovider;

import java.util.ArrayList;
import java.util.List;

import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.ServiceException;

public class ProductProviderServiceFactory {
    
    private final static String CLASS_NAME_PARAMETER =
        "ProductProviderServiceFactory/className";

    private static List<ProductProviderService> instance;
    
    static {
        try {
        	instance = new ArrayList<ProductProviderService>();
            String implClassName = ConfigurationParametersManager.getParameter(
				CLASS_NAME_PARAMETER);
            for (String s : getConfProviders(implClassName)) {
            	Class<?> implClass = Class.forName(s);
            	instance.add((ProductProviderService) implClass.newInstance());
            }
        } catch (Exception e) {
        }
    }
    
    private ProductProviderServiceFactory() {}
    
    public static List<ProductProviderService> getProductProviderService()
        throws ServiceException {
        
        if (instance == null) {
            throw new ServiceException("Can not create instance of " +
                ProductProviderService.class);
        } else {
            return instance;
        }
        
    }    
    
    private static List<String> getConfProviders(String implClass) {
    	List<String> providerS = new ArrayList<String>();
    	String[] splitProv = implClass.split(";"); 
    	
    	for (String s : splitProv) {
    		providerS.add(s);
    	}
    	return providerS;
    }

}
