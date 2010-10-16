package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class SearchEventMB {

	// Search by Event Name
	private String eventNameSearched ="";

	//Search by Event Date
	private Date eventDateSearched;
	
	//Use filters
	private String eventFilter = "";
	private static String SEE_ALL = "See All Events";
	private static String SEE_PAST = "Only Past Events";
	private static String SEE_NEXT = "Only Upcoming Events";
	private String[] options = {SEE_ALL, SEE_PAST, SEE_NEXT};			
	
	private List<DatatypeEventSummary> eventResults;
	
	private String mensaje ="";

	private List<String> filterByDateOptions;
	
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

	public Date getEventDateSearched() {
		return this.eventDateSearched;
	}

	public void setEventDateSearched(Date eventDate) {
		this.eventDateSearched = eventDate;
	}

	
	public List<DatatypeEventSummary> getEventResults() {
		return this.eventResults;
	}

	public void setEventResults(ArrayList<DatatypeEventSummary> events) {
		this.eventResults = events;
	}

	public List<String> getFilterByDateOptions(){
		if (this.filterByDateOptions == null) {
			this.filterByDateOptions = new ArrayList<String>();
			for(int i = 0; i<this.options.length; i++) {
				this.filterByDateOptions.add(options[i]);
			}
		} 
		return this.filterByDateOptions;
	}

	public void setFilterByDateOptions(List<String> list) {
		this.filterByDateOptions = list;
	}

	public String getEventFilter(){
		return this.eventFilter;
	}
	
	public void setEventFilter(String filter){
		this.eventFilter = filter;
	}
	
	public List<DatatypeEventSummary> searchEventsByName() {
		this.eventDateSearched = null;
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.eventResults = service.searchForEventByName(eventNameSearched, 10);
			if (this.eventResults == null){
				this.mensaje = "No se han encontrado resultados";
			}
			return this.eventResults;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<DatatypeEventSummary> searchEventsByDate() {
		this.eventNameSearched = "";
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.eventResults = service.searchForEventByDate(eventDateSearched, 10);
			if (this.eventResults == null){
				this.mensaje = "No se han encontrado resultados";
			}
			return this.eventResults;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} 
	}

	public List<DatatypeEventSummary> filterEvents() {
		this.eventDateSearched = null;
		this.eventNameSearched = "";
		int maxEvents = 10; //TODO Quien se encarga de la paginacion?
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			if (this.eventFilter.equals(SEE_ALL)){
				this.eventResults = service.filterAllEvents(maxEvents);
			} else if (this.eventFilter.equals(SEE_PAST)) {
				this.eventResults = service.filterPastEvents(maxEvents);
			} else if (this.eventFilter.equals(SEE_NEXT)){
				this.eventResults = service.filterNextEvents(maxEvents);				
			}			
			if (this.eventResults == null){
				this.mensaje = "No se han encontrado resultados";
			}
			return this.eventResults;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} 
	}

}