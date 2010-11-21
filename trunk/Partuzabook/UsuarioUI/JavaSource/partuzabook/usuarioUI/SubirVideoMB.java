package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeCategoryAux;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeFileAux;
import partuzabook.datatypes.DatatypeYoutubeToken;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirVideoMB{
	
	private static final String SERVICE_UPLOAD = "PartuzabookEAR/ServicesUpload/remote";
	private static final String SERVICE_EVENT = "PartuzabookEAR/ServicesEvent/remote";
	private static final String NEXT_URL = "http://localhost:8080/UsuarioUI/YoutubeRedirect";
	private static final String TODAS = "Todas";
	private static final String ALBUM = "Album";
	private static final String NEW_CAT_NAME_INVALID = "Nombre de categoria no permitido";
	private static final String NEW_CAT_ALREADY_EXISTS = "La categoria ingresada ya existe.";
	private static final String RES_OK = "El video fue subido exitosamente";
	private static final String RES_NO_OK = "Ocurrio un error al subir el video. Intente nuevamente en unos minutos";
	
	private int eventId;
	private String description;
	private List<DatatypeCategoryAux> categories;
	private String message;
	
	private String newCatAux;
	private String newCatAuxMessage;
	
	private String post_url;
	private String token_id;
	private String youtubeFormToken;
	
	private boolean videoUploaded = false; //True if a video has been uploaded
	
	public SubirVideoMB() {
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	public String confirmUpload() {
		newCatAux = "";
		newCatAuxMessage = "";
		message = "";
		try {
			/*
			if(getCatsSelected().size() > 0)
				getCatsSelected().remove(getCatsSelected().size() - 1);
			*/
			String username = SessionUtils.getUsername();
			String youtube_id = (String)SessionUtils.getAttribute("youtube_id");
			
			Context ctx = getContext();
			ServicesUploadRemote service = (ServicesUploadRemote)ctx.lookup(SERVICE_UPLOAD);
			int cnt_id = service.confirmUploadVideo(eventId, username, youtube_id, description);

			List<String> cats = new ArrayList<String>();
			for(ListIterator<DatatypeCategoryAux> itCatsAux = getCategories().listIterator(); itCatsAux.hasNext(); ) {
				DatatypeCategoryAux catAux = itCatsAux.next();
				if(catAux.isValue()) {
					cats.add(catAux.getCategory());
				}
			}	
			getServicesEvent().addCategoryToContent(cnt_id, cats);
			message = RES_OK;
			setVideoUploaded(false);
			setCategories(null);
			description = "";
			return "verEvento";
		} catch (NamingException e) {
			//TODO: Redirigir a una pagina de error
			e.printStackTrace();
			message = RES_NO_OK;
			return "subirVideo";
		}
	}

	public void setEventId(int eventId) {
		if(this.eventId != eventId) {
			setVideoUploaded(false);
		}
		this.eventId = eventId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void getYoutubeFormToken() {
			
		ServicesUploadRemote service = getServicesUpload(); 
		DatatypeYoutubeToken token = service.getYoutubeToken();
		this.post_url = token.getPost_url() + "?nexturl=" + NEXT_URL;
		this.token_id = token.getToken_id();
		/*
		if(youtubeFormToken == null || youtubeFormToken.equals("")) {
			ServicesUploadRemote service = getServicesUpload(); 
			DatatypeYoutubeToken token = service.getYoutubeToken();
			youtubeFormToken = "<form action=\"";
			youtubeFormToken += token.getPost_url();
			youtubeFormToken +=	"?nexturl=" + NEXT_URL;
			youtubeFormToken +=	"\" method =\"post\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"file\"/><input type=\"hidden\" name=\"token\" value=\"";
			youtubeFormToken +=	token.getToken_id();
			youtubeFormToken +=	"\"/><input type=\"submit\" value=\"go\" /></form>";
			this.post_url = token.getPost_url() + "?nexturl=" + NEXT_URL;
			this.token_id = token.getToken_id();
		}
		return youtubeFormToken;
		*/
	}
	
	private void setYoutubeFormToken(String youtubeFormToken) {}
	
    public ServicesUploadRemote getServicesUpload() {
        try {
			Context ctx = getContext();
			return (ServicesUploadRemote)ctx.lookup(SERVICE_UPLOAD);
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public ServicesEventRemote getServicesEvent() {
        try {
			Context ctx = getContext();
			return (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}

	public String getPost_url() {
		if(SessionUtils.getAttribute("youtube_eror") == null || !SessionUtils.getAttribute("youtube_eror").equals("200")){
			post_url  = null;
			token_id = null;
			SessionUtils.removeAttribute("youtube_error");
		}
		if(post_url == null || post_url.equals(""))
			getYoutubeFormToken();
		return post_url;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setCategories(List<DatatypeCategoryAux> categories) {
		this.categories = categories;
	}

	public List<DatatypeCategoryAux> getCategories() {
		if(categories == null) {
			categories = getAllCategoriesAux();
		}
		return categories;
	}

	public List<DatatypeCategoryAux> getAllCategoriesAux() {
		List<DatatypeCategorySummary> allCategories = getServicesEvent().getEventDetails(eventId, false).getContentCategories();
		List<DatatypeCategoryAux> res = new ArrayList<DatatypeCategoryAux>();
		for(Iterator<DatatypeCategorySummary> it = allCategories.iterator(); it.hasNext(); ){
			DatatypeCategorySummary dat = it.next();
			if(!dat.getCategory().equalsIgnoreCase(TODAS) && !dat.getCategory().equalsIgnoreCase(ALBUM))
				res.add(new DatatypeCategoryAux(dat.getCategory(), false));
		}
		return res;
	}
		
	public void addCategory() {
		newCatAuxMessage = "";
		if(newCatAux != null && !newCatAux.equals("")) {
			//Check if it already exists
			if(newCatAux.equalsIgnoreCase(TODAS) || newCatAux.equalsIgnoreCase(ALBUM)) {
				newCatAuxMessage = NEW_CAT_NAME_INVALID;
				return;
			}
			if(getCategories() != null) {
				for(ListIterator<DatatypeCategoryAux> it = getCategories().listIterator(); it.hasNext(); ) {
					DatatypeCategoryAux data = it.next();
					if(data.getCategory().equalsIgnoreCase(newCatAux)) {
						newCatAuxMessage = NEW_CAT_ALREADY_EXISTS;
						return;
					}		
				}
				getCategories().add(new DatatypeCategoryAux(newCatAux, true));
				newCatAux = "";
			}
		}
	}

	public void borrarCats() {
		for(Iterator<DatatypeCategoryAux> it2 = getCategories().iterator(); it2.hasNext(); ) {
			it2.next().setValue(false);
		}
	}

	public void setNewCatAux(String newCatAux) {
		this.newCatAux = newCatAux;
	}

	public String getNewCatAux() {
		return newCatAux;
	}

	public void setNewCatAuxMessage(String newCatAuxMessage) {
		this.newCatAuxMessage = newCatAuxMessage;
	}

	public String getNewCatAuxMessage() {
		return newCatAuxMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setVideoUploaded(boolean videoUploaded) {
		if(!videoUploaded) {
			SessionUtils.removeAttribute("youtube_id");
			post_url = null;
		}
	}

	public boolean isVideoUploaded() {
		String youtube_id = (String)SessionUtils.getAttribute("youtube_id");
		return youtube_id != null && !youtube_id.equals("");
	}

	public String clearUploadData() {
		setVideoUploaded(false);
		return "verEvento";
	}
	
}