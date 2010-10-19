package partuzabook.datos.persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;

public class Main {

	private static Context c;

	 /** Creates a new instance of Main */
    public Main() {
    	 try {
             
           
             Properties properties = new Properties();
             properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
             properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
             properties.put("java.naming.provider.url", "jnp://localhost:1099");
             c = new InitialContext(properties);
             //Context ctx = new InitialContext();
             System.out.println("Got context");           
                         
         }
         catch(NamingException ne) {
            
             throw new RuntimeException(ne);
         }
    }
    
    static void addNotification() {
    	try {
 			NormalUserDAO nuDao = (NormalUserDAO)c.lookup("PruebaDeploy/NormalUserDAOBean/local");
			AdminDAO adminDao = (AdminDAO)c.lookup("PruebaDeploy/AdminDAOBean/local");
			NotificationDAO notDao = (NotificationDAO)c.lookup("PruebaDeploy/NotificationDAOBean/local");
			
			NormalUser nu = nuDao.findByID("Normla User");
			Admin admin = adminDao.findByID("admin");
			//Notification not = notDao.findByID(1);
			
			//nu.getNotificationsReceived().add(not);
			//admin.getNotificationsCreated().add(not);
			
			
			Notification not = new Notification();
			not.setNotDate(new Timestamp((new java.util.Date()).getTime()));
			
			not.setRead(false);
			not.setRegDate(new Timestamp((new java.util.Date()).getTime()));
			not.setText("Alohomora sr, como le va 22222222222?");
			not.setUserFrom(admin);
			not.setUserTo(nu);
			//not.setNotIdAuto(1);
			notDao.persist(not);
			
			List<Notification> s1 = admin.getNotificationsCreated();
			List<Notification> s2 = nu.getNotificationsReceived();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//new Main();
    	//TestUserService();
    	//TestAdminDAOBean();
    	//TestNormalUserDAOBean();
    	//TestClientDAOBean();
    	//TestGetNormalClient();
    	
    	//addNotification();
    	String email = "ggismero@gmail.jjojojo.com";
		boolean res = true;
		String regex = "[a-zA-Z0-9]+(\\.-\\w)*@[a-zA-Z0-9]+(\\.-\\w)*(\\.\\w{2,4})+";
		System.out.println(email.matches(regex));
    	
    }
    
    static String TestUserService() {
    	try {
    		
			UserServiceRemote aDAO=(UserServiceRemote) c.lookup("PruebaDeploy/UserService/remote");
			/*
			Admin a = new Admin();
			a.setUsername("admin");
	        a.setPassword("admin");
	        a.setRegDate(new Timestamp((new java.util.Date()).getTime()));
	        aDAO.persist(a);
	        */
			List<Notification> set = aDAO.getUpdateNotifications("Normla User");
			System.out.println("Tamanio del set: " + set.size());
			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}
    	
    }
    
    
    static String TestAdminDAOBean() {
    	try {
    		
			UserServiceRemote uSer =(UserServiceRemote) c.lookup("PruebaDeploy/UserService/remote");
			
			//uSer.addUser1();
			uSer.getAllUsers();

			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}
    	
    }
    
    static String TestNormalUserDAOBean() {
    	try {
    		NormalUserDAO nuDAO=(NormalUserDAO) c.lookup("PruebaDeploy/NormalUserDAOBean/remote");
    		NormalUser nu = new NormalUser();
			nu.setUsername("Normla User");
	        nu.setPassword("my pass");
	        nu.setRegDate(new Timestamp((new java.util.Date()).getTime()));
	        nuDAO.persist(nu);
			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}    	
    }
    
    static String TestClientDAOBean() {
//    	try {
//    		ClientDAO cliDAO=(ClientDAO) c.lookup("PruebaDeploy/ClientDAOBean/remote");
//    		Client cli = new Client();
//			cli.setUsername("client1");
//			cli.setPassword("my pass");
//			cli.setRegDate(new Timestamp((new java.util.Date()).getTime()));
//			cliDAO.persist(cli);
			return "OK";
//		} catch (NamingException e) {			
//			e.printStackTrace();
//			return "ERROR";
//		}
    	
    }
    
    static String TestGetNormalClient() {
    	try {
    		
			NormalUserDAO nuDAO=(NormalUserDAO) c.lookup("PruebaDeploy/NormalUserDAOBean/remote");
			/*
			Admin a = new Admin();
			a.setUsername("admin");
	        a.setPassword("admin");
	        a.setRegDate(new Timestamp((new java.util.Date()).getTime()));
	        aDAO.persist(a);
	        */
			NormalUser nu = nuDAO.findByID("client1");
			List a = nu.getNotificationsReceived();
			System.out.println(a.size());
			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}    	
    }
    
}
