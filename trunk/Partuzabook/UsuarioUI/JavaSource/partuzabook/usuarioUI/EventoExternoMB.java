package partuzabook.usuarioUI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.integracion.ws.productora_web.DataContent;
import partuzabook.integracion.ws.productora_web.IntegracionWSServicePortType;
import partuzabook.integracion.ws.proxy.WebServiceProductoraExternoRemote;

public class EventoExternoMB {
	public static final int PAGE_SIZE = 100;
	public static final boolean isSuperUser = false;

	private DatatypeEventSummary evento;
	private Integer eventId;

	private List<DatatypeContent> contents;
	private Integer contentsCount;

	private Integer contentId;
	private DatatypeContent selectedContent;

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

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setSelectedContent(DatatypeContent selectedContent) {
		this.selectedContent = selectedContent;
	}

	public DatatypeContent getSelectedContent() {
		return selectedContent;
	}

}