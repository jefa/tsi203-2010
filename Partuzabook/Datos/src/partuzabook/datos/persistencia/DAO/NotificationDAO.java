package partuzabook.datos.persistencia.DAO;

import java.util.List;

import javax.ejb.Local;

import partuzabook.datos.persistencia.beans.Notification;

@Local
public interface NotificationDAO extends Dao<Integer, Notification>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);

	public List<Notification> findAll();

	public List<Notification> findByUser(String user);
	
	public List<Notification> findByUserUnread(String user);
}
