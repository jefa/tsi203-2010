
package partuzabook.integracion.ws.busqueda;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "Busqueda", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda")
public interface Busqueda {


    /**
     * 
     * @param total
     * @param maxEventos
     * @param nombre
     * @param evento
     */
    @WebMethod
    @RequestWrapper(localName = "searchByName", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchByNameRequest")
    @ResponseWrapper(localName = "searchByNameResponse", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchResponse")
    public void searchByName(
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre,
        @WebParam(name = "maxEventos", targetNamespace = "")
        int maxEventos,
        @WebParam(name = "evento", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<List<Evento>> evento,
        @WebParam(name = "total", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> total);

    /**
     * 
     * @param total
     * @param maxEventos
     * @param fecha
     * @param evento
     */
    @WebMethod
    @RequestWrapper(localName = "searchByDate", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchByDateRequest")
    @ResponseWrapper(localName = "searchByDateResponse", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchResponse")
    public void searchByDate(
        @WebParam(name = "fecha", targetNamespace = "")
        XMLGregorianCalendar fecha,
        @WebParam(name = "maxEventos", targetNamespace = "")
        int maxEventos,
        @WebParam(name = "evento", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<List<Evento>> evento,
        @WebParam(name = "total", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> total);

    /**
     * 
     * @param total
     * @param maxEventos
     * @param evento
     * @param fechaFrom
     * @param fechaTo
     */
    @WebMethod
    @RequestWrapper(localName = "searchBetweenDate", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchBetweenDateRequest")
    @ResponseWrapper(localName = "searchBetweenDateResponse", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchResponse")
    public void searchBetweenDate(
        @WebParam(name = "fechaFrom", targetNamespace = "")
        XMLGregorianCalendar fechaFrom,
        @WebParam(name = "fechaTo", targetNamespace = "")
        XMLGregorianCalendar fechaTo,
        @WebParam(name = "maxEventos", targetNamespace = "")
        int maxEventos,
        @WebParam(name = "evento", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<List<Evento>> evento,
        @WebParam(name = "total", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> total);

    /**
     * 
     * @param total
     * @param tipoEvento
     * @param evento
     */
    @WebMethod
    @RequestWrapper(localName = "searchByType", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchByTypeRequest")
    @ResponseWrapper(localName = "searchByTypeResponse", targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda", className = "partuzabook.integracion.ws.busqueda.SearchResponse")
    public void searchByType(
        @WebParam(name = "tipoEvento", targetNamespace = "")
        int tipoEvento,
        @WebParam(name = "evento", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<List<Evento>> evento,
        @WebParam(name = "total", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> total);

}
