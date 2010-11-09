package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeFileAux;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirFotoMB{
	
	private static final String TODAS = "Todas";
	private static final String ALBUM = "Album";
	
	private ArrayList<DatatypeFileAux> filesAux = new ArrayList<DatatypeFileAux>();
	//private ArrayList<DataTypeFile> files = new ArrayList<DataTypeFile>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = true;
	private int idEvento;
	private String message;
	private String categoriesToSelect;
	private ServicesEventRemote servicesEvent;
	private String fileNewCat;

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
		System.out.println("subirFotomb.setEvento():: Event="+idEvento);
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
	    getCategoriesToSelect();
	}  
	  
	public String clearUploadData() {
		filesAux.clear();
		setUploadsAvailable(5);
		return null;
	}
	
	public String confirmUpload() {
		//Actualizamos las categorias de los files
		fileNewCat = filesAux.size() + "";
		addCat();
		
		ServicesUploadRemote servUpload = getServicesUpload();
		if (servUpload != null) {
			String username = SessionUtils.getUsername();
			List<DataTypeFile> files  = new ArrayList<DataTypeFile>();
			for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
				files.add((DataTypeFile)it.next());
			}
			List<String> cnt_id = servUpload.uploadMultimedia(idEvento, username, files);
		
			//Agregamos las categorias para cada archivo
			int i = 0;
			ServicesEventRemote serE = getServicesEvent();
			for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
			
				DatatypeFileAux file = it.next();
				
				//En catsSelected tengo las categorias seleccionadas, verifiquemos que no hayan nombres repetidos, etc
				List<String> categoriasSeleccionadas = file.getCatsSelected();
				//Quitamos todos los ""
				while(categoriasSeleccionadas.contains("")) {
					categoriasSeleccionadas.remove("");
				}
				
				List<DatatypeCategorySummary> categoriasParaAgregar = new ArrayList<DatatypeCategorySummary>();
				//Pasamos los String a Categorias
				for(Iterator<DatatypeCategorySummary> it2 = getAllCategories().iterator(); it2.hasNext(); ) {
					DatatypeCategorySummary dat = it2.next();
					if(categoriasSeleccionadas.contains(dat.getCategory())) {
						//Es una categoria que ya existe
						categoriasParaAgregar.add(dat);
						categoriasSeleccionadas.remove(dat.getCategory());
					}					
				}
				//Agregamos las categorias que no existen
				for(Iterator<String> it2 = categoriasSeleccionadas.iterator(); it2.hasNext(); ) {
					DatatypeCategorySummary nuevaCat = new DatatypeCategorySummary();
					nuevaCat.setCategory(it2.next());
					nuevaCat.setCategoryId(0); //0 es porque no existe
					categoriasParaAgregar.add(nuevaCat);
				}
				
				serE.addCategoryToContent(Integer.parseInt(cnt_id.get(i)), categoriasParaAgregar);
				i++;
			}
			this.message = "Se han subido de forma exitosa";
			filesAux.clear();
			setUploadsAvailable(5);
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
	private List<DatatypeCategorySummary> getAllCategories() {
		//if(allCategories == null || allCategories.size() == 0) {
			//eventId = 1001; //FIXME esta linea es para realizar pruebas. Hay que comentarla para que funcione adecuadamente
			allCategories = getServicesEvent().getEventDetails(idEvento, false).getContentCategories();
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
	//Establece las categor�as que pueden ser seleccionadas
	public String getCategoriesToSelect() {
		for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
			DatatypeFileAux file = it.next();
			file.setCategoriesToSelect(new HashMap<String, String>());
			if(file.getCatsSelected() == null)
				file.setCatsSelected(new ArrayList<String>());
			
			for(Iterator<DatatypeCategorySummary> it2 = getAllCategories().iterator(); it2.hasNext(); ) {
				DatatypeCategorySummary dat = it2.next();
				if(!file.getCatsSelected().contains(dat.getCategory())){
					file.getCategoriesToSelect().put(dat.getCategory(), dat.getCategory());
				}		
			}
				file.getCategoriesToSelect().put("Nueva categoria", "Nueva categoria");
		}
		return "";
	}

	public void setCategoriesToSelect(String categoriesToSelect) {
		this.categoriesToSelect = categoriesToSelect;
	}

	public void setFileNewCat(String fileNewCat) {
		this.fileNewCat = fileNewCat;
	}

	public String getFileNewCat() {
		return fileNewCat;
	}
	
	public void addCat() {
		
		for(Iterator<DatatypeFileAux> it = filesAux.iterator(); it.hasNext(); ) {
			DatatypeFileAux file = it.next();
			/*
			if(!file.getCatAux().equals("Nueva categoria"))
				file.getCatsSelected().add(file.getCatAux());
			else
				file.getCatsSelected().add("");
			*/
			if(file.getNewCat() != null && !file.getNewCat().equals("")) {
				boolean agregar = true;
				for(Iterator<String> it2 = file.getCatsSelected().iterator(); it2.hasNext(); ){
					if(it2.next().equalsIgnoreCase(file.getNewCat()))
						agregar = false;
				}
				if(agregar) {
					file.getCatsSelected().add(file.getNewCat());
					file.getCatsSelected().remove("");
				}
				file.setNewCat("");
			}
		}
		try {
			DatatypeFileAux file = filesAux.get(Integer.parseInt(fileNewCat));
			if(file != null) {
				if(!file.getCatAux().equals("Nueva categoria"))
					file.getCatsSelected().add(file.getCatAux());
				else
					file.getCatsSelected().add("");
			}
		} catch(IndexOutOfBoundsException ioub) {}
		/*
		if(file.getNewCat() != null && !file.getNewCat().equals("")) {
			boolean agregar = true;
			for(Iterator<String> it = file.getCatsSelected().iterator(); it.hasNext(); ){
				if(it.next().equalsIgnoreCase(file.getNewCat()))
					agregar = false;
			}
			if(agregar) {
				file.getCatsSelected().add(file.getNewCat());
				file.getCatsSelected().remove("");
			}
			file.setNewCat("");
		}
		
		*/
		
		getCategoriesToSelect();
	}
	
	public void borrarCats() {
		DatatypeFileAux file = filesAux.get(Integer.parseInt(fileNewCat));
		file.setCatsSelected( new ArrayList<String>());
		getCategoriesToSelect();
	}
}
