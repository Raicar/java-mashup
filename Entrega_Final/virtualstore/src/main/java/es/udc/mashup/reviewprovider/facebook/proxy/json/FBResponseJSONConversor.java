package es.udc.mashup.reviewprovider.facebook.proxy.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jdom.DataConversionException;
import es.udc.ws.util.exceptions.ParsingException;

public class FBResponseJSONConversor {
	
	private FBResponseJSONConversor() {}
    
    public static FBResponse toServiceResponse(JSONObject json) 
        throws ParsingException {
        
    	JSONObject itemI, jsonReturn = new JSONObject();
    	JSONArray jA = new JSONArray();
    	
        try {
        	
        	if (json.has("data")) {
        		JSONArray jsonA = json.getJSONArray("data");
        		
        		for (int i=0; i<jsonA.size(); i++) {
        			
        			itemI = jsonA.getJSONObject(i);
        			
        			if (itemI.has("message"))
        				jA.add(itemI);
        		}
        		
        		jsonReturn.put("data", jA);
        		return new FBResponse(jsonReturn);
        	}else
        		return new FBResponse(
        				toResponseException(json.getJSONObject("error")));
        
        } catch (Exception e) {
            throw new ParsingException("Error deserializing instance of " +
                    FBResponse.class, e);
        }
        
    } 
  
    private static ExceptionInServiceResponse toResponseException(
            JSONObject exception) throws DataConversionException {
        
        int code = exception.getInt("code");
        String message = exception.getString("message");
        
        return new ExceptionInServiceResponse(code, message);
        
    }
    
}
