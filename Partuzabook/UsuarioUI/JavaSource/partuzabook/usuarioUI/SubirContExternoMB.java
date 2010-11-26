package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeCategoryAux;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeYoutubeToken;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirContExternoMB{
	
	private static final String SERVICE_UPLOAD = "PartuzabookEAR/ServicesUpload/remote";
	private static final String SERVICE_EVENT = "PartuzabookEAR/ServicesEvent/remote";
	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final String NOT_SPACES = "No deben haber espacios en este campo";
	private static final String TODAS = "Todas";
	private static final String ALBUM = "Album";
	private static final String VIDEO ="V";
	private static final String PHOTO ="P";
	private static final String NEW_CAT_NAME_INVALID = "Nombre de categoria no permitido";
	private static final String NEW_CAT_ALREADY_EXISTS = "La categoria ingresada ya existe.";
	
	
	private int eventId;
	private String eventName;
	private String description;
	
	private String type = "P";
	private String url;
	private String urlMessage;
	
	private String newCatAux;
	private String newCatAuxMessage;
	private List<DatatypeCategoryAux> categories;
	
	public SubirContExternoMB() {
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	private static void filtrarRepetidos(List<String> list) {
		if(list == null || list.size() == 0)
			return;
		String element = list.remove(0);
		filtrarRepetidos(list);
		//Comparamos no case sensitive, si fuera case sensitive con el contains alcanzaba
		boolean agregar = true;
		for(Iterator<String> it = list.iterator(); it.hasNext(); ) {
			if(element.equalsIgnoreCase(it.next())){
				//Agrego de nuevo a la variable a
				agregar = false;
			}
		}
		if(agregar)
			list.add(0, element);
	}

	public String confirmUpload() {

		try {
			urlMessage = null;
			
			if(url == null || url.equals("")) {
				urlMessage = INPUT_OBLIG;
				return "subirContExterno";
			} else if(url.contains(" ")) {
				urlMessage = NOT_SPACES;
				return "subirContExterno";
			}
			
			String username = SessionUtils.getUsername();
			
			Context ctx = getContext();
			ServicesUploadRemote service = (ServicesUploadRemote)ctx.lookup(SERVICE_UPLOAD);
			int cnt_id = 0;
			if(type.equals(PHOTO))
				cnt_id = service.confirmUploadExternPhoto(eventId, username, description, url);
			else if(type.equals(VIDEO))
				cnt_id = service.confirmUploadExternVideo(eventId, username, description, url);
			else
				return "subirContExterno";
			
			List<String> cats = new ArrayList<String>();
			for(ListIterator<DatatypeCategoryAux> itCatsAux = getCategories().listIterator(); itCatsAux.hasNext(); ) {
				DatatypeCategoryAux catAux = itCatsAux.next();
				if(catAux.isValue()) {
					cats.add(catAux.getCategory());
				}
			}	
			
			getServicesEvent().addCategoryToContent(cnt_id, cats);
			url = "";
			setCategories(null);
			description = "";
			return "verEvento";
			
		} catch (NamingException e) {
			//TODO: Redirigir a una pagina de error
			e.printStackTrace();
			return "subirContExterno";
		}
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
		setEventId(getServicesEvent().searchForEventByName(eventName, 1).get(0).getEvtId());
	}

	public String getEventName() {
		return eventName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrlMessage(String urlMessage) {
		this.urlMessage = urlMessage;
	}

	public String getUrlMessage() {
		return urlMessage;
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


	
}