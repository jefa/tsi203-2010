package partuzabook.datos.persistencia.DAO;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Admin;

@Remote
public interface AdminDAO extends Dao<String, Admin>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Admin create(String username, String password);
	
}
