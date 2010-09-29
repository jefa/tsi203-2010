package partuzabook.datos.persistencia.DAO;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Admin;

@Remote
public interface AdminDAO {
	Admin create(String username, String password);
	Admin findById(String username);
	void delete(Admin admin);
}
