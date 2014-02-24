package es.udc.mashup.ui.client.virtualstore;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

public interface VirtualStoreServiceAsync {

    public void findProducts(String productName,
                             String category,
                             double minPrice,
                             double maxPrice,
                             AsyncCallback callback)
    throws InvalidSearchException;

    public void findCategories(AsyncCallback callback)
    throws InvalidSearchException;

}
