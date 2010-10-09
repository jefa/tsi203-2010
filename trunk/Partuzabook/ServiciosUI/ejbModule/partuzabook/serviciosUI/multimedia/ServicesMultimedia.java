package partuzabook.serviciosUI.multimedia;

import java.util.List;
import java.util.Properties;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.servicioDatos.eventos.ServicesEvent;

/**
 * Session Bean implementation class ServicesMultimedia
 */
@Stateless
public class ServicesMultimedia implements ServicesMultimediaRemote {
	private ServicesEvent servEvent;
	
    /**
     * Default constructor. 
     */
    public ServicesMultimedia() {
    	
    }
    
    @PostActivate
    public void postActivate() {
        try {
			Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        servEvent = (ServicesEvent) ctx.lookup("ServicesEvent/remote");  
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PrePassivate
    public void prePassivate() {
    	servEvent = null;
    }

}
