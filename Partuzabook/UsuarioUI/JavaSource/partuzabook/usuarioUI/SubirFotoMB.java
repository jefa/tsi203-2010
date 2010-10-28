package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirFotoMB{
	
	
	private ArrayList<DataTypeFile> files = new ArrayList<DataTypeFile>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = true;
	private int idEvento;
	private String message;
	
	private String currentFileId;
	private String currentDescription;

	// All existing categories associated to the event
	private List<DatatypeCategorySummary> allCategories = null;

	// Associated to Suggestion Box
	private String suggestCateg = "";
	private List<DatatypeCategorySummary> resultCategories = null;

	// Subset of existing categories. Shown in dataList
	private List<DatatypeCategorySummary> chosenCategories = null;
	
	private ServicesEventRemote servicesEvent;

	public List<DatatypeCategorySummary> autocomplete(Object suggestParam) {
			suggestCateg = ((String)suggestParam).toLowerCase();
			if (suggestCateg.equals("")) {
				// Si se busca el string vacío se devuelven todos	
				resultCategories = getAllCategories();
			} else {
				resultCategories = new ArrayList<DatatypeCategorySummary>();
				Iterator<DatatypeCategorySummary> it = allCategories.iterator();
				while (it.hasNext()) {
					DatatypeCategorySummary categ = (DatatypeCategorySummary) it.next();
					if (categ.getCategory().toLowerCase().contains(suggestCateg)) {
						resultCategories.add(categ);
					}
				}
			}
		return resultCategories;
	}

	public List<DatatypeCategorySummary> getAllCategories(){
		if (this.allCategories == null){
			this.servicesEvent = getServicesEvent();
			if (this.servicesEvent != null) {
				DatatypeEvent dataEvent = this.servicesEvent.getEventDetails(this.idEvento);
				if (dataEvent != null) {
					List<DatatypeCategorySummary> results = dataEvent.getContentCategories();
					if (results != null) {
						this.allCategories = results;
					}					
				}
			} 	
		}
		return this.allCategories;
	}		

	public String getSuggestCateg(){
		return this.suggestCateg;
	}
	
	public void setSuggestCateg(String sug){
		this.suggestCateg = sug;
	}

	public List<DatatypeCategorySummary> getResultCategories(){
		 return this.resultCategories;
	} 
	
	public List<DatatypeCategorySummary> getChosenCategories(){
		if (this.chosenCategories == null || this.chosenCategories.size()== 0){
			this.chosenCategories = new ArrayList<DatatypeCategorySummary>();
			// First calculate all Categories
			if (this.allCategories == null || this.allCategories.size() == 0){
				getAllCategories();
			}
			// Add default category to chosenCategories
			Iterator<DatatypeCategorySummary> it = this.allCategories.iterator();
			while (it.hasNext()){
				DatatypeCategorySummary dataCateg = it.next();
				if (dataCateg.getCategory().equals("Todas")){
					this.chosenCategories.add(dataCateg);
				}
			}
		}
		return this.chosenCategories;
	}
	
	public void addCategory(){
		if (this.suggestCateg != null && !this.suggestCateg.equals("")){
			Iterator<DatatypeCategorySummary> it = this.allCategories.iterator();
			while (it.hasNext()){
				DatatypeCategorySummary dataCateg = it.next();
				if (dataCateg.getCategory().equals(this.suggestCateg)) {
					if (!this.chosenCategories.contains(dataCateg)) {
						this.chosenCategories.add(dataCateg);
					}
				}
			}
		}
	}

	public void resetChosenCategories(){
		this.chosenCategories = null;
		getChosenCategories();
	}
	

	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String msg){
		this.message = msg;
	}

	public String getCurrentFileId(){
		return this.currentFileId;
	}
	
	public void setCurrentFileId(String id){
		this.currentFileId = id;
	}
	
	public String getCurrentDescription(){
		return this.currentDescription;
	}
	
	public void setCurrentDescription(String desc){
		this.currentDescription = desc;
	}
	
	public void addDescription(){
		Iterator<DataTypeFile> it = files.iterator();
		while (it.hasNext()){
			DataTypeFile file = it.next();
			if (file.getId() == this.currentFileId){
				file.setDescription(this.currentDescription);
			}
		}
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
		if (getFiles().size()>0){
			return getFiles().size();
		}else 
		{
			return 0;
		}
	}

	public SubirFotoMB() {
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer)object).getData());
	}
	
	public void listener(UploadEvent event) throws Exception{
	    UploadItem item = event.getUploadItem();
	    DataTypeFile file = new DataTypeFile();
	    // Set a local file Id
	    Integer currId = files.size();	    
	    file.setId(currId.toString());
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	    uploadsAvailable--;
	}  
	  
	public String clearUploadData() {
		files.clear();
		this.suggestCateg = "";
		setUploadsAvailable(5);
		return null;
	}
	
	public String confirmUpload() {
		ServicesUploadRemote servUpload = getServicesUpload();
		if (servUpload != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			String username = (String) session.getAttribute("username");
			servUpload.uploadMultimedia(idEvento, username, files);
			this.message = "Se han subido de forma exitosa";
			files.clear();
			this.suggestCateg = "";
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
	
	public ArrayList<DataTypeFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<DataTypeFile> files) { 
		this.files = files;
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

}