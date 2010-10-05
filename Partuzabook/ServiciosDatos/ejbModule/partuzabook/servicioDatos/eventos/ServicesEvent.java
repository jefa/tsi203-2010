package partuzabook.servicioDatos.eventos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.ContentDAO;
import partuzabook.datos.persistencia.DAO.EventDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;
import partuzabook.datos.persistencia.beans.User;
import partuzabook.servicioDatos.exception.ContentNotFoundException;
import partuzabook.servicioDatos.exception.EventNotFoundException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class ServicesEvent implements ServicesEventRemote {

	private EventDAO eventDAO;
	private ContentDAO contentDAO;
	private NormalUserDAO normalUserDAO;
	
    public ServicesEvent() {
    	
    }
    
    @PostConstruct
    public void postConstruct() {
        try {
			Context ctx = getContext();
	        eventDAO = (EventDAO) ctx.lookup("EventDAOBean/local");  
    		contentDAO = (ContentDAO) ctx.lookup("ContentDAOBean/local");
    		normalUserDAO = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

    @PreDestroy
    public void preDestroy() {
    	eventDAO = null;
    	contentDAO = null;
    	normalUserDAO = null;
    }
    
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents,
			int maxContentPerEvent) {
		// Calculate one week before date
		Calendar after = new GregorianCalendar();
		after.set(Calendar.DAY_OF_YEAR, after.get(Calendar.DAY_OF_YEAR) - 7);
		Date afterDate = new Date(after.getTimeInMillis());
		
		// Take the first maxEvents and translate to the datatype
		List<Event> list =  eventDAO.findAllAfterDate(afterDate);
		if (maxEvents < list.size()) {
			list = list.subList(0, maxEvents);
		}
		List<DatatypeEventSummary> listDatatypes = TranslatorCollection.translateEventSummary(list);
		
		// add to the datatype the contents and translate them
		Iterator<Event> itEvent = list.iterator();
		Iterator<DatatypeEventSummary> itDatatype = listDatatypes.iterator();
		while (itEvent.hasNext()){
			Event ev = (Event) itEvent.next();
			List<Content> contents = ev.getContents();
			if (maxContentPerEvent < contents.size()) {
				contents = contents.subList(0, maxContentPerEvent);
			}
			itDatatype.next().contents = TranslatorCollection.translateContent(contents);
		}
		return listDatatypes;
	}
		
	public boolean isUserRelatedToEvent(String eventName, String user){
		Event ev = eventDAO.findByName(eventName);
		if (ev == null) {
			throw new EventNotFoundException();
		}
		NormalUser nUser = normalUserDAO.findByID(user);
		if (nUser == null) {
			throw new UserNotFoundException();
		}
		return nUser.getMyEvents().contains(ev);
  	}

	
	public DatatypeContent getGalleryMultimediaAtPos(String eventID, int pos) {
		//TODO getGalleryMultimediaAtPos
		return null;
	}
	
	public List<DatatypeUser> getUsersForTag(String eventID, int contentID){
		// Verify existence of Event
		Event event = (Event) eventDAO.findByName(eventID);
		if (event == null) {
			throw new EventNotFoundException();
		}
		// Verify existence of content
		Content cont = (Content) contentDAO.findByIDInEvent(event, contentID);
		if (cont == null) {
			throw new ContentNotFoundException();
		}
		
		// Obtain set of users already tagged in the content
		List<User> usersAlreadyTagged = new ArrayList<User>();		
		List<Tag> tags = cont.getTags();
		Iterator<Tag> it = tags.iterator();
		while (it.hasNext()) {
			Tag current = it.next();	
			TagForUser currentCast;
			if (current instanceof TagForUser) {
				currentCast = (TagForUser) current;
				usersAlreadyTagged.add(currentCast.getCreator());
			}
		}
			
		// Obtain a set of all users related to the event 
		List<NormalUser> allUsersInEvent = event.getMyParticipants();
    	if (allUsersInEvent.isEmpty()){
    		return null;
    	} 
    	
    	// Filter users that have already been tagged in this content    	
    	allUsersInEvent.removeAll(usersAlreadyTagged);
    	
    	return TranslatorCollection.translateNormalUser(allUsersInEvent);
	}
	
	public void tagUserInContent(String eventID, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception {
		// Verify existence of Event
		Event event = (Event) eventDAO.findByName(eventID);
		if (event == null) {
			throw new EventNotFoundException();
		}
		// Verify existence of content
		Content cont = (Content) contentDAO.findByIDInEvent(event, contentID);
		if (cont == null) {
			throw new ContentNotFoundException();
		}
		// Verify existence of user who is tagging and user who will be tagged
		User tagger = (User) normalUserDAO.findByID(userTagger);
		if (tagger == null || (!(tagger instanceof NormalUser))) {
			throw new UserNotFoundException();
		}
		NormalUser nUserTagger = (NormalUser) tagger;
		// Check if user to tag is registered 
		User tagged = (User) normalUserDAO.findByID(userToTag);
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
			
			// Notify the existing user that has been tagged
			Notification ntfTagged = new Notification();
			ntfTagged.setRead(false);
			ntfTagged.setText("Has sido etiquetado");
			ntfTagged.setUserTo(nUserTagged);
		}
		tag.setContent(cont);
		//TODO Averiguar si es necesario setear ademas el tag al content, ya setee el content al tag
		cont.getTags().add(tag);

		tag.setCreator(nUserTagger);
		//TODO Averiguar si es necesario setear ademas el tag a los users, ya setee los users al tag
		nUserTagger.getTagsCreated().add(tag);
		
		tag.set_posX_(posX);
		tag.set_posY_(posY);
		
		// Create a new notification once the tag has been created
		Notification ntfToCreator = new Notification();
		ntfToCreator.setRead(false);
		ntfToCreator.setText("La etiqueta ha sido creada de forma exitosa");
		ntfToCreator.setUserTo(nUserTagger);
		
	}

	public void confirmUploadContent(List<Content> list) {
		// TODO Auto-generated method stub	
	}
    
}
