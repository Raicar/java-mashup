package es.udc.mashup.virtualstore.service.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.mashup.reviewprovider.ProductReviewFTO;
import es.udc.mashup.virtualstore.service.ProductReviewTO;

public class VSPRTOTypeConversor {
	
	public static List<ProductReviewTO> fromPRTO(List<ProductReviewFTO> info) {
		List<ProductReviewTO> newInfo = new ArrayList<ProductReviewTO>();
		if (info != null) {
			for (int i=0; i<info.size(); i++) {
				newInfo.add(fromPRTO(info.get(i)));
			}
		}
		return newInfo;
	}

	
	public static ProductReviewTO fromPRTO(ProductReviewFTO info) {
		
    	ProductReviewTO pr = new ProductReviewTO();
    	pr.setDescription(info.getDescription());
    	pr.setReviewer(info.getReviewer());
    	pr.setScore(info.getScore());
        	
        return pr;
		
	}

}
