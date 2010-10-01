package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.RatingPK;

@Local
public interface RatingDAO extends Dao<RatingPK, Rating>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
