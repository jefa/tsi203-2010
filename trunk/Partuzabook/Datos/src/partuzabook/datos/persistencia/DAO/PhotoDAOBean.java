package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Photo;
import partuzabook.datos.persistencia.beans.UnmoderatedEvent;

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


}
