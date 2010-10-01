package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.NormalUser;

@Local
public interface NormalUserDAO extends Dao<String, NormalUser>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//NormalUser create(String username, String password);
	
}
