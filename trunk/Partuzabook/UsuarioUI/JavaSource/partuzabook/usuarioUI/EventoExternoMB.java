package partuzabook.usuarioUI;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.proxy.WebServiceProductoraExternoRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class EventoExternoMB {
	public static final int PAGE_SIZE = 100;
	public static final boolean isSuperUser = false;

	private DatatypeEventSummary evento;
	private Integer eventId;

	private List<DatatypeContent> contents;
	private Integer contentsCount;

	private DatatypeContent selectedContent;
	private String selectedContentType;
	private String selectedContentUrl;
	
	private String eventoUser;
	private List<String> eventosUser;
	
	public EventoExternoMB() {

	}

	public boolean getIsSuperUser() {
		return isSuperUser;
	}

	public void setEvento(DatatypeEventSummary evento) {
		this.evento = evento;
		setEventId(evento.getEvtId());
	}

	public DatatypeEventSummary getEvento() {
		return this.evento;
	}

	public void setEventId(Integer eventId) {
		try {
			List<DatatypeContent> partuzabookResults =
				getPartuzabookProxy().getAlbumContents(eventId.toString());
//			List<DatatypeContent> productoraWebResults =
//				getProductoraWebProxy().getAlbumContents(eventId);
//			partuzabookResults.addAll(productoraWebResults);
			setContentsCount(partuzabookResults.size());

			setContents(partuzabookResults);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getEventId() {
		return this.eventId;
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

	public void setContents(List<DatatypeContent> contents) {
		this.contents = contents;
		if (contents.size() > 0) {
			setSelectedContentUrl(contents.get(0).getUrl());
		}
		else {
			setSelectedContentUrl(null);
		}
	}

	public List<DatatypeContent> getContents() {
		return contents;
	}

	public void setContentsCount(Integer contentsCount) {
		this.contentsCount = contentsCount;
	}

	public Integer getContentsCount() {
		return contentsCount;
	}

	public void setSelectedContent(DatatypeContent selectedContent) {
		this.selectedContent = selectedContent;
		setSelectedContentType(selectedContent.getType() + "");
	}

	public DatatypeContent getSelectedContent() {
		return selectedContent;
	}

	public void setSelectedContentType(String selectedContentType) {
		this.selectedContentType = selectedContentType;
	}

	public String getSelectedContentType() {
		return selectedContentType;
	}

	private DatatypeContent findByUrl(String url) {
		Iterator<DatatypeContent> it = contents.iterator();
		while (it.hasNext()) {
			DatatypeContent datatypeContent = (DatatypeContent) it.next();
			if (datatypeContent.getUrl().equals(url)) {
				return datatypeContent;
			}
		}
		return null;
	}
	
	public void setSelectedContentUrl(String selectedContentUrl) {
		this.selectedContentUrl = selectedContentUrl;
		setSelectedContent(findByUrl(selectedContentUrl));
	}

	public String getSelectedContentUrl() {
		return selectedContentUrl;
	}

	public void setEventoUser(String eventoUser) {
		this.eventoUser = eventoUser;
	}

	public String getEventoUser() {
		return eventoUser;
	}

	public void setEventosUser(List<String> eventosUser) {
		this.eventosUser = eventosUser;
	}

	public List<String> getEventosUser() {
		try {
			Context ctx = getContext();
			ServicesUserRemote service = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			eventosUser = service.getEventNamesForUser(SessionUtils.getUsername());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventosUser;
	}

}