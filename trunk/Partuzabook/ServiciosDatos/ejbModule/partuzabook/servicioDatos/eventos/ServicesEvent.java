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

import partuzabook.datos.persistencia.DAO.ContentDAO;
import partuzabook.datos.persistencia.DAO.EventDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Participant;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;
import partuzabook.datos.persistencia.beans.User;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class ServicesEvent implements ServicesEventRemote {

	private EventDAO evDao;
	private NormalUserDAO nUserDao;
	private ContentDAO contDao;
	
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
	        contDao = (ContentDAO) ctx.lookup("ContentDAOBean/local");
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
			int thisMonth = today.getMonth();
			int evMonth = evDate.getMonth();
			// Por ahora me quedo con los eventos del mes actual
			if (thisMonth != evMonth) {
				list.remove(ev);
			}
		}
		return list;		
	}
		
	// Returns true if NormalUser is related to the Event
	public boolean isUserRelatedToEvent(String eventID, String user){
		NormalUser nUser = (NormalUser) nUserDao.findByID(user);
		Event event = (Event) evDao.findByName(eventID);
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
    	}
    	return ret.contains(event);  	
  	}

	
	public Content getGalleryMultimediaAtPos(String eventID, int pos) {
		return null;
	}
	
	// Returns a list of candidate Users for Tagging -who have not already been tagged in the content-   
	public List<User> getUsersForTag(String eventID, int contentID){
		// Verify existence of Event
		Event event = (Event) evDao.findByName(eventID);
		if (event == null) {
			return null;
		}
		// Verify existence of content
		Content cont = (Content) contDao.findByIDInEvent(event, contentID);
		if (cont == null) {
			return null;
		}
		// Obtain set of users already tagged in the content
		List<User> usersAlreadyTagged = new ArrayList<User>();
	/*	List<Tag> tags = cont.getTags();
		Iterator<Tag> it = tags.iterator();
		while (it.hasNext()) {
			Tag current = it.next();	
			TagForUser currentCast;
			if (current instanceof TagForUser) {
				currentCast = (TagForUser) current;
				usersAlreadyTagged.add(currentCast.getUserCreator());
			}
		}
	*/
		
		// Obtain a set of all users related to the event 
    	Set<Participant> part =  (Set<Participant>) event.getParticipants();
    	if (part.isEmpty()){
    		return null;
    	} 
	    List<User> ret = new ArrayList<User>();
    	Iterator<Participant> it = part.iterator();
    	while (it.hasNext()){
    		Participant p = it.next();
    		// Filter users that have already been tagged in this content
    		if (!usersAlreadyTagged.contains(p.getUser())) {
    			ret.add(it.next().getUser());
    		}	
    	}    	
    	return ret;  			
	}
	
	// Create a new instance of Tag associated to the content, user that was tagged, and the tagger 
	public void tagUserInContent(String eventID, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception {
		// Verify existence of Event
		Event event = (Event) evDao.findByName(eventID);
		if (event == null) {
			new Exception("Tagging failed - Event was not found");
		}
		// Verify existence of content
		Content cont = (Content) contDao.findByIDInEvent(event, contentID);
		if (cont == null) {
			new Exception("Tagging failed - Content was not found");
		}
		// Verify existence of user who is tagging and user who will be tagged
		User tagger = (User) nUserDao.findByID(userTagger);
		if (tagger == null || (!(tagger instanceof NormalUser))) {
			new Exception("Tagging failed - User Tagger was not found");
		}
		NormalUser nUserTagger = (NormalUser) tagger;
		// Check if user to tag is registered 
		User tagged = (User) nUserDao.findByID(userToTag);
		Tag tag;
		if (tagged == null) {
			// Unregistered user was tagged
			tag = new TagForNotUser();
			TagForNotUser tagNotUser = (TagForNotUser) tag;
			tagNotUser.setUsrTagCustom(userToTag);
		} else {
			// Registered user was tagged
			NormalUser nUserTagged = (NormalUser) tagged;
			tag = new TagForUser();
			TagForUser tagUser = (TagForUser) tag;
			tagUser.setUserTagged(nUserTagged);
			//TODO Averiguar si es necesario setear ademas el tag al userTagged, ya setee el userTagged al tag
			nUserTagged.getMyTags().add(tagUser);
		}
		tag.setCntId(contentID);
		//TODO Averiguar si es necesario setear ademas el tag al content, ya setee el content al tag
		//		cont.getTags().add(tag;)

		tag.setUserCreator(nUserTagger);
		//TODO Averiguar si es necesario setear ademas el tag a los users, ya setee los users al tag
		nUserTagger.getTagsMade().add(tag);
	}

	public void confirmUploadContent(List<Content> list) {
		// TODO Auto-generated method stub
		
	}

    
}
