package es.udc.mashup.reviewprovider.facebook.proxy.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import es.udc.mashup.reviewprovider.ProductReviewFTO;
import es.udc.ws.util.exceptions.ParsingException;

public class PRFBResponseTypeConversor {
	
	public static Map<String, List<ProductReviewFTO>> toProductReviewFList(FBResponse r) {
		
		String desc = null, rev = null, productName;
		int score = 0;
    	JSONObject itemI, itemJ, jCom, jCFrom;
    	JSONArray jCArray;
    	Map<String, List<ProductReviewFTO>> reviews = new HashMap<String, List<ProductReviewFTO>>();
    	
    	try {
	    	JSONArray jsonA = r.getDataElements().getJSONArray("data");
	    
	    	for (int i=0; i<jsonA.size(); i++) {//Each product
	    		
				itemI = jsonA.getJSONObject(i);
		    	List<ProductReviewFTO> prList = new ArrayList<ProductReviewFTO>();
				
				if (itemI.has("message")) {
					productName = itemI.getString("message");
					
					if (itemI.has("comments")) {
						jCom = itemI.getJSONObject("comments");
						jCArray = jCom.getJSONArray("data");
						
						for (int j=0; j<jCArray.size(); j++) {//each comment
					
							itemJ = jCArray.getJSONObject(j);
							
							if (itemJ.has("from"))  {
								jCFrom = itemJ.getJSONObject("from");
								if (jCFrom.has("name"))
									rev = jCFrom.getString("name");
							}
							if (itemJ.has("message"))
								desc = itemJ.getString("message");
							if (itemJ.has("like_count"))
								score = itemJ.getInt("like_count");
							
							prList.add(toProductReviewF(desc, rev, score));
							desc = null; rev = null; score = 0;
						}
						
						reviews.put(productName, prList);
					}
				}
	    	}
	    	
	    	return reviews;
    	
		} catch (Exception e) {
	        throw new ParsingException("Error deserializing instance of " +
	                FBResponse.class, e);
	    }
	}

	
	private static ProductReviewFTO toProductReviewF(String desc, String rev, 
			int score) {
		
		ProductReviewFTO pr = new ProductReviewFTO();
		
		if (desc == null)
			desc = "No Description";
		if (rev == null)
			rev = "Unknown";
		
		pr.setDescription(desc);
		pr.setReviewer(rev);
		pr.setScore(score);
		
		return pr;
	}
	
}
