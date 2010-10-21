package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Tag;

@Stateless
public class TagDAOBean extends JpaDao<Integer, Tag> implements TagDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Tag> findAll() {
		Query namedQuery = em.createNamedQuery("Tag.findAll");
		return (List<Tag>)namedQuery.getResultList();
	}

}
