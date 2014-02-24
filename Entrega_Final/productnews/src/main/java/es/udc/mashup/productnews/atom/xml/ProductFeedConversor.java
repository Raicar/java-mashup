package es.udc.mashup.productnews.atom.xml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import es.udc.mashup.productnews.atom.EntryTO;
import es.udc.mashup.productnews.atom.FeedTO;
import es.udc.mashup.virtualstore.service.ProductReviewTO;
import es.udc.mashup.virtualstore.service.ProductTO;

public class ProductFeedConversor {
	
	private final static String FEED_NAME = "Last products";
	private final static String LINK_NAME = "http://www.acme.com/lastproducts.atom";
	private final static String DESC = "Last products added	in the last 24 hours";
	private final static String AUTHOR = "Facultad de Informatica";

	public static FeedTO toFeed(List<ProductTO> products) {
		FeedTO feed = new FeedTO();
		
		feed.setTitle(FEED_NAME);
		feed.setLink(LINK_NAME);
		feed.setDescription(DESC);
		feed.setAuthor(AUTHOR);
		feed.setUpdated(Calendar.getInstance());
		
		feed.setEntries(toEntry(products));
		
		return feed;
	}
	
	private static List<EntryTO> toEntry(List<ProductTO> products) {
		List<EntryTO> entries = new ArrayList<EntryTO>();
		
		for(ProductTO p : products) {
			entries.add(toEntry(p));
		}
		return entries;
	}
	
	private static EntryTO toEntry(ProductTO product) {
		EntryTO entry = new EntryTO();
		
		entry.setTitle(product.getName());
		entry.setSummary(toSummary(product));
		entry.setUpdated(product.getDate());
		entry.setId(LINK_NAME + "/" + product.getName().replaceAll(" ", "_") + ".html");
		return entry;
	}
	
	private static String toSummary(ProductTO product) {
		String value;
		
		value = "Description:" + product.getDescription() + "\n" + 
				"Category:" + product.getCategory() 	+ "\n" + 
				"Price:" + product.getPrice() + "\n" + 
				"Image:" + product.getImageURL() + "\n" +
				"Reviews:\n\t" + toReviews(product.getReviews());
		return value;
	}
	
	private static String toReviews(List<ProductReviewTO> reviews) {
		String value = "";
		int i = 1;
		
		for (ProductReviewTO pr : reviews) {
			value += "Review" + i + ":\n\t\t" +
						"DescRev: " + pr.getDescription() + "\n\t\t" +
						"Reviewer: " + pr.getReviewer() + "\n\t\t" +
						"Score: " + pr.getScore() + "\n\t";	
			i++;
		}
		
		return value;
	}
}
