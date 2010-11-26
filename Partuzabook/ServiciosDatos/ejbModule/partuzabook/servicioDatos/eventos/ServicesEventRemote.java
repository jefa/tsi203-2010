package partuzabook.servicioDatos.eventos;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeAlbum;
import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.servicioDatos.exception.ContentNotFoundException;
import partuzabook.servicioDatos.exception.EventNotFoundException;
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
			Date date, String duration, String address, String creator, boolean moderated, 
			String category, double latitude, double longitude, String hashtag) throws UserNotFoundException, EvtCategoryNotFoundException;
	
	/**
	 * Update a Event in the database and returns a datatype which represents the event.
	 * @param evt_id			- Id of the event to update
	 * @param name				- Name of the event
	 * @param description		- The description of the event
	 * @param date				- Date of the event
	 * @param duration			- Duration of the event in minutes
	 * @param address 			- Address of the event
	 * @param creator			- username of the admin who created the event
	 * @param moderated			- true if the event is moderated, false otherwise
	 * @param category			- category for the event
	 * @return	A Datatype representing the event
	 */
	public DatatypeEventSummary updateEvent(int evt_id, String name, String description, 
			Date date, String duration, String address, String creator, String category, 
			double latitude, double longitude, String hashtag) throws UserNotFoundException, EvtCategoryNotFoundException;
	
	
	/**
	 * Adds new Users as participants to event with evt_id_auto = evt_id. 
	 * @param evt_id			- id of the event
	 * @param newParts			- The new participants to add for the event
	 * */
	public DatatypeEventSummary addParticipantstoEvent(int evt_id, List<String> newParts)
	throws EventNotFoundException, UserNotFoundException;

	
	/**
	 * Adds new Users as mods to event with evt_id_auto = evt_id. If the user is only a normal user, it transforms to a client.
	 * @param evt_id			- id of the event
	 * @param newMods			- The new mods to add for the event
	 * */
	public DatatypeEventSummary addModtoEvent(int evt_id, List<String> newMods) 
		throws EventNotFoundException, UserNotFoundException ;
	
	/**
	 * Updates the event's mods with evt_id_auto = evt_id. If the user is only a normal user.
	 * @param evt_id			- id of the event
	 * @param mods				- The new mods to add for the event
	 * */
	public DatatypeEventSummary updateModsEvent(int evt_id, List<String> mods) 
		throws EventNotFoundException, UserNotFoundException ;
	
	
	/**
	 * Returns a list of all the categories for events
	 * @return	A list of all the categories for events
	 * */
	public List<String> findAllEvtCategories();
	
    /**
     * Returns a list of Events of interest (Eg: Events from this week)
     * @param maxEvents				- Max number of events to return. If maxEvents is -1 it returns all the events
     */ 
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents);
	
    /**
     * Returns the full details of the event including its categories
	 * @param eventID				- Identifier of the event
	 * @return						- Details for the event including its categories
     */ 
	public DatatypeEvent getEventDetails(int eventID, boolean isSuperUser);

    /**
     * Returns the full details of the album associated to the event
	 * @param eventID				- Identifier of the event
	 * @param isSuperUser			- True if it's being invoked from SuperUser UI
	 * @return						- Details for album and its contents
     */ 
	public DatatypeAlbum getAlbumDetails(int eventID);

	/**
	 * Returns a paged result of the contents for the category in the event
	 * @param eventID				- Identifier of the event
	 * @param categoryID			- Identifier of the category in the event
	 * @param startAt				- Start index for the paged result contents
	 * @param count					- Max returned contents
	 * @return						- List of the contents for the category
	 */
	public DatatypeCategory getCategoryContents(int eventID, int categoryID, int startAt, int count);
	
	/**
	 * Returns the complete description for the content including description,
	 * comments, tags and the average rating
	 * @param contentID				- Identifier for the content
	 * @param username				- Username requesting the content
	 * @return						- Content details including description, comments, tags and rating
	 */
	public DatatypeContent getContentDetails(int contentID, String username) throws ContentNotFoundException;
	
	/**
	 * Returns true if the User exists, and is related to the Event
	 * @param eventName				- Name of the event
	 * @param user					- Identifier of the user  
	 */
	public boolean isUserRelatedToEvent(int eventID, String user);
	
	/**
	 * Returns true if the User exists, and is moderator for the Event.
	 * All administrators are moderators for every event.
	 * @param eventId				- Identifier of the event
	 * @param userName				- Identifier of the user
	 * @return						- True if user is moderator for the event
	 */
	public boolean isUserModeratorInEvent(Integer eventId, String username);
	
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
	public void tagUserInContent(int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception;

	/**
	 * Removes the associated tag to userToRemove in the content, username must have moderator privileges
	 * @param contentID				- Identifier of the content
	 * @param username				- Identifier of the user removing the tag
	 * @param userToRemove			- Identifier of the user tag removed
	 */
	public void removeTagInContent(int contentID, String username,
			Boolean userToRemoveIsReal, String userToRemove) throws Exception;

	/**
	 * Uploads the list of contents and returns confirmation token
	 * @param eventID				- Identifier for the event
	 * @param username				- Uploader's username
	 * @param list					- File's list to upload
	 * @return						- Tokens for uploaded contents
	 */
	public List<String> uploadContent(int eventID, String username, List<DataTypeFile> list);
	
	/**
	 * Gets the content data if the user is related to the event
	 * @param username				- User's username requesting the content
	 * @param contentID				- Requested content's identifier
	 * @param thumbnail				- max size of the thumbnail, if 0 returns the original data
	 * @return						- content's data
	 */
	public byte[] getContent(String username, int contentID, String thumbnail);
	
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
     * Returns a list of Events taking place on that day
     * @param after				- Since date
     * @param before			- To date
     * @param maxEvents			- Max number of events to return in the list
     */ 
	public List<DatatypeEventSummary> searchForEventBetweenDates(Date after, Date before, int maxEvents);
	
	
	/**
	 * Returns a list of the best picture for each event, ordered by rating
	 * @param lenght - length of the list to return 
	 */
	public List<DatatypeContent> getBestRankedContent(int length);
	
	/**
	 * Returns  a list of the best qualified pictures in all the server
	 * @param lenght - length of the list to return
	 */
	public List<DatatypeContent> getMostCommentedContent(int length);

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
     * Returns a list of maxEvents Events which take place in that date
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterEventsByDate(java.util.Date date, int maxEvents);
		
	/**
     * Returns a list of maxEvents upcoming Events 
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterNextEvents(int maxEvents);
	
	/**
     * Returns a list of maxEvents Events of 'type' event category 
     * type == 0 -> "Aniversario"
     * type == 1 -> "Casamiento"
     * type == 2 -> "Cumpleaños de quince"
     * type == 3 -> "Otro"
     * @param maxEvents				- Max number of events to return 
     */ 
	public List<DatatypeEventSummary> filterEventsByEvtCategory(int evtCategory,int maxEvents);
	

	/**
	 * Create a new Comment associated to the content and to the creator user 
	 * @param contentID				- Identifier of the content within the event
	 * @param textComment			- Comment
	 * @param userCommenter			- Identifier of the user who is commenting
	 * @throws Exception 
	 */
	public void commentContent(int contentID, String textComment, String userCommenter) throws Exception;

	/**
	 * Removes a comment from the content.
	 * @param contentId
	 * @param username
	 * @param userCommenter
	 * @param textComment
	 * @throws ContentNotFoundException
	 * @throws UserNotFoundException
	 * @throws IllegalAccessException
	 */
	public void removeCommentFromContent(int eventId, int contentId, String username,
			String userCommenter, String textComment)
		throws ContentNotFoundException, UserNotFoundException, IllegalAccessException;
	
	/**
	 * User rates the content
	 * @param contentID				- Identifier of the content within the event
	 * @param rating				- Score from 1 to 5 
	 * @param user					- Identifier of the user who is rating
	 * @throws Exception 
	 */
	public void rateContent(int contentID, int rating, String user) throws Exception;

	/**
	 * Returns the public content, used in the main page.
	 * */
	public byte[] getPublicContent(String type, int pos, String thumbnail);
	
	public List<DatatypeContent> getOrderedAlbum(int eventId);
	
	
	/**
	 * If true, returns the Album category associated to the event
	 * Returns null if no album has been created for the event
	 * @param eventID
	 */
	public DatatypeCategory existsAlbum(int eventID);
	
	/**
	 * Returns true if the album for the event has been created and finalized
	 * @param eventID
	 */	
	public boolean isAlbumFinalized(int eventID);

	/**
	 * Finalizes and publishes the album of the event 
	 * @param eventID
	 */		
	public void finalizeAlbum(int eventID);
	
	/**
	 * Adds the content in position pos from the album of the event
	 * @param contentID
	 * @param eventID
	 */
	public void addContentToAlbum(int contentID, int eventID);
	
	/**
	 * Retrieve contents included in album of eventID 
	 * ordered by posAlbum
	 * @param eventID
	 */
	public List<DatatypeContent> getAlbumContents(int eventID);
	
	/**
	 * Modifies the position of each content in the album to 
	 * the positions determined by the list newOrder
	 * @param eventID
	 * @param newOrder
	 */
	public void changeAlbumOrder(int eventID, int[] newOrder);
	
	/**
	 * Modifies the position of the content in the album. 
	 * Contents in oldPos and newPos are exchanged.
	 * @param contentID
	 * @param eventID
	 * @param pos
	private void changePosInAlbum(int contentID, int eventID, int newPos);
	 */
	
	/**
	 * Removes the content from the album of the event. 
	 * Last content of the album is moved to removed position.
	 * @param contentID
	 * @param eventID
	 */
	public void removeContentFromAlbum(int contentID, int eventID);
	
	/**
	 * Sets contentID as cover of eventID. 
	 * @param contentID
	 * @param eventID
	 */
	public void setCoverImage(int contentID, int eventID);
	
	
	/**
	 * Returns a list of albums and their information
	 * @param maxAlbums
	 */
	public List<DatatypeAlbum> getRecentAlbums(int maxAlbums);

	/**
	 * Removes a content from the event and all rating, comments, tags and categories it belongs to.
	 * User must have moderator rights.
	 * @param eventId
	 * @param contentId
	 * @param username
	 * @throws EventNotFoundException
	 * @throws ContentNotFoundException
	 * @throws IllegalAccessException
	 */
	public void removeContentFromEvent(Integer eventId, Integer contentId, String username)
		throws EventNotFoundException, ContentNotFoundException, IllegalAccessException;
	
	/**
	 * Retrieves the rating of the user for the current content
	 * @param contentId
	 * @param userName
	 * @return
	 */
	public int getMyRatingForContent(Integer contentId, String userName);

	/**
	 * Creates a new video from a youtube video
	 * @param eventId			- Event for the new content
	 * @param creator			- username of the NormalUser creator of the content
	 * @param yotube_id			- YouTube id for the video
	 * @param description		- Description for the video
	 * */
	public int uploadYoutubeVideo(int eventId, String creator, String youtube_id, 
			String description);
	
	/**
	 * Add categories to content with id = cntId. If the categories doesn't exist in the evento, they are created. 
	 * @param cntId				- Id of the content
	 * @param catsToAdd			- List of categories to add
	 * */
	public void addCategoryToContent(int cntId, List<String> catsToAdd);

	/**
	 * Creates a new external video from a youtube video
	 * @param eventId			- Event for the new content
	 * @param creator			- username of the NormalUser creator of the content
	 * @param description		- Description for the video
	 * @param url				- url of the youtube video
	 * */
	public int uploadExternVideo(int eventId, String creator, String description, String url);

	/**
	 * Creates a new external photo from a photo hosted in another site
	 * @param eventId			- Event for the new content
	 * @param creator			- username of the NormalUser creator of the content
	 * @param description		- Description for the video
	 * @param url				- url of the photo
	 * */
	public int uploadExternPhoto(int eventId, String creator, String description, String url);

	/**
	 * If the user is not related to the event, sends a mail to all administrators
	 * asking for permission to access the event contents.
	 * @param eventId
	 * @param userName
	 * @throws UserNotFoundException
	 */
	public void sendAdmitMail(Integer eventId, String userName)
		throws EventNotFoundException, UserNotFoundException;
	
	public void updateAlbum(Integer eventId, List<DatatypeContent> contenido);
}
