package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Album;

@Stateless
public class AlbumDAOBean extends JpaDao<Integer, Album> implements AlbumDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Album> findAll() {
		Query namedQuery = em.createNamedQuery("Album.findAll");
		return (List<Album>)namedQuery.getResultList();
	}


}
