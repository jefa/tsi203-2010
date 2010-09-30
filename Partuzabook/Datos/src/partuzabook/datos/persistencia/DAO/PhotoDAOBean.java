package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.beans.Photo;

@Stateless
public class PhotoDAOBean extends JpaDao<Integer, Photo> implements PhotoDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Photo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
