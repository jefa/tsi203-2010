
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
    private final static QName _SearchByNameResponse_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "searchByNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partuzabook.integracion.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link Evento }
     * 
     */
    public Evento createEvento() {
        return new Evento();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByName")
    public JAXBElement<SearchRequest> createSearchByName(SearchRequest value) {
        return new JAXBElement<SearchRequest>(_SearchByName_QNAME, SearchRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/busqueda", name = "searchByNameResponse")
    public JAXBElement<SearchResponse> createSearchByNameResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchByNameResponse_QNAME, SearchResponse.class, null, value);
    }

}
