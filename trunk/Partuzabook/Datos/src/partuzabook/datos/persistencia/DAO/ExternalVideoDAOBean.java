package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.ExternalVideo;

@Stateless
public class ExternalVideoDAOBean extends JpaDao<Integer, ExternalVideo> implements ExternalVideoDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<ExternalVideo> findAll() {
		Query namedQuery = em.createNamedQuery("ExternalVideo.findAll");
		return (List<ExternalVideo>)namedQuery.getResultList();
	}


}
