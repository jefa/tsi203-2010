package partuzabook.servicioDatos.usuarios;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.Dao;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;

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

    }
    
    @PostActivate
    public void postActivate() {
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
		}
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PrePassivate
    public void prePassivate() {
    	nUserDao = null;
    	notifDao = null;
    }

	public DatatypeUser createUser(String username, String password, String mail) {
		if (existsUser(username)) {
			throw new UserAlreadyExistsException();
		}
		NormalUser newUser = new NormalUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		//TODO newUser.setMail(mail);
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		
		nUserDao.persist(newUser);
		
		
		return null;
	}
	
	public boolean existsUser(String username) {
		return nUserDao.findByID(username) !=null;
	}

    public List<Event> getEventSummaryByUser(String user) {
    	NormalUser nUser = (NormalUser) nUserDao.findByID(user);   	
    	if (nUser == null) {
    		return null;
    	}
    	List<Event> ret = nUser.getMyEvents();
    	if (ret.isEmpty()){
    		return null;
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

	public String getUserPassword(String username) {
		// TODO Auto-generated method stub
		return "";
	}

}
