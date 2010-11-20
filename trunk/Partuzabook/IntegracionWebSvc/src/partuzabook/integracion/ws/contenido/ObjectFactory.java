
package partuzabook.integracion.ws.contenido;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the partuzabook.integracion.ws.contenido package. 
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

    private final static QName _GetAlbumContentsResponse_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/contenido", "getAlbumContentsResponse");
    private final static QName _GetAlbumContents_QNAME = new QName("http://edu.tsi2.ws/integracion/ws/contenido", "getAlbumContents");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partuzabook.integracion.ws.contenido
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ContentResponse }
     * 
     */
    public ContentResponse createContentResponse() {
        return new ContentResponse();
    }

    /**
     * Create an instance of {@link ContentRequest }
     * 
     */
    public ContentRequest createContentRequest() {
        return new ContentRequest();
    }

    /**
     * Create an instance of {@link Contenido_Type }
     * 
     */
    public Contenido_Type createContenido_Type() {
        return new Contenido_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/contenido", name = "getAlbumContentsResponse")
    public JAXBElement<ContentResponse> createGetAlbumContentsResponse(ContentResponse value) {
        return new JAXBElement<ContentResponse>(_GetAlbumContentsResponse_QNAME, ContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://edu.tsi2.ws/integracion/ws/contenido", name = "getAlbumContents")
    public JAXBElement<ContentRequest> createGetAlbumContents(ContentRequest value) {
        return new JAXBElement<ContentRequest>(_GetAlbumContents_QNAME, ContentRequest.class, null, value);
    }

}
