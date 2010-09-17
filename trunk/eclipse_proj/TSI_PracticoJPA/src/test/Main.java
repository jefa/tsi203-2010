/*
 * Main.java
 *
 * Created on April 29, 2008, 4:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package test;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import manager.IWebserviceManager;
import session.TestRemote;

/**
 *
 * @author bijul
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        System.out.println("Invoking the remote bean");
        TestRemote r=lookupNewSessionBean();
        System.out.println(r.sayHello("Bijul Soni"));
        /*
        IWebserviceManager r=lookupNewWebserviceManager();
        try {
			r.create("uno", "2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    
    static TestRemote lookupNewSessionBean() {
        try {
            Context c = new InitialContext();
          
           Properties properties = new Properties();
            properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
            properties.put("java.naming.provider.url", "jnp://localhost:1099");
            Context ctx = new InitialContext(properties);
            //Context ctx = new InitialContext();
            System.out.println("Got context");
            session.TestRemote ans=(session.TestRemote) ctx.lookup("TestBean/remote");  
            return ans;
           
        }
        catch(NamingException ne) {
           
            throw new RuntimeException(ne);
        }
    }
    
    static IWebserviceManager lookupNewWebserviceManager() {
        try {
            Context c = new InitialContext();
          
           Properties properties = new Properties();
            properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
            properties.put("java.naming.provider.url", "jnp://localhost:1099");
            Context ctx = new InitialContext(properties);
            //Context ctx = new InitialContext();
            System.out.println("Got context");
            manager.IWebserviceManager ans=(manager.IWebserviceManager) ctx.lookup("WebserviceManager/remote");  
            return ans;
           
        }
        catch(NamingException ne) {
           
            throw new RuntimeException(ne);
        }
    }
    
}
