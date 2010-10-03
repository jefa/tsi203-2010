package partuzabook.servicioDatos.prueba;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
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
	        ServicesUserRemote usr = (ServicesUserRemote) ctx.lookup("ServicesUser/remote");
	        if (usr != null) {
		        // Invocar getUpdateNotifications
	        	List<DatatypeNotification> list = usr.getUpdateNotifications("vero");
		        if (list == null){
		        	System.out.println("LIST DE NOTIF ES NULL !!");
		        } else {
		        	System.out.println("El tamaño de lo devuelto es: " + list.size());
		        }
		        // Invocar getEventsSummary
		        List<Event> setEv = usr.getEventSummaryByUser("vero");
		        if (setEv == null){
		        	System.out.println("SET DE EVENTS ES NULL !!");
		        } else {
		        	System.out.println("El tamaño del set de evs es: " + setEv.size());
		        }
		    } else {
		        System.out.println("ServicesUserRemote was not found");    	
	        }
	        
	        ServicesEventRemote evt = (ServicesEventRemote) ctx.lookup("ServicesEvent/remote");
	        if (evt != null) {
	        	// Invocar isUserRelatedToEvent
	        	if (evt.isUserRelatedToEvent("Cumple Vero", "vero")) {
	        		System.out.println("El user 'vero' es participante de 'Cumple Vero'");
	        	} else {
	        		System.out.println("No estan asociados 'vero' y 'Cumple Vero'");
	        	}
	        	// Invocar getSummaryEvents
	        	List<DatatypeEventSummary> list = evt.getSummaryEvents(10, 4);
	        	if (list == null){
	        		System.out.println("getSummaryEvents retorno lista vacia");
	        	} else {
	        		System.out.println("El tamaño de la lista de events es: " + list.size());
	        	}
	        	// Invocar getUsersForTag
	        	List<DatatypeUser> users = evt.getUsersForTag("Cumple Vero",0);
	        	if (users == null){
	        		System.out.println("getUsersForTag retorno lista vacia");
	        	} else {
	        		System.out.println("El tamaño de la lista de users para taggear es: " + users.size());
	        	}
	        	// Invocar tagUserInContent
	        	evt.tagUserInContent("Cumple Vero", 0, "vero", "gonzalo", 1, 1);	        		        	
	        	System.out.println("termine");
	        	usr.getUpdateNotifications("gonzalo");
	        } else {
	        	System.out.println("ServicesEventRemote was not found");    	
	        }

	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
