package partuzabook.integracion.ws;
 
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
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.sun.xml.bind.v2.TODO;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
 
@Stateless
@WebService(
   name="Busqueda",
   targetNamespace = "http://edu.tsi2.ws/integracion/ws/busqueda",
   serviceName = "BusquedaService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class BusquedaBean {
 
	@WebMethod
	public SearchResponse searchByName(SearchRequest request) {
		
		SearchResponse sr = new SearchResponse();
		sr.total = new Integer(10);
		sr.evento = new ArrayList<Evento>();
		
		if (request.getNombre() == null /*|| "".equals(request.getNombre())*/) {
			System.out.println("BusquedaBean.searchByName(): Nombre no puede ser vacio");
			return sr;
		}

		try {
			Context ctx = getContext();
			ServicesEventRemote srvEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
			List<DatatypeEventSummary> eventResults = srvEvent.searchForEventByName(
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
			XMLGregorianCalendarImpl gCal = new XMLGregorianCalendarImpl();
			// @TODO: setear todos los campos de fecha en el xml field
			evento.setFecha(gCal);
			evento.setIdEvento(datatypeEventSummary.getEvtId());
			evento.setNombre(datatypeEventSummary.getEvtName());
			//evento.setTipo();
			//evento.setUrlCover();
			translatedCollection.add(evento);
			i++;
		}
		return translatedCollection;
	}


//public List<DatatypeEventSummary> searchForEventByDate(Date date, int maxEvents)
//public List<DatatypeEventSummary> searchForEventBetweenDates(Date after, Date before, int maxEvents);
   
}
 
