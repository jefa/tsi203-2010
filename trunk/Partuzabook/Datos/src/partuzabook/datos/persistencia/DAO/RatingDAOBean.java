package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Rating;
import partuzabook.datos.persistencia.beans.RatingPK;

@Stateless
public class RatingDAOBean extends JpaDao<RatingPK, Rating> implements RatingDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Rating> findAll() {
		Query namedQuery = em.createNamedQuery("Rating.findAll");
		return (List<Rating>)namedQuery.getResultList();
	}
	
	public int getAverageRatingOfContent(int contentID) {
		Query namedQuery = em.createNamedQuery("Rating.avgRating");
		namedQuery.setParameter("cntId", contentID);
		return (Integer)namedQuery.getSingleResult();
	}


}
