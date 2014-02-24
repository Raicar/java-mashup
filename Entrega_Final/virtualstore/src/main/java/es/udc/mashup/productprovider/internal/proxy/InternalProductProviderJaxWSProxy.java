package es.udc.mashup.productprovider.internal.proxy;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import es.udc.mashup.productprovider.ProductPTO;
import es.udc.mashup.productprovider.ProductProviderService;
import es.udc.mashup.productprovider.internal.proxy.wsdlutils.InternalWSDLTypeConversor;
import es.udc.mashup.productprovider.internal.wsdl.InternalProvider;
import es.udc.mashup.productprovider.internal.wsdl.InternalProviderService;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.configuration.MissingConfigurationParameterException;
import es.udc.ws.util.configuration.UnavailableConfigurationParametersException;
import es.udc.ws.util.exceptions.ServiceException;

public class InternalProductProviderJaxWSProxy implements
		ProductProviderService {
	
	private final static String ENDPOINT_ADDRESS_PARAMETER =
            "InternalProviderServiceJAXWSProxy/endpointAddress";
	
	private static String endpointAddress;

	private InternalProviderService internalProviderService =
			new InternalProviderService();
	
	private InternalProvider internalProvider = internalProviderService.
			getInternalProviderPort();

	public InternalProductProviderJaxWSProxy() {
		
		try {
			((BindingProvider)internalProvider).getRequestContext().
			put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
					getEndpointAddress());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<ProductPTO> findProducts(String productName, String category,
			double minPrice, double maxPrice) throws ServiceException {
		
		try {
			return InternalWSDLTypeConversor.fromWSDL(internalProvider.
					findProducts(productName, category, minPrice, maxPrice));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<ProductPTO> findProductsByDate(Calendar firstDate, Calendar secondDate)
			throws ServiceException {
		
		DatatypeFactory df1, df2;
		XMLGregorianCalendar d1, d2;
		
		try {			
			df1 = DatatypeFactory.newInstance();
			df2 = DatatypeFactory.newInstance();
			d1 = df1.newXMLGregorianCalendar((GregorianCalendar) firstDate);
			d2 = df2.newXMLGregorianCalendar((GregorianCalendar) secondDate);
					
			return InternalWSDLTypeConversor.fromWSDL(internalProvider.
					findProductsByDate(d1, d2));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	private static String getEndpointAddress()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (endpointAddress == null) {
            endpointAddress = ConfigurationParametersManager.getParameter(
                    ENDPOINT_ADDRESS_PARAMETER);
        }

        return endpointAddress;

    }



}
