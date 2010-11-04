package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.ExternalPhoto;

@Stateless
public class ExternalPhotoDAOBean extends JpaDao<Integer, ExternalPhoto> implements ExternalPhotoDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<ExternalPhoto> findAll() {
		Query namedQuery = em.createNamedQuery("ExternalPhoto.findAll");
		return (List<ExternalPhoto>)namedQuery.getResultList();
	}


}
