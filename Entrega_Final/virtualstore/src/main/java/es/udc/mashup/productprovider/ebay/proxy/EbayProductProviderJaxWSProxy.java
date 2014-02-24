package es.udc.mashup.productprovider.ebay.proxy;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import es.udc.mashup.productprovider.ProductPTO;
import es.udc.mashup.productprovider.ProductProviderService;
import es.udc.mashup.productprovider.ebay.proxy.wsdlutils.EbayWSDLTypeConversor;
import es.udc.mashup.productprovider.ebay.wsdl.FindItemsAdvancedRequest;
import es.udc.mashup.productprovider.ebay.wsdl.FindItemsAdvancedResponse;
import es.udc.mashup.productprovider.ebay.wsdl.FindingService;
import es.udc.mashup.productprovider.ebay.wsdl.FindingServicePortType;
import es.udc.mashup.productprovider.ebay.wsdl.ItemFilter;
import es.udc.mashup.productprovider.ebay.wsdl.ItemFilterType;
import es.udc.mashup.productprovider.ebay.wsdl.PaginationInput;
import es.udc.mashup.productprovider.ebay.wsdl.SearchItem;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.configuration.MissingConfigurationParameterException;
import es.udc.ws.util.configuration.UnavailableConfigurationParametersException;
import es.udc.ws.util.exceptions.ServiceException;

