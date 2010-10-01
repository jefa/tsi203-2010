package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;

@Stateless
public class NotificationDAOBean extends JpaDao<Integer, Notification> implements NotificationDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Notification> findAll() {
		Query namedQuery = em.createNamedQuery("Notification.findAll");
		return (List<Notification>)namedQuery.getResultList();
	}
	
	public List<Notification> findByUser(NormalUser user) {
		Query namedQuery = em.createNamedQuery("Notification.findByUser");
		namedQuery.setParameter("user", user);
		return (List<Notification>) namedQuery.getResultList();
	}

	public List<Notification> findByUserUnread(String user) {
		Query namedQuery = em.createNamedQuery("Notification.findByUserUnread");
		namedQuery.setParameter("user", user);
		return (List<Notification>) namedQuery.getResultList();
	}


}
