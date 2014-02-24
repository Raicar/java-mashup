
package es.udc.mashup.productprovider.internal.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.udc.mashup.productprovider.internal.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindProductsByDate_QNAME = new QName("http://internalprovider.mashup.udc.es/", "findProductsByDate");
    private final static QName _FindProductsResponse_QNAME = new QName("http://internalprovider.mashup.udc.es/", "findProductsResponse");
    private final static QName _FindProducts_QNAME = new QName("http://internalprovider.mashup.udc.es/", "findProducts");
    private final static QName _FindProductsByDateResponse_QNAME = new QName("http://internalprovider.mashup.udc.es/", "findProductsByDateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.udc.mashup.productprovider.internal.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindProductsByDate }
     * 
     */
    public FindProductsByDate createFindProductsByDate() {
        return new FindProductsByDate();
    }

    /**
     * Create an instance of {@link FindProductsByDateResponse }
     * 
     */
    public FindProductsByDateResponse createFindProductsByDateResponse() {
        return new FindProductsByDateResponse();
    }

    /**
     * Create an instance of {@link ProductWTO }
     * 
     */
    public ProductWTO createProductWTO() {
        return new ProductWTO();
    }

    /**
     * Create an instance of {@link FindProducts }
     * 
     */
    public FindProducts createFindProducts() {
        return new FindProducts();
    }

    /**
     * Create an instance of {@link FindProductsResponse }
     * 
     */
    public FindProductsResponse createFindProductsResponse() {
        return new FindProductsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindProductsByDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://internalprovider.mashup.udc.es/", name = "findProductsByDate")
    public JAXBElement<FindProductsByDate> createFindProductsByDate(FindProductsByDate value) {
        return new JAXBElement<FindProductsByDate>(_FindProductsByDate_QNAME, FindProductsByDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://internalprovider.mashup.udc.es/", name = "findProductsResponse")
    public JAXBElement<FindProductsResponse> createFindProductsResponse(FindProductsResponse value) {
        return new JAXBElement<FindProductsResponse>(_FindProductsResponse_QNAME, FindProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://internalprovider.mashup.udc.es/", name = "findProducts")
    public JAXBElement<FindProducts> createFindProducts(FindProducts value) {
        return new JAXBElement<FindProducts>(_FindProducts_QNAME, FindProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindProductsByDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://internalprovider.mashup.udc.es/", name = "findProductsByDateResponse")
    public JAXBElement<FindProductsByDateResponse> createFindProductsByDateResponse(FindProductsByDateResponse value) {
        return new JAXBElement<FindProductsByDateResponse>(_FindProductsByDateResponse_QNAME, FindProductsByDateResponse.class, null, value);
    }

}
