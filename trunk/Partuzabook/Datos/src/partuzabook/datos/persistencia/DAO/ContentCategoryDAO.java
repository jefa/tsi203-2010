package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.datos.persistencia.beans.Event;

@Local
public interface ContentCategoryDAO extends Dao<Integer, CntCategory>{
	
	public CntCategory findByIDInEvent(Event event, int contentCategoryID);
	public CntCategory findByNameInEvent(Event event, String name);
	
}
