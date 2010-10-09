package partuzabook.datos.persistencia.DAO;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Admin;

@Local
public interface AdminDAO extends Dao<String, Admin>{
	
	
}
