package partuzabook.servicioDatos.eventos;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.ModeratedEventDAO;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.User;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class Event implements EventRemote {

	private ModeratedEventDAO dao;
	
    /**
     * Default constructor. 
     */
    public Event() {
        // TODO Auto-generated constructor stub
        try {
			Context c = new InitialContext();
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context");
	        dao = (ModeratedEventDAO) ctx.lookup("ModeratedEventDAOBean/remote");  
	        System.out.println("Lookup worked!"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	public List<Event> getSummaryEvents() {
		return null;
	}
	
	public Content getGalleryMultimediaAtPos(String eventID, int pos) {
		return null;
	}
	
	public boolean isUserRelatedToEvent(String eventID, String user){
		return false;
	}
	
	public List<User> getUsersForTag(String eventID, String contentID){
		return null;
	}
	
	public void tagUserInContent(String eventID, String contentID, String user, int posX, int posY){

	}

	public void confirmUploadContent(List<Content> list) {
		// TODO Auto-generated method stub
		
	}

    
}
