
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

    private final static QName _DataEventDescripcion_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "descripcion");
    private final static QName _DataEventUrlPortada_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "urlPortada");
    private final static QName _DataEventFecha_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "fecha");
    private final static QName _DataEventIdEvento_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "idEvento");
    private final static QName _DataEventDireccion_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "direccion");
    private final static QName _DataEventNombre_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "nombre");
    private final static QName _SearchEventByNameName_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy", "name");
    private final static QName _DataContentUrlPublica_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy/xsd", "urlPublica");
    private final static QName _SearchEventByDateDate_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy", "date");
    private final static QName _GetAlbumContentsEventID_QNAME = new QName("http://ws.integracion.tsi2.fing.edu.uy", "eventID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partuzabook.integracion.ws.productora_web
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataEvent }
     * 
     */
    public DataEvent createDataEvent() {
        return new DataEvent();
    }

    /**
     * Create an instance of {@link SearchEventByName }
     * 
     */
    public SearchEventByName createSearchEventByName() {
        return new SearchEventByName();
    }

    /**
     * Create an instance of {@link SearchEventByDate }
     * 
     */
    public SearchEventByDate createSearchEventByDate() {
        return new SearchEventByDate();
    }

    /**
     * Create an instance of {@link DataContent }
     * 
     */
    public DataContent createDataContent() {
        return new DataContent();
    }

    /**
     * Create an instance of {@link SearchEventByDateResponse }
     * 
     */
    public SearchEventByDateResponse createSearchEventByDateResponse() {
        return new SearchEventByDateResponse();
    }

    /**
     * Create an instance of {@link SearchEventByTypeResponse }
     * 
     */
    public SearchEventByTypeResponse createSearchEventByTypeResponse() {
        return new SearchEventByTypeResponse();
    }

    /**
     * Create an instance of {@link SearchEventByType }
     * 
     */
    public SearchEventByType createSearchEventByType() {
        return new SearchEventByType();
    }

    /**
     * Create an instance of {@link SearchEventByNameResponse }
     * 
     */
    public SearchEventByNameResponse createSearchEventByNameResponse() {
        return new SearchEventByNameResponse();
    }

    /**
     * Create an instance of {@link GetAlbumContents }
     * 
     */
    public GetAlbumContents createGetAlbumContents() {
        return new GetAlbumContents();
    }

    /**
     * Create an instance of {@link GetAlbumContentsResponse }
     * 
     */
    public GetAlbumContentsResponse createGetAlbumContentsResponse() {
        return new GetAlbumContentsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "descripcion", scope = DataEvent.class)
    public JAXBElement<String> createDataEventDescripcion(String value) {
        return new JAXBElement<String>(_DataEventDescripcion_QNAME, String.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "urlPortada", scope = DataEvent.class)
    public JAXBElement<String> createDataEventUrlPortada(String value) {
        return new JAXBElement<String>(_DataEventUrlPortada_QNAME, String.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "fecha", scope = DataEvent.class)
    public JAXBElement<XMLGregorianCalendar> createDataEventFecha(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DataEventFecha_QNAME, XMLGregorianCalendar.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "idEvento", scope = DataEvent.class)
    public JAXBElement<Integer> createDataEventIdEvento(Integer value) {
        return new JAXBElement<Integer>(_DataEventIdEvento_QNAME, Integer.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "direccion", scope = DataEvent.class)
    public JAXBElement<String> createDataEventDireccion(String value) {
        return new JAXBElement<String>(_DataEventDireccion_QNAME, String.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "nombre", scope = DataEvent.class)
    public JAXBElement<String> createDataEventNombre(String value) {
        return new JAXBElement<String>(_DataEventNombre_QNAME, String.class, DataEvent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy", name = "name", scope = SearchEventByName.class)
    public JAXBElement<String> createSearchEventByNameName(String value) {
        return new JAXBElement<String>(_SearchEventByNameName_QNAME, String.class, SearchEventByName.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "descripcion", scope = DataContent.class)
    public JAXBElement<String> createDataContentDescripcion(String value) {
        return new JAXBElement<String>(_DataEventDescripcion_QNAME, String.class, DataContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy/xsd", name = "urlPublica", scope = DataContent.class)
    public JAXBElement<String> createDataContentUrlPublica(String value) {
        return new JAXBElement<String>(_DataContentUrlPublica_QNAME, String.class, DataContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy", name = "date", scope = SearchEventByDate.class)
    public JAXBElement<XMLGregorianCalendar> createSearchEventByDateDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SearchEventByDateDate_QNAME, XMLGregorianCalendar.class, SearchEventByDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integracion.tsi2.fing.edu.uy", name = "eventID", scope = GetAlbumContents.class)
    public JAXBElement<String> createGetAlbumContentsEventID(String value) {
        return new JAXBElement<String>(_GetAlbumContentsEventID_QNAME, String.class, GetAlbumContents.class, value);
    }

}
