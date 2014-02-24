package es.udc.mashup.internalprovider.service.jaxws;

import java.util.Calendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.udc.mashup.internalprovider.model.InternalProductFactory;
import es.udc.mashup.internalprovider.service.jaxws.wsdlutils.InternalWSDLTypeConversor;

@WebService(
		name = "InternalProvider",
		serviceName = "InternalProviderService",
		targetNamespace = "http://internalprovider.mashup.udc.es/"
)
public class InternalProviderJaxWSBindingImpl {
	
	public InternalProviderJaxWSBindingImpl() {}

	@WebMethod(
			operationName = "findProducts"
	)
	public List<ProductWTO> findProducts(String productName, String category,
			double minPrice, double maxPrice)
	{
		return InternalWSDLTypeConversor.toWSDL(InternalProductFactory.
				getInternalProviderService().
				findProducts(productName, category, minPrice, maxPrice));
	}
	
	@WebMethod(
			operationName = "findProductsByDate"
	)
	public List<ProductWTO> findProductsByDate(Calendar firstDate, Calendar secondDate)
	{
		return InternalWSDLTypeConversor.toWSDL(InternalProductFactory.
				getInternalProviderService().
				findProductsByDate(firstDate, secondDate));
	}
	

}
