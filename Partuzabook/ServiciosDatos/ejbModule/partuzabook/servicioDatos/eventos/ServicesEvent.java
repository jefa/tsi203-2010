package partuzabook.servicioDatos.eventos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.AlbumDAO;
import partuzabook.datos.persistencia.DAO.CommentDAO;
import partuzabook.datos.persistencia.DAO.ContentCategoryDAO;
import partuzabook.datos.persistencia.DAO.ContentDAO;
import partuzabook.datos.persistencia.DAO.EventDAO;
import partuzabook.datos.persistencia.DAO.EvtCategoryDAO;
import partuzabook.datos.persistencia.DAO.ExternalPhotoDAO;
import partuzabook.datos.persistencia.DAO.ExternalVideoDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.DAO.RatingDAO;
import partuzabook.datos.persistencia.DAO.TagDAO;
import partuzabook.datos.persistencia.DAO.VideoDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Album;
import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.datos.persistencia.beans.Comment;
import partuzabook.datos.persistencia.beans.CommentPK;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.EvtCategory;
import partuzabook.datos.persistencia.beans.ExternalPhoto;
import partuzabook.datos.persistencia.beans.ExternalVideo;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.RatingPK;
import partuzabook.datos.persistencia.beans.SelfContent;
import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagForUser;
import partuzabook.datos.persistencia.beans.User;
import partuzabook.datos.persistencia.beans.Video;
import partuzabook.datos.persistencia.filesystem.FileSystemLocal;
import partuzabook.entityTranslators.TranslatorAlbum;
import partuzabook.entityTranslators.TranslatorCategory;
import partuzabook.entityTranslators.TranslatorContent;
import partuzabook.entityTranslators.TranslatorEventSummary;
import partuzabook.servicioDatos.exception.AlbumNotFoundException;
import partuzabook.servicioDatos.exception.ContentNotFoundException;
import partuzabook.servicioDatos.exception.ContentNotInAlbumException;
import partuzabook.servicioDatos.exception.EventNotFoundException;
import partuzabook.servicioDatos.exception.EvtCategoryNotFoundException;
import partuzabook.servicioDatos.exception.InvalidPositionInAlbumException;
import partuzabook.servicioDatos.exception.UnrecognizedFileTypeException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.servicioDatos.exception.UserNotRelatedToEventException;
import partuzabook.servicioDatos.notificaciones.ServicesNotificationRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Event
 */
@Stateless
public class ServicesEvent implements ServicesEventRemote {

	private static final String YOUTUBE_PRE = "http://www.youtube.com/v/";
	private static final String YOUTUBE_POS = "?fs=1&amp;hl=es_ES";
	private static final String DEFAULT_VIDEO_THB_URL = "video.jpg";
	private static final String PRE_ADMIT_MAIL = "El usuario ";
	private static final String MID_ADMIT_MAIL = " ha solicitado ser agegado al evento ";
	private static final String POS_ADMIT_MAIL = ".";
	private static final String ADMIT_MAIL_SUBJECT = "Solicitud para evento";
	
