package es.udc.mashup.virtualstore.service.impl;

import es.udc.mashup.productprovider.ProductProviderService;
import es.udc.mashup.productprovider.ProductProviderServiceFactory;
import es.udc.mashup.reviewprovider.ReviewProviderService;
import es.udc.mashup.reviewprovider.ReviewProviderServiceFactory;
import es.udc.mashup.virtualstore.service.ProductTO;
import es.udc.mashup.virtualstore.service.VirtualProductProviderService;
import es.udc.mashup.virtualstore.service.utils.VSPPTOTypeConversor;
import es.udc.mashup.virtualstore.service.utils.VSPRTOTypeConversor;
import es.udc.ws.util.exceptions.ServiceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VirtualProductProviderStore implements VirtualProductProviderService {

	
	private static List<ProductProviderService> productsProviders;
	private static ReviewProviderService facebookReviewsProvider;
	
	static	{
		try {
			productsProviders = ProductProviderServiceFactory.
					getProductProviderService();
			facebookReviewsProvider = ReviewProviderServiceFactory.
					getReviewProviderService();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
    @Override
    public List<ProductTO> findProducts(String productName, String category,
                                        double minPrice, double maxPrice)
                                        throws ServiceException {
        List<ProductTO> products = new ArrayList<ProductTO>();
        
        for (ProductProviderService pp : productsProviders) {
        	products.addAll(VSPPTOTypeConversor.fromPPTO(pp.findProducts(
        			productName, category, minPrice, maxPrice)));
        }
        facebookReviewsProvider.findReviews("", 0);  
        for (ProductTO p : products) {
			p.setReviews(VSPRTOTypeConversor.fromPRTO(facebookReviewsProvider.
	        		findReviews(p.getName(), 1)));
		}
        
        return products;
    }

	@Override
	public List<ProductTO> findProductsByDate(Calendar firstDate, Calendar secondDate)
			throws ServiceException {
	
		List<ProductTO> products = new ArrayList<ProductTO>();
		
		for (ProductProviderService pp : productsProviders) {
        	products.addAll(VSPPTOTypeConversor.fromPPTO(
        			pp.findProductsByDate(firstDate, secondDate)));
        }
		facebookReviewsProvider.findReviews("", 0);  
        for (ProductTO p : products) {
			p.setReviews(VSPRTOTypeConversor.fromPRTO(facebookReviewsProvider.
	        		findReviews(p.getName(), 1)));
		}
		
		return products;
	}

}
