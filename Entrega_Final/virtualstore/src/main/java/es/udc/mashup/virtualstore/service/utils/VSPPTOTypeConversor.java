package es.udc.mashup.virtualstore.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.mashup.productprovider.ProductPTO;
import es.udc.mashup.virtualstore.service.ProductReviewTO;
import es.udc.mashup.virtualstore.service.ProductTO;

public class VSPPTOTypeConversor {
	
	public static List<ProductTO> fromPPTO(List<ProductPTO> info) {
		List<ProductTO> newInfo = new ArrayList<ProductTO>();
		for (int i=0; i<info.size(); i++) {
			newInfo.add(fromPPTO(info.get(i)));
		}
		return newInfo;
	}

	
	public static ProductTO fromPPTO(ProductPTO info) {
		
		ProductTO newInfo = new ProductTO();
        newInfo.setCategory(info.getCategory());
        if (info.getDate() != null)
        	newInfo.setDate(info.getDate());
        newInfo.setDescription(info.getDescription());
        newInfo.setImageURL(info.getImageURL());
        newInfo.setName(info.getName());
        newInfo.setPrice(info.getPrice());
        newInfo.setReviews(new ArrayList<ProductReviewTO>());

        return newInfo;
		
	}


}
