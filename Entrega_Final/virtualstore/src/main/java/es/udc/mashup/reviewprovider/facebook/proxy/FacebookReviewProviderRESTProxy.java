package es.udc.mashup.reviewprovider.facebook.proxy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import es.udc.mashup.reviewprovider.ProductReviewFTO;
import es.udc.mashup.reviewprovider.ReviewProviderService;
import es.udc.mashup.reviewprovider.facebook.proxy.json.ExceptionInServiceResponse;
import es.udc.mashup.reviewprovider.facebook.proxy.json.PRFBResponseTypeConversor;
import es.udc.mashup.reviewprovider.facebook.proxy.json.FBResponse;
import es.udc.mashup.reviewprovider.facebook.proxy.json.FBResponseJSONConversor;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.configuration.MissingConfigurationParameterException;
import es.udc.ws.util.configuration.UnavailableConfigurationParametersException;
import es.udc.ws.util.exceptions.ServiceException;

public class FacebookReviewProviderRESTProxy implements 
		ReviewProviderService {
	
	private final static String ENDPOINT_ADDRESS_PARAMETER =
	        "FacebookReviewProviderRESTFulProxy/endpointAddress";
	private final static String APP_ID = "FacebookReviewProvider/appId";
	private final static String ACCESS_TOKEN = 
			"FacebookReviewProvider/accessToken";

	private static String endpointAddress;
	private static String appId;
	private static String accessToken;
	
	private static Map<String, List<ProductReviewFTO>> reviews = null;
	
	public FacebookReviewProviderRESTProxy() {}

	@Override
	public List<ProductReviewFTO> findReviews(String productName, int op) {
		
		int statusCode;
		String queryGet = null;
		String fields = "feed?fields=message,comments.fields(from,message,like_count)";
		GetMethod method = null;
		
		try {
			
			if (op == 0) {
			
				queryGet = getEndpointAddress() + getAppId() + "/" + fields + "&" +
					"access_token=" + URLEncoder.encode(getAccessToken(), "UTF-8");
			
				method = new GetMethod(queryGet);
					
				HttpClient client = new HttpClient();
				statusCode = client.executeMethod(method);
			
				handleHTTPStatusCode(statusCode);
			
				InputStream in = method.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in, Charset.forName("UTF-8")));
	        	String jsonTxt = br.readLine();
	        	JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
	        	FBResponse fbResponse = FBResponseJSONConversor.
						toServiceResponse(json);
				
	        	if (fbResponse.getContentType() == 
						FBResponse.ContentType.DATA) {
	        		reviews = PRFBResponseTypeConversor.toProductReviewFList(fbResponse);
	        	} else {
					throw getUnexpectedServiceException(
							fbResponse.getException());
				}
	        	
			} else {
				if (reviews != null)
					return reviews.get(productName);
			}
			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			if (op == 0)
				method.releaseConnection();
		}
		return null;
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
	
	private static String getAppId()
			throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (appId == null) {
            appId = ConfigurationParametersManager.getParameter(
                    APP_ID);
        }

        return appId;

    }
	
	private static String getAccessToken()
			throws MissingConfigurationParameterException,
            UnavailableConfigurationParametersException {

        if (accessToken == null) {
            accessToken = ConfigurationParametersManager.getParameter(
                    ACCESS_TOKEN);
        }

        return accessToken;

    }
	
	private void handleHTTPStatusCode(int statusCode) throws ServiceException {
		
		if (statusCode != HttpStatus.SC_OK)
			throw new ServiceException("Status Code = " + statusCode);
	}
	
	private ServiceException getUnexpectedServiceException (
	        ExceptionInServiceResponse exception) {

	        return new ServiceException("Server exception; " +
	            "code = " + exception.getCode() +
	            "; message = " + exception.getMessage());

	}
	
}
