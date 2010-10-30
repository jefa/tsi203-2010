package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NamedQuery;

import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;

@Local
public interface NotificationDAO extends Dao<Integer, Notification>{
	
	public List<Notification> findAll();

//	public List<Notification> findByUser(String user);
	
	public List<Notification> findByUser(NormalUser user);

	public List<Notification> findByUserUnread(String user);

	public List<Notification> findSentByUser(String user);

	public List<Notification> findReceivedByUser(String user);

}
