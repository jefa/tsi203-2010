package partuzabook.datos.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.User;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class UserService implements UserServiceRemote {

	private NormalUserDAO dao;
	
	@EJB
	private AdminDAO dao2;
	
	public void addUser1() {
		
		Admin a = new Admin();
		a.setUsername("admin");
        a.setPassword("admin");
        a.setRegDate(new Timestamp((new java.util.Date()).getTime()));
        dao2.persist(a);
	}
	
	public List<User> getAllUsers() {
		List<User> res = new ArrayList();
		res.addAll(dao2.findAll());
		res.addAll(dao.findAll());
		return res;
	}	
	
    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
        try {
			Context c = new InitialContext();
        	Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context!!");
	        dao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
	        dao2 = (AdminDAO) ctx.lookup("AdminDAOBean/local");
	        System.out.println("Lookup worked!"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public List<Event> getEventSummary(String user) {
    	NormalUser nUser = (NormalUser) dao.findByID(user);

    	return null;
    }

    public List<Notification> getUpdateNotifications(String user) {
    	NormalUser nUser = (NormalUser) dao.findByID(user);
    	if (nUser == null) {
    		System.out.println("EMPTY NORMAL USER!!");
    		return null;
    	} else {
    		System.out.println("Encontre al NORMAL USER!!");
    		List<Notification> res = nUser.getNotificationsReceived();
    		return res;
    	}
    }

}
