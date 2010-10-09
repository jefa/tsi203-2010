package partuzabook.serviciosUI.autenticacion;

import java.util.Properties;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import partuzabook.servicioDatos.usuarios.ServicesUser;

/**
 * Session Bean implementation class Autenticacion
 */
@Stateless
public class ServicesAutenticacion implements ServicesAutenticacionRemote {
	private ServicesUser servUser;

    public ServicesAutenticacion() {
        
    }
    
    @PostActivate
    public void postActivate() {
        try {
			Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context");
	        servUser = (ServicesUser) ctx.lookup("ServicesUser/remote");  
	        System.out.println("Lookup worked!"); 
		}
        catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    @PrePassivate
    public void prePassivate() {
    	servUser = null;
    }

    public boolean verifyUserAndPassword(String username, String password) throws LoginException {
    	if (servUser.existsNormalUser(username)) {
    		if (servUser.getNormalUserPassword(username).equals(password)) {
    			throw new LoginException("Incorrect password.");
    		}
    		else {
    			return true;
    		}
    	}
    	else {
    		throw new LoginException("User doesn't exists.");
    	}
    }
}
