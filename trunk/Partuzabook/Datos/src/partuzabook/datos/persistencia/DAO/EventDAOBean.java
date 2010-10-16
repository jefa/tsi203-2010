package partuzabook.datos.persistencia.DAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;

@Stateless
public class EventDAOBean extends JpaDao<Integer, Event> implements EventDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	
	public Event findByName(String name) {
		Query namedQuery = em.createNamedQuery("Event.findByName");
		namedQuery.setParameter("name", name);
		return (Event)namedQuery.getSingleResult();
	}

	public List<Event> findBySimilarName(String name) {
		Query namedQuery = em.createNamedQuery("Event.findBySimilarName");
		namedQuery.setParameter("name", "%"+name+"%");
		return (List<Event>)namedQuery.getResultList();	
	}

	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllBeforeDate(Date date) {
		return (List<Event>)em.createNamedQuery("Event.findAllBeforeDate")
			.setParameter("before", date).getResultList();
	}

	
	public NormalUser findMostTagged() {
		Query namedQuery = em.createNamedQuery("Event.findMostTagged");
		return (NormalUser) namedQuery.getSingleResult();	
	}

	public List<Event> findByDate(Date date) {
		// Create date from params
/*		Calendar cal = new GregorianCalendar();
		cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		Date date = new Date(cal.getTimeInMillis());
*/		
		// Query events
		Query namedQuery = em.createNamedQuery("Event.findByDate");
		namedQuery.setParameter("date", date);
		return (List<Event>)namedQuery.getResultList();		
	}


}
