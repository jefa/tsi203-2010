
package partuzabook.integracion.ws.productora_web;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the partuzabook.integracion.ws.productora_web package. 
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

    private final static QName _GetAlbumContentsResponseReturn_QNAME = new QName("http://ejb.integracion.tsi2.fing.edu.uy", "return");
    private final static QName _SearchEventByNameName_QNAME = new QName("http://ejb.integracion.tsi2.fing.edu.uy", "name");
    private final static QName _GetAlbumContentsEventID_QNAME = new QName("http://ejb.integracion.tsi2.fing.edu.uy", "eventID");
    private final static QName _SearchEventByDateDate_QNAME = new QName("http://ejb.integracion.tsi2.fing.edu.uy", "date");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partuzabook.integracion.ws.productora_web
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAlbumContentsResponse }
     * 
     */
    public GetAlbumContentsResponse createGetAlbumContentsResponse() {
        return new GetAlbumContentsResponse();
    }

    /**
     * Create an instance of {@link SearchEventByNameResponse }
     * 
     */
    public SearchEventByNameResponse createSearchEventByNameResponse() {
        return new SearchEventByNameResponse();
    }

    /**
     * Create an instance of {@link SearchEventByName }
     * 
     */
    public SearchEventByName createSearchEventByName() {
        return new SearchEventByName();
    }

    /**
     * Create an instance of {@link SearchEventByType }
     * 
     */
    public SearchEventByType createSearchEventByType() {
        return new SearchEventByType();
    }

    /**
     * Create an instance of {@link GetAlbumContents }
     * 
     */
    public GetAlbumContents createGetAlbumContents() {
        return new GetAlbumContents();
    }

    /**
     * Create an instance of {@link SearchEventByDateResponse }
     * 
     */
    public SearchEventByDateResponse createSearchEventByDateResponse() {
        return new SearchEventByDateResponse();
    }

    /**
     * Create an instance of {@link SearchEventByDate }
     * 
     */
    public SearchEventByDate createSearchEventByDate() {
        return new SearchEventByDate();
    }

    /**
     * Create an instance of {@link SearchEventByTypeResponse }
     * 
     */
    public SearchEventByTypeResponse createSearchEventByTypeResponse() {
        return new SearchEventByTypeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "return", scope = GetAlbumContentsResponse.class)
    public JAXBElement<Object> createGetAlbumContentsResponseReturn(Object value) {
        return new JAXBElement<Object>(_GetAlbumContentsResponseReturn_QNAME, Object.class, GetAlbumContentsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "return", scope = SearchEventByNameResponse.class)
    public JAXBElement<Object> createSearchEventByNameResponseReturn(Object value) {
        return new JAXBElement<Object>(_GetAlbumContentsResponseReturn_QNAME, Object.class, SearchEventByNameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "name", scope = SearchEventByName.class)
    public JAXBElement<String> createSearchEventByNameName(String value) {
        return new JAXBElement<String>(_SearchEventByNameName_QNAME, String.class, SearchEventByName.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "eventID", scope = GetAlbumContents.class)
    public JAXBElement<String> createGetAlbumContentsEventID(String value) {
        return new JAXBElement<String>(_GetAlbumContentsEventID_QNAME, String.class, GetAlbumContents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "return", scope = SearchEventByDateResponse.class)
    public JAXBElement<Object> createSearchEventByDateResponseReturn(Object value) {
        return new JAXBElement<Object>(_GetAlbumContentsResponseReturn_QNAME, Object.class, SearchEventByDateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "date", scope = SearchEventByDate.class)
    public JAXBElement<XMLGregorianCalendar> createSearchEventByDateDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SearchEventByDateDate_QNAME, XMLGregorianCalendar.class, SearchEventByDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.integracion.tsi2.fing.edu.uy", name = "return", scope = SearchEventByTypeResponse.class)
    public JAXBElement<Object> createSearchEventByTypeResponseReturn(Object value) {
        return new JAXBElement<Object>(_GetAlbumContentsResponseReturn_QNAME, Object.class, SearchEventByTypeResponse.class, value);
    }

}
