package partuzabook.servicioDatos.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.ParticipantDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.Participant;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class User implements UserRemote {

	private NormalUserDAO dao;
		
    /**
     * Default constructor. 
     */
    public User() {
        // TODO Auto-generated constructor stub
        try {
			Context c = new InitialContext();
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context!!");
	        dao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/remote");  
	        System.out.println("Lookup worked!"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public List<Event> getEventSummary(String user) {
    	NormalUser nUser = (NormalUser) dao.findByID(user);
    	
    	List<Participant> part =  (List<Participant>) nUser.getParticipants();

    	List<Event> ret = new ArrayList<Event>();
    	Iterator<Participant> it = part.iterator();
    	while (it.hasNext()){
    		ret.add(it.next().getEvent());
        	it.next();
    	}
    	return ret;
    }

    public List<Notification> getUpdateNotifications(String user) {
    	NormalUser nUser = (NormalUser) dao.findByID(user);
    	if (nUser == null) {
    		System.out.println("EMPTY NORMAL USER!!");
    		return null;
    	} else {
    		System.out.println("Encontré al NORMAL USER!!");
    		return (List<Notification>) nUser.getNotificationsReceived();
    	}
    }

}
