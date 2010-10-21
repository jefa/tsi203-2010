package partuzabook.datos.persistencia.DAO;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.TagForUser;

@Stateless
public class TagForUserDAOBean extends JpaDao<Integer, TagForUser> implements TagForUserDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<TagForUser> findAll() {
		Query namedQuery = em.createNamedQuery("TagForUser.findAll");
		return (List<TagForUser>)namedQuery.getResultList();
	}

	@Override
	public List<String> getMostTagged() {
		Query q = em.createNamedQuery("TagForUser.getMostTagged");
		List list = q.getResultList();
		return (List<String>)list;
	}


}
