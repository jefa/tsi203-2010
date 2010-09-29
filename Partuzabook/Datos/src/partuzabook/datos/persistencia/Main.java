package partuzabook.datos.persistencia;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.DAO.AdminDAO;

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
        AdminDAO r = lookupNewSessionBean();
        r.create("gonazlo", "pass");
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
    
    static AdminDAO lookupNewSessionBean() {
        try {
            Context c = new InitialContext();
          
           Properties properties = new Properties();
            properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
            properties.put("java.naming.provider.url", "jnp://localhost:1099");
            Context ctx = new InitialContext(properties);
            //Context ctx = new InitialContext();
            System.out.println("Got context");
            AdminDAO ans=(AdminDAO) ctx.lookup("AdminDAOBean/remote");  
            return ans;
           
        }
        catch(NamingException ne) {
           
            throw new RuntimeException(ne);
        }
    }

}
