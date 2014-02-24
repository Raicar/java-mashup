package es.udc.mashup.productnews.atom.xml;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import es.udc.mashup.productnews.atom.FeedTO;
import es.udc.mashup.productnews.atom.EntryTO;
import es.udc.ws.util.exceptions.ParsingException;

public class FeedXMLConversor {
	
	private final static Namespace XML_NS = 
			Namespace.getNamespace("http://www.w3.org/2005/Atom");
	
	public static void toXML(FeedTO feed, OutputStream out) {
		
		try {
			
			Element productElement = toXML(feed);
            Document document = new Document(productElement);
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            
            outputter.output(document, out);
			
		} catch (Exception e) {
			throw new ParsingException("Error deserializing instance of "
                    + FeedTO.class, e);
		}
		
	}
	
	private static Element toXML(FeedTO feed) {
		
		Element element = new Element("feed", XML_NS);
		
		Element titleE = new Element("title", XML_NS);
		Element linkE = new Element("link", XML_NS);
		Element descE = new Element("subtitle", XML_NS);
		Element updateE = new Element("updated", XML_NS);
		Element authorE = new Element("author", XML_NS);
		
		titleE.setText(feed.getTitle());
		linkE.setAttribute("href", feed.getLink());
		descE.setText(feed.getDescription());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		updateE.setText(format.format(feed.getUpdated().getTime()));
		authorE.addContent(feed.getAuthor());
		
		element.addContent(titleE);
		element.addContent(linkE);
		element.addContent(descE);
		element.addContent(updateE);
		element.addContent(authorE);
		
		element.addContent(addEntries(feed.getEntries()));
		
		return element;
		
	}
	
	private static List<Element> addEntries(List<EntryTO> entries) {
		List<Element> entryList = new ArrayList<Element>();
		
		for (EntryTO e : entries) {
			Element entry = new Element("entry", XML_NS);
		
			Element titleE = new Element("title", XML_NS);
			titleE.setText(e.getTitle());
			entry.addContent(titleE);
			
			Element idE = new Element("id", XML_NS);
			idE.setText(e.getId());
			entry.addContent(idE);
			
			Element dateE = new Element("updated", XML_NS);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
			dateE.setText(format.format(e.getUpdated().getTime()));
			entry.addContent(dateE);
			
			Element summaryE = new Element("summary", XML_NS);
			summaryE.setText(e.getSummary());
			entry.addContent(summaryE);
			
			entryList.add(entry);
		}
		return entryList;
	}

}
