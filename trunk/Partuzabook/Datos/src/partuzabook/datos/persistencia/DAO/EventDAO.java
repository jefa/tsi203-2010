package partuzabook.datos.persistencia.DAO;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;

@Local
public interface EventDAO extends Dao<Integer, Event>{

	public Event findByName(String name);

	public List<Event> findBySimilarName(String name);

	public List<Event> findAll();
	
	public List<Event> findAllAfterDate(Date after);

	public NormalUser findMostTagged();

}
