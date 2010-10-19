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
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

/**
 * Session Bean implementation class ServicesUpload
 */
@Stateless
public class ServicesUpload implements ServicesUploadRemote {
	
	private ServicesEventRemote servicesEvent;
	private ServicesUserRemote servicesUser;
	
    /**
     * Default constructor. 
     */
    public ServicesUpload() {
    }

    @PostConstruct
    public void postConstruct() {
        try {
			Context ctx = getContext();
			servicesEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");  
			servicesUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
		}
        catch (NamingException e) {
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

	public byte[] getContent(String username, int contentID, int thumbnail) {
		return servicesEvent.getContent(username, contentID, thumbnail);
	}
	
	public byte[] getUserAvatar(String userID) {
		return servicesUser.getUserAvatar(userID);
	}
	
}
