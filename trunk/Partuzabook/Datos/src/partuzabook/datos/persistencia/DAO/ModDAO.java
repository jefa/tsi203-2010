package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Mod;
import partuzabook.datos.persistencia.beans.ModPK;

@Local
public interface ModDAO extends Dao<ModPK, Mod>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
