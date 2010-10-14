package partuzabook.usuarioUI;

import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesMultimediaRemote;

public class EventoMB {

	private DatatypeEventSummary evento;
	private DatatypeContent content;
	private Integer eventId; 
	private String userName;

	private ServicesMultimediaRemote servicesMultimedia;
	private ServicesEventRemote servicesEvent;
	private boolean validUserForContext;	

	public boolean isValidUserForContext() {
		return validUserForContext;
	}

	/*public void setValidUserForContext(boolean validUserForContext) {
		this.validUserForContext = validUserForContext;
	}*/

	public void setUserName(String userName) {
		System.out.println("EventoMB.setUserName(): "+userName);
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	
	public void setEvento(DatatypeEventSummary evento) {
		System.out.println("EventoMB.setEvento():: Event="+evento.evtId);
		//this.evento = evento;
		setEventId(evento.evtId);
		this.validUserForContext = calcValidUserForContent();
		System.out.println("EventoMB.setEvento():: validUserForContext="+this.validUserForContext);
	}
	
	public DatatypeEventSummary getEvento() {
		return this.evento;
	}
	
	public DatatypeContent getContent(){
		return this.content;
	}
	
	public void setContent(DatatypeContent con){
		System.out.println("EventoMB.setContent():: new Content="+con);
		this.content = con;
	}

	public Integer getEventId() {
		return this.eventId; 
	}
	
	public void setEventId(Integer evtId){
		this.eventId = evtId;
		// Also set the Event
		try {
			DatatypeEventSummary ev = getServicesEevnt().findEventById(evtId);
			if (ev != null) {
				this.evento = ev;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private ServicesEventRemote getServicesEevnt() throws NamingException {
		if (servicesEvent == null) {
			Context ctx = getContext();
			this.servicesEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
		}
		return servicesEvent;
	}
	
	private ServicesMultimediaRemote getServicesMultimedia() {
		try {
			if (servicesMultimedia == null){
				Context ctx = getContext();
				this.servicesMultimedia = (ServicesMultimediaRemote) ctx.lookup("PartuzabookEAR/ServicesMultimedia/remote");
			}
			return servicesMultimedia;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer getPage(){
        final Integer index = this.evento.contents.indexOf(this.content);
        return index / 5 + 1;
    }
	
	/*public DatatypeContent getContentAtPosition(){
		if (evento == null)
			return null;
		if (evento.contents.size() <= 0)
			return null;
		return evento.contents.get(position);
	}*/

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
	
/*	public List<DatatypeEventSummary> getEventosRecientes() {

		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
			eventosRecientes = service.get.getSummaryEvents(10, 5);
			return eventosRecientes;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setEventosRecientes(
			ArrayList<DatatypeEventSummary> eventosRecientes) {
		this.eventosRecientes = eventosRecientes;
	}*/
	
	private Boolean calcValidUserForContent() {
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String username = (String) session.getAttribute("username");		
		return getServicesMultimedia().isUserRelatedToEvent(this.evento.evtId, username);
	}
}