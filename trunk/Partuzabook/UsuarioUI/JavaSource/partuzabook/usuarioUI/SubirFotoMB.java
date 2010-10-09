package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class SubirFotoMB{
	
	private ArrayList<DataTypeFile> files = new ArrayList<DataTypeFile>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = true;
	
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
			int eventID = 1;
			String username = "rodri";
			servUpload.uploadMultimedia(eventID, username, files);
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