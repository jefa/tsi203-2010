package partuzabook.servicioDatos.eventos;

import java.sql.Date;
import java.sql.Timestamp;
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

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.ContentDAO;
import partuzabook.datos.persistencia.DAO.EventDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.DAO.TagDAO;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.SelfContent;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;
import partuzabook.datos.persistencia.beans.User;
import partuzabook.datos.persistencia.beans.Video;
import partuzabook.datos.persistencia.filesystem.FileSystemLocal;
import partuzabook.servicioDatos.exception.ContentNotFoundException;
import partuzabook.servicioDatos.exception.EventNotFoundException;
import partuzabook.servicioDatos.exception.UnrecognizedFileTypeException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.servicioDatos.exception.UserNotRelatedToEventException;
import partuzabook.utils.TranslatorCollection;
import partuzabook.entityTranslators.TranslatorContent;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class ServicesEvent implements ServicesEventRemote {

	private EventDAO evDao;
	private ContentDAO contDao;
	private NormalUserDAO nUserDao;
	private TagDAO tagDao;
	private NotificationDAO notifDao;
	private FileSystemLocal fileSystem;
	
    public ServicesEvent() {
    	
    }
    
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

	@PostConstruct
    public void postConstruct() {
        try {
			Context ctx = getContext();
	        evDao = (EventDAO) ctx.lookup("EventDAOBean/local");  
    		contDao = (ContentDAO) ctx.lookup("ContentDAOBean/local");
    		nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
    		tagDao = (TagDAO) ctx.lookup("TagDAOBean/local");    		
    		notifDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
    		fileSystem = (FileSystemLocal) ctx.lookup("FileSystem/local");
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @PreDestroy
    public void preDestroy() {
    	evDao = null;
    	contDao = null;
    	nUserDao = null;
    	tagDao = null;
    	notifDao = null;
    }
    
    private Event getEvent(int eventID) throws EventNotFoundException { 
    	Event event = evDao.findByID(eventID);
    	if (event == null) {
			throw new EventNotFoundException();
		}
    	return event;
    }
    
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents,
			int maxContentPerEvent) {
		// Calculate one week before date
		Calendar after = new GregorianCalendar();
		after.set(Calendar.DAY_OF_YEAR, after.get(Calendar.DAY_OF_YEAR) - 7);
		Date afterDate = new Date(after.getTimeInMillis());
		
		// Take the first maxEvents and translate to the datatype
		List<Event> list =  evDao.findAllAfterDate(afterDate);
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
		
	public boolean isUserRelatedToEvent(int eventID, String user) {
		Event ev = getEvent(eventID);
		NormalUser nUser = nUserDao.findByID(user);
		if (nUser == null) {
			throw new UserNotFoundException();
		}
		return nUser.getMyEvents().contains(ev);
  	}

	/**
	 * Returns the photo at position in the photo gallery associated to the event
	 * @param eventName - Name of the Event 
	 * @param pos - Position of content from Photo Gallery of the event
	 */
	public DatatypeContent getGalleryPhotoAtPos(String eventName, int pos) {
		// Verify existence of Event
		Event event = (Event) evDao.findByName(eventName);
		if (event == null) {
			throw new EventNotFoundException();
		}
		Content c = contDao.findByPosInGalleryEvent(event, pos);
		if (c == null) {
			throw new ContentNotFoundException();
		}
		TranslatorContent trans = new TranslatorContent();
		return (DatatypeContent) trans.translate(c);
	}
	
	public List<DatatypeUser> getUsersForTag(int eventID, int contentID){
		// Verify existence of Event
		Event event = getEvent(eventID);
		// Verify existence of content
		Content cont = (Content) contDao.findByIDInEvent(event, contentID);
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
	
	public void tagUserInContent(int eventID, int contentID, String userTagger, String userToTag,
			int posX, int posY) throws EventNotFoundException, ContentNotFoundException, UserNotFoundException {
		// Verify existence of Event
		Event event = getEvent(eventID);
		// Verify existence of content
		Content cont = (Content) contDao.findByIDInEvent(event, contentID);
		if (cont == null) {
			throw new ContentNotFoundException();
		}
		// Verify existence of user who is tagging and user who will be tagged
		User tagger = (User) nUserDao.findByID(userTagger);
		if (tagger == null || (!(tagger instanceof NormalUser))) {
			throw new UserNotFoundException();
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
		} 
		else {
			// Registered user was tagged
			NormalUser nUserTagged = (NormalUser) tagged;
			tag = new TagForUser();
			TagForUser tagUser = (TagForUser) tag;

			tagUser.setUserTagged(nUserTagged);
			//TODO Averiguar si es necesario setear ademas el tag al userTagged, ya setee el userTagged al tag
			//nUserTagged.getMyTags().add(tagUser);
			
			// Notify the existing user that has been tagged
			Notification ntfTagged = new Notification();
			ntfTagged.setNotDate(new Timestamp(new java.util.Date().getTime()));
			ntfTagged.setRead(false);
			ntfTagged.setReference("La referencia va aca");
			ntfTagged.setRegDate(new Timestamp(new java.util.Date().getTime()));
			ntfTagged.setText("Has sido etiquetado");
			ntfTagged.setType(0);
			ntfTagged.setUserFrom(nUserTagger);	
			ntfTagged.setUserTo(nUserTagged);
			
			try {
				notifDao.persist(ntfTagged);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//tag.setCntId(cont.getId().getCntIdAuto());
		//tag.setEvtId(cont.getId().getEvtId());
		
		tag.setContent(cont); 
		//TODO Averiguar si es necesario setear ademas el tag al content, ya setee el content al tag
		cont.getTags().add(tag);
	
		tag.setCreator(nUserTagger);
		//TODO Averiguar si es necesario setear ademas el tag a los users, ya setee los users al tag
		//nUserTagger.getTagsCreated().add(tag);
		
		tag.set_posX_(posX);
		tag.set_posY_(posY);
		
		tag.setRegDate(new Timestamp(new java.util.Date().getTime()));
		
		try {
		tagDao.persist(tag);
		} catch (Exception e){
			e.printStackTrace();
		}
}


	public List<String> uploadContent(int eventID, String username, List<DataTypeFile> files) {
		if (!isUserRelatedToEvent(eventID, username)) {
			throw new UserNotRelatedToEventException();
		}
		List<String> result = new ArrayList<String>();
		Event event = getEvent(eventID);
		NormalUser user = nUserDao.findByID(username);
		Iterator<DataTypeFile> it = files.iterator();
		while (it.hasNext()) {
			DataTypeFile file = (DataTypeFile) it.next();
			SelfContent content;
			if (file.isImage()) {
				content = new Photo();
			}
			else if (file.isVideo()) {
				content = new Video();
				//TODO setVideoDuration
			}
			else {
				throw new UnrecognizedFileTypeException();
			}
			//TODO initMultimediaManager();
			Timestamp t = new Timestamp(new java.util.Date().getTime());
			String tstring = t.toString().replace(':', '-').replace('.', '-');
			String url = fileSystem.writeFile(file.getData(), file.getMime(), eventID + "/", tstring + "-");
			content.setAlbum(false);
			content.setEvent(event);
			content.setUser(user);
			content.setRegDate(t);
			content.setSize((int) file.getLength());
			content.setUrl(url);
			
			contDao.persist(content);
			result.add(content.getCntIdAuto() + "");
		}
	    return result;
	}
	
	public byte[] getContent(int eventID, int contentID) {
		Event event = getEvent(eventID);
		Content content = contDao.findByIDInEvent(event, contentID);
		if (content == null) {
			throw new ContentNotFoundException();
		}
		if (content instanceof Photo) {
			return fileSystem.readFile(content.getUrl());
		}
		return null;
	}
}
