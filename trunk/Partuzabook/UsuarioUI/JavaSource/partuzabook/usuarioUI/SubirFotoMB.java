package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeCategoryAux;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeFileAux;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirFotoMB{
	
	private static final String TODAS = "Todas";
	private static final String ALBUM = "Album";
	private static final String NEW_CAT_ALREADY_EXISTS = "La categoria ingresada ya existe.";
	private static final int FILE_DESC_MAX_LENGTH = 100;
	private static final String FILE_DESC_MAX_LEN_ERROR = "La descripcion de uno de los archivos excede el tamaño maximo permitido. (" + FILE_DESC_MAX_LENGTH +")";
	private static final String RES_OK = "Las imagenes fueron subidas exitosamente";
	private static final String RES_NO_OK = "Ocurrio un error al subir las imagenes. Intente nuevamente en unos minutos";
	private static final String NEW_CAT_NAME_INVALID = "Nombre de categoria no permitido";
	
	private ArrayList<DatatypeFileAux> filesAux = new ArrayList<DatatypeFileAux>();
	//private ArrayList<DataTypeFile> files = new ArrayList<DataTypeFile>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = true;
	private boolean useFlash = true;
	private int idEvento;
	private String message;
	//private String categoriesToSelect;
	private ServicesEventRemote servicesEvent;
	private String fileCleanCat;
	
	//private List<Boolean> categoriesBoolean;
	private List<String> categoriesName;
	private String newCatAux;
	private String newCatAuxMessage;

	private List<DatatypeCategorySummary> allCategories = null;
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String msg){
		this.message = msg;
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
	
	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		if (this.idEvento != idEvento)
			clearUploadData();	
		this.idEvento = idEvento;
		//System.out.println("subirFotomb.setEvento():: Event="+idEvento);
	}

	public int getSize() {
		if (getFilesAux().size()>0){
			return getFilesAux().size();
		}else 
		{
			return 0;
		}
	}

	public SubirFotoMB() {
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFilesAux().get((Integer)object).getData());
	}
	
	public void listener(UploadEvent event) throws Exception{
	    UploadItem item = event.getUploadItem();
	    DatatypeFileAux file = new DatatypeFileAux();
	    // Set a local file Id
	    Integer currId = filesAux.size();
	    file.setId(currId.toString());
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    filesAux.add(file);
	    uploadsAvailable--;
	    List<String> categories = getAllCategoriesName();
		file.setCategoriesAux(new ArrayList<DatatypeCategoryAux>());	
		for(ListIterator<String> it2 = categories.listIterator(); it2.hasNext(); ) {
			file.getCategoriesAux().add(new DatatypeCategoryAux(it2.next(), false));
		}
	}  
	  
	public String clearUploadData() {
		filesAux.clear();
		setUploadsAvailable(5);
		return null;
	}
	
	public String confirmUpload() {
		//Actualizamos las categorias de los files
		newCatAux = "";
		ServicesUploadRemote servUpload = getServicesUpload();
		if (servUpload != null) {
			try {
				String username = SessionUtils.getUsername();
				List<DataTypeFile> files  = new ArrayList<DataTypeFile>();
				for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
					DataTypeFile dataf = (DataTypeFile)it.next();
					if(dataf.getDescription().length() <= FILE_DESC_MAX_LENGTH)
						files.add(dataf);
					else {
						this.message = FILE_DESC_MAX_LEN_ERROR;
					}						
				}
				List<String> cnt_id = servUpload.uploadMultimedia(idEvento, username, files);
			
				//Agregamos las categorias para cada archivo
				int i = 0;
				ServicesEventRemote serE = getServicesEvent();
				for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
				
					DatatypeFileAux file = it.next();
					
					List<String> cats = new ArrayList<String>();
					
					for(ListIterator<DatatypeCategoryAux> itCatsAux = file.getCategoriesAux().listIterator(); itCatsAux.hasNext(); ) {
						DatatypeCategoryAux catAux = itCatsAux.next();
						if(catAux.isValue()) {
							cats.add(catAux.getCategory());
						}
					}					
					serE.addCategoryToContent(Integer.parseInt(cnt_id.get(i)), cats);
					
					i++;
				}
				this.message = RES_OK;
				filesAux.clear();
				setUploadsAvailable(5);
			} catch (Exception e) {
				e.printStackTrace();
				this.message = RES_NO_OK;
			}			
		}
		return null;
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

    public ServicesUploadRemote getServicesUpload() {
        try {
			Context ctx = getContext();
			return (ServicesUploadRemote)ctx.lookup("PartuzabookEAR/ServicesUpload/remote");
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	public long getTimeStamp(){
		return System.currentTimeMillis();
	}
	
	public ArrayList<DatatypeFileAux> getFilesAux() {
		return filesAux;
	}

	public void setFiles(ArrayList<DatatypeFileAux> filesAux) { 
		this.filesAux = filesAux;
		//initCategoriesBoolean();
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	
	
	//Funciones auxiliares para las categorias de las fotos
	private List<String> getAllCategoriesName() {
		List<String> res = new ArrayList<String>();
		if(filesAux != null && filesAux.size() > 0 && filesAux.get(0).getCategoriesAux() != null && filesAux.get(0).getCategoriesAux() != null) {
			for(ListIterator<DatatypeCategoryAux> it = filesAux.get(0).getCategoriesAux().listIterator(); it.hasNext(); ) {
				res.add(it.next().getCategory());
			}
		} else {
			for(ListIterator<DatatypeCategorySummary> it = getAllCategories().listIterator(); it.hasNext(); ) {
				res.add(it.next().getCategory());
			}
		}
		return res;
	}
	
	private List<DatatypeCategorySummary> getAllCategories() {		
		//if(allCategories == null || allCategories.size() == 0) {
			allCategories = getServicesEvent().getEventDetails(idEvento, false).getContentCategories();
			int i = 0;
			int todas_i = -1;
			int album_i = -1;
			for(ListIterator<DatatypeCategorySummary> it = allCategories.listIterator(); it.hasNext(); ){
				DatatypeCategorySummary dat = it.next();
				if(dat.getCategory().equalsIgnoreCase(TODAS))
					todas_i = i;
				else if(dat.getCategory().equalsIgnoreCase(ALBUM))
					album_i = i;
				i++;
			}
			if(todas_i > -1) {
				allCategories.remove(todas_i);
				if(todas_i < album_i)
					album_i--;
			}
			if(album_i > -1) {
				allCategories.remove(album_i);
			}
		return allCategories;
	}

	public void setFileCleanCat(String fileCleanCat) {
		this.fileCleanCat = fileCleanCat;
	}

	public String getFileCleanCat() {
		return fileCleanCat;
	}
	
	public void borrarCats() {
		for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
			DatatypeFileAux file = it.next();
			if(file.getId().equals(fileCleanCat))
				for(Iterator<DatatypeCategoryAux> it2 = file.getCategoriesAux().iterator(); it2.hasNext(); ) {
					it2.next().setValue(false);
				}
		}
	}

	
	public void addCategoriesAux() {
		newCatAuxMessage = "";
		if(newCatAux != null && !newCatAux.equals("")) {
			//Check if it already exists
			if(newCatAux.equalsIgnoreCase(TODAS) || newCatAux.equalsIgnoreCase(ALBUM)) {
				newCatAuxMessage = NEW_CAT_NAME_INVALID;
				return;
			}				
			if(filesAux.get(0).getCategoriesAux() != null) {
				for(Iterator<DatatypeCategoryAux> it = filesAux.get(0).getCategoriesAux().iterator(); it.hasNext(); ) {
					DatatypeCategoryAux data = it.next();
					if(data.getCategory().equalsIgnoreCase(newCatAux)) {
						newCatAuxMessage = NEW_CAT_ALREADY_EXISTS;
						return;
					}		
				}
				for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
					it.next().getCategoriesAux().add(new DatatypeCategoryAux(newCatAux, false));		
				}
				newCatAux = "";
			}			
		}
	}

	public void initCategoriesAux() {
		List<String> categories = getAllCategoriesName();
		for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
			DatatypeFileAux file = it.next();
			if(file.getCategoriesAux() == null){
				file.setCategoriesAux(new ArrayList<DatatypeCategoryAux>());	
	
				for(ListIterator<String> it2 = categories.listIterator(); it2.hasNext(); ) {
					file.getCategoriesAux().add(new DatatypeCategoryAux(it2.next(), false));
				}
			}
		}
	}
/*
	public List<String> getCategoriesName() {
		initCategoriesAux();
		return null;
	}
*/
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