	private EventDAO eventDao;
	private EvtCategoryDAO evtCategoryDao;
	private ContentCategoryDAO contentCategoryDao;
	private ContentDAO contentDao;
	private CommentDAO commentDao;
	private NormalUserDAO normalUserDao;
	private TagDAO tagDao;
	private NotificationDAO notificationDao;
	private FileSystemLocal fileSystem;
	private RatingDAO ratingDao;
	private AdminDAO adminDao;
	private EvtCategoryDAO eventCategoryDao;
	private AlbumDAO albumDao; 
	private VideoDAO videoDao;
	private ExternalVideoDAO extVideoDao;
	private ExternalPhotoDAO extPhotoDao;
	private ServicesUserRemote servicesUser;
	private ServicesNotificationRemote servicesNotification;

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
			eventDao = (EventDAO) ctx.lookup("EventDAOBean/local");  
			evtCategoryDao = (EvtCategoryDAO) ctx.lookup("EvtCategoryDAOBean/local");  
			contentCategoryDao = (ContentCategoryDAO) ctx.lookup("ContentCategoryDAOBean/local");  
			contentDao = (ContentDAO) ctx.lookup("ContentDAOBean/local");
			commentDao = (CommentDAO) ctx.lookup("CommentDAOBean/local");
			normalUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
			tagDao = (TagDAO) ctx.lookup("TagDAOBean/local");    		
			notificationDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
			fileSystem = (FileSystemLocal) ctx.lookup("FileSystem/local");
			ratingDao = (RatingDAO) ctx.lookup("RatingDAOBean/local");
			adminDao = (AdminDAO) ctx.lookup("AdminDAOBean/local");
			eventCategoryDao = (EvtCategoryDAO) ctx.lookup("EvtCategoryDAOBean/local");
			albumDao = (AlbumDAO) ctx.lookup("AlbumDAOBean/local");
			videoDao = (VideoDAO) ctx.lookup("VideoDAOBean/local");
			extPhotoDao = (ExternalPhotoDAO) ctx.lookup("ExternalPhotoDAOBean/local");
			extVideoDao = (ExternalVideoDAO) ctx.lookup("ExternalVideoDAOBean/local");
			servicesUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			servicesNotification = (ServicesNotificationRemote) ctx.lookup("PartuzabookEAR/ServicesNotification/remote");
		}
        catch (NamingException e) {
			e.printStackTrace();
		}
    }

	@PreDestroy
	public void preDestroy() {
		eventDao = null;
		evtCategoryDao = null;
		contentDao = null;
		commentDao = null;
		normalUserDao = null;
		tagDao = null;
		notificationDao = null;
		ratingDao = null;
		adminDao = null;
		eventCategoryDao = null;
		albumDao = null;
		videoDao = null;
		extVideoDao = null;
		extPhotoDao = null;
		servicesUser = null;
		servicesNotification = null;
	}

	private Event getEvent(int eventID) throws EventNotFoundException { 
		Event event = eventDao.findByID(eventID);
		if (event == null) {
			throw new EventNotFoundException();
		}
    	return event;
    }
    
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents) {
		// Calculate one week before date
		Calendar after = new GregorianCalendar();
		after.set(Calendar.DAY_OF_YEAR, after.get(Calendar.DAY_OF_YEAR) - 7);
		Date afterDate = new Date(after.getTimeInMillis());
		
		// Take the first maxEvents and translate to the datatype
		List<Event> list =  eventDao.findAllAfterDate(afterDate);
		if (maxEvents != -1 && maxEvents < list.size()) {
			list = list.subList(0, maxEvents);
		}
		return TranslatorCollection.translateEventSummary(list);
	}
	
	public DatatypeEvent getEventDetails(int eventID, boolean isSuperUser) {
		Event event = getEvent(eventID);
		DatatypeEvent dat = new DatatypeEvent((DatatypeEventSummary) new TranslatorEventSummary().translate(event));
		if (!isSuperUser && !isAlbumFinalized(eventID)) {
			CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
			ArrayList<CntCategory> categories = new ArrayList<CntCategory>(event.getCntCategories());
			categories.remove(catAlbum);
			dat.setContentCategories(TranslatorCollection.translateContentCategoriesSummary(categories));
		}
		else {
			dat.setContentCategories(TranslatorCollection.
					translateContentCategoriesSummary(event.getCntCategories()));			
		}
		return dat;
	}

	public DatatypeAlbum getAlbumDetails(int eventID) {
		Event event = getEvent(eventID);
		List<Content> albumContents = contentDao.getAllInAlbumOfEvent(event);
		return (DatatypeAlbum)new TranslatorAlbum().translate(albumContents);
	}

	
	public List<DatatypeEventSummary> searchForEventByName(String name, int maxEvents){
		name = name.toLowerCase();
		// First search by full name entered
		List<Event> list =  eventDao.findBySimilarName(name);
		if (list.size() >= maxEvents) {
			list = list.subList(0, maxEvents);
			return TranslatorCollection.translateEventSummary(list);
		} else if (list.size() < maxEvents) {
			int cant = list.size();
			// Try searching for substrings of the entered name
			String[] substr = name.split(" ");
			if (substr.length > 1) {
				int i = 0;
				while (i < substr.length && cant < maxEvents) {
					List<Event> listSubstr = eventDao.findBySimilarName(substr[i]); 
					Iterator<Event> it = listSubstr.iterator();
					while (it.hasNext()) {
						Event ev = it.next();
						if (!list.contains(ev)) {
							list.add(ev);
						}	
					}
					cant = list.size();
					i++;
				}
			}
			if (list.size() == 0){
				return null;
			} else if (list.size() > maxEvents) {
				list = list.subList(0, maxEvents);					
			} 
			return TranslatorCollection.translateEventSummary(list);			
		}

		
		return null;
	}

	public List<DatatypeEventSummary> searchForEventByDate(java.util.Date date, int maxEvents){
/*		String[] composedDate = date.split("/");
		String day = composedDate[0];
		String month = composedDate[1];
		String year = composedDate[2];
*/				
		
		Date sqlDate = new Date(date.getTime());
		// Search by date entered		
		List<Event> list =  eventDao.findByDate(sqlDate);		
		if (list.size() > maxEvents) {
			list = list.subList(0, maxEvents);
			return TranslatorCollection.translateEventSummary(list);
		} else if (list.size() == 0){
			return null;
		} else { 
			return TranslatorCollection.translateEventSummary(list);			
		}
	}


	public List<DatatypeEventSummary> searchForEventBetweenDates(java.util.Date after,
			java.util.Date before, int maxEvents){
		Date sqlAfter = new Date(after.getTime());
		Date sqlBefore = new Date(before.getTime());
		// Search by date entered		
		List<Event> list =  eventDao.findAllBetweenDates(sqlAfter, sqlBefore);		
		if (list.size() > maxEvents) {
			list = list.subList(0, maxEvents);
			return TranslatorCollection.translateEventSummary(list);
		}
		else { 
			return TranslatorCollection.translateEventSummary(list);			
		}
	}


	public boolean isUserRelatedToEvent(int eventId, String username) {
		Event ev = getEvent(eventId);
		if (isUserModeratorInEvent(eventId, username)) {
			return true;
		}
		else {
			// Verify if user is Normal User
			NormalUser nUser = normalUserDao.findByID(username);
			if (nUser == null) {
				return false;
			}
			else {
				return nUser.getMyEvents().contains(ev);
			}
		}
	}

	public boolean isUserModeratorInEvent(Integer eventId, String username) {
		// Verify if user is Admin
		Admin aUser = adminDao.findByID(username);
		if (aUser ==  null) {
			// Verify if user is Normal User
			NormalUser nUser = normalUserDao.findByID(username);
			if (nUser == null) {
				return false;
			}
			Event ev = getEvent(eventId);
			return ev.getMyMods().contains(nUser);
		}
		return true;
	}

	public DatatypeCategory getCategoryContents(int eventID, int categoryID, int startAt, int count) {
		// Verify existence of Event
		Event event = getEvent(eventID);
		CntCategory category = contentCategoryDao.findByIDInEvent(event, categoryID);
		List<Content> list;
		if (category.getCategory().equals("Album")) {
			list = contentDao.getAllInAlbumOfEvent(event);
		} else {
			list = category.getContents();
		}

		if (startAt > 0 && startAt <= list.size()) {
			list = list.subList(startAt - 1, list.size());
			if (list.size() > count) {
				list = list.subList(0, count);
			}
		}
		else {
			list = null;
		}
	
		CntCategory cat = new CntCategory();
		cat.setCatIdAuto(category.getCatIdAuto());
		cat.setCategory(category.getCategory());
		cat.setContents(list);
		return (DatatypeCategory)new TranslatorCategory().translate(cat);
	}

	public DatatypeContent getContentDetails(int contentID, String username) throws ContentNotFoundException {
		Content content = getContentAndVerifyPermission(username, contentID);
		if (content == null) {
			throw new ContentNotFoundException();
		}
		return (DatatypeContent)new TranslatorContent().translate(content);
	}

	public List<DatatypeUser> getUsersForTag(int eventID, int contentID){
		// Verify existence of Event
		Event event = getEvent(eventID);
		// Verify existence of content
		Content cont = (Content) contentDao.findByIDInEvent(event, contentID);
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
				usersAlreadyTagged.add(currentCast.getUserTagged());
			}
		}

		// Obtain a set of all users related to the event 
		List<NormalUser> allUsersInEvent = event.getMyParticipants();
		if (allUsersInEvent.isEmpty()){
			return TranslatorCollection.translateNormalUser(allUsersInEvent); 
		} 

		// Filter users that have already been tagged in this content    	
		allUsersInEvent.removeAll(usersAlreadyTagged);

		return TranslatorCollection.translateNormalUser(allUsersInEvent);
	} 

	public void tagUserInContent(int contentID, String userTagger, String userToTag,
			int posX, int posY) throws EventNotFoundException, ContentNotFoundException, UserNotFoundException {
		// Verify existence of content
		Content cont = (Content) contentDao.findByID(contentID);
		if (cont == null) {
			throw new ContentNotFoundException();
		}
		Event event = cont.getEvent();
		// Verify existence of user who is tagging and user who will be tagged
		User tagger = (User) normalUserDao.findByID(userTagger);
		if (tagger == null || (!(tagger instanceof NormalUser))) {
			throw new UserNotFoundException();
		}
		NormalUser nUserTagger = (NormalUser) tagger;
		// Check if user to tag is registered 
		User tagged = (User) normalUserDao.findByID(userToTag);
		Tag tag;
		if (tagged == null || !event.getMyParticipants().contains(tagged)) {
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
			ntfTagged.setText(tagged.getName() +",\r\nhas sido etiquetado en un contenido del evento \"" + cont.getEvent().getEvtName() + "\" por " + tagger.getName() + ".");
			ntfTagged.setSubject("Has sido etiquetado!");
			ntfTagged.setType(2);
			ntfTagged.setUserFrom(nUserTagger);	
			ntfTagged.setUserTo(nUserTagged);

			try {
				notificationDao.persist(ntfTagged);
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

	public void removeTagInContent(int contentID, String username,
			Boolean userToRemoveIsReal, String userToRemove)
		throws ContentNotFoundException, IllegalAccessException {
		// Verify existence of content
		Content content = getContentAndVerifyPermission(username, contentID);
		if (!isUserModeratorInEvent(content.getEvent().getEvtIdAuto(), username)) {
			throw new IllegalAccessException();
		}
		// Check if user to tag is registered
		Iterator<Tag> it = content.getTags().iterator();
		while (it.hasNext()) {
			Tag tag = (Tag) it.next();
			if (userToRemoveIsReal && tag instanceof TagForUser) {
				if (((TagForUser)tag).getUserTagged().getUsername().equals(userToRemove)) {
					tagDao.remove(tag);
					break;
				}
			}
			else if (!userToRemoveIsReal && tag instanceof TagForNotUser) {
				if (((TagForNotUser)tag).getUsrTagCustom().equals(userToRemove)) {
					tagDao.remove(tag);
					break;
				}
			}
		}
	}
	
	private void sendNotificationsForUploadContent(Content cont, User uploader) {
		if (cont.getEvent().getMyMods() != null) {
			for(ListIterator<NormalUser> it = cont.getEvent().getMyMods().listIterator(); it.hasNext(); ) {
				NormalUser nu = it.next();
				Notification ntfTagged = new Notification();
				ntfTagged.setNotDate(new Timestamp(new java.util.Date().getTime()));
				ntfTagged.setRead(false);
				ntfTagged.setReference("La referencia va aca");
				ntfTagged.setRegDate(new Timestamp(new java.util.Date().getTime()));
				ntfTagged.setText(nu.getName() + ",\r\n" + uploader.getName() + " ha agregado un contenido en el evento \"" + cont.getEvent().getEvtName() + "\".");
				ntfTagged.setSubject("Se subi� un contenido en un evento moderado.");
				ntfTagged.setType(2);
				ntfTagged.setUserFrom(uploader);	
				ntfTagged.setUserTo(nu);

				try {
					notificationDao.persist(ntfTagged);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
	}

	public List<String> uploadContent(int eventID, String username, List<DataTypeFile> files) {
		if (!isUserRelatedToEvent(eventID, username)) {
			throw new UserNotRelatedToEventException();
		}
		List<String> result = new ArrayList<String>();
		Event event = getEvent(eventID);
		User user;
		NormalUser nUser = normalUserDao.findByID(username);
		if (nUser == null) {
			// Try if user is Admin
			Admin admin = adminDao.findByID(username);
			if (admin == null){
				throw new UserNotFoundException();
			}
			user = admin;
		} else {
			user = nUser;
		}
		
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
			String url = fileSystem.writeFile(file.getData(), file.getMime(), eventID + "/");
			content.setEvent(event);
			content.setUser(user);
			content.setRegDate(new Timestamp(new java.util.Date().getTime()));
			content.setSize((int) file.getLength());
			content.setUrl(url);
			content.setDescription(file.getDescription());
			content.setPosGallery(contentDao.findNextPosInGalleryEvent(event));
			CntCategory categoryTodas = contentCategoryDao.findByNameInEvent(event, "Todas");
			if(content.getCntCategories() == null)
				content.setCntCategories(new ArrayList<CntCategory>());
			content.getCntCategories().add(categoryTodas);
			if(categoryTodas.getContents() == null)
				categoryTodas.setContents(new ArrayList<Content>());
			categoryTodas.getContents().add(content);
			contentDao.persist(content);
			if (event.getCover() == null) {
				event.setCover(content);
			}
			result.add(content.getCntIdAuto() + "");
						
			sendNotificationsForUploadContent(content, user);
		}
		return result;
	}

	public byte[] getContent(String username, int contentID, String thumbnail) {
		if (contentID == -1) {
			return fileSystem.readFile("imagen_no_disponible.jpg", thumbnail);
		}
		Content content = getContentAndVerifyPermission(username, contentID);
		if (content instanceof Photo || content instanceof ExternalPhoto) {
			return fileSystem.readFile(content.getUrl(), thumbnail);
		} else if(content instanceof Video || content instanceof ExternalVideo) {
			return fileSystem.readFile(DEFAULT_VIDEO_THB_URL, thumbnail);
		}
		return null;
	}

	public byte[] getPublicContent(String type, int pos, String thumbnail) {
		List<DatatypeContent> contents = null;
		if (type.equals("bestRanked")) {
			contents =  getBestRankedContent(pos);
		}
		else if (type.equals("mostCommented")) {
			contents = getMostCommentedContent(pos);
		}
		if (contents.size() >= pos) {
			DatatypeContent content = contents.get(pos - 1);
			return fileSystem.readFile(contentDao.findByID(content.getContId()).getUrl(), thumbnail);
		}
		return null;
	}
	
	public List<DatatypeContent> getBestRankedContent(int length) {
		List<Integer> list = contentDao.getBestRanked();
		if (list.size() > length) {
			list = list.subList(0, length);
		}
		List<Content> contents = getContentListById(list);
		return TranslatorCollection.translateContent(contents);
	}

	public List<DatatypeContent> getMostCommentedContent(int length) {
		List<Integer> list = contentDao.getMostCommented();
		if (list.size() > length) {
			list = list.subList(0, length);
		}
		List<Content> contents = getContentListById(list);
		return TranslatorCollection.translateContent(contents);
	}

	private List<Content> getContentListById(List<Integer> list) {
		List<Content> contents = new ArrayList<Content>();
		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			contents.add(contentDao.findByID(it.next()));
		}
		return contents;
	}

	public DatatypeEventSummary findEventById(int eventId) {
		Event ev = eventDao.findByID(eventId);
		if (ev != null) {
			TranslatorEventSummary transEv = new TranslatorEventSummary();
			return (DatatypeEventSummary) transEv.translate(ev); 
		} 
		return null;
	}

	public List<DatatypeEventSummary> filterAllEvents(int maxEvents) {
		List<Event> allEvents = eventDao.findAll();
		return TranslatorCollection.translateEventSummary(allEvents);
	}

	public List<DatatypeEventSummary> filterPastEvents(int maxEvents) {
		Date today = new Date(new java.util.Date().getTime());
		List<Event> beforeEvents = eventDao.findAllBeforeDate(today);
		return TranslatorCollection.translateEventSummary(beforeEvents);
	}
	
	public List<DatatypeEventSummary> filterEventsByDate(java.util.Date date, int maxEvents) {
		Date day = new Date(date.getTime());
		List<Event> events = eventDao.findByDate(day);
		return TranslatorCollection.translateEventSummary(events);	
	}
	

	public List<DatatypeEventSummary> filterNextEvents(int maxEvents) {
		Date today = new Date(new java.util.Date().getTime());
		List<Event> afterEvents = eventDao.findAllAfterDate(today);
		return TranslatorCollection.translateEventSummary(afterEvents);
	}

	/**
	 * type == 0 -> "Aniversario"
     * type == 1 -> "Casamiento"
     * type == 2 -> "Cumpleaños de quince"
     * type == 3 -> "Otro"

	 */
	public List<DatatypeEventSummary> filterEventsByEvtCategory(
			int evtCategory, int maxEvents) {
		String categ;
		switch (evtCategory){
			case 0: categ = "Aniversario"; break;
			case 1: categ = "Casamiento"; break;
			case 2: categ = "Cumpleaños de quince"; break;
			case 3: categ = "Otro"; break;
			default: categ = "";
		}
		if (!categ.equals("")) {
			EvtCategory eventCateg = evtCategoryDao.findByID(categ);
			List<Event> eventsByCategory = eventDao.findByCategory(eventCateg);
			return TranslatorCollection.translateEventSummary(eventsByCategory);
		}
		return null;
	}
	
	public void commentContent(int contentID, String textComment,
			String userCommenter) throws Exception {		
		// Verify existence of content
		Content cont = getContentAndVerifyPermission(userCommenter, contentID);
		// Verify existence of user who is commenting
		NormalUser nUser = normalUserDao.findByID(userCommenter);
		// Create Comment
		Comment com = new Comment();
		com.setContent(cont);
		CommentPK pk = new CommentPK();
		pk.setCntId(contentID);
		pk.setUsrId(userCommenter);		
		java.util.Date today = new java.util.Date(); 
		pk.setDate(today);
		com.setId(pk);
		Timestamp now = new Timestamp(today.getTime());
		com.setRegDate(now);
		com.setText(textComment);
		com.setUser(nUser);
		
		//TODO: Javier enviar notificaci�n por mail a los moderadores que hay un nuevo comentario
		//para el contenido en el evento. (Me parece que eso ya se est� haciendo arriba(GG))
		//Agrego que se envie una notificacion por mensajeria interna a los moderadores (GG)
		
		if (cont.getEvent().getMyMods() != null) {
			for(ListIterator<NormalUser> it = cont.getEvent().getMyMods().listIterator(); it.hasNext(); ) {
				NormalUser nu = it.next();
				Notification ntfTagged = new Notification();
				ntfTagged.setNotDate(new Timestamp(new java.util.Date().getTime()));
				ntfTagged.setRead(false);
				ntfTagged.setReference("La referencia va aca");
				ntfTagged.setRegDate(new Timestamp(new java.util.Date().getTime()));
				ntfTagged.setText(nu.getName() + ",\r\n" + userCommenter +" ha comentado un contenido del evento \"" + cont.getEvent().getEvtName() + "\".");
				ntfTagged.setSubject("Se coment� un contenido de un evento moderado.");
				ntfTagged.setType(2);
				ntfTagged.setUserFrom(nUser);	
				ntfTagged.setUserTo(nu);

				try {
					notificationDao.persist(ntfTagged);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		
		commentDao.persist(com);
	}
	
	public void removeCommentFromContent(int eventId, int contentId, String username,
		String userCommenter, String textComment)
			throws ContentNotFoundException, UserNotFoundException, IllegalAccessException {
		if (!isUserModeratorInEvent(eventId, username)) {
			throw new IllegalAccessException();
		}
		// Verify existence of content
		Content content = getContentAndVerifyPermission(username, contentId);
		// Check if user to tag is registered
		Iterator<Comment> it = content.getComments().iterator();
		while (it.hasNext()) {
			Comment comment = (Comment) it.next();
			if (comment.getUser().getUsername().equals(userCommenter) &&
				comment.getText().equals(textComment)) {
				commentDao.remove(comment);
				break;
			}
		}
	}

	public void rateContent(int contentID, int rating,
		String userId) throws Exception {
		// Verify existence of content
		Content cont = (Content) contentDao.findByID(contentID);
		if (cont == null) {
			throw new ContentNotFoundException();
		}
		//TODO verify that user is related to event?
		// Verify existence of user who is commenting
		User user = (User) normalUserDao.findByID(userId);
		if (user == null || (!(user instanceof NormalUser))) {
			throw new UserNotFoundException();
		}
		NormalUser nUser = (NormalUser) user;
		
		// Create Rating
		RatingPK pk = new RatingPK();
		pk.setCntId(contentID);
		pk.setUsrId(userId);
		Rating rate = ratingDao.findByID(pk);
		if (rating == 0) {
			if (rate != null) {
				ratingDao.remove(rate);
			}
			return;
		}
		if (rate == null) {
			rate = new Rating();
			rate.setContent(cont);
			rate.setId(pk);
			rate.setUser(nUser);
		}
		Date today = new Date(new java.util.Date().getTime()); 
		Timestamp now = new Timestamp(today.getTime());
		rate.setRegDate(now);
		rate.setScore(rating);
		
		ratingDao.persist(rate);		
	}
	
	private Content getContentAndVerifyPermission(String username, int contentID) {
		Content content = contentDao.findByID(contentID);
		if (content == null) {
			throw new ContentNotFoundException();
		}
		// El cover es publico
		if (content.getEvent().getCover() != null &&
				content.getEvent().getCover().equals(content)) {
			return content;
		}
		// Si es del album tambien es publico
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(content.getEvent(), "Album");
		if (catAlbum !=  null && content.getCntCategories().contains(catAlbum)) {
			return content;
		}
		if (!isUserRelatedToEvent(content.getEvent().getEvtIdAuto(), username)) {
			throw new UserNotRelatedToEventException();
		}
		return content;
	}
	
	public DatatypeEventSummary createEvent(String name, String description,
			java.util.Date date, String duration, String address, String creator,
			boolean moderated, String category, double latitude, double longitude, String hashtag) throws UserNotFoundException, EvtCategoryNotFoundException{
		
		Admin a = adminDao.findByID(creator);
		if(a == null) {
			throw new UserNotFoundException();
		}
		
		EvtCategory eCat = eventCategoryDao.findByID(category);
		if(eCat == null) {
			throw new EvtCategoryNotFoundException();
		}
		
		Event evt = new Event();
		
		if(moderated)
			evt.setMyMods( new ArrayList<NormalUser>());
		else 
			evt.setMyMods(null);
		
		evt.setEvtName(name);
		evt.setDescription(description);
		evt.setDate(new Timestamp(date.getTime()));
		evt.setDuration(duration);
		evt.setAddress(address);
		evt.setCreator(a);
		evt.setEvtCategory(eCat);
		evt.setLatitude(latitude);
		evt.setLongitude(longitude);
		evt.setHashtag(hashtag);
		evt.setRegDate(new Timestamp(new java.util.Date().getTime()));

		// Create default category for contents: "Todas" 
		CntCategory defaultCategory = new CntCategory();
		defaultCategory.setCategory("Todas");
		defaultCategory.setContents(new ArrayList<Content>());
		defaultCategory.setEvent(evt);

		// Create category for album 
		CntCategory albumCategory = new CntCategory();
		albumCategory.setCategory("Album");
		albumCategory.setContents(new ArrayList<Content>());
		albumCategory.setEvent(evt);

		evt.setCntCategories(new ArrayList<CntCategory>());
		evt.getCntCategories().add(albumCategory);
		evt.getCntCategories().add(defaultCategory);
		
		eventDao.persist(evt);
		contentCategoryDao.persist(defaultCategory);
		contentCategoryDao.persist(albumCategory);
		
		return (DatatypeEventSummary)new TranslatorEventSummary().translate(evt);
	}

	public DatatypeEventSummary addParticipantstoEvent(int evt_id, List<String> newParts)
	throws EventNotFoundException, UserNotFoundException {

		Event event = getEvent(evt_id);

		if(event.getMyParticipants() == null)
			event.setMyParticipants(new ArrayList<NormalUser>());
		
		//Verificamos que existan todos los usuarios
		List<NormalUser> newUsers = new ArrayList<NormalUser>();
		for(Iterator<String> it = newParts.iterator(); it.hasNext(); ) {
			String username = it.next();
			NormalUser user = normalUserDao.findByID(username);
			if(user == null)
				throw new UserNotFoundException();
			newUsers.add(user);
		}
		
		//Generamos una lista con los nombres de los participantes viejos
		List<String> actualParts = new ArrayList<String>();
		for(Iterator<NormalUser> it = event.getMyParticipants().iterator(); it.hasNext(); ) {
			actualParts.add(it.next().getUsername());
		}
		
		//Agregamos los nuevos participantes 
		for(Iterator<NormalUser> it = newUsers.iterator(); it.hasNext(); ) {
			NormalUser newPart = it.next();
			if(!actualParts.contains(newPart.getUsername())){
				event.getMyParticipants().add(newPart);
				newPart.getMyEvents().add(event);
				//nUserDao.persist(newMod);
			}
		}

		eventDao.persist(event);

		return (DatatypeEventSummary)new TranslatorEventSummary().translate(event);
	}

	
	public DatatypeEventSummary addModtoEvent(int evt_id, List<String> newMods)
	throws EventNotFoundException, UserNotFoundException {

		//No es necesario el control de null, porque ya se hace en getEvent
		Event event = getEvent(evt_id);

		if(event.getMyMods() == null)
			event.setMyMods(new ArrayList<NormalUser>());
		
		//Verificamos que existan todos los usuarios
		List<NormalUser> newUsers = new ArrayList<NormalUser>();
		for(Iterator<String> it = newMods.iterator(); it.hasNext(); ) {
			String username = it.next();
			NormalUser user = normalUserDao.findByID(username);
			if(user == null)
				throw new UserNotFoundException();
			newUsers.add(user);
		}
		
		// Verifico que todos sean participantes del evento. En caso negativo, los agrego.
		if(event.getMyParticipants() == null)
			event.setMyParticipants(new ArrayList<NormalUser>());
		for(Iterator<NormalUser> it = newUsers.iterator(); it.hasNext(); ) {
			NormalUser currentUser = it.next();
			if (!event.getMyParticipants().contains(currentUser)) {
				event.getMyParticipants().add(currentUser);
			}
		}

		//Generamos una lista con los nombres de los moderados viejos
		List<String> actualMods = new ArrayList<String>();
		for(Iterator<NormalUser> it = event.getMyMods().iterator(); it.hasNext(); ) {
			actualMods.add(it.next().getUsername());
		}
		//Agregamos los nuevos moderadores que no existian
		for(Iterator<NormalUser> it = newUsers.iterator(); it.hasNext(); ) {
			NormalUser newMod = it.next();
			if(!actualMods.contains(newMod.getUsername())){
				event.getMyMods().add(newMod);
				newMod.getMyModeratedEvents().add(event);
				//nUserDao.persist(newMod);
			}
		}

		eventDao.persist(event);

		return (DatatypeEventSummary)new TranslatorEventSummary().translate(event);
	}

	public List<String> findAllEvtCategories() {
		List<String> res = new ArrayList<String>();
		for(Iterator<EvtCategory> it = eventCategoryDao.findAll().iterator(); it.hasNext(); ) {
			res.add(it.next().getCategory());
		}
		return res;
	}

	public List<DatatypeContent> getOrderedAlbum(int eventId) {
		Event event = getEvent(eventId);
		List<Content> contents = contentDao.getOrderedAlbum(event);
		return TranslatorCollection.translateContent(contents);
	}
	
	public DatatypeCategory existsAlbum(int eventID){
		Event event = getEvent(eventID);
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) return null;
		TranslatorCategory transCat = new TranslatorCategory();
		return (DatatypeCategory) transCat.translate(catAlbum);
	}
	
	public boolean isAlbumFinalized(int eventID){
		Event event = getEvent(eventID);
		Album album = event.getAlbum();
		return (album != null);
	}
	
	public void addContentToAlbum(int contentID, int eventID) {
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		// Verify if content exists 
		Content currentContent = contentDao.findByIDInEvent(event, contentID);
		if (currentContent == null)
			throw new ContentNotFoundException();
		// Associate to album and set pos to content
		if (!currentContent.getCntCategories().contains(catAlbum)) {
			currentContent.getCntCategories().add(catAlbum);
			if (!catAlbum.getContents().contains(currentContent)) {
				catAlbum.getContents().add(currentContent);
				currentContent.setPosAlbum(contentDao.findNextPosInAlbumEvent(event));
			}
		} 	
	}
	
	public List<DatatypeContent> getAlbumContents(int eventID){
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		return TranslatorCollection.translateContent(contentDao.getAllInAlbumOfEvent(event));
	}
	
	public void changeAlbumOrder(int eventID, int[] newOrder){
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		int cantCont = catAlbum.getContents().size();
//		// Si no me pasan la misma cantidad de nuevas posiciones da error
//		if (newOrder.length != cantCont) {
//			throw new Exception();
//		}

		int[] listCntId = new int[cantCont];
		for (int i = 0; i < cantCont; i++){
			listCntId[i] = contentDao.findByPosAlbum(event, i).getCntIdAuto();
		}

	/*	for(ListIterator<Content> it = catAlbum.getContents().listIterator(); it.hasNext(); ) {
			Content cnt = it.next();
			cnt.setPosAlbum(newOrder[cnt.getPosAlbum()]);
			contentDao.persist(cnt);
		}
		*/
		for (int i = 0; i < cantCont; i++){
			// Change position of content
			changePosInAlbum(listCntId[newOrder[i]],eventID,i);
		}			
		
	//	eventDao.persist(event);
		
	}
	
	private void changePosInAlbum(int contentId, int eventID, int newPos) {
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		// Verify if content is part of the album
		Content currentContent = contentDao.findByIDInEvent(event, contentId);
		if (currentContent == null)
			throw new ContentNotFoundException();
		if (!currentContent.getCntCategories().contains(catAlbum))
			throw new ContentNotInAlbumException();
/*		Content otherContent = contentDao.findByPosAlbum(event, newPos);
		if (otherContent == null) 
			throw new InvalidPositionInAlbumException();
*/		// Exchange positions in album
		currentContent.setPosAlbum(newPos);
//		otherContent.setPosAlbum(oldPos);
/*		int indexOtherContent = catAlbum.getContents().indexOf(otherContent);
		catAlbum.getContents().get(indexOtherContent).setPosAlbum(oldPos);
		int indexCurrentContent = catAlbum.getContents().indexOf(currentContent);
		catAlbum.getContents().get(indexCurrentContent).setPosAlbum(newPos);
		sortContentsInAlbum(catAlbum);
		contentCategoryDao.persist(catAlbum);	
		contentDao.persist(currentContent);
		contentDao.flush();
*/	}
	
	public void removeContentFromAlbum(int contentID, int eventID) {
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		// Verify if content exists 
		Content currentContent = contentDao.findByIDInEvent(event, contentID);
		if (currentContent == null)
			throw new ContentNotFoundException();
		// Associate to album and set pos to content
		if (currentContent.getCntCategories().contains(catAlbum)) {
			if (catAlbum.getContents().contains(currentContent)) {
				int posRemoved = currentContent.getPosAlbum();
				Content lastContent = contentDao.findByPosAlbum(event, contentDao.findNextPosInAlbumEvent(event)-1);
				if (lastContent != null) {
					// Exchange last with position that was removed
					lastContent.setPosAlbum(posRemoved);
				}
				catAlbum.getContents().remove(currentContent);
				currentContent.getCntCategories().remove(catAlbum);
				currentContent.setPosAlbum(null);
				sortContentsInAlbum(catAlbum);
				contentDao.persist(lastContent);
				contentDao.persist(currentContent);
			}
		} 	
		
	}
	
	public void setCoverImage(int contentID, int eventID) {
		Event event = getEvent(eventID);
		Content content = contentDao.findByID(contentID);
		if (content == null) {
			throw new ContentNotFoundException();
		}
		event.setCover(content);
	}
	
	private void sortContentsInAlbum(CntCategory catAlbum){
		List<Content> contents = catAlbum.getContents();
		int i = 0;
		while (i < contents.size()){
			if (contents.get(i).getPosAlbum() != i) {
				Content contI = contents.get(i);
				contents.remove(i);
				contents.add(contI);
				int j = i;
				while (j < contents.size()) {
					if (contents.get(j).getPosAlbum() == i) {
						// Exchange content in position i with content in position j
						Content contJ = contents.get(j);
						contents.remove(j);
						contents.add(i, contJ);
						break;	
					} else {
						j++; 
					}
				}
				if (contents.get(i).getPosAlbum() != i) {
					// No encontre content con esa pos
					throw new ContentNotFoundException();
				}
			}
			i++;
		}
		
	}
	
	public void finalizeAlbum(int eventID){
		Event event = getEvent(eventID);
		// Search for Album category
		CntCategory catAlbum = contentCategoryDao.findByNameInEvent(event, "Album");
		if (catAlbum == null) {	
			throw new AlbumNotFoundException();
		}
		Album album = new Album();
		// TODO: generar la url
		album.setAlbumUrl("");
		album.setEvent(event);
		album.setRegDate(new Timestamp(new java.util.Date().getTime()));
		albumDao.persist(album);
		event.setAlbum(album);
		eventDao.persist(event);
	}
	
	
	public void removeContentFromEvent(Integer eventId, Integer contentId, String username)
		throws EventNotFoundException, ContentNotFoundException, IllegalAccessException {
		if (!isUserModeratorInEvent(eventId, username)) {
			throw new IllegalAccessException();
		}
		Content content = getContentAndVerifyPermission(username, contentId);
		Iterator<CntCategory> itCntCategory = content.getCntCategories().iterator();
		while (itCntCategory.hasNext()) {
			CntCategory cntCategory = (CntCategory) itCntCategory.next();
			if (cntCategory.getContents().size() == 1) {
				contentCategoryDao.remove(cntCategory);
			}
			else {
				cntCategory.getContents().remove(content);
			}
		}
		Iterator<Rating> itRating = content.getRatings().iterator();
		while (itRating.hasNext()) {
			Rating rating = (Rating) itRating.next();
			ratingDao.remove(rating);
		}
		Iterator<Tag> itTag = content.getTags().iterator();
		while (itTag.hasNext()) {
			Tag tag = (Tag) itTag.next();
			tagDao.remove(tag);
		}
		Iterator<Comment> itComment = content.getComments().iterator();
		while (itComment.hasNext()) {
			Comment comment = (Comment) itComment.next();
			commentDao.remove(comment);
		}
		contentDao.remove(content);
	}
	
	public int getMyRatingForContent(Integer contentId, String username) {
		Rating rating = ratingDao.findByContentAndUsername(contentId, username);
		if (rating != null) {
			return rating.getScore();
		}
		return 0;
	}
	
	public List<DatatypeAlbum> getRecentAlbums(int maxAlbums) {
		// Calculate one week before date
/*		Calendar after = new GregorianCalendar();
		after.set(Calendar.DAY_OF_YEAR, after.get(Calendar.DAY_OF_YEAR) - 7);
		Date afterDate = new Date(after.getTimeInMillis());
		
		List<Album> list =  albumDao.findAllAfterDate(afterDate);
*/		
		// Take the first maxEvents and translate to the datatype
		List<Album> list =  albumDao.findAll(); 
		if (maxAlbums < list.size()) {
			list = list.subList(0, maxAlbums);
		}
		return TranslatorCollection.translateAlbums(list);
	}

	public int uploadYoutubeVideo(int eventId, String creator,
			String youtube_id, String description) {
		
		if (!isUserRelatedToEvent(eventId, creator)) {
			throw new UserNotRelatedToEventException();
		}
		
		Event event = getEvent(eventId);
		User user;
		NormalUser nUser = normalUserDao.findByID(creator);
		if (nUser == null) {
			// Try if user is Admin
			Admin admin = adminDao.findByID(creator);
			if (admin == null){
				throw new UserNotFoundException();
			}
			user = admin;
		} else {
			user = nUser;
		}	
		Video content = new Video();	
		content.setEvent(event);
		content.setUser(user);
		content.setRegDate(new Timestamp(new java.util.Date().getTime()));
		content.setUrl(YOUTUBE_PRE + youtube_id + YOUTUBE_POS);
		content.setDescription(description);
		content.setPosGallery(contentDao.findNextPosInGalleryEvent(event));
		content.setDuration("");
		content.setSize(0);
		//contDao.persist(content);
		videoDao.persist(content);
		
		CntCategory categoryTodas = contentCategoryDao.findByNameInEvent(event, "Todas");
		
		if(content.getCntCategories() == null) 
			content.setCntCategories(new ArrayList<CntCategory>());
		content.getCntCategories().add(categoryTodas);
		if(categoryTodas.getContents() == null)
			categoryTodas.setContents(new ArrayList<Content>());
		categoryTodas.getContents().add(content);
		contentDao.persist(content);
		
		sendNotificationsForUploadContent(content, user);
		
		return content.getCntIdAuto();		
	}
	
	public void addCategoryToContent(int cntId, List<String> catsToAdd) {
		if(catsToAdd == null || catsToAdd.size() == 0)
			return;

		Content content = contentDao.findByID(cntId);
		if(content == null)
			throw new ContentNotFoundException();
				
		//Obtenemos las categorias del contenido
		List<Integer> myCatsId = new ArrayList<Integer>();
		for(Iterator<CntCategory> it = content.getCntCategories().iterator(); it.hasNext(); ) {
			myCatsId.add(it.next().getCatIdAuto());
		} 
		
		//Agregamos las categorias
		for(Iterator<String> it = catsToAdd.iterator(); it.hasNext(); ) {
			String categoryName = it.next();
			CntCategory category = contentCategoryDao.findByNameInEvent(content.getEvent(), categoryName);
			if(category == null){
				//No existe la categoria, la creamos y la agregamos al contenido
				CntCategory newCat = new CntCategory();
				newCat.setCategory(categoryName);
				newCat.setEvent(content.getEvent());
				contentCategoryDao.persist(newCat);
				
				newCat.setContents(new ArrayList<Content>());
				newCat.getContents().add(content);
				content.getCntCategories().add(newCat);
			} else if(!myCatsId.contains(category.getCatIdAuto())) {
				//La categoria existe, pero no pertenece al contenido, la agregamos
				category.getContents().add(content);
				content.getCntCategories().add(category);
			}
		}
		contentDao.persist(content);
	}
	/*
	public void testVideo() {
		NormalUser nu = nUserDao.findByID("ggismero");
		
		Event event = evDao.findByID(1001);
		Video content = new Video();
		String youtube_id = "TEST";
		content.setAlbum(false);
		content.setEvent(event);
		content.setUser(nu);
		content.setRegDate(new Timestamp(new java.util.Date().getTime()));
		content.setUrl("http://www.youtube.com/watch?v=" + youtube_id + "&feature=youtube_gdata");
		content.setDescription("una desc");
		content.setPos(contDao.findNextPosInGalleryEvent(event));
		content.setDuration("");
		content.setSize(0);
		//TODO: Setear la duraci�n del videos			
		//contDao.persist(content);
		
		videoDao.persist(content);
		try {
			videoDao.flush();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	*/

	public DatatypeEventSummary updateEvent(int evt_id, String name,
			String description, java.util.Date date, String duration,
			String address, String creator, String category,
			double latitude, double longitude, String hashtag) throws UserNotFoundException,
			EvtCategoryNotFoundException, EventNotFoundException {
		
		Event evt = eventDao.findByID(evt_id);
		if(evt == null) {
			throw new EventNotFoundException();
		}			
		
		Admin a = adminDao.findByID(creator);
		if(a == null) {
			throw new UserNotFoundException();
		}

		EvtCategory eCat = eventCategoryDao.findByID(category);
		if(eCat == null) {
			throw new EvtCategoryNotFoundException();
		}	

		evt.setEvtName(name);
		evt.setDescription(description);
		evt.setDate(new Timestamp(date.getTime()));
		evt.setDuration(duration);
		evt.setAddress(address); 
		evt.setCreator(a);
		evt.setEvtCategory(eCat);
		evt.setLatitude(latitude);
		evt.setLongitude(longitude);
		evt.setHashtag(hashtag);

		eventDao.persist(evt);
		
		return (DatatypeEventSummary)new TranslatorEventSummary().translate(evt);
		
	}

	public DatatypeEventSummary updateModsEvent(int evt_id, List<String> mods)
			throws EventNotFoundException, UserNotFoundException {
				
		//No es necesario el control de null, porque ya se hace en getEvent
		Event event = getEvent(evt_id);

		if(event.getMyMods() == null) {
			event.setMyMods(new ArrayList<NormalUser>());
			return addModtoEvent(evt_id, mods);
		} else if(event.getMyMods().size() == 0) {
			return addModtoEvent(evt_id, mods);
		} else {
			//Verificar si debeo quitar moderadores
			
			//Verificamos que existan todos los usuarios
			List<NormalUser> newUsers = new ArrayList<NormalUser>();
			for(Iterator<String> it = mods.iterator(); it.hasNext(); ) {
				String username = it.next();
				NormalUser user = normalUserDao.findByID(username);
				if(user == null)
					throw new UserNotFoundException();
				newUsers.add(user);
			}
			//Generamos una lista con los nombres de los moderados viejos
			List<String> actualMods = new ArrayList<String>();
			for(Iterator<NormalUser> it = event.getMyMods().iterator(); it.hasNext(); ) {
				actualMods.add(it.next().getUsername());
			}
			//Borramos los moderadores que no est�n en la lista
			for(Iterator<String> it = actualMods.iterator(); it.hasNext(); ) {
				String oldMod = it.next();
				if(!mods.contains(oldMod)) {
					//El moderador oldMod no esta en la nueva lista, debo borrarlo
					NormalUser oldModNU = normalUserDao.findByID(oldMod);
					event.getMyMods().remove(oldModNU);
					oldModNU.getMyModeratedEvents().remove(event);
				}
			}
			//Agregamos los nuevos moderadores que no existian y borramos los que no pertenezcan
			for(Iterator<NormalUser> it = newUsers.iterator(); it.hasNext(); ) {
				NormalUser newMod = it.next();
				if(!actualMods.contains(newMod.getUsername())){
					event.getMyMods().add(newMod);
					newMod.getMyModeratedEvents().add(event);
				}
			}
			eventDao.persist(event);
			
			return (DatatypeEventSummary)new TranslatorEventSummary().translate(event);
		}
		
	}
	
	public void sendAdmitMail(Integer eventId, String username)
		throws EventNotFoundException, UserNotFoundException {
		
		if (!isUserRelatedToEvent(eventId, username)) {
			//TODO Javier: Enviar el mail a los administradores diciendo que username quiere
			//acceder al contenido del evento eventId
			
			NormalUser user = normalUserDao.findByID(username);
			if(user == null)
				throw new UserNotFoundException("No existe el usuario " + username);
						
			Event event = getEvent(eventId);
			Admin admin = event.getCreator();
			String body = PRE_ADMIT_MAIL + '[' + username + "] " + user.getName() + MID_ADMIT_MAIL + '[' + eventId + "] " + event.getEvtName() + POS_ADMIT_MAIL;
			
			servicesNotification.createNotification(username, admin.getUsername(), Notification.OTHER_NOTIF_TYPE, body, ADMIT_MAIL_SUBJECT);
		
		}
	}

	public int uploadExternVideo(int eventId, String creator,
			String description, String url) {
		
		if (!isUserRelatedToEvent(eventId, creator)) {
			throw new UserNotRelatedToEventException();
		}
		
		Event event = getEvent(eventId);
		
		User user;
		NormalUser nUser = normalUserDao.findByID(creator);
		if (nUser == null) {
			// Try if user is Admin
			Admin admin = adminDao.findByID(creator);
			if (admin == null){
				throw new UserNotFoundException();
			}
			user = admin;
		} else {
			user = nUser;
		}
		
		ExternalVideo content = new ExternalVideo();	
		content.setEvent(event);
		content.setUser(user);
		content.setRegDate(new Timestamp(new java.util.Date().getTime()));
		//Debo modificar la url para que quede como las dem�s. http://www.youtube.com/watch?v=sdV4xpTiS1s&feature=topvideos
		if (url.startsWith("http://www.youtube.com/watch?v=")) {
			url = url.substring(31, 42);
		}
		else if (url.startsWith("http://www.youtube.com/v/")) {
			url = url.substring(25, 36);
		}
		String urlToSave = YOUTUBE_PRE + url + YOUTUBE_POS;
		content.setUrl(urlToSave);
		
		content.setDescription(description);
		content.setPosGallery(contentDao.findNextPosInGalleryEvent(event));
		
		extVideoDao.persist(content);
		
		CntCategory categoryTodas = contentCategoryDao.findByNameInEvent(event, "Todas");
		
		if(content.getCntCategories() == null) 
			content.setCntCategories(new ArrayList<CntCategory>());
		content.getCntCategories().add(categoryTodas);
		if(categoryTodas.getContents() == null)
			categoryTodas.setContents(new ArrayList<Content>());
		categoryTodas.getContents().add(content);
		contentDao.persist(content);
		
		sendNotificationsForUploadContent(content, user);
		
		return content.getCntIdAuto();
	}

	public int uploadExternPhoto(int eventId, String creator,
			String description, String url) {
		
		if (!isUserRelatedToEvent(eventId, creator)) {
			throw new UserNotRelatedToEventException();
		}
		
		Event event = getEvent(eventId);
		
		User user;
		NormalUser nUser = normalUserDao.findByID(creator);
		if (nUser == null) {
			// Try if user is Admin
			Admin admin = adminDao.findByID(creator);
			if (admin == null){
				throw new UserNotFoundException();
			}
			user = admin;
		} else {
			user = nUser;
		}
		
		ExternalPhoto content = new ExternalPhoto();	
		
		String urlToSave = fileSystem.saveExternalFile(eventId + "/", url);
		
		content.setEvent(event);
		content.setUser(user);
		content.setRegDate(new Timestamp(new java.util.Date().getTime()));
		content.setUrl(urlToSave);
		content.setDescription(description);
		content.setPosGallery(contentDao.findNextPosInGalleryEvent(event));
		
		extPhotoDao.persist(content);
		
		CntCategory categoryTodas = contentCategoryDao.findByNameInEvent(event, "Todas");
		
		if(content.getCntCategories() == null) 
			content.setCntCategories(new ArrayList<CntCategory>());
		content.getCntCategories().add(categoryTodas);
		if(categoryTodas.getContents() == null)
			categoryTodas.setContents(new ArrayList<Content>());
		categoryTodas.getContents().add(content);
		contentDao.persist(content);
		
		sendNotificationsForUploadContent(content, user);
		
		return content.getCntIdAuto();	
	}

}
