package partuzabook.serviciosUI.autenticacion;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import partuzabook.servicioDatos.usuarios.ServicesUser;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

/**
 * Session Bean implementation class Autenticacion
 */
@Stateless
public class ServicesAutenticacion implements ServicesAutenticacionRemote {
	private ServicesUserRemote servUser;

    public ServicesAutenticacion() {
        
    }
    
    @PostConstruct
    public void postActivate() {
        try {
			Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        servUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");  
		}
        catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void prePassivate() {
    	servUser = null;
    }

    public boolean verifyUserAndPassword(String username, String password) {
    	if (servUser.existsNormalUser(username)) {
    		if (servUser.getNormalUserPassword(username).equals(password)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    }
}
