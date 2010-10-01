package partuzabook.servicioDatos.prueba;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Invoking the remote bean");
	    
        try {
	        Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context - Main");
	        ServicesUserRemote usr = (ServicesUserRemote) ctx.lookup("User/remote");
	        if (usr != null) {
		        List<Notification> list = usr.getUpdateNotifications("vero");
		        if (list == null){
		        	System.out.println("LIST DE NOTIF ES NULL !!");
		        } else {
		        	System.out.println("El tamaño de lo devuelto es: " + list.size());
		        }
		    } else {
		        System.out.println("Nulllll");
			    	
	        }
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
