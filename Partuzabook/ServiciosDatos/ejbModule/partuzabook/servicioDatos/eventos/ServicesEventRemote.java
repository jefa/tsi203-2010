package partuzabook.servicioDatos.eventos;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Content;

@Remote
public interface ServicesEventRemote {
	
    /**
     * Returns a list of Events of interest (Eg: Events from this week)
     * @param maxEvents				- Max number of events to return 
     * @param maxContentPerEvent	- Max number of content to return for each event
     */ 
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents,
			int maxContentsPerEvent);
	
	/**
	 * Returns the content details 
	 * @param eventName				-
	 * @param pos					-
	 * @return						-
	 */
	public DatatypeContent getGalleryMultimediaAtPos(String eventName, int pos);
	
	/**
	 * Returns true if the User exists, and is related to the Event
	 * @param eventName				- Name of the event
	 * @param user					- Identifier of the user  
	 */
	public boolean isUserRelatedToEvent(String eventName, String user);
	
	/**
	 * Returns a list of candidate Users for Tagging -participants of the event, who have not already been tagged in the content-
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content 
	 */
	public List<DatatypeUser> getUsersForTag(String eventName, int contentID);	
	
	/**
	 * Create a new instance of Tag associated to the content, user that was tagged, and the tagger
	 * @param eventID				- Identifier of the event
	 * @param contentID				- Identifier of the content within the event
	 * @param userTagger			- Identifier of the user who is tagging
	 * @param userToTag				- User tagged -may be a registered user or not- 
	 * @param posX					- Position of the tag in the X axis within the content
	 * @param posY					- Position of the tag in the Y axis within the content 
	 */
	public void tagUserInContent(String eventName, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception;

	/**
	 * Confirms the contents uploaded and makes them public
	 * @param list					-
	 */
	public void confirmUploadContent(List<Content> list); 
	
}
