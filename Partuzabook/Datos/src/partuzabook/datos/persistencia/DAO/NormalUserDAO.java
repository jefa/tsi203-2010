package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.NormalUser;

@Local
public interface NormalUserDAO extends Dao<String, NormalUser>{
	
	
}
