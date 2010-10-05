package partuzabook.servicioDatos.usuarios;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
//import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.entityTranslators.TranslatorUser;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class ServicesUser implements ServicesUserRemote {

	private NormalUserDAO nUserDao;
//	private NotificationDAO notifDao;
		
    /**
     * Default constructor. 
     */
    public ServicesUser() {

    }
    
    @PostConstruct
    public void postConstruct() {
        try {
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context!!");
	        nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");  
//	        notifDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
	        System.out.println("Lookup worked!"); 
		}
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	nUserDao = null;
//    	notifDao = null;
    }

	public DatatypeUser createNormalUser(String username, String password, String mail) {
		if (existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		NormalUser newUser = new NormalUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		//TODO newUser.setMail(mail);
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		
		nUserDao.persist(newUser);
		
		return (DatatypeUser)new TranslatorUser().translate(newUser);
	}
	
	public boolean existsNormalUser(String username) {
		return nUserDao.findByID(username) !=null;
	}
	
	private NormalUser getNormalUser(String username) {
		NormalUser user = nUserDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

    public List<DatatypeEventSummary> getEventSummaryByUser(String user) {
    	NormalUser nUser = getNormalUser(user);   	
    	List<Event> ret = nUser.getMyEvents();
    	return TranslatorCollection.translateEventSummary(ret);
    }

    public List<DatatypeNotification> getUpdateNotifications(String user) {
    	NormalUser nUser = getNormalUser(user);  
    	List<Notification> notif = nUser.getNotificationsReceived();
    	return TranslatorCollection.translateNotification(notif);
    }

	public String getNormalUserPassword(String username) {
		return getNormalUser(username).getPassword();
	}

}
