package es.udc.mashup.productprovider;

import java.util.Calendar;
import java.util.List;

import es.udc.ws.util.exceptions.ServiceException;

public interface ProductProviderService {
    
    public List<ProductPTO> findProducts(String productName,
                                        String category,
    	                                double minPrice,
                                        double maxPrice)
                                        throws ServiceException;
    
    public List<ProductPTO> findProductsByDate(Calendar firstDate, Calendar secondDate)
			throws ServiceException;
   
}
