package es.udc.mashup.virtualstore.service;

import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.ServiceException;

public class VirtualProductProviderFactory {
    
    private final static String CLASS_NAME_PARAMETER =
        "VirtualProductProviderFactory/className";

    private static VirtualProductProviderService instance;
    
    static {
        try {
            String implClassName = ConfigurationParametersManager.getParameter(
				CLASS_NAME_PARAMETER);
            Class<?> implClass = Class.forName(implClassName);
            instance = (VirtualProductProviderService) implClass.newInstance();
        } catch (Exception e) {
        }
    }
    
    private VirtualProductProviderFactory() {}
    
    public static VirtualProductProviderService getVirtualProviderService()
        throws ServiceException {
        
        if (instance == null) {
            throw new ServiceException("Can not create instance of " +
                VirtualProductProviderService.class);
        } else {
            return instance;
        }
        
    }    

}
