package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.ModeratedEvent;

@Local
public interface ModeratedEventDAO extends Dao<Integer, ModeratedEvent>{
	
	
}
