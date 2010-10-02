package partuzabook.servicioDatos.usuarios;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.Notification;

@Remote
public interface ServicesUserRemote {

    public List<Event> getEventSummaryByUser(String user);

    public List<Notification> getUpdateNotifications(String user);

}
