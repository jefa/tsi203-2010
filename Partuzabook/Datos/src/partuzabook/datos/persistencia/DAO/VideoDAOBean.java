package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.ContentPK;
import partuzabook.datos.persistencia.beans.Video;

@Stateless
public class VideoDAOBean extends JpaDao<ContentPK, Video> implements VideoDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Video> findAll() {
		Query namedQuery = em.createNamedQuery("Video.findAll");
		return (List<Video>)namedQuery.getResultList();
	}


}
