package es.udc.mashup.productnews.atom.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import es.udc.mashup.productnews.atom.FeedTO;
import es.udc.mashup.productnews.atom.xml.FeedXMLConversor;

public class ServletUtils {
	
	public static void writeServiceResponse(FeedTO feed,
            HttpServletResponse response) 
            		throws IOException {

		OutputStream out = response.getOutputStream();

		response.setContentType("text/xml; charset=UTF-8"); 
		FeedXMLConversor.toXML(feed, out);
		out.close();

	}

}
