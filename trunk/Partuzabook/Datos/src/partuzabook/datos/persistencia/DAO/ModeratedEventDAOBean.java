package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.ModeratedEvent;

@Stateless
public class ModeratedEventDAOBean extends JpaDao<Integer, ModeratedEvent> implements ModeratedEventDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<ModeratedEvent> findAll() {
		Query namedQuery = em.createNamedQuery("ModeratedEvent.findAll");
		return (List<ModeratedEvent>)namedQuery.getResultList();
	}


}
