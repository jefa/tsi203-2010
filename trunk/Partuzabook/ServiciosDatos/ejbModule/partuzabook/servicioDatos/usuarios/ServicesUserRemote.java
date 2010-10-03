package partuzabook.servicioDatos.usuarios;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Notification;

@Remote
public interface ServicesUserRemote {

	public DatatypeUser createNormalUser(String username, String password, String mail);
	public boolean existsNormalUser(String username);
	public String getNormalUserPassword(String username);
	
    public List<Event> getEventSummaryByUser(String user);
    public List<DatatypeNotification> getUpdateNotifications(String user);
    
}
