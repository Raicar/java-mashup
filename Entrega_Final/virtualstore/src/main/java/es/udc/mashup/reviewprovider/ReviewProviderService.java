package es.udc.mashup.reviewprovider;

import java.util.List;

public interface ReviewProviderService {

	public List<ProductReviewFTO> findReviews(String productName, int op);
}
