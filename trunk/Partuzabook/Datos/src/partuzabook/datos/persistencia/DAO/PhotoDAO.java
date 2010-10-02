package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.ContentPK;
import partuzabook.datos.persistencia.beans.Photo;

@Local
public interface PhotoDAO extends Dao<ContentPK, Photo>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
