package partuzabook.datos.persistencia.DAO;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.UnmoderatedEvent;

@Remote
public interface UnmoderatedEventDAO extends Dao<String, UnmoderatedEvent>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//UnmoderatedEvent create();
	
}
