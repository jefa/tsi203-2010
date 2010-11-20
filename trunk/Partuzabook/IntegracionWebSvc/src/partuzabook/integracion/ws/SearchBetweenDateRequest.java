
package partuzabook.integracion.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for searchBetweenDateRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchBetweenDateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fechaTo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="maxEventos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchBetweenDateRequest", propOrder = {
    "fechaFrom",
    "fechaTo",
    "maxEventos"
})
public class SearchBetweenDateRequest {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaTo;
    protected int maxEventos;

    /**
     * Gets the value of the fechaFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFrom() {
        return fechaFrom;
    }

    /**
     * Sets the value of the fechaFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFrom(XMLGregorianCalendar value) {
        this.fechaFrom = value;
    }

    /**
     * Gets the value of the fechaTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaTo() {
        return fechaTo;
    }

    /**
     * Sets the value of the fechaTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaTo(XMLGregorianCalendar value) {
        this.fechaTo = value;
    }

    /**
     * Gets the value of the maxEventos property.
     * 
     */
    public int getMaxEventos() {
        return maxEventos;
    }

    /**
     * Sets the value of the maxEventos property.
     * 
     */
    public void setMaxEventos(int value) {
        this.maxEventos = value;
    }

}
