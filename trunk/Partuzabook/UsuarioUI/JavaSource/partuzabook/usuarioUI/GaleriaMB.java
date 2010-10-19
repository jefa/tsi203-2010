package partuzabook.usuarioUI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesMultimediaRemote;

public class GaleriaMB {

	private String userName;
	private int eventId;
	private String eventName;
	private int categoryId;
	private DatatypeCategory contentsCategory;
	private Integer contentId;
	private DatatypeContent selectedContent;
	private String comentario = "";
	
	private ServicesMultimediaRemote servicesMultimedia;
	private ServicesEventRemote servicesEvent;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public DatatypeContent getSelectedContent(){
		selectedContent = servicesEvent.getContentDetails(contentId, userName);
		return selectedContent;
	}
	
	public void setSelectedContent(DatatypeContent selectedContent){
		this.selectedContent = selectedContent;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		setContentsCategory(getServicesEvent().getCategoryContents(eventId, categoryId, 1, 10));
	}

	public int getCategory() {
		return categoryId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentsCategory(DatatypeCategory contentsCategory) {
		this.contentsCategory = contentsCategory;
		setContentId(contentsCategory.getContents().get(0).getContId());
	}

	public DatatypeCategory getContentsCategory() {
		//contentsCategory = getServicesEvent().getContentCategory(category);
		return contentsCategory;
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

	public ServicesEventRemote getServicesEvent() {
		try {
			if (servicesEvent == null) {
				servicesEvent = (ServicesEventRemote) getContext().lookup("PartuzabookEAR/ServicesEvent/remote");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servicesEvent;
	}

	public Integer getPage(){
		//final Integer index = contentsCategory.getContents().indexOf(this.content);
		return 1;
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
}