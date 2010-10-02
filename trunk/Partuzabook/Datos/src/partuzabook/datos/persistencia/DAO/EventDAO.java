package partuzabook.datos.persistencia.DAO;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Event;

@Local
public interface EventDAO extends Dao<String, Event>{

	List<Event> findAll();
	List<Event> findAllAfterDate(Date after);
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//ModeratedEvent create();
	
}
