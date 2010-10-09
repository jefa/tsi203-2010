package partuzabook.serviciosUI.multimedia;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

/**
 * Session Bean implementation class ServicesUpload
 */
@Stateless
public class ServicesUpload implements ServicesUploadRemote {
	
	private ServicesEventRemote servicesEvent;
	
    /**
     * Default constructor. 
     */
    public ServicesUpload() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void postConstruct() {
        try {
			Context ctx = getContext();
			servicesEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");  
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	servicesEvent = null;
    }
    
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	public List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files) {
		return servicesEvent.uploadContent(eventID, username, files);
	}

	public byte[] getMultimedia(int eventID, String username, int contentID) {
		return servicesEvent.getContent(eventID, username, contentID);
	}
	
	public byte[] getMultimediaThumbnail(int eventID, String username, int contentID) {
		return servicesEvent.getContentThumbnail(eventID, username, contentID);
	}
	
}
