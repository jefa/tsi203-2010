package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Client;

@Local
public interface ClientDAO extends Dao<String, Client>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Client create(String username, String password);
	
}