package partuzabook.servicioDatos.eventos;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;

@Remote
public interface ServicesEventRemote {
	
	public List<Event> getSummaryEvents();
	
	public Content getGalleryMultimediaAtPos(String eventName, int pos);
	
	public boolean isUserRelatedToEvent(String eventName, String user);
	
	public List<NormalUser> getUsersForTag(String eventName, int contentID);	
	
	public void tagUserInContent(String eventName, int contentID, String userTagger, String userToTag, int posX, int posY) throws Exception;

	public void confirmUploadContent(List<Content> list); 
	
}
