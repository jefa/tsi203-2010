package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Client;

@Local
public interface ClientDAO extends Dao<String, Client>{
	
	
}
