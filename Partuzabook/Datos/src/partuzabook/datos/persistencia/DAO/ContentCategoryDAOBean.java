package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.datos.persistencia.beans.Event;

@Stateless
public class ContentCategoryDAOBean extends JpaDao<Integer, CntCategory> implements ContentCategoryDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CntCategory> findAll() {
		Query namedQuery = em.createNamedQuery("Content.findAll");
		return (List<CntCategory>)namedQuery.getResultList();
	}
	
	public CntCategory findByIDInEvent(Event event, int contentID) {
		Query namedQuery = em.createNamedQuery("CntCategory.findByIDInEvent");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("category", contentID);
		return (CntCategory)namedQuery.getSingleResult();	
	}

	public CntCategory findByNameInEvent(Event event, String name) {
		Query namedQuery = em.createNamedQuery("CntCategory.findByNameInEvent");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("category", name);
		if (namedQuery.getResultList().size() > 0) {
			return (CntCategory)namedQuery.getSingleResult();
		}
		return null;
	}
}
