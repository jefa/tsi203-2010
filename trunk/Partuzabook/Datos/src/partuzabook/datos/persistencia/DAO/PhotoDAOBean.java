package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Photo;

@Stateless
public class PhotoDAOBean extends JpaDao<Integer, Photo> implements PhotoDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Photo> findAll() {
		Query namedQuery = em.createNamedQuery("Photo.findAll");
		return (List<Photo>)namedQuery.getResultList();
	}

	
	@Override
	public Photo findBestRatedInEvent(Event event) {
		Query namedQuery = em.createNamedQuery("Photo.findBestRatedInEvent");		
		return (Photo)namedQuery.getResultList().get(0);
	}

	@Override
	public Photo findMostCommentedInEvent(Event event) {
		Query namedQuery = em.createNamedQuery("Photo.findMostCommentedInEvent");
		namedQuery.setParameter("event", event);
		return (Photo)namedQuery.getResultList().get(0);
	}


}
