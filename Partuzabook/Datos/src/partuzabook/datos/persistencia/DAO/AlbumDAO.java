package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Album;
import partuzabook.datos.persistencia.beans.AlbumPK;

@Local
public interface AlbumDAO extends Dao<AlbumPK, Album>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Album create(String username, String password);
	
}
