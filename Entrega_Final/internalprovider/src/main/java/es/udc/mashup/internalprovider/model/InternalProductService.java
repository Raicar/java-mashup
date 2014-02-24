package es.udc.mashup.internalprovider.model;

import java.util.Calendar;
import java.util.List;

public interface InternalProductService {
    
    public List<ProductITO> findProducts(String productName,
                                        String category,
    	                                double minPrice,
                                        double maxPrice);
    
    public List<ProductITO> findProductsByDate(Calendar firstDate, 
    		Calendar secondDate);
}
