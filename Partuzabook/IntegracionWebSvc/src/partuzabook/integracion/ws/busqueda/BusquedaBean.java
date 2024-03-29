package partuzabook.integracion.ws.busqueda;
 
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.utils.Parameters;
 
@Stateless
@WebService(
   name="Busqueda",
   targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda",
   serviceName = "BusquedaService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class BusquedaBean {
 
	@WebMethod
	public SearchResponse searchByName(SearchByNameRequest request) {
		
		SearchResponse sr = new SearchResponse();
		
		if (request.getNombre() == null /*|| "".equals(request.getNombre())*/) {
			System.out.println("BusquedaBean.searchByName(): Nombre no puede ser vacio");
			return sr;
		}

		try {
			List<DatatypeEventSummary> eventResults = getServicesEvent().searchForEventByName(
					request.getNombre(), request.getMaxEventos());
			
			if (eventResults == null) {
				System.out.println("BusquedaBean.searchByName(): No se han encontrado resultados");
				return sr;
			}

			sr.evento = translate(eventResults);
			sr.total = new Integer(eventResults.size());
			
		} catch (NamingException e) {
			System.out.println("BusquedaBean.searchByName(): Error haciendo la busqueda: "+e.getMessage());
			e.printStackTrace();
		}
		
		return sr;
	}

	@WebMethod
	public SearchResponse searchByDate(SearchByDateRequest request){
		
		SearchResponse sr = new SearchResponse();
		
		try {
			
			GregorianCalendar c = request.getFecha().toGregorianCalendar();
			Date dateFrom = new Date(c.getTimeInMillis());
			
			List<DatatypeEventSummary> eventResults = getServicesEvent().searchForEventByDate(
					dateFrom, request.getMaxEventos());
			
			if (eventResults == null) {
				System.out.println("BusquedaBean.searchByDate(): No se han encontrado resultados");
				return sr;
			}

			sr.evento = translate(eventResults);
			sr.total = new Integer(eventResults.size());
			
		} catch (NamingException e) {
			System.out.println("BusquedaBean.searchByDate(): Error haciendo la busqueda: "+e.getMessage());
			e.printStackTrace();
		}
		
		return sr;
		
	}
	
	@WebMethod
	public SearchResponse searchBetweenDate(SearchBetweenDateRequest request){

		SearchResponse sr = new SearchResponse();
		
		try {
			
			GregorianCalendar c = request.getFechaFrom().toGregorianCalendar();
			Date dateFrom = new Date(c.getTimeInMillis());
			c = request.getFechaTo().toGregorianCalendar();
			Date dateTo = new Date(c.getTimeInMillis());
			
			List<DatatypeEventSummary> eventResults = getServicesEvent().searchForEventBetweenDates(
					dateFrom, dateTo, request.getMaxEventos());
			
			if (eventResults == null) {
				System.out.println("BusquedaBean.searchBetweenDates(): No se han encontrado resultados");
				return sr;
			}

			sr.evento = translate(eventResults);
			sr.total = new Integer(eventResults.size());
			
		} catch (NamingException e) {
			System.out.println("BusquedaBean.searchBetweenDates(): Error haciendo la busqueda: "+e.getMessage());
			e.printStackTrace();
		}
		
		return sr;
				
	}

	@WebMethod
	public SearchResponse searchByType(SearchByTypeRequest request){

		SearchResponse sr = new SearchResponse();

		try {
			List<DatatypeEventSummary> eventResults = getServicesEvent().filterEventsByEvtCategory(
					request.getTipoEvento(), 100);
			
			if (eventResults == null) {
				System.out.println("BusquedaBean.searchByType(): No se han encontrado resultados");
				return sr;
			}

			sr.evento = translate(eventResults);
			sr.total = new Integer(eventResults.size());
			
		} catch (NamingException e) {
			System.out.println("BusquedaBean.searchByType(): Error haciendo la busqueda: "+e.getMessage());
			e.printStackTrace();
		}
		
		return sr;
				
	}

	private ServicesEventRemote getServicesEvent() throws NamingException {
		Context ctx = getContext();
		ServicesEventRemote srvEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
		return srvEvent;
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	private List<Evento> translate(List<DatatypeEventSummary> eventsSummary){
		List<Evento> translatedCollection = new ArrayList<Evento>();
		int i=0;
		for (Iterator iterator = eventsSummary.iterator(); iterator.hasNext();) {
			DatatypeEventSummary datatypeEventSummary = (DatatypeEventSummary) iterator.next();
			System.out.println("BusquedaBean.searchByName(): Encotnrado evento "+datatypeEventSummary.getEvtName());
			Evento evento = new Evento();
			evento.setDescripcion(datatypeEventSummary.getDescription());
			evento.setDireccion(datatypeEventSummary.getAddress());
			
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(datatypeEventSummary.getDate().getTime());
			XMLGregorianCalendarImpl gCal = new XMLGregorianCalendarImpl(gc);
				
			
			evento.setFecha(gCal);
			evento.setIdEvento(datatypeEventSummary.getEvtId());
			evento.setNombre(datatypeEventSummary.getEvtName());
			//evento.setTipo();
			evento.setUrlCover("http://"+Parameters.PARTUZABOOK_IP+"/UsuarioUI/ContentFeeder?id="+datatypeEventSummary.getCoverId());
			translatedCollection.add(evento);
			i++;
		}
		return translatedCollection;
	}
}
 
