package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.TagForNotUser;
import partuzabook.datos.persistencia.beans.TagPK;

@Stateless
public class TagForNotUserDAOBean extends JpaDao<TagPK, TagForNotUser> implements TagForNotUserDAO {
	
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
