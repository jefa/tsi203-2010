package partuzabook.datos.persistencia.DAO;

import java.sql.Date;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAll() {
		Query namedQuery = em.createNamedQuery("Event.findAll");
		return (List<Event>)namedQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllAfterDate(Date after) {
		return (List<Event>)em.createNamedQuery("Event.findAllAfterDate")
			.setParameter("after", after).getResultList();
	}


}
