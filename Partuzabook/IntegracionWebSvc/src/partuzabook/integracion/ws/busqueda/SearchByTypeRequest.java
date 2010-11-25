
package partuzabook.integracion.ws.busqueda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchByTypeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchByTypeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoEvento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchByTypeRequest", propOrder = {
    "tipoEvento"
})
public class SearchByTypeRequest {

    protected int tipoEvento;

    /**
     * Gets the value of the tipoEvento property.
     * 
     */
    public int getTipoEvento() {
        return tipoEvento;
    }

    /**
     * Sets the value of the tipoEvento property.
     * 
     */
    public void setTipoEvento(int value) {
        this.tipoEvento = value;
    }

}
