package partuzabook.superusuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class PaginaInicioMB {

	// General
	private boolean isUserLogged;
	private List<DatatypeEventSummary> eventosRecientes;
	private List<DatatypeContent> fotosMejorCalificadas;
	private int cantFotosMejorCalificadas;
	private List<DatatypeContent> fotosMasComentadas;
	private int cantFotosMasComentadas;
	private List<DatatypeMostTagged> usuariosMasTaggeados;
	private int cantUsuariosMasTaggeados;
	private List<DatatypeAlbum> albumsRecientes;
	
	// Para el usuario logueado
	private String username;
	private List<DatatypeEventSummary> misEventosRecientes;
	private List<DatatypeNotification> misNotificaciones;
	private List<DatatypeNotification> misNotificacionesNoLeidas;
	
	
	public PaginaInicioMB(){
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		this.username = SessionUtils.getUsername();
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
			this.fotosMejorCalificadas = service.getBestRankedContent(getCantFotosMejorCalificadas());
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
		cantFotosMejorCalificadas = 8;
		if (this.fotosMejorCalificadas != null) {
			cantFotosMejorCalificadas = this.fotosMejorCalificadas.size();
		}
		if (cantFotosMejorCalificadas > 8) {
			cantFotosMejorCalificadas = 8;
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
			this.fotosMasComentadas = service.getMostCommentedContent(getCantFotosMasComentadas());
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
		cantFotosMasComentadas = 8;
		if (this.fotosMasComentadas != null) {
			cantFotosMasComentadas = this.fotosMasComentadas.size();
		}
		if (cantFotosMasComentadas > 8) {
			this.cantFotosMasComentadas = 8;
		}
		return this.cantFotosMasComentadas;
	}

	public void setCantFotosMasComentadas(int cant){
		this.cantFotosMasComentadas = cant;
	}

	public List<DatatypeMostTagged> getUsuariosMasTaggeados() {
		try {
			Context ctx = getContext();
			ServicesUserRemote service = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");	
			this.usuariosMasTaggeados = service.getMostTagged(getCantUsuariosMasTaggeados());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return this.usuariosMasTaggeados;	
	}

	public void setUsuariosMasTaggeados(ArrayList<DatatypeMostTagged> list) {
		this.usuariosMasTaggeados = list;
	}

	public int getCantUsuariosMasTaggeados(){
		cantUsuariosMasTaggeados = 8;
		if (this.usuariosMasTaggeados != null) {
			cantUsuariosMasTaggeados = this.usuariosMasTaggeados.size();
		}
		if (cantUsuariosMasTaggeados > 8) {
			this.cantUsuariosMasTaggeados = 8;
		}
		return this.cantUsuariosMasTaggeados;
	}

	public void setCantUsuariosMasTaggeados(int cant){
		this.cantUsuariosMasTaggeados = cant;
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


	public boolean getIsUserLogged(){
		this.isUserLogged = checkIfUserLogged();
		return this.isUserLogged;
	}
	
	public void setIsUserLogged(boolean logged){
		this.isUserLogged = logged;
	}
	
	public boolean checkIfUserLogged(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String user = SessionUtils.getUsername();		
		return (user != null );
	}

	public List<DatatypeAlbum> getAlbumsRecientes() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.albumsRecientes = service.getRecentAlbums(10);
			return albumsRecientes;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setAlbumsRecientes(ArrayList<DatatypeAlbum> albumsRecientes) {
		this.albumsRecientes = albumsRecientes;
	}

}