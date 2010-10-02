package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;

@Stateless
public class ContentDAOBean extends JpaDao<Integer, Content> implements ContentDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Content> findAll() {
		Query namedQuery = em.createNamedQuery("ExternalContent.findAll");
		return (List<Content>)namedQuery.getResultList();
	}
	
	public Content findByIDInEvent(Event event, int contentID) {
		Query namedQuery = em.createNamedQuery("Content.findByIDInEvent");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("content", contentID);
		return (Content)namedQuery.getSingleResult();	
	}


}
