package partuzabook.usuarioUI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.productora_web.DataEvent;
import partuzabook.integracion.ws.productora_web.IntegracionWSServicePortType;
import partuzabook.integracion.ws.proxy.WebServiceProductoraExternoRemote;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class SearchEventMB {

	private static int EVENTS_PER_PAGE = 6;

	// Search by Event Name
	private String eventNameSearched = "";

	// Search by Event Date
	private Date eventDateSearched;

	// Use filters
	private String eventFilter = "";
	private static String SEE_ALL = "Ver todos los eventos";
	private static String SEE_PAST = "Ver eventos que ya ocurrieron";
	private static String SEE_NEXT = "Ver eventos futuros";
	private String[] options = { SEE_ALL, SEE_PAST, SEE_NEXT };
	private static String ANIV = "Aniversario";
	private static String CASAM = "Casamiento";
	private static String CUMPLE = "Cumpleaños de quince";
	private static String OTROS = "Otros";
	private String[] optionsEvtCateg = { ANIV, CASAM, CUMPLE, OTROS };

	private List<DatatypeEventSummary> eventResults;
	private List<DatatypeEventSummary> externalEventResults;

	private String mensaje = "";
	private String mensajeValidacionNombre = "";
	private String mensajeValidacionFecha = "";

	private List<String> filterByDateOptions;
	private List<String> filterByCategOptions;

	private int paginaActual = 0;
	private ArrayList<Integer> paginas = null;

	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

	public String getEventNameSearched() {
		return this.eventNameSearched;
	}

	public void setEventNameSearched(String eventName) {
		this.eventNameSearched = eventName;

	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String msj) {
		this.mensaje = msj;
	}

	public String getMensajeValidacionNombre() {
		return this.mensajeValidacionNombre;
	}

	public void setMensajeValidacionNombre(String msj) {
		this.mensajeValidacionNombre = msj;
	}

	public String getMensajeValidacionFecha() {
		return this.mensajeValidacionFecha;
	}

	public void setMensajeValidacionFecha(String msj) {
		this.mensajeValidacionFecha = msj;
	}

	public Date getEventDateSearched() {
		return this.eventDateSearched;
	}

	public void setEventDateSearched(Date eventDate) {
		this.eventDateSearched = eventDate;
	}

	public List<DatatypeEventSummary> getEventResults() {
		if (this.eventResults == null || this.eventResults.size() == 0) {
			return null;
		}
		return this.eventResults.subList(
				paginaActual * EVENTS_PER_PAGE,
				Math.min((paginaActual + 1) * EVENTS_PER_PAGE,
						eventResults.size()));
	}

	public void setEventResults(ArrayList<DatatypeEventSummary> events) {
		this.eventResults = events;
	}

	public List<String> getFilterByDateOptions() {
		if (this.filterByDateOptions == null) {
			this.filterByDateOptions = new ArrayList<String>();
			for (int i = 0; i < this.options.length; i++) {
				this.filterByDateOptions.add(options[i]);
			}
		}
		return this.filterByDateOptions;
	}

	public void setFilterByDateOptions(List<String> list) {
		this.filterByDateOptions = list;
	}

	public List<String> getFilterByCategOptions() {
		if (this.filterByCategOptions == null) {
			this.filterByCategOptions = new ArrayList<String>();
			for (int i = 0; i < this.optionsEvtCateg.length; i++) {
				this.filterByCategOptions.add(optionsEvtCateg[i]);
			}
		}
		return this.filterByCategOptions;
	}

	public void setFilterByCategOptions(List<String> list) {
		this.filterByCategOptions = list;
	}

	public String getEventFilter() {
		return this.eventFilter;
	}

	public void setEventFilter(String filter) {
		this.eventFilter = filter;
	}

	public void limpiarBusqueda() {
		this.mensajeValidacionNombre = "";
		this.mensajeValidacionFecha = "";
		this.eventResults = null;
		this.externalEventResults = null;
	}

	public List<DatatypeEventSummary> searchEventsByName() {
		this.mensajeValidacionNombre = "";
		this.mensajeValidacionFecha = "";
		try {
			if (this.eventNameSearched == null
					|| this.eventNameSearched.equals("")) {
				this.eventResults = null;
				this.mensajeValidacionNombre = "Ingrese un nombre para buscar";
				return this.eventResults;
			}
			this.mensajeValidacionNombre = "";
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx
					.lookup("PartuzabookEAR/ServicesEvent/remote");
			this.eventResults = service.searchForEventByName(eventNameSearched,100);
			this.setExternalEventResults(searchEventsByNameExterno(eventNameSearched));
			
			if (this.eventResults == null || this.eventResults.size() == 0) {
				this.mensaje = "No se han encontrado resultados";
				this.getTotalPaginas();
				return this.eventResults;
			}

			paginas = new ArrayList<Integer>();

			for (int i = 0; i < this.getTotalPaginas(); i++) {
				paginas.add(new Integer(i));
			}

			this.paginaActual = 0;

			this.eventDateSearched = null;
			this.eventFilter = "";
			this.eventNameSearched = "";

			return this.eventResults.subList(
					paginaActual * EVENTS_PER_PAGE,
					Math.min((paginaActual + 1) * EVENTS_PER_PAGE,
							eventResults.size()));
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DatatypeEventSummary> searchEventsByDate() {
		this.mensajeValidacionNombre = "";
		this.mensajeValidacionFecha = "";
		this.eventNameSearched = "";
		try {
			if (this.eventDateSearched == null) {
				this.eventResults = null;
				this.mensajeValidacionFecha = "Ingrese una fecha para buscar";
				return this.eventResults;
			}
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx
					.lookup("PartuzabookEAR/ServicesEvent/remote");
			this.eventResults = service.searchForEventByDate(eventDateSearched,10);
			this.setExternalEventResults(searchEventsByDateExterno(eventDateSearched));
			
			if (this.eventResults == null || this.eventResults.size() == 0) {
				this.mensaje = "No se han encontrado resultados";
				this.getTotalPaginas();
				return this.eventResults;
			}

			paginas = new ArrayList<Integer>();

			for (int i = 0; i < this.getTotalPaginas(); i++) {
				paginas.add(new Integer(i));
			}
			this.paginaActual = 0;

			this.eventDateSearched = null;
			this.eventFilter = "";
			this.eventNameSearched = "";
			return this.eventResults.subList(
					paginaActual * EVENTS_PER_PAGE,
					Math.min((paginaActual + 1) * EVENTS_PER_PAGE,
							eventResults.size()));
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DatatypeEventSummary> filterEvents() {
		this.eventDateSearched = null;
		this.eventNameSearched = "";
		int maxEvents = 10;
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx
					.lookup("PartuzabookEAR/ServicesEvent/remote");
			if (this.eventFilter.equals(SEE_ALL)) {
				this.eventResults = service.filterAllEvents(maxEvents);
			} else if (this.eventFilter.equals(SEE_PAST)) {
				this.eventResults = service.filterPastEvents(maxEvents);
			} else if (this.eventFilter.equals(SEE_NEXT)) {
				this.eventResults = service.filterNextEvents(maxEvents);
			}
			if (this.eventResults == null || this.eventResults.size() == 0) {
				this.mensaje = "No se han encontrado resultados";
				this.getTotalPaginas();
				return this.eventResults;
			}

			paginas = new ArrayList<Integer>();

			for (int i = 0; i < this.getTotalPaginas(); i++) {
				paginas.add(new Integer(i));
			}
			this.paginaActual = 0;

			this.eventDateSearched = null;
			this.eventFilter = "";
			this.eventNameSearched = "";
			return this.eventResults.subList(
					paginaActual * EVENTS_PER_PAGE,
					Math.min((paginaActual + 1) * EVENTS_PER_PAGE,
							eventResults.size()));
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private int getEvtCategoryPos(String evtCategory) {
		for (int i = 0; i < optionsEvtCateg.length; i++) {
			if (evtCategory.equals(optionsEvtCateg[i])) {
				return i;
			}
		}
		return -1;
	}

	public List<DatatypeEventSummary> filterEventsByEvtCategory() {
		this.eventDateSearched = null;
		this.eventNameSearched = "";
		int maxEvents = 10;
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx
					.lookup("PartuzabookEAR/ServicesEvent/remote");

			
			this.eventResults = service.filterEventsByEvtCategory(getEvtCategoryPos(eventFilter), maxEvents);
			this.setExternalEventResults(searchEventsByTypeExterno(getEvtCategoryPos(eventFilter)));			
			if (this.eventResults == null || this.eventResults.size() == 0) {
				this.mensaje = "No se han encontrado resultados";
				this.getTotalPaginas();
				return this.eventResults;
			}
			

			paginas = new ArrayList<Integer>();

			for (int i = 0; i < this.getTotalPaginas(); i++) {
				paginas.add(new Integer(i));
			}
			this.paginaActual = 0;

			this.eventDateSearched = null;
			this.eventFilter = "";
			this.eventNameSearched = "";
			return this.eventResults.subList(
					paginaActual * EVENTS_PER_PAGE,
					Math.min((paginaActual + 1) * EVENTS_PER_PAGE,
							eventResults.size()));
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Integer> getPaginas() {
		return paginas;
	}

	public void setPaginas(ArrayList<Integer> paginas) {
		this.paginas = paginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public int getTotalPaginas() {
		if (eventResults == null || eventResults.size() == 0) {
			return 1;
		}
		if ((eventResults.size() % EVENTS_PER_PAGE) == 0) {
			return eventResults.size() / EVENTS_PER_PAGE;
		}
		return eventResults.size() / EVENTS_PER_PAGE + 1;
	}

	public List<DatatypeEventSummary> searchEventsByNameExterno(String name) {
		try {
			List<DatatypeEventSummary> partuzabookResults =
				getPartuzabookProxy().searchEventByName(name);
//			List<DatatypeEventSummary> productoraWebResults =
//				getProductoraWebProxy().searchEventByName(name);
//			partuzabookResults.addAll(productoraWebResults);
			return partuzabookResults;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DatatypeEventSummary> searchEventsByDateExterno(Date date) {
		try {
			List<DatatypeEventSummary> partuzabookResults =
				getPartuzabookProxy().searchEventByDate(date);
//			List<DatatypeEventSummary> productoraWebResults =
//				getProductoraWebProxy().searchEventByDate(date);
//			partuzabookResults.addAll(productoraWebResults);
			return partuzabookResults;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DatatypeEventSummary> searchEventsByTypeExterno(Integer type) {
		try {
			List<DatatypeEventSummary> partuzabookResults =
				getPartuzabookProxy().searchEventByType(type);
//			List<DatatypeEventSummary> productoraWebResults =
//				getProductoraWebProxy().searchEventByType(type);
//			partuzabookResults.addAll(productoraWebResults);
			return partuzabookResults;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private WebServiceProductoraExternoRemote getPartuzabookProxy() {
		try {
			Context ctx = getContext();
			return (WebServiceProductoraExternoRemote) ctx
				.lookup("PartuzabookEAR/WebServiceProductoraExternoPartuzabook/remote");
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private WebServiceProductoraExternoRemote getProductoraWebProxy() {
		try {
			Context ctx = getContext();
			return (WebServiceProductoraExternoRemote) ctx
				.lookup("PartuzabookEAR/WebServiceProductoraExternoProductoraWeb/remote");
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setExternalEventResults(List<DatatypeEventSummary> externalEventResults) {
		this.externalEventResults = externalEventResults;
	}

	public List<DatatypeEventSummary> getExternalEventResults() {
		return externalEventResults;
	}

}