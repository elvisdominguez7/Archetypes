package ${package}.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvalidInputFaultDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvalidInputFaultDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvalidInputFaultDetails", namespace = "http://${serviceName}.${package}", propOrder = {
    "errorMessage"
})
public class InvalidInputFaultDetails
    implements Serializable
{

   
    @XmlElement(required = true)
    protected List<String> errorMessage;

    /**
     * Gets the value of the errorMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new ArrayList<String>();
        }
        return this.errorMessage;
    }

    public InvalidInputFaultDetails withErrorMessage(String... values) {
        if (values!= null) {
            for (String value: values) {
                getErrorMessage().add(value);
            }
        }
        return this;
    }

    public InvalidInputFaultDetails withErrorMessage(Collection<String> values) {
        if (values!= null) {
            getErrorMessage().addAll(values);
        }
        return this;
    }

}
