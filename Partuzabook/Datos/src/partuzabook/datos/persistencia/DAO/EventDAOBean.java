package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Event;

@Stateless
public class EventDAOBean extends JpaDao<String, Event> implements EventDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Event> findAll() {
		Query namedQuery = em.createNamedQuery("Event.findAll");
		return (List<Event>)namedQuery.getResultList();
	}


}
