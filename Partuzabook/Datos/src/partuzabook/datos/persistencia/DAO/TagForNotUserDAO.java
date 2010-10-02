package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagPK;

@Local
public interface TagForNotUserDAO extends Dao<TagPK, TagForNotUser>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
