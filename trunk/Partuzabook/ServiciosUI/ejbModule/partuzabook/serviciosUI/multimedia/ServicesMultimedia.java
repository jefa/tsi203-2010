package partuzabook.serviciosUI.multimedia;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

/**
 * Session Bean implementation class ServicesMultimedia
 */
@Stateless
public class ServicesMultimedia implements ServicesMultimediaRemote {
	private ServicesEventRemote servEvent;
	
    /**
     * Default constructor. 
     */
    public ServicesMultimedia() {
    	
    }
    
    @PostConstruct
    public void postConstruct() {
        try {
			Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        servEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");  
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	servEvent = null;
    }

    
	public List<DatatypeContent> getBestQualifiedPictures(int length) {
		return servEvent.getBestQualifiedPictures(length);
	}
	
	
	public List<DatatypeContent> getMostCommentedPictures(int length) {
		return servEvent.getMostCommentedPictures(length);
	}
	
	
	public List<DatatypeMostTagged> getMostTagged(int length) {
		return servEvent.getMostTagged(length);
	}
	
	public boolean isUserRelatedToEvent(int eventID, String user){
		return servEvent.isUserRelatedToEvent(eventID, user);
	}
    
}
