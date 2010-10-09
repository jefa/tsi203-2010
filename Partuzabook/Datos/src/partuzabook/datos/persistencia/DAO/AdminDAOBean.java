package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Admin;

@Stateless
public class AdminDAOBean extends JpaDao<String, Admin> implements AdminDAO {

	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	

	@Override
	public List<Admin> findAll() {
		Query namedQuery = em.createNamedQuery("Admin.findAll");
		return (List<Admin>)namedQuery.getResultList();
	}

}
