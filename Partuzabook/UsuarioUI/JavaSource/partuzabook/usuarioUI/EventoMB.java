package partuzabook.usuarioUI;

import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesMultimediaRemote;

public class EventoMB {
	public static final int PAGE_SIZE = 12;
	
	private ServicesMultimediaRemote servicesMultimedia;
	private ServicesEventRemote servicesEvent;
	
	private boolean validUserForContext;	
	private String userName;
	
	private DatatypeEvent evento;
	private Integer eventId; 
	
	private int categoryId;
	private DatatypeCategory selectedCategory;
	
	private Integer contentId;
	private DatatypeContent selectedContent;
	
	private String comentario = "";
	
	public DatatypeContent getSelectedContent(){
		selectedContent = servicesEvent.getContentDetails(contentId, userName);
		return selectedContent;
	}
	
	public void setSelectedContent(DatatypeContent selectedContent){
		this.selectedContent = selectedContent;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		setSelectedCategory(servicesEvent.getCategoryContents(eventId, categoryId, 1, PAGE_SIZE));
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
		if (this.userName != null){
			setSelectedContent(servicesEvent.getContentDetails(contentId, userName));
		}
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setSelectedCategory(DatatypeCategory selectedCategory) {
		this.selectedCategory = selectedCategory;
		setContentId(selectedCategory.getContents().get(0).getContId());
	}

	public DatatypeCategory getSelectedCategory() {
		return selectedCategory;
	}

	
	public String getComentario(){
		return this.comentario;
	}
	
	public void setComentario(String com){
		this.comentario = com;
	}
	
	public void comentar() {
		try {
			ServicesEventRemote service = getServicesEvent();
			if (comentario != "") {
				service.commentContent(contentId, comentario, userName);
				comentario = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getPage(){
		//final Integer index = contentsCategory.getContents().indexOf(this.content);
		return 1;
	}

	public boolean isValidUserForContext() {
		validUserForContext = calcValidUserForContent();
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

	public void setEvento(DatatypeEvent evento) {
		this.evento = evento;
		setCategoryId(evento.getContentCategories().get(0).getCategoryId());
	}

	public DatatypeEvent getEvento() {
		return this.evento;
	}

	
	public Integer getEventId() {
		return this.eventId; 
	}

	public void setEventId(Integer eventId){
		this.eventId = eventId;
		calcValidUserForContent();
		// Also set the Event
		setEvento(getServicesEvent().getEventDetails(eventId));
	}

	private ServicesEventRemote getServicesEvent() {
		if (servicesEvent == null) {
			try {
				Context ctx;
				ctx = getContext();
				this.servicesEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		this.userName = (String) session.getAttribute("username");		
		if (userName == null) {
			return false;
		} 
		return getServicesMultimedia().isUserRelatedToEvent(eventId, userName);
	}
}