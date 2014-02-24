package es.udc.mashup.reviewprovider.facebook.proxy.json;

import net.sf.json.JSONObject;

public class FBResponse {

	public enum ContentType {DATA, EXCEPTION};
    
    private ContentType contentType;
    private JSONObject dataElements;
    private ExceptionInServiceResponse exception;
	
    public FBResponse() {
        dataElements = new JSONObject();
        contentType = ContentType.DATA;
    }
    
    public FBResponse(JSONObject dElements) {
    	dataElements = dElements;
        contentType = ContentType.DATA;
    }
    
    public FBResponse(ExceptionInServiceResponse exception) {
        this.exception = exception;
        contentType = ContentType.EXCEPTION;
    }

	public ContentType getContentType() {
		return contentType;
	}

	public JSONObject getDataElements() {
		return dataElements;
	}

	public ExceptionInServiceResponse getException() {
		return exception;
	}
    
    
    
}
