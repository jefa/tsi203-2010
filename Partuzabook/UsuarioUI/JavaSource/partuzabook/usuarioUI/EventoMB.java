package partuzabook.usuarioUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.exception.ContentNotFoundException;
import partuzabook.servicioDatos.exception.EventNotFoundException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

public class EventoMB {
	public static final int PAGE_SIZE = 100;
	public static final boolean isSuperUser = false;
	
	private PartuzaMailer mailer = new PartuzaMailer();
	
	private ServicesEventRemote servicesEvent;
	private ServicesUserRemote servicesUser;
	
	private boolean validUserForContext;
	private boolean userIsModerator;
	
	private DatatypeEvent evento;
	private List<DatatypeCategorySummary> categories;
	private Integer eventId;
	private Integer categoriesCount;

	private int categoryId;
	private DatatypeCategory selectedCategory;
	private List<DatatypeContent> contents;
	private Integer contentsCount;
	private int tagsCount;
	private int commentsCount;
	
	private Integer contentId;
	private DatatypeContent selectedContent;
	
	private List<Integer> averageRates;
	private int rating = 0;
	
	private String comentario = "Escribe un comentario...";
	private String commentToRemoveUser;
	private String commentToRemoveText;
	
	private boolean hasAlbum;
	
	private List<DatatypeUser> candidates;
	private List<DatatypeUser> results;
	private String suggest = "";
	private Boolean tagToRemoveUserIsReal;
	private String tagToRemoveUser;
	
	private String firstContentType;
	private String firstContentUrl;
	
	private boolean albumExists;
	private DatatypeCategory album;
	private boolean isAlbumFinalized;

	private boolean contentIsInAlbum;
	
	private String orderedList = "";
	
	public EventoMB(){
		int sessionEvtId = getEvtId();
		if (sessionEvtId != -1){
			setEventId(sessionEvtId);
		}
	}
	
	public boolean getIsSuperUser() {
		return isSuperUser;
	}
	
	public void setSelectedContent(DatatypeContent selectedContent){
		this.selectedContent = selectedContent;
		setTagsCount(selectedContent.getTags().size());
		setCommentsCount(selectedContent.getComments().size());
	}
	
	public DatatypeContent getSelectedContent(){
		//selectedContent = servicesEvent.getContentDetails(contentId, userName);
		return selectedContent;
	}
	
	public boolean isPhotoSelectedContent(){
		return this.selectedContent.getType() == 1;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		setSelectedCategory(servicesEvent.getCategoryContents(eventId, categoryId, 1, PAGE_SIZE));
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void updateCategory() {
		System.out.println("Category Update");
	}
	
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
		suggest = null;
		
		setSelectedContent(servicesEvent.getContentDetails(contentId, getUserName()));
		setRating(servicesEvent.getMyRatingForContent(contentId, getUserName()));
		for(Iterator<DatatypeContent> it = this.getContents().iterator(); it.hasNext(); ) {
			DatatypeContent data = it.next();
			if(data.getContId() == contentId) {
				firstContentType = data.getType() + "";
				firstContentUrl = data.getUrl();
			}
		}
		 
	}

	public Integer getContentId() {
		return contentId;
	}
	
