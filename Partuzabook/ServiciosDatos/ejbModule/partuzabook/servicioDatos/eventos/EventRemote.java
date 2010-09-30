package partuzabook.servicioDatos.eventos;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.User;

@Remote
public interface EventRemote {
	
	public List<Event> getSummaryEvents();
	
	public Content getGalleryMultimediaAtPos(String eventID, int pos);
	
	public boolean isUserRelatedToEvent(String eventID, String user);
	
	public List<User> getUsersForTag(String eventID, String contentID);	
	
	public void tagUserInContent(String eventID, String contentID, String user, int posX, int posY);

	public void confirmUploadContent(List<Content> list); 
	
}
