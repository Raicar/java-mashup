package es.udc.mashup.productnews.atom.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.udc.mashup.productnews.atom.FeedTO;
import es.udc.mashup.productnews.atom.xml.ProductFeedConversor;
import es.udc.mashup.virtualstore.service.VirtualProductProviderFactory;
import es.udc.mashup.virtualstore.service.VirtualProductProviderService;
import es.udc.mashup.virtualstore.service.ProductTO;

public class AtomGetProductServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ProductTO> productList = new ArrayList<ProductTO>();
		Calendar firstDate, secondDate;
		FeedTO feed;
		
		firstDate = Calendar.getInstance();
		secondDate = Calendar.getInstance(); //today
		firstDate.add(Calendar.DATE, -1); //yesterday
		
		productList = getProducts().findProductsByDate(firstDate, secondDate);

		feed = ProductFeedConversor.toFeed(productList);
		
		ServletUtils.writeServiceResponse(feed, resp);
		
	}
	
	private VirtualProductProviderService getProducts() {
		return VirtualProductProviderFactory.getVirtualProviderService();
	}
	
}
