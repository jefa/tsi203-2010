package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.datos.persistencia.beans.Content;
import partuzabook.datos.persistencia.beans.Event;

@Stateless
public class ContentDAOBean extends JpaDao<Integer, Content> implements ContentDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Content> findAll() {
		Query namedQuery = em.createNamedQuery("Content.findAll");
		return (List<Content>)namedQuery.getResultList();
	}
	
	public List<Content> getAllInAlbumOfEvent(Event event){
		Query namedQuery = em.createNamedQuery("Content.findAllInAlbumOfEvent");
		namedQuery.setParameter("event", event);
		return (List<Content>)namedQuery.getResultList();
	}

	
	public Content findByIDInEvent(Event event, int contentID) {
		Query namedQuery = em.createNamedQuery("Content.findByIDInEvent");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("content", contentID);
		if (namedQuery.getResultList().size() > 0) {
			return (Content)namedQuery.getSingleResult();	
		} else {
			return null;
		}	
	}

	public Content findByPosInEvent(Event event, int pos) {
		Query namedQuery = em.createNamedQuery("Content.findByPosInEvent");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("pos", pos);
		if (namedQuery.getResultList().size() > 0) {
			return (Content)namedQuery.getSingleResult();	
		} else {
			return null;
		}	
	}
	
	public Content findByPosAlbum(Event event, int pos) {
		Query namedQuery = em.createNamedQuery("Content.findByPosAlbum");
		namedQuery.setParameter("event", event);
		namedQuery.setParameter("pos", pos);
		if (namedQuery.getResultList().size() > 0) {
			return (Content)namedQuery.getSingleResult();	
		} else {
			return null;
		}	
	}

	public List<Content> getOrderedAlbum(Event event) {
		Query namedQuery = em.createNamedQuery("Content.getOrderedAlbum");
		namedQuery.setParameter("event", event);
		return (List<Content>)namedQuery.getResultList();
	}

	public Integer findNextPosInGalleryEvent(Event event) {
		Query namedQuery = em.createNamedQuery("Content.findNextPosInGalleryEvent");
		namedQuery.setParameter("event", event);
		return (Integer)namedQuery.getResultList().size();	
	}

	public Integer findNextPosInAlbumEvent(Event event) {
		Query namedQuery = em.createNamedQuery("Content.findNextPosInAlbumEvent");
		namedQuery.setParameter("event", event);
		return (Integer)namedQuery.getResultList().size();	
	}

	@Override
	public List<Integer> getBestRanked() {
		Query namedQuery = em.createNamedQuery("Content.getBestRanked");
		return (List<Integer>)namedQuery.getResultList();
	}
	
	@Override
	public List<Integer> getMostCommented() {
		Query namedQuery = em.createNamedQuery("Content.getMostCommented");
		List list = namedQuery.getResultList();
		return (List<Integer>)list;
	}
	
}
