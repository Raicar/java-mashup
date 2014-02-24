package es.udc.mashup.internalprovider.service.jaxws.wsdlutils;

import java.util.ArrayList;
import java.util.List;
import es.udc.mashup.internalprovider.model.ProductITO;
import es.udc.mashup.internalprovider.service.jaxws.ProductWTO;

public class InternalWSDLTypeConversor {

	public static List<ProductWTO> toWSDL(List<ProductITO> info) {

        List<ProductWTO> newInfo = new ArrayList<ProductWTO>();

        for (int i=0; i<info.size(); i++) {
            newInfo.add(toWSDL(info.get(i)));
        }

        return newInfo;
    }
	
	public static ProductWTO toWSDL(ProductITO info) {

        ProductWTO newInfo = new ProductWTO();
        newInfo.setCategory(info.getCategory());
        newInfo.setDate(info.getDate());
        newInfo.setDescription(info.getDescription());
        newInfo.setImageURL(info.getImageURL());
        newInfo.setName(info.getName());
        newInfo.setPrice(info.getPrice());
        newInfo.setDiscount(info.getDiscount());

        return newInfo;
    }
	
}
