package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.beans.UnmoderatedEvent;

@Stateless
public class UnmoderatedEventDAOBean extends JpaDao<String, UnmoderatedEvent> implements UnmoderatedEventDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<UnmoderatedEvent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}