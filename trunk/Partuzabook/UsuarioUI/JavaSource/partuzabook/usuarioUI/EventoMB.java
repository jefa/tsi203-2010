package partuzabook.usuarioUI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class EventoMB {

	private DatatypeEventSummary evento;
	private DatatypeContent content;
	private Integer eventId; 
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	
	public void setEvento(DatatypeEventSummary evento) {
		System.out.println("EventoMB.setEvento():: Event="+evento.evtId);
		this.evento = evento;
		
		if (this.evento != null && this.evento.contents.size() > 0){
			this.content = this.evento.contents.get(0);
		}
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
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			DatatypeEventSummary ev = service.findEventById(evtId);
			if (ev != null) {
				this.evento = ev;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

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

}