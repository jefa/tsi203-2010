package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.beans.Notification;

@Stateless
public class NotificationDAOBean extends JpaDao<Integer, Notification> implements NotificationDAO {
	
	@PersistenceContext
	EntityManager em;
	@Resource
	SessionContext sc;
	
	@Override
	public List<Notification> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
