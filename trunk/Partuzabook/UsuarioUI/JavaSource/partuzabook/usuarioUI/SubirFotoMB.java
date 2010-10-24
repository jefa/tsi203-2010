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
	private List<SelectItem> allCategoriesSelItems;
	private List<SelectItem> selectedCategoriesSelItems;

	private List<String> categoriesToChooseString = null;
	private List<DatatypeCategorySummary> categoriesToChoose = null;
	private List<DatatypeCategorySummary> chosenCategories = null;	
	private DatatypeCategorySummary selectedCategory = null;
	public String selectedCategoryString ;
	
	private ServicesEventRemote servicesEvent;

	
	public String getSelectedCategoryString(){
		return this.selectedCategoryString;
	}

	public void setSelectedCategoryString(String str){
		this.selectedCategoryString = str;
	}

	public List<String> getCategoriesToChooseString(){
		if (this.categoriesToChoose == null){
			getCategoriesToChoose();
		}
		return this.categoriesToChooseString;
	}

	public void setCategoriesToChooseString(List<String> list){
		this.categoriesToChooseString = list;
	}
	

	public List<DatatypeCategorySummary> getCategoriesToChoose(){
		if (this.chosenCategories == null){
			this.servicesEvent = getServicesEvent();
			if (this.servicesEvent != null) {
				DatatypeEvent dataEvent = this.servicesEvent.getEventDetails(this.idEvento);
				if (dataEvent != null) {
					setCategoriesToChoose(dataEvent.getContentCategories());
				}
			}	
		}
		return this.categoriesToChoose;
	}

	public void setCategoriesToChoose(List<DatatypeCategorySummary> list){
		this.categoriesToChoose = list;
		List<String> listStr = new ArrayList<String>();
		Iterator<DatatypeCategorySummary> itList = list.iterator();
		while (itList.hasNext()){
			listStr.add(itList.next().getCategory());
		}
		setCategoriesToChooseString(listStr);
	}
	
	public List<DatatypeCategorySummary> getChosenCategories(){
		return this.chosenCategories;
	}
	
	public void setChosenCategories(List<DatatypeCategorySummary> list){
		this.chosenCategories = list;
	}
	
	public DatatypeCategorySummary getSelectedCategory(){
		return this.selectedCategory;
	}
	
	public void setSelectedCategory(DatatypeCategorySummary dataCat){
		this.selectedCategory = dataCat;
	}

	public void addChosenCategoriesString(){
		if (this.selectedCategoryString == null){
			Iterator<DatatypeCategorySummary> it = this.categoriesToChoose.iterator();
			while (it.hasNext()){
				DatatypeCategorySummary dataCat = it.next();
				if (this.selectedCategoryString.equals(dataCat.getCategory())){
					this.selectedCategory = dataCat;
					addChosenCategories();
				}
			}
		}
	}
	
	public void addChosenCategories(){
		if (this.selectedCategory != null) {
			this.chosenCategories.add(this.selectedCategory);
			this.categoriesToChoose.remove(this.selectedCategory);
		}
	}

	public void removeChosenCategories(){
		if (this.selectedCategory != null) {
			this.chosenCategories.remove(this.selectedCategory);
			this.categoriesToChoose.add(this.selectedCategory);
		}
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
	
	public List<SelectItem> getAllCategoriesSelItems(){
		if (this.allCategoriesSelItems == null){
			List<SelectItem> allCats =  new ArrayList<SelectItem>();		
			this.servicesEvent = getServicesEvent();
			if (this.servicesEvent != null) {
				DatatypeEvent dataEvent = this.servicesEvent.getEventDetails(this.idEvento);
				if (dataEvent != null) {
					List<DatatypeCategorySummary> allCategories = dataEvent.getContentCategories();
					Iterator<DatatypeCategorySummary> it = allCategories.iterator();
					while (it.hasNext()){
						DatatypeCategorySummary dataCateg = it.next();
						allCats.add(new SelectItem(dataCateg,dataCateg.getCategory()));
					}
				}
			}
			this.allCategoriesSelItems = allCats;
		}
		return this.allCategoriesSelItems;
	}
	
	public void setAllCategoriesSelItems(List<SelectItem> cats){
		this.allCategoriesSelItems = cats;
	}
		
	public List<SelectItem> getSelectedCategoriesSelItems(){		
		return this.selectedCategoriesSelItems;
	}

	public void setSelectedCategoriesSelItems(List<SelectItem> cats){
		this.selectedCategoriesSelItems = cats;
	}

	public void saveCategories(){
		Iterator<DataTypeFile> it = files.iterator();
		while (it.hasNext()){
			DataTypeFile file = it.next();
			if (file.getId() == this.currentFileId){
				List<DatatypeCategory> listDataCat = new ArrayList<DatatypeCategory>();
				Iterator<SelectItem> itSelItm = this.selectedCategoriesSelItems.iterator();
				while (itSelItm.hasNext()) {
					listDataCat.add((DatatypeCategory) itSelItm.next().getValue());
				}
				file.setCategories(listDataCat);
			}
		}
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