	public void removeContent() {
		try {
			getServicesEvent().removeContentFromEvent(eventId, contentId, getUserName());
			setCategoryId(getCategoryId());
		} catch (EventNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSelectedCategory(DatatypeCategory selectedCategory) {
		this.selectedCategory = selectedCategory;
		if (selectedCategory.getCategory().equals("Album")) {
			ServicesEventRemote service = getServicesEvent();
			setContents(service.getAlbumContents(this.eventId));
		} else {
			setContents(selectedCategory.getContents());
		}
		setContentsCount(this.selectedCategory.getContents().size());
		if (getContentsCount() > 0) {
			setContentId(selectedCategory.getContents().get(0).getContId());
			setFirstContentType(selectedCategory.getContents().get(0).getType() + "");
			setFirstContentUrl(selectedCategory.getContents().get(0).getUrl());
		}
	}

	public DatatypeCategory getSelectedCategory() {
		//setCategoryId(this.categoryId);
		//setFirstContentType(selectedCategory.getContents().get(0).getType() + "");
		//setFirstContentUrl(selectedCategory.getContents().get(0).getUrl());
		return selectedCategory;
	}

	public void setContents(List<DatatypeContent> list){
		this.contents = list;
		setContentsCount(this.contents.size());
	}
	
	public List<DatatypeContent> getContents(){
		return this.contents;
	}
	
	public void setContentsCount(Integer contentsCount) {
		this.contentsCount = contentsCount;
	}

	public Integer getContentsCount() {
		return contentsCount;
	}
	
	public void setTagsCount(int count){
		this.tagsCount = count;
	}
	
	public int getTagsCount(){
		return this.tagsCount;
	}

	public void setCommentsCount(int count){
		this.commentsCount = count;
	}
	
	public int getCommentsCount(){
		return this.commentsCount;
	}

	public String getComentario(){
		return this.comentario;
	}
	
	public void setComentario(String com){
		this.comentario = com;
	}
	
	public String getCommentToRemoveUser() {
		return commentToRemoveUser;
	}

	public void setCommentToRemoveUser(String commentToRemoveUser) {
		this.commentToRemoveUser = commentToRemoveUser;
	}

	public String getCommentToRemoveText() {
		return commentToRemoveText;
	}

	public void setCommentToRemoveText(String commentToRemoveText) {
		this.commentToRemoveText = commentToRemoveText;
	}

	public void comentar() {
		try {
			ServicesEventRemote service = getServicesEvent();
			if (comentario != "") {
				comentario = comentario.replace("<p>", "").replace("</p>","");
				service.commentContent(contentId, comentario, getUserName());
				
				//Agrego que en la l�gica se genere una notificaci�n para los moderadores sobre el comentario (GG).
				
				//TODO: Javier enviar notificación por mail a los moderadores que hay un nuevo comentario
				//para el contenido en el evento.

				String emailTo = getServicesUser().getNormalUserMailAddress(getUserName());
				mailer.sendFormattedMail(getUserName(), getServicesUser().getName(getUserName()),
						"Se ha agregado comentario a contenido "+contentId, 
						new SimpleDateFormat().format(new Date()), null, emailTo, null, null, 
					"Se ha comentado un evento");
				
				setContentId(contentId);
				comentario = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeComment() {
		try {
			getServicesEvent().removeCommentFromContent(eventId, contentId,
					getUserName(), commentToRemoveUser, commentToRemoveText);
			setContentId(getContentId());
		} catch (ContentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getPage(){
		//final Integer index = contentsCategory.getContents().indexOf(this.content);
		return 1;
	}

	public boolean isValidUserForContext() {
		return validUserForContext;
	}

	public void setValidUserForContext(boolean validUserForContext) {
		this.validUserForContext = calcValidUserForContent();
		setUserIsModerator(false);
	}

	public boolean isUserIsModerator() {
		return userIsModerator;
	}

	public void setUserIsModerator(boolean userIsModerator) {
		if (eventId != null) {
			this.userIsModerator = getServicesEvent().isUserModeratorInEvent(eventId, getUserName());
		}
	}

	public String getUserName() {
		return SessionUtils.getUsername();
	}

	public Integer getEvtId() {
		return SessionUtils.getEventId();
	}
	
	public void setEvento(DatatypeEvent evento) {
		this.evento = evento;
		// Setear solamente la categoría publica Album en caso que exista
		List<DatatypeCategorySummary> newList = new ArrayList<DatatypeCategorySummary>();
		List<DatatypeCategorySummary> catList = evento.getContentCategories();
		Iterator<DatatypeCategorySummary> it = catList.iterator();
		while (it.hasNext()) {
			DatatypeCategorySummary dataCateg = it.next();
			if (dataCateg.getCategory().equals("Album")){
				newList.add(dataCateg);
			}
		}
		if (!isValidUserForContext()) {
			if (!evento.getHasAlbum()) {
				setCategories(null);
				setCategoriesCount(0);
			} else {
				// Setear solamente la categoria Album
				setCategories(newList);
				setCategoriesCount(1);
				setCategoryId(newList.get(0).getCategoryId());
			}
		} else {
			Iterator<DatatypeCategorySummary> itCat = catList.iterator();
			while (itCat.hasNext()) {
				DatatypeCategorySummary dataCateg = itCat.next();
				if (!dataCateg.getCategory().equals("Album")){
					newList.add(dataCateg);	
				}
			}
			setCategories(newList);
			setCategoriesCount(newList.size());
			if (getCategoriesCount() > 0) {
				setCategoryId(newList.get(0).getCategoryId());
			}
		}
	}

	public DatatypeEvent getEvento() {
		int eventParam = getEvtId();
		if (eventParam != -1) {
			setEventId(eventParam);
		}
		return this.evento;
	}
	
	public void setCategories(List<DatatypeCategorySummary> list) {
		this.categories = list;
	}

	public List<DatatypeCategorySummary> getCategories(){
		// Actualizar las categorias
		//setEvento(this.evento);
		return this.categories;
	}
	
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId){
		this.eventId = eventId;
		setValidUserForContext(false);
		// Also set the Event
		setEvento(getServicesEvent().getEventDetails(eventId, getIsSuperUser()));
	}

	public void setCategoriesCount(Integer categoriesCount) {
		this.categoriesCount = categoriesCount;
	}

	public Integer getCategoriesCount() {
		return categoriesCount;
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

	private ServicesUserRemote getServicesUser() {
		if (servicesUser == null) {
			try {
				Context ctx;
				ctx = getContext();
				this.servicesUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return servicesUser;
	}

	private void setCandidates() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			candidates = service.getUsersForTag(eventId, contentId);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<DatatypeUser> autocomplete(Object suggestParam) {
		setCandidates();
		suggest = ((String)suggestParam).toLowerCase();
		results = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidates.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggest)
					|| user.name.toLowerCase().contains(suggest)) {
				results.add(user);
			}
		}
		return results;
	}

	public void setResults(List<DatatypeUser> results) {
		this.results = results;
	}
	
	public void removeTag() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			service.removeTagInContent(contentId, getUserName(), tagToRemoveUserIsReal, tagToRemoveUser);
			suggest = null;
			setContentId(getContentId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean getTagToRemoveUserIsReal() {
		return tagToRemoveUserIsReal;
	}

	public void setTagToRemoveUserIsReal(Boolean tagToRemoveUserIsReal) {
		this.tagToRemoveUserIsReal = tagToRemoveUserIsReal;
	}

	public String getTagToRemoveUser() {
		return tagToRemoveUser;
	}

	public void setTagToRemoveUser(String tagToRemoveUser) {
		this.tagToRemoveUser = tagToRemoveUser;
	}

	public String getSuggest() {
		return suggest;
	}
 
 
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public boolean getHasAlbum(){
		Context ctx;
		try {
			ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			this.hasAlbum = service.getEventDetails(eventId, getIsSuperUser()).getHasAlbum(); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.hasAlbum;
	}
	
	public void setHasAlbum(boolean album){
		this.hasAlbum = album;
	}

	public void setAverageRates(List<Integer> averageRates) {
		this.averageRates = averageRates;
	}

	public List<Integer> getAverageRates() {
		if (averageRates == null) {
			averageRates = new ArrayList<Integer>();
			for (int i = 0; i < 25; i++) {
				averageRates.add(i);
			}
		}
		return averageRates;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}
	
	public void rate() {
		try {
			getServicesEvent().rateContent(contentId, rating, getUserName());
			setContentId(getContentId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private Boolean calcValidUserForContent() {
		
		return getServicesEvent().isUserRelatedToEvent(eventId, getUserName());
	}
	
	public void sendAdmitMail() {
		getServicesEvent().sendAdmitMail(eventId, getUserName());
	}

	public void setFirstContentType(String firstContentType) {
		//this.firstContentType = firstContentType;
	}

	public String getFirstContentType() {
		return firstContentType;
	}

	public void setFirstContentUrl(String firstContentUrl) {
		//this.firstContentUrl = firstContentUrl;
	}

	public String getFirstContentUrl() {
		return firstContentUrl;
	}

	public boolean getContentIsInAlbum(){
		if (this.selectedContent == null){
			return false;
		}
		return this.selectedContent.getPosAlbum() != null;
	}
	
	public void setContentIsInAlbum(boolean contentIsInAlbum){
		this.contentIsInAlbum = contentIsInAlbum;
	}
	
	public DatatypeCategory getAlbum(){
		try {
			ServicesEventRemote service = getServicesEvent();
			DatatypeCategory dataCntCateg = service.existsAlbum(this.eventId);
			this.album = dataCntCateg;
			return this.album;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setAlbum(DatatypeCategory alb){
		this.album = alb;
	}

	public boolean getAlbumExists(){
		DatatypeCategory album = getAlbum();
		if (album == null) {
			this.albumExists = false;
		} else {
			this.album = album;
			this.albumExists = true;
		}
		return this.albumExists;
	}
	
	public void setAlbumExists(boolean exists){
		this.albumExists = exists;
	}
	
	public boolean getIsAlbumFinalized() {
		try {
			ServicesEventRemote service = getServicesEvent();
			this.isAlbumFinalized =  service.isAlbumFinalized(this.eventId);
			return this.isAlbumFinalized;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void setIsAlbumFinalized(boolean is){
		this.isAlbumFinalized = is;
	}
	
	public void agregarContenidoAlbum() {
		try {
			ServicesEventRemote service = getServicesEvent();
			service.addContentToAlbum(this.contentId, this.eventId);
			//setCategoryId(this.categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void quitarContenidoAlbum() {
		try {
			ServicesEventRemote service = getServicesEvent();
			service.removeContentFromAlbum(this.contentId, this.eventId);
			setCategoryId(this.categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setearCoverEvento() {
		try {
			ServicesEventRemote service = getServicesEvent();
			service.setCoverImage(this.contentId, this.eventId);
			setEventId(this.eventId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void setOrderedList(String list){
		this.orderedList = list;
	}
	
	public String getOrderedList(){
		return this.orderedList;
	}

}