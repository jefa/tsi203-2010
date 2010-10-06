package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.TagForNotUser;

@Stateless
public class TagForNotUserDAOBean extends JpaDao<Integer, TagForNotUser> implements TagForNotUserDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<TagForNotUser> findAll() {
		Query namedQuery = em.createNamedQuery("TagForNotUser.findAll");
		return (List<TagForNotUser>)namedQuery.getResultList();
	}


}
