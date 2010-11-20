
package partuzabook.integracion.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the partuzabook.integracion.ws package. 
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

    private final static QName _SearchByName_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchByName");
    private final static QName _SearchByDateResponse_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchByDateResponse");
    private final static QName _SearchBetweenDate_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchBetweenDate");
    private final static QName _SearchBetweenDateResponse_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchBetweenDateResponse");
    private final static QName _SearchByNameResponse_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchByNameResponse");
    private final static QName _SearchByDate_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchByDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partuzabook.integracion.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchBetweenDateRequest }
     * 
     */
    public SearchBetweenDateRequest createSearchBetweenDateRequest() {
        return new SearchBetweenDateRequest();
    }

    /**
     * Create an instance of {@link SearchByNameRequest }
     * 
     */
    public SearchByNameRequest createSearchByNameRequest() {
        return new SearchByNameRequest();
    }

    /**
     * Create an instance of {@link SearchByDateRequest }
     * 
     */
    public SearchByDateRequest createSearchByDateRequest() {
        return new SearchByDateRequest();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link Evento }
     * 
     */
    public Evento createEvento() {
        return new Evento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByName")
    public JAXBElement<SearchByNameRequest> createSearchByName(SearchByNameRequest value) {
        return new JAXBElement<SearchByNameRequest>(_SearchByName_QNAME, SearchByNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByDateResponse")
    public JAXBElement<SearchResponse> createSearchByDateResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchByDateResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchBetweenDateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchBetweenDate")
    public JAXBElement<SearchBetweenDateRequest> createSearchBetweenDate(SearchBetweenDateRequest value) {
        return new JAXBElement<SearchBetweenDateRequest>(_SearchBetweenDate_QNAME, SearchBetweenDateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchBetweenDateResponse")
    public JAXBElement<SearchResponse> createSearchBetweenDateResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchBetweenDateResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByNameResponse")
    public JAXBElement<SearchResponse> createSearchByNameResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchByNameResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByDateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByDate")
    public JAXBElement<SearchByDateRequest> createSearchByDate(SearchByDateRequest value) {
        return new JAXBElement<SearchByDateRequest>(_SearchByDate_QNAME, SearchByDateRequest.class, null, value);
    }

}
