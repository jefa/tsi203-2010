package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;

@Local
public interface ContentDAO extends Dao<Integer, Content>{
	
	public Content findByIDInEvent(Event event, int contentID);
	
	public Content findByPosInEvent(Event event, int pos);
	
	public Integer findNextPosInGalleryEvent(Event event);

	public List<Integer> getBestRanked();
	
	public List<Integer> getMostCommented();
}
