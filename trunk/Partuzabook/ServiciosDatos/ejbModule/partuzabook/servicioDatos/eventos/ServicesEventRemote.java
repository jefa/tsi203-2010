package partuzabook.servicioDatos.eventos;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Content;

@Remote
public interface ServicesEventRemote {
	
	public List<DatatypeEventSummary> getSummaryEvents(int maxEvents,
			int maxContentsPerEvent);
	
	public DatatypeContent getGalleryMultimediaAtPos(String eventName, int pos);
	
	public boolean isUserRelatedToEvent(String eventName, String user);
	
	public List<DatatypeUser> getUsersForTag(String eventName, int contentID);	
	
	public void tagUserInContent(String eventName, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception;

	public void confirmUploadContent(List<Content> list); 
	
}
