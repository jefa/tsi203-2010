package partuzabook.datos.persistencia;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.ClientDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Client;
import partuzabook.datos.persistencia.beans.NormalUser;

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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new Main();
    	TestAdminDAOBean();
    	//TestNormalUserDAOBean();
    	//TestClientDAOBean();
    }
    
    static String TestAdminDAOBean() {
    	try {
    		
			AdminDAO aDAO=(AdminDAO) c.lookup("AdminDAOBean/remote");
			/*
			Admin a = new Admin();
			a.setUsername("admin");
	        a.setPassword("admin");
	        a.setRegDate(new Timestamp((new java.util.Date()).getTime()));
	        aDAO.persist(a);
	        */
			List<Admin> la = aDAO.findAll();
			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}
    	
    }
    
    static String TestNormalUserDAOBean() {
    	try {
    		NormalUserDAO nuDAO=(NormalUserDAO) c.lookup("NormalUserDAOBean/remote");
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
    	try {
    		ClientDAO cliDAO=(ClientDAO) c.lookup("ClientDAOBean/remote");
    		Client cli = new Client();
			cli.setUsername("client1");
			cli.setPassword("my pass");
			cli.setRegDate(new Timestamp((new java.util.Date()).getTime()));
			cliDAO.persist(cli);
			return "OK";
		} catch (NamingException e) {			
			e.printStackTrace();
			return "ERROR";
		}
    	
    }
    
}
