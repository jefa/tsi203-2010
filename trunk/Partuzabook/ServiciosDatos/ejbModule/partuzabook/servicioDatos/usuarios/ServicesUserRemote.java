package partuzabook.servicioDatos.usuarios;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Notification;

@Remote
public interface ServicesUserRemote {

    public Set<Event> getEventSummary(String user);

    public List<Notification> getUpdateNotifications(String user);

}
