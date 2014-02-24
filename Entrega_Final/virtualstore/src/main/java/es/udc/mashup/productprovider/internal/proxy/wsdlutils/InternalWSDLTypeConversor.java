package es.udc.mashup.productprovider.internal.proxy.wsdlutils;

import java.util.ArrayList;
import java.util.List;


import es.udc.mashup.productprovider.ProductPTO;
import es.udc.mashup.productprovider.internal.wsdl.ProductWTO;


public class InternalWSDLTypeConversor {
	
	public static List<ProductPTO> fromWSDL(List<ProductWTO> info) {
		List<ProductPTO> newInfo = new ArrayList<ProductPTO>();
		for (int i=0; i<info.size(); i++) {
			newInfo.add(fromWSDL(info.get(i)));
		}
		return newInfo;
	}

	
	public static ProductPTO fromWSDL(ProductWTO info) {
		
		ProductPTO newInfo = new ProductPTO();
        newInfo.setCategory(info.getCategory());
        if (info.getDate() != null)
        	newInfo.setDate(info.getDate().toGregorianCalendar());
        newInfo.setDescription(info.getDescription());
        newInfo.setImageURL(info.getImageURL());
        newInfo.setName(info.getName());
        newInfo.setPrice(info.getPrice() * info.getDiscount());

        return newInfo;
		
	}

}
