package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;

@Local
public interface ContentDAO extends Dao<Integer, Content>{
	
	public Content findByIDInEvent(Event event, int contentID);
	
	public Content findByPosInGalleryEvent(Event event, int pos);

	
}
