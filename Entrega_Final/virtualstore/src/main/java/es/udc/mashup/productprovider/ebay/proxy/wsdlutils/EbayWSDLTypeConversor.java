package es.udc.mashup.productprovider.ebay.proxy.wsdlutils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import es.udc.mashup.productprovider.ProductPTO;
import es.udc.mashup.productprovider.ebay.wsdl.Amount;
import es.udc.mashup.productprovider.ebay.wsdl.Category;
import es.udc.mashup.productprovider.ebay.wsdl.ListingInfo;
import es.udc.mashup.productprovider.ebay.wsdl.SearchItem;
import es.udc.mashup.productprovider.ebay.wsdl.SellingStatus;
import es.udc.mashup.productprovider.ebay.wsdl.ShippingInfo;

public class EbayWSDLTypeConversor {
	
	public static List<ProductPTO> fromWSDL(List<SearchItem> info) {
		List<ProductPTO> newInfo = new ArrayList<ProductPTO>();
		for (int i=0; i<info.size(); i++) {
			newInfo.add(fromWSDL(info.get(i)));
		}
		return newInfo;
	}

	public static ProductPTO fromWSDL(SearchItem sItem) {
		ProductPTO newInfo = new ProductPTO();
		Category c;
		ListingInfo lInfo;
		XMLGregorianCalendar gCal;
		SellingStatus sStat;
		ShippingInfo sShip;
		Amount pSell, pShip;
		double pSellV = 0, pShipV = 0;
		
		newInfo.setName(sItem.getTitle());
		newInfo.setDescription(sItem.getSubtitle());
		
		c = sItem.getPrimaryCategory();
		if (c != null)
			newInfo.setCategory(c.getCategoryName());
		
		newInfo.setImageURL(sItem.getPictureURLLarge());
		
		lInfo = sItem.getListingInfo();
		if (lInfo != null) {
			gCal = lInfo.getStartTime();
			if (gCal != null)
				newInfo.setDate(gCal.toGregorianCalendar());
		}
		
		sStat = sItem.getSellingStatus();
		sShip = sItem.getShippingInfo();
		if (sStat != null) {
			pSell = sStat.getConvertedCurrentPrice();
			if (pSell != null)
				pSellV = pSell.getValue();
		}
		if (sShip != null) {
			pShip = sShip.getShippingServiceCost();
			if (pShip != null)
				pShipV = pShip.getValue();
		}
		newInfo.setPrice(pSellV + pShipV);
		
		return newInfo;
	}
}
