
package es.udc.mashup.internalprovider.service.jaxws.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import es.udc.mashup.internalprovider.service.jaxws.ProductWTO;

@XmlRootElement(name = "findProductsByDateResponse", namespace = "http://internalprovider.mashup.udc.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findProductsByDateResponse", namespace = "http://internalprovider.mashup.udc.es/")
public class FindProductsByDateResponse {

    @XmlElement(name = "return", namespace = "")
    private List<ProductWTO> _return;

    /**
     * 
     * @return
     *     returns List<ProductWTO>
     */
    public List<ProductWTO> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<ProductWTO> _return) {
        this._return = _return;
    }

}
