
package es.udc.mashup.internalprovider.service.jaxws.jaxws;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findProductsByDate", namespace = "http://internalprovider.mashup.udc.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findProductsByDate", namespace = "http://internalprovider.mashup.udc.es/", propOrder = {
    "arg0",
    "arg1"
})
public class FindProductsByDate {

    @XmlElement(name = "arg0", namespace = "")
    private Calendar arg0;
    @XmlElement(name = "arg1", namespace = "")
    private Calendar arg1;

    /**
     * 
     * @return
     *     returns Calendar
     */
    public Calendar getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Calendar arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Calendar
     */
    public Calendar getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(Calendar arg1) {
        this.arg1 = arg1;
    }

}