public class EbayProductProviderJaxWSProxy implements
		ProductProviderService {

	private final static String ENDPOINT_ADDRESS_PARAMETER =
            "EbayProviderServiceJAXWSProxy/endpointAddress";
	private final static String APPID = "EbayProviderServiceJAXWSProxy/appId";
	private final static String WSDLLOCATION = "EbayProviderServiceJAXWSProxy/wsdlLocation";
	private final static String QNAME = "EbayProviderServiceJAXWSProxy/qName";
	private final static String CAT = "EbayProviderServiceJAXWSProxy/category";
	private final static String PAGENUMBER = "EbayProviderServiceJAXWSProxy/pageNumber";
	private final static String ENTRIESPERPAGE = "EbayProviderServiceJAXWSProxy/entriesPerPage";
	
	private static String endpointAddress;
	private static String appId;
	private static String wsdlLocation;
	private static String qName;
	private static Integer pageNumber;
	private static Integer entriesPerPage;
	private static FindingServicePortType port;
	
	public EbayProductProviderJaxWSProxy() {}
	
	static {
		try {
			FindingService service = new FindingService(new URL(getWsdlLocation()),
					new QName(getQname(), "FindingService"));
			port = service.getFindingServiceSOAPPort();
			BindingProvider bindingProvider = (BindingProvider) port;
			
			Map requestProperties = bindingProvider.getRequestContext();
			Map<String, List<String>> httpHeaders = 
					new HashMap<String, List<String>>();
			
			httpHeaders.put("X-EBAY-SOA-OPERATION-NAME", 
					Collections.singletonList("findItemsAdvanced"));
			httpHeaders.put("X-EBAY-SOA-GLOBAL-ID", 
					Collections.singletonList("EBAY-ES"));
			httpHeaders.put("X-EBAY-SOA-SECURITY-APPNAME", 
					Collections.singletonList(getAppId()));
			
			requestProperties.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
			requestProperties.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
					getEndpointAddress());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private void setFilters(List<ItemFilter> itemFilters) {
		
		ItemFilter availableToFilter = new ItemFilter();
		availableToFilter.setName(ItemFilterType.AVAILABLE_TO);
		availableToFilter.getValue().add("ES");
		
		ItemFilter listiningTypeFilter = new ItemFilter();
		listiningTypeFilter.setName(ItemFilterType.LISTING_TYPE);
		listiningTypeFilter.getValue().add("AuctionWithBIN");
		listiningTypeFilter.getValue().add("FixedPrice");
		listiningTypeFilter.getValue().add("StoreInventory");
		
		ItemFilter hideDuplicatedFilter =new ItemFilter();
		hideDuplicatedFilter.setName(ItemFilterType.HIDE_DUPLICATE_ITEMS);
		hideDuplicatedFilter.getValue().add("true");
		
		
		itemFilters.add(availableToFilter);
		itemFilters.add(listiningTypeFilter);
		itemFilters.add(hideDuplicatedFilter);
		
	}
	
	private void setPagInput(FindItemsAdvancedRequest request) {
		PaginationInput paginationInput = new PaginationInput();
		paginationInput.setPageNumber(1);
		paginationInput.setEntriesPerPage(getEntriesPerPage());
		request.setPaginationInput(paginationInput);
	}
	
	@Override
	public List<ProductPTO> findProducts(String productName, String category,
			double minPrice, double maxPrice) throws ServiceException {
		List<ProductPTO> products = new ArrayList<ProductPTO>();
		
		FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
		FindItemsAdvancedResponse response;
		List<SearchItem> items;
		List<ItemFilter> itemFilters = request.getItemFilter();
		
		setFilters(itemFilters);
		
		if (minPrice > 0) {
			ItemFilter minPriceFilter = new ItemFilter();
			minPriceFilter.setName(ItemFilterType.MIN_PRICE);
			minPriceFilter.getValue().add(Double.toString(minPrice));
			itemFilters.add(minPriceFilter);
		}
		if (maxPrice > 0 && maxPrice < Integer.MAX_VALUE) {
			ItemFilter maxPriceFilter = new ItemFilter();
			maxPriceFilter.setName(ItemFilterType.MAX_PRICE);
			maxPriceFilter.getValue().add(Double.toString(maxPrice));
			itemFilters.add(maxPriceFilter);
		}
		
		setPagInput(request);
		
		request.getCategoryId().add(getCategory(category));
		request.setKeywords(productName);
		
		int pages = getPageNumber();
		
		for(int i = 1; i <= pages; i++) {
			response = port.findItemsAdvanced(request);
			items = response.getSearchResult().getItem();
			products.addAll(EbayWSDLTypeConversor.fromWSDL(items));
			if (i + 1 <= pages )
				request.getPaginationInput().setPageNumber(i + 1);
		}
		
		return products;
	}

	@Override
	public List<ProductPTO> findProductsByDate(Calendar firstDate, Calendar secondDate)
			throws ServiceException {
		List<ProductPTO> products = new ArrayList<ProductPTO>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		
		FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
		FindItemsAdvancedResponse response;
		List<SearchItem> items;
		List<ItemFilter> itemFilters = request.getItemFilter();
		
		setFilters(itemFilters);
		
		if (firstDate != null) {
			ItemFilter modTimeFromFilter = new ItemFilter();
			modTimeFromFilter.setName(ItemFilterType.MOD_TIME_FROM);
			modTimeFromFilter.getValue().add(format.format(firstDate.getTime()));
		}
		
		setPagInput(request);
		
		request.getCategoryId().add(getCategory(null));
		
		int pages = getPageNumber();
		
		for(int i = 1; i <= pages; i++) {
			response = port.findItemsAdvanced(request);
			items = response.getSearchResult().getItem();
			products.addAll(EbayWSDLTypeConversor.fromWSDL(items));
			if (i + 1 <= pages )
				request.getPaginationInput().setPageNumber(i + 1);
		}
		
		
		return products;
	}

	
	private static String getEndpointAddress()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (endpointAddress == null) {
            endpointAddress = ConfigurationParametersManager.getParameter(
                    ENDPOINT_ADDRESS_PARAMETER);
        }

        return endpointAddress;

    }

	private static String getWsdlLocation()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (wsdlLocation == null) {
            wsdlLocation = ConfigurationParametersManager.getParameter(
                    WSDLLOCATION);
        }

        return wsdlLocation;

    }
	
	private static String getAppId()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (appId == null) {
            appId = ConfigurationParametersManager.getParameter(
                    APPID);
        }

        return appId;

    }

	private static String getQname()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (qName == null) {
            qName = ConfigurationParametersManager.getParameter(
                    QNAME);
        }

        return qName;

    }
	
	private static Integer getPageNumber()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (pageNumber == null) {
            pageNumber = Integer.parseInt(
            		ConfigurationParametersManager.getParameter(PAGENUMBER));
        }

        return pageNumber;

    }
	
	private static Integer getEntriesPerPage()
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (entriesPerPage == null) {
            entriesPerPage = Integer.parseInt(
            		ConfigurationParametersManager.getParameter(ENTRIESPERPAGE));
        }

        return entriesPerPage;

    }
	
	private static String getCategory(String category)
            throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (category == null)
            return ConfigurationParametersManager.getParameter(CAT);

        return ConfigurationParametersManager.getParameter(CAT + category);

    }
	
}
