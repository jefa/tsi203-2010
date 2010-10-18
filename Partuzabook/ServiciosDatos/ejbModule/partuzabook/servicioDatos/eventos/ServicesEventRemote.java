package partuzabook.servicioDatos.eventos;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.exception.EventNotFoundException;
import partuzabook.servicioDatos.exception.EventNotModeratedException;
import partuzabook.servicioDatos.exception.EvtCategoryNotFoundException;
import partuzabook.servicioDatos.exception.UserNotFoundException;

@Remote
public interface ServicesEventRemote {
	
	/**
	 * Creates a new Event in the database and returns a datatype which represents the event.
	 * @param name				- Name of the new event
	 * @param description		- The description of the new event
	 * @param date				- Date of the event
	 * @param duration			- Duration of the event in minutes
	 * @param address 			- Address of the new event
	 * @param creator			- username of the admin who created the event
	 * @param moderated			- true if the event is moderated, false otherwise
	 * @param category			- category for the event
	 * @return	A Datatype representing the event
	 */
	public DatatypeEventSummary createEvent(String name, String description, 
			Date date, int duration, String address, String creator, boolean moderated, 
			String category) throws UserNotFoundException, EvtCategoryNotFoundException;
	
	/**
	 * Adds new Users as mods to event with evt_id_auto = evt_id. If the user is only a normal user, it transforms to a client.
	 * @param evt_id			- id of the event
	 * @param newMods			- The new mods to add for the event
	 * */
	public DatatypeEventSummary addModtoEvent(int evt_id, List<String> newMods) 
		throws EventNotFoundException, UserNotFoundException, EventNotModeratedException ;
	
	/**
	 * Returns a list of all the categories for events
	 * @return	A list of all the categories for events
	 * */
	public List<String> findAllEvtCategories();
	
    /**
     * Returns a list of Events of interest (Eg: Events from this week)
     * @param maxEvents				- Max number of events to return 
     * @param maxContentPerEvent	- Max number of content to return for each event
     */ 
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents,
			int maxContentsPerEvent);
	
	/**
	 * Returns the photo at position in the photo gallery associated to the event
	 * @param eventName - Name of the Event 
	 * @param pos - Position of content from Photo Gallery of the event
	 */
	public DatatypeContent getGalleryPhotoAtPos(int eventID, int pos);
	
	/**
	 * Returns true if the User exists, and is related to the Event
	 * @param eventName				- Name of the event
	 * @param user					- Identifier of the user  
	 */
	public boolean isUserRelatedToEvent(int eventID, String user);
	
	/**
	 * Returns a list of candidate Users for Tagging -participants of the event, who have not already been tagged in the content-
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content 
	 */
	public List<DatatypeUser> getUsersForTag(int eventID, int contentID);	
	
	/**
	 * Create a new instance of Tag associated to the content, user that was tagged, and the tagger
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content within the event
	 * @param userTagger			- Identifier of the user who is tagging
	 * @param userToTag				- User tagged -may be a registered user or not- 
	 * @param posX					- Position of the tag in the X axis within the content
	 * @param posY					- Position of the tag in the Y axis within the content 
	 */
	public void tagUserInContent(int eventID, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception;

	public List<String> uploadContent(int eventID, String username, List<DataTypeFile> list); 
	public byte[] getContent(int eventID, String username, int contentID);
	public byte[] getContentThumbnail(int eventID, String username, int contentID);
	
	public DatatypeContent getContentInfo(int eventID, int contentID);
	
	/**
     * Returns a list of Events that match the name provided
     * @param name				- Name to search  
     * @param maxEvents			- Max number of events to return in the list
     */ 
	public List<DatatypeEventSummary> searchForEventByName(String name, int maxEvents);
	
	/**
     * Returns a list of Events taking place on that day
     * @param date				- Date to search  
     * @param maxEvents			- Max number of events to return in the list
     */ 
	public List<DatatypeEventSummary> searchForEventByDate(Date date, int maxEvents);
	
	
	/**
	 * Returns a list of the best picture for each event, ordered by rating
	 * @param lenght - length of the list to return 
	 */
	public List<DatatypeContent> getBestQualifiedPictures(int length);
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 * @param lenght - length of the list to return
	 */
	public List<DatatypeContent> getMostCommentedPictures(int length);
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 * @param lenght - length of the list to return
	 */
	public List<DatatypeMostTagged> getMostTagged(int lenght);

	/**
     * Returns information of the Event identified by eventId
     * @param eventId			- Event identifier
     */ 
	public DatatypeEventSummary findEventById(int eventId);

	/**
     * Returns a list of maxEvents Events with no filter
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterAllEvents(int maxEvents);

	/**
     * Returns a list of maxEvents Events that have already occurred
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterPastEvents(int maxEvents);

	/**
     * Returns a list of maxEvents upcoming Events 
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterNextEvents(int maxEvents);

	/**
	 * Create a new Comment associated to the content and to the creator user 
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content within the event
	 * @param textComment			- Comment
	 * @param userCommenter			- Identifier of the user who is commenting
	 * @throws Exception 
	 */
	public void commentContent(int eventID, int contentID, String textComment, String userCommenter) throws Exception;

	/**
	 * User rates the content
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content within the event
	 * @param rating				- Score from 1 to 5 
	 * @param user					- Identifier of the user who is rating
	 * @throws Exception 
	 */
	public void rateContent(int eventID, int contentID, int rating, String user) throws Exception;

}
