package partuzabook.datos.persistencia.DAO;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Notification;

@Remote
public interface NotificationDAO extends Dao<Integer, Notification>{
	
	//TODO: Preguntar si quieren que la creacion de la entidad sea en el DAO o que la haga la logica
	//Comment create(String username, String password);
	
}
