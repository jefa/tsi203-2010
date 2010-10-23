package partuzabook.servicioDatos.usuarios;

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

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.TagForUserDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.filesystem.FileSystemLocal;
import partuzabook.entityTranslators.TranslatorUser;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class ServicesUser implements ServicesUserRemote {

	private static final String DEFAULT_IMAGE = "profile/avatar_default.png";
	
	private NormalUserDAO nUserDao;
	private AdminDAO adminDao;
	private FileSystemLocal fileSystem;
	private TagForUserDAO tagForUserDao;
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
	        adminDao = (AdminDAO) ctx.lookup("AdminDAOBean/local");
	        nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local"); 
    		fileSystem = (FileSystemLocal) ctx.lookup("FileSystem/local");
    		tagForUserDao = (TagForUserDAO) ctx.lookup("TagForUserDAOBean/local");    		
//	        notifDao = (NotificationDAO) ctx.lookup("NotificationDAOBean/local");
		}
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	nUserDao = null;
    	fileSystem = null;
    	tagForUserDao = null;
//    	notifDao = null;
    }

	public DatatypeUser createNormalUser(String username, String password, String mail, String name) throws UserAlreadyExistsException{
		if (existsAdminUser(username) || existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		NormalUser newUser = new NormalUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(mail);
		newUser.setName(name);
		newUser.setImgPath("");
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		
		nUserDao.persist(newUser);
		
		return (DatatypeUser)new TranslatorUser().translate(newUser);
	}
	
	public DatatypeUser createAdminUser(String username, String password, String mail, String name) throws UserAlreadyExistsException{
		if (existsAdminUser(username) || existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		Admin newUser = new Admin();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(mail);
		newUser.setName(name);
		newUser.setImgPath("");
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		
		adminDao.persist(newUser);
		
		return (DatatypeUser)new TranslatorUser().translate(newUser);
	}
	
	public DatatypeUser updateNormalUser(String username, String password, String mail, String name, String img_path) throws UserNotFoundException {
		if(!existsNormalUser(username))
			throw new UserNotFoundException();
		
		NormalUser nu = nUserDao.findByID(username);
		nu.setPassword(password);
		nu.setEmail(mail);
		nu.setName(name);
		nu.setImgPath(img_path);
		nUserDao.persist(nu);
		return (DatatypeUser)new TranslatorUser().translate(nu);
	}
	
	public boolean existsNormalUser(String username) {
		return nUserDao.findByID(username) !=null;
	}
	
	public List<Boolean> existsNormalUser(List<String> usernames) {
		List<Boolean> res = new ArrayList<Boolean>();
		for(ListIterator<String> it = usernames.listIterator(); it.hasNext(); ){
			res.add(existsNormalUser(it.next()));				
		} 
		return res;
	}
	
	private NormalUser getNormalUser(String username) {
		NormalUser user = nUserDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		} 
		return user;
	}
	
	public boolean existsAdminUser(String username) {
		return adminDao.findByID(username) !=null;
	}
	
	private Admin getAdminUser(String username) {
		Admin user = adminDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		} 
		return user;
	}
	
	public DatatypeUser getUserForPublicProfile(String username) {
		return (DatatypeUser)new TranslatorUser().translate(getNormalUser(username));
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

    public List<DatatypeNotification> getUpdateNotificationsUnread(String user) {
    	List<Notification> unreadNotif = new ArrayList<Notification>();
    	NormalUser nUser = getNormalUser(user);  
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

	public String getNormalUserPassword(String username) {
		return getNormalUser(username).getPassword();
	}
	
	public String getAdminUserPassword(String username) {
		return getAdminUser(username).getPassword();
	}

	public byte[] getUserAvatar(String username, int thumbnail) {
		NormalUser user = getNormalUser(username);
		if(user.getImgPath() != null && !user.getImgPath().equals(""))
			return fileSystem.readFile(user.getImgPath(), thumbnail);
		else
			return fileSystem.readFile(DEFAULT_IMAGE, thumbnail);
	}

	public List<DatatypeUser> findAllNormalUsers() {		
		return TranslatorCollection.translateNormalUser(nUserDao.findAll());
	}
	
	private List<String> getMostTaggedUsernames() {
		List<String> list = tagForUserDao.getMostTagged();
		return list;
	}
	
	public List<DatatypeMostTagged> getMostTagged(int length) {
		List<String> list = getMostTaggedUsernames();
		if (list.size() > length) {
			list = list.subList(0, length);
		}
		List<NormalUser> users = new ArrayList<NormalUser>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String username = (String) it.next();
			users.add(getNormalUser(username));
		}
		return TranslatorCollection.translateMostTagged(users);
	}

	public byte[] getPublicAvatar(String type, int pos, int thumbnail) {
		List<String> usernames = null;
		if (type.equals("mostTagged")) {
			usernames = getMostTaggedUsernames();
		}
		if (usernames.size() >= pos) {
			return getUserAvatar(usernames.get(pos - 1), thumbnail);
		}
		return null;
	}
	
}
