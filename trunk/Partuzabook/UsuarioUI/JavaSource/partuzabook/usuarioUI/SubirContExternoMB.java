package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

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
	
	
	private int eventId;
	private String description;
	private List<DatatypeCategorySummary> allCategories;
	
	private Map<String,String> categoriesToSelect;
	private List<String> catsSelected;
	private String catAux;
	private String newCat;
	private List<String> newCatsAux;
	
	private String type = "P";
	private String url;
	private String urlMessage;
	
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
			if(getCatsSelected().size() > 0)
				getCatsSelected().remove(getCatsSelected().size() - 1);
			
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
			
			ServicesEventRemote serE = getServicesEvent();
			
			//En catsSelected tengo las categorias seleccionadas, verifiquemos que no hayan nombres repetidos, etc
			List<String> categoriasSeleccionadas = getCatsSelected();
			//Quitamos todos los ""
			while(categoriasSeleccionadas.contains("")) {
				categoriasSeleccionadas.remove("");
			}
			
			List<DatatypeCategorySummary> categoriasParaAgregar = new ArrayList<DatatypeCategorySummary>();
			//Pasamos los String a Categorias
			for(Iterator<DatatypeCategorySummary> it = getAllCategories().iterator(); it.hasNext(); ) {
				DatatypeCategorySummary dat = it.next();
				if(categoriasSeleccionadas.contains(dat.getCategory())) {
					//Es una categoria que ya existe
					categoriasParaAgregar.add(dat);
					categoriasSeleccionadas.remove(dat.getCategory());
				}					
			}
			//Agregamos las categorias que no existen
			for(Iterator<String> it = categoriasSeleccionadas.iterator(); it.hasNext(); ) {
				DatatypeCategorySummary nuevaCat = new DatatypeCategorySummary();
				nuevaCat.setCategory(it.next());
				nuevaCat.setCategoryId(0); //0 es porque no existe
				categoriasParaAgregar.add(nuevaCat);
			}
			
			serE.addCategoryToContent(cnt_id, categoriasParaAgregar);
			url = "";
			catsSelected = null;
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

	public void setAllCategories(List<DatatypeCategorySummary> allCategories) {
		this.allCategories = allCategories;
	}

	public List<DatatypeCategorySummary> getAllCategories() {
		//if(allCategories == null || allCategories.size() == 0) {
			//eventId = 1001; //FIXME esta linea es para realizar pruebas. Hay que comentarla para que funcione adecuadamente
			allCategories = getServicesEvent().getEventDetails(eventId, false).getContentCategories();
			int i = 0;
			int remove = 0;
			for(Iterator<DatatypeCategorySummary> it = allCategories.iterator(); it.hasNext(); ){
				DatatypeCategorySummary dat = it.next();
				if(dat.getCategory().equals(TODAS))
					remove = i;
				else if(dat.getCategory().equals(ALBUM))
					remove = i;
				i++;
			}
			allCategories.remove(remove);
		//}
		return allCategories;
	}

	public void setCategoriesToSelect(Map<String, String> categoriesToSelect) {
		this.categoriesToSelect = categoriesToSelect;
	}

	public Map<String,String> getCategoriesToSelect() {
		categoriesToSelect = new HashMap<String, String>();
		for(Iterator<DatatypeCategorySummary> it = getAllCategories().iterator(); it.hasNext(); ) {
			DatatypeCategorySummary dat = it.next();
			if(!getCatsSelected().contains(dat.getCategory())){
				categoriesToSelect.put(dat.getCategory(), dat.getCategory());
			}		
		}
		//if(!(getCatsSelected() == null || getCatsSelected().size() == 0))
			categoriesToSelect.put("Nueva categoria", "Nueva categoria");
		return categoriesToSelect;
	}

	public void setCatAux(String catAux) {
		if(catsSelected == null || catsSelected.size() == 0)
			catsSelected = new ArrayList<String>();
		if(!catAux.equals("Nueva categoria"))
			this.catsSelected.add(catAux);
		else
			this.catsSelected.add("");
	}

	public String getCatAux() {
		return catAux;
	}
	
	public void setCatsSelected(List<String> catsSelected) {
		this.catsSelected = catsSelected;
	}

	public List<String> getCatsSelected() {
		if(catsSelected == null)
			catsSelected = new ArrayList<String>();
		return catsSelected;
	}
	
	public void setNewCatsAux(List<String> newCatsAux) {
		this.newCatsAux = newCatsAux;
	}

	public List<String> getNewCatsAux() {
		if(newCatsAux == null)
			newCatsAux = new ArrayList<String>();
		return newCatsAux;
	}
	
	public void borrarCats() {
		setCatsSelected(new ArrayList<String>());
		newCatsAux = null;
	}

	public void setNewCat(String newCat) {
		if(newCat != null && !newCat.equals("")) {
			boolean agregar = true;
			for(Iterator<String> it = getCatsSelected().iterator(); it.hasNext(); ){
				if(it.next().equalsIgnoreCase(newCat))
					agregar = false;
			}
			if(agregar) {
				getCatsSelected().add(newCat);
				getCatsSelected().remove("");
			}
		}
	}

	public String getNewCat() {
		return newCat;
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
	
	
}