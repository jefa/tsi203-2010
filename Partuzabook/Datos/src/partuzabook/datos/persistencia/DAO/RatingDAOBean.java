package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.CntCategory;
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
		if (namedQuery.getResultList().size() > 0) {
			return ((Double)namedQuery.getSingleResult()).intValue();	
		} else {
			return 0;
		}
	}

	@Override
	public Rating findByContentAndUsername(Integer contentId, String username) {
		Query q = em.createNamedQuery("Rating.findByContentAndUsername")
			.setParameter("username", username)
			.setParameter("contentId", contentId);
		if (q.getResultList().size() > 0) {
			return (Rating)q.getSingleResult();
		}
		return null;
	}


}
