package partuzabook.datos.persistencia;
import java.util.List;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.User;

@Remote
public interface UserServiceRemote {

	public void addUser1();
	public List<User> getAllUsers();
	
    public List<Event> getEventSummary(String user);

    public List<Notification> getUpdateNotifications(String user);

}
