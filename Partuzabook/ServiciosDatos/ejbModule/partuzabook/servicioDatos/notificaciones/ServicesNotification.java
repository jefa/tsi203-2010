package partuzabook.servicioDatos.notificaciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.DAO.TagForUserDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.User;
import partuzabook.datos.persistencia.filesystem.FileSystemLocal;
import partuzabook.entityTranslators.TranslatorNotification;
import partuzabook.entityTranslators.TranslatorUser;
import partuzabook.servicioDatos.exception.MessageTooLongException;
import partuzabook.servicioDatos.exception.NotificationNotFoundException;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Notificacion
 */
@Stateless
public class ServicesNotification implements ServicesNotificationRemote {

	private NormalUserDAO nUserDao;
	private AdminDAO adminDao;
	private NotificationDAO notifDao;
		
    /**
     * Default constructor. 
     */
    public ServicesNotification() {

    }
    
    @PostConstruct
    public void postConstruct() {
        try {
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        adminDao = (AdminDAO) ctx.lookup("AdminDAOBean/local");
	        nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local"); 
    		notifDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
		}
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	adminDao = null;
    	nUserDao = null;
    	notifDao = null;
    }
    
    private NormalUser getNormalUser(String username) {
		NormalUser user = nUserDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		} 
		return user;
	}
    
    private Notification getNotification(int notId) {
		Notification not = notifDao.findByID(notId);
		if (not == null) {
			throw new NotificationNotFoundException();
		} 
		return not;
	}
    
    public boolean existsNormalUser(String username) {
		return nUserDao.findByID(username) != null;
	}
    
    public boolean existsAdminUser(String username) {
		return adminDao.findByID(username) !=null;
	}

    public List<DatatypeNotification> getUpdateNotifications(String user) {
    	User nUser;
    	if(existsNormalUser(user)) {
			nUser = nUserDao.findByID(user);
		} else if(existsAdminUser(user)) {
			nUser = adminDao.findByID(user);
		} else {
			throw new UserNotFoundException("No existe el usuario " + user);
		}
    	List<Notification> notif = nUser.getNotificationsReceived();
    	return TranslatorCollection.translateNotification(notif);
    }

    public List<DatatypeNotification> getUpdateNotificationsUnread(String user) {
    	List<Notification> unreadNotif = new ArrayList<Notification>();
    	User nUser;
    	if(existsNormalUser(user)) {
			nUser = nUserDao.findByID(user);
		} else if(existsAdminUser(user)) {
			nUser = adminDao.findByID(user);
		} else {
			throw new UserNotFoundException("No existe el usuario " + user);
		}  
    	List<Notification> notif = nUser.getNotificationsReceived();
    	Iterator<Notification> it = notif.iterator();
    	while (it.hasNext()) {
    		Notification ntf = it.next();
    		if (!ntf.getRead()) {
    			unreadNotif.add(ntf);
    			//TODO Aca creo que habria que marcarlas como leídas.... no?
    		}
    	}
    	return TranslatorCollection.translateNotification(unreadNotif);
    }
    
	public List<DatatypeNotification> getUpdateNotificationsReceived(String username) {
    	User nUser;
    	if(existsNormalUser(username)) {
			nUser = nUserDao.findByID(username);
		} else if(existsAdminUser(username)) {
			nUser = adminDao.findByID(username);
		} else {
			throw new UserNotFoundException("No existe el usuario " + username);
		}
    	List<Notification> notif = nUser.getNotificationsReceived();
    	return TranslatorCollection.translateNotification(notif);
	}

	public List<DatatypeNotification> getUpdateNotificationsSent(String username) {
    	User nUser;
    	if(existsNormalUser(username)) {
			nUser = nUserDao.findByID(username);
		} else if(existsAdminUser(username)) {
			nUser = adminDao.findByID(username);
		} else {
			throw new UserNotFoundException("No existe el usuario " + username);
		}
    	List<Notification> notif = nUser.getNotificationsCreated();
    	return TranslatorCollection.translateNotification(notif);
	}
	
	public DatatypeNotification createNotification(String fromUser, String toUser, 
			Integer type, String message, String subject) throws UserNotFoundException, MessageTooLongException{
		
		User fUser;
		User tUser;
		
		if(subject != null && subject.length() > 200)
			throw new MessageTooLongException("El asunto del mensaje debe tener menos de 200 caracteres.");
		
		if(message.length() > 5000)
			throw new MessageTooLongException("El mensaje debe tener menos de 5000 caracteres.");
		
		if(existsNormalUser(fromUser)) {
			fUser = nUserDao.findByID(fromUser);
		} else if(existsAdminUser(fromUser)) {
			fUser = adminDao.findByID(fromUser);
		} else {
			throw new UserNotFoundException("No existe el usuario " + fromUser);
		}
		
		if(existsNormalUser(toUser)) {
			tUser = nUserDao.findByID(toUser);
		} else if(existsAdminUser(toUser)) {
			tUser = adminDao.findByID(toUser);
		} else {
			throw new UserNotFoundException("No existe el usuario " + toUser);
		}
		
		Notification not = new Notification();
		not.setNotDate(new Timestamp((new java.util.Date()).getTime()));
		not.setRead(false);
		not.setRegDate(new Timestamp((new java.util.Date()).getTime()));
		not.setText(message);
		not.setType(type);
		not.setUserFrom(fUser);
		not.setUserTo(tUser);
		not.setSubject(subject);
		
		notifDao.persist(not);
		
		//necetiso el flush para que falle persistir notification y no se mande mail en ese caso
		notifDao.flush();
		
		return (DatatypeNotification)new TranslatorNotification().translate(not);
	}

	public void setNotificationRead(int notId, boolean read) throws NotificationNotFoundException {
		Notification not = getNotification(notId);
		not.setRead(read);
		notifDao.persist(not);
	}

}
