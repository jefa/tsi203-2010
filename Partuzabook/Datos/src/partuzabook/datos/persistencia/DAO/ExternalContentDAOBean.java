package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.beans.ExternalContent;
import partuzabook.datos.persistencia.beans.Photo;

@Stateless
public class ExternalContentDAOBean extends JpaDao<Integer, ExternalContent> implements ExternalContentDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<ExternalContent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
