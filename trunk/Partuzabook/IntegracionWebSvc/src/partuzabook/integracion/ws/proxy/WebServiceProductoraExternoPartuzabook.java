package partuzabook.integracion.ws.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.busqueda.Busqueda;
import partuzabook.integracion.ws.busqueda.Evento;
import partuzabook.integracion.ws.contenido.Contenido;
import partuzabook.integracion.ws.contenido.Contenido_Type;
import partuzabook.integracion.ws.translators.TranslatorCollection;
import partuzabook.utils.Parameters;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * Session Bean implementation class WebServiceProductoraExterno
 */
@Stateless
public class WebServiceProductoraExternoPartuzabook implements WebServiceProductoraExternoRemote {

    /**
     * Default constructor. 
     */
    public WebServiceProductoraExternoPartuzabook() {
        // TODO Auto-generated constructor stub
    }

    private Busqueda getBusquedaService() {
		try {
			Service service = Service.create(
					new URL("http://"+Parameters.PARTUZABOOK_IP+"/PartuzabookEAR-IntegracionWebSvc/BusquedaBean?wsdl"),
					new QName("http://edu.tsi2.ws/integracion/ws/busqueda", "BusquedaService")
					
			);
			return service.getPort(Busqueda.class);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    private Contenido getContenidoService() {
		try {
			Service service = Service.create(
					new URL("http://"+Parameters.PARTUZABOOK_IP+"/PartuzabookEAR-IntegracionWebSvc/ContenidoBean?wsdl"),
					new QName("http://edu.tsi2.ws/integracion/ws/contenido", "ContenidoService")
					
			);
			return service.getPort(Contenido.class);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	@Override
	public List<DatatypeEventSummary> searchEventByName(String name) {
		Busqueda service = getBusquedaService();
		
		Holder<List<Evento>> holderEventos = new Holder<List<Evento>>();
		holderEventos.value = new ArrayList<Evento>();
		Holder<Integer> holderTotal = new Holder<Integer>();
		holderTotal.value = new Integer(0);
		service.searchByName(name, 100, holderEventos, holderTotal);
		return TranslatorCollection.translateFromEvento(holderEventos.value);
	}

	@Override
	public List<DatatypeEventSummary> searchEventByDate(Date date) {
		Busqueda service = getBusquedaService();
		
		Holder<List<Evento>> holderEventos = new Holder<List<Evento>>();
		holderEventos.value = new ArrayList<Evento>();
		Holder<Integer> holderTotal = new Holder<Integer>();
		holderTotal.value = new Integer(0);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		XMLGregorianCalendar g = new XMLGregorianCalendarImpl(gc);
		service.searchByDate(g, 100, holderEventos, holderTotal);
		return TranslatorCollection.translateFromEvento(holderEventos.value);
	}

	@Override
	public List<DatatypeEventSummary> searchEventBetweenDates(Date before, Date after) {
		Busqueda service = getBusquedaService();
		
		Holder<List<Evento>> holderEventos = new Holder<List<Evento>>();
		holderEventos.value = new ArrayList<Evento>();
		Holder<Integer> holderTotal = new Holder<Integer>();
		holderTotal.value = new Integer(0);
		XMLGregorianCalendar gAfter = new XMLGregorianCalendarImpl();
		gAfter.setMillisecond((int) after.getTime());
		XMLGregorianCalendar gBefore = new XMLGregorianCalendarImpl();
		gBefore.setMillisecond((int) before.getTime());
		service.searchBetweenDate(gAfter, gBefore, 100, holderEventos, holderTotal);
		return TranslatorCollection.translateFromEvento(holderEventos.value);
	}

	@Override
	public List<DatatypeEventSummary> searchEventByType(Integer type) {
		Busqueda service = getBusquedaService();
		
		Holder<List<Evento>> holderEventos = new Holder<List<Evento>>();
		holderEventos.value = new ArrayList<Evento>();
		Holder<Integer> holderTotal = new Holder<Integer>();
		holderTotal.value = new Integer(0);
		service.searchByType(type, holderEventos, holderTotal);
		return TranslatorCollection.translateFromEvento(holderEventos.value);
	}

	@Override
	public List<DatatypeContent> getAlbumContents(String eventID) {
		Contenido service = getContenidoService();
		
		Holder<List<Contenido_Type>> holderContenidos = new Holder<List<Contenido_Type>>();
		holderContenidos.value = new ArrayList<Contenido_Type>();
		Holder<Integer> holderTotal = new Holder<Integer>();
		holderTotal.value = new Integer(0);
		service.getAlbumContents(Integer.parseInt(eventID), holderContenidos, holderTotal);
		return TranslatorCollection.translateFromContenido_Type(holderContenidos.value);
	}

}
