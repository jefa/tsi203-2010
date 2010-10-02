package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Tag;
import partuzabook.datos.persistencia.beans.TagPK;

@Local
public interface TagDAO extends Dao<TagPK, Tag>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
	
}
