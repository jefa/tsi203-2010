package partuzabook.servicioDatos.eventos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.EventDAO;
import partuzabook.datos.persistencia.DAO.ModeratedEventDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.ModeratedEvent;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Participant;
import partuzabook.datos.persistencia.beans.User;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class ServicesEvent implements ServicesEventRemote {

	private EventDAO evDao;
	private NormalUserDAO nUserDao;
	
    public ServicesEvent() {
        try {
			Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context");
	        evDao = (EventDAO) ctx.lookup("EventDAOBean/local");  
	        nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
	        System.out.println("Lookup worked!"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Returns a list of Events of interest (Eg: Events from this week)
	public List<Event> getSummaryEvents() {
		List<Event> list =  evDao.findAll();
		//TODO Aca debemos decidir como filtramos los eventos, si es por fecha o como
		java.util.Date d = new java.util.Date();
		Date today = new Date(d.getTime());
		Iterator<Event> it = list.iterator();
		while (it.hasNext()){
			Event ev = (Event) it.next();
			Date evDate = (Date) ev.getDate();
			if ((today.before(evDate) && evDate.compareTo(today) > 5) 
					|| (today.after(evDate) && today.compareTo(evDate) > 5)) {
				list.remove(ev);
			} 
		}
		return list;		
	}
		
	// Returns true if NormalUser is related to the Event
	public boolean isUserRelatedToEvent(String eventID, String user){
		NormalUser nUser = (NormalUser) nUserDao.findByID(user);
		Event event = (Event) evDao.findByID(eventID);
		if (nUser == null  || event == null) {
			return false;
		}
    	Set<Participant> part =  (Set<Participant>) nUser.getParticipants();
    	if (part.isEmpty()){
    		return false;
    	} 
	    Set<Event> ret = new HashSet<Event>();
    	Iterator<Participant> it = part.iterator();
    	while (it.hasNext()){
    		ret.add(it.next().getEvent());
        	it.next();
    	}
    	return ret.contains(event);  	
  	}

	
	public Content getGalleryMultimediaAtPos(String eventID, int pos) {
		return null;
	}
	
	// Returns a list of candidate Users for Tag 
	public List<User> getUsersForTag(String eventID, String contentID){
		Event event = (Event) evDao.findByID(eventID);
		if (event == null) {
			return null;
		}
    	Set<Participant> part =  (Set<Participant>) event.getParticipants();
    	if (part.isEmpty()){
    		return null;
    	} 
	    List<User> ret = new ArrayList<User>();
    	Iterator<Participant> it = part.iterator();
    	while (it.hasNext()){
    		ret.add(it.next().getUser());
        	it.next();
    	}
    	return ret;  			
	}
	
	public void tagUserInContent(String eventID, String contentID, String user, int posX, int posY){

	}

	public void confirmUploadContent(List<Content> list) {
		// TODO Auto-generated method stub
		
	}

    
}
