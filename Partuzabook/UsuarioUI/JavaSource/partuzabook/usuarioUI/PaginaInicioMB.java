package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class PaginaInicioMB {

	// General
	private List<DatatypeEventSummary> eventosRecientes;
	private List<DatatypeContent> fotosMejorCalificadas;
	private int cantFotosMejorCalificadas;
	private List<DatatypeContent> fotosMasComentadas;
	private int cantFotosMasComentadas;
	private List<DatatypeMostTagged> fotosMasTaggeadas;
	private int cantFotosMasTaggeadas;
	
	// Para el usuario logueado
	private String username;
	private List<DatatypeEventSummary> misEventosRecientes;
	private List<DatatypeNotification> misNotificaciones;
	private List<DatatypeNotification> misNotificacionesNoLeidas;
	
	
	public PaginaInicioMB(){
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		this.username = (String) session.getAttribute("username");		
	}
	
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

	public List<DatatypeEventSummary> getEventosRecientes() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			eventosRecientes = service.getSummaryEvents(10);
			return eventosRecientes;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setEventosRecientes(ArrayList<DatatypeEventSummary> eventosRecientes) {
		this.eventosRecientes = eventosRecientes;
	}
	
	public List<DatatypeContent> getFotosMejorCalificadas() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.fotosMejorCalificadas = service.getBestQualifiedPictures(10);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return this.fotosMejorCalificadas;
	}

	public void setFotosMejorCalificadas(ArrayList<DatatypeContent> list) {
		this.fotosMejorCalificadas = list;
	}
	
	public int getCantFotosMejorCalificadas(){
		int cant = 0;
		if (this.fotosMejorCalificadas != null) {
			cant = this.fotosMejorCalificadas.size();
		}
		if (cant > 4) {
			this.cantFotosMejorCalificadas = 4;
		}
		return this.cantFotosMejorCalificadas;
	}

	public void setCantFotosMejorCalificadas(int cant){
		this.cantFotosMejorCalificadas = cant;
	}
	
	public List<DatatypeContent> getFotosMasComentadas() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.fotosMasComentadas = service.getMostCommentedPictures(10);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return this.fotosMasComentadas;	
	}

	public void setFotosMasComentadas(ArrayList<DatatypeContent> list) {
		this.fotosMasComentadas = list;
	}

	public int getCantFotosMasComentadas(){
		int cant = 0;
		if (this.fotosMasComentadas != null) {
			cant = this.fotosMasComentadas.size();
		}
		if (cant > 4) {
			this.cantFotosMasComentadas = 4;
		}
		return this.cantFotosMasComentadas;
	}

	public void setCantFotosMasComentadas(int cant){
		this.cantFotosMasComentadas = cant;
	}

	public List<DatatypeMostTagged> getFotosMasTaggeadas() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.fotosMasTaggeadas = service.getMostTagged(10);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return this.fotosMasTaggeadas;	
	}

	public void setFotosMasTaggeadas(ArrayList<DatatypeMostTagged> list) {
		this.fotosMasTaggeadas = list;
	}

	public int getCantFotosMasTaggeadas(){
		int cant = 0;
		if (this.fotosMasTaggeadas != null) {
			cant = this.fotosMasTaggeadas.size();
		}
		if (cant > 4) {
			this.cantFotosMasTaggeadas = 4;
		}
		return this.cantFotosMasTaggeadas;
	}

	public void setCantFotosMasTaggeadas(int cant){
		this.cantFotosMasTaggeadas = cant;
	}
	
	public List<DatatypeEventSummary> getMisEventosRecientes() {
		try {
			Context ctx = getContext();
			ServicesUserRemote service = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			this.misEventosRecientes = service.getEventSummaryByUser(username);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return this.misEventosRecientes;	
	}

	public void setMisEventosRecientes(ArrayList<DatatypeEventSummary> list) {
		this.misEventosRecientes = list;
	}
	
	public List<DatatypeNotification> getMisNotificaciones() {
		try {
			Context ctx = getContext();
			ServicesUserRemote service = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");	
			this.misNotificaciones = service.getUpdateNotifications(username);
		} catch(NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return this.misNotificaciones;	
	}

	public void setMisNotificaciones(ArrayList<DatatypeNotification> list) {
		this.misNotificaciones = list;
	}
	
	public List<DatatypeNotification> getMisNotificacionesNoLeidas() {
		try {
			Context ctx = getContext();
			ServicesUserRemote service = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");	
			this.misNotificacionesNoLeidas = service.getUpdateNotificationsUnread(username);
		} catch(NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return this.misNotificacionesNoLeidas;	
	}

	public void setMisNotificacionesNoLeidas(ArrayList<DatatypeNotification> list) {
		this.misNotificacionesNoLeidas = list;
	}

}
