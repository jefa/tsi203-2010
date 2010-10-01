package partuzabook.servicioDatos.usuarios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Participant;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class ServicesUser implements ServicesUserRemote {

	private NormalUserDAO nUserDao;
	private NotificationDAO notifDao;
		
    /**
     * Default constructor. 
     */
    public ServicesUser() {
        // TODO Auto-generated constructor stub
        try {
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context!!");
	        nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");  
	        notifDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
	        System.out.println("Lookup worked!"); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public Set<Event> getEventSummary(String user) {
    	NormalUser nUser = (NormalUser) nUserDao.findByID(user);   	
    	if (nUser == null) {
    		return null;
    	}
    	Set<Participant> part =  (Set<Participant>) nUser.getParticipants();
    	if (part.isEmpty()){
    		return null;
    	} 
	    Set<Event> ret = new HashSet<Event>();
    	Iterator<Participant> it = part.iterator();
    	while (it.hasNext()){
    		ret.add(it.next().getEvent());
        	it.next();
    	}
    	return ret;
    }

    public List<Notification> getUpdateNotifications(String user) {
    	NormalUser nUser = (NormalUser) nUserDao.findByID(user);  
    	Set<Notification> notif = (Set<Notification>) nUser.getNotificationsReceived();
    	if (notif.isEmpty()) {
    		return null;
    	} else {
	    	List<Notification> ntf = new ArrayList<Notification>();
	    	Iterator<Notification> it = notif.iterator();
	    	while (it.hasNext()) {
	    		ntf.add((Notification)it.next());    		
	    	}
	    	return ntf;
	    }
    }

}
