package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.ModeratedEvent;

@Local
public interface ModeratedEventDAO extends Dao<String, ModeratedEvent>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//ModeratedEvent create();
	
}
