package partuzabook.servicioDatos.prueba;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Invoking the remote bean");
	    
        try {
	        Properties properties = new Properties();
	        properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	        properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
	        properties.put("java.naming.provider.url", "jnp://localhost:1099");
	        Context ctx = new InitialContext(properties);
	        System.out.println("Got context - Main");
	        ServicesUserRemote usr = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
	        if (usr != null) {
	        	System.out.println("**** Testeando CU Ver pagina de Inicio ****");
	        	String usuario = "veromanduk";
	        	System.out.println("1. Invocando getUpdateNotifications para " + usuario);
	        	List<DatatypeNotification> list = usr.getUpdateNotifications(usuario);
		        if (list == null){
		        	System.out.println("   Result: NULL");
		        } else {
		        	System.out.println("   Result: " + list.size() + " notificationes");
		        	for (int i = 0; i < list.size(); i++) {
		        		System.out.println("   " + list.get(i).text + " - From: " + list.get(i).userFrom);
		        	}
		        }
		        System.out.println("2. Invocando getEventsSummary");
		        List<DatatypeEventSummary> setEv = usr.getEventSummaryByUser(usuario);
		        if (setEv == null){
		        	System.out.println("   Result: NULL");
		        } else {
		        	System.out.println("   Result: " + setEv.size() + " eventos");
		        	for (int i = 0; i < setEv.size(); i++) {
		        		System.out.println("   " + setEv.get(i).getEvtName() + " - Descr: " + setEv.get(i).getDescription());
		        	}
		        }
		    } else {
		        System.out.println("ServicesUserRemote was not found");    	
	        }
	        
	        ServicesEventRemote evt = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
	        if (evt != null) {
	        	System.out.println("3. Invocando getSummaryEvents");
	        	int maxEvents = 10;
	        	List<DatatypeEventSummary> eventSummary = evt.getSummaryEvents(maxEvents);
	        	if (eventSummary == null){
	        		System.out.println("   Result: NULL");
	        	} else {
	        		for (int i = 0; i < eventSummary.size(); i++) {
		        		System.out.println("   Evento " + i + " : " + eventSummary.get(i).getEvtName());
		        	}
	        	}	        	
	        	System.out.println("4. Invocando getMostTagged");
	        	List<DatatypeMostTagged> masTaggeados = usr.getMostTagged(5);
	        	if (masTaggeados == null) {
		        	System.out.println("   Result: NULL");
		        } else {
		        	for (int i = 0; i < masTaggeados.size(); i++) {
		        		System.out.println("   El mas taggeado es:  " 
		        				+ masTaggeados.get(i).user.username + " con " + masTaggeados.get(i).cantTags + " tags");
		        	}	        		
	        	}	
	        	System.out.println("5. Invocando getMostCommentedPictures");
	        	List<DatatypeContent> masComentadas = evt.getMostCommentedContent(5);
	        	if (masComentadas == null) {
		        	System.out.println("   Result: NULL");
		        } else {
		        	for (int i = 0; i < masComentadas.size(); i++) {
		        		System.out.println("   El contenido con ID " + masComentadas.get(i).getContId() + " tiene " 
		        				+ masComentadas.get(i).getComments().size() + " comentarios");
		        	}	        		
	        	}	
	        	System.out.println("6. Invocando getBestQualifiedPictures");
	        	List<DatatypeContent> mayorRating = evt.getBestRankedContent(5);
	        	if (mayorRating == null) {
		        	System.out.println("   Result: NULL");
		        } else {
		        	for (int i = 0; i < mayorRating.size(); i++) {
		        		System.out.println("   El contenido con ID " + mayorRating.get(i).getContId() + " tiene rating promedio de " 
		        				+ mayorRating.get(i).getAvgScore());
		        	}	        		
	        	}
	        	System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - ");
	        	System.out.println("**** Testeando CU Navegar Galería de Fotos ****");
	        	String usuario = "veromanduk";
	        	int eventID = 1004;
	        	int pos = 0;
	        	System.out.println("1. Invocando isUserRelatedToEvent para el event con ID = 1 y user vero");
	        	if (!evt.isUserRelatedToEvent(eventID, usuario)) {
	        		System.out.println("   El user no es participante del evento - No puede navegar la galería");
	        	} else {
	        		System.out.println("   El user es participante del evento");
	        		System.out.println("2. Invocando getContentDetails - Obteniendo imagen en pos " + pos);
	        		DatatypeContent cont = evt.getContentDetails(eventID, usuario);	
	        		if (cont == null) {
	        			System.out.println("   Result: NULL");
			        } else {
			        	System.out.println("   Galería de fotos - Posicion " + pos + " - Contenido con ID: " +  cont.getContId());	        			
	        			System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - ");
			        	System.out.println("**** Testeando CU Etiquetar Usuario En Foto ****");
		        		System.out.println("1. Invocando getUsersForTag");
		        		List<DatatypeUser> users = evt.getUsersForTag(eventID,cont.getContId());
			        	if (users == null){
		        			System.out.println("   Result: NULL");
			        	} else {
				        	for (int i = 0; i < users.size(); i++) {
				        		System.out.println("  Usuario: " + users.get(i).username );
				        	}	
				        	System.out.println("2. Invocando tagUserInContent");				        	
				        	evt.tagUserInContent(eventID, cont.getContId(), usuario, users.get(pos).username, 1, 1);	        		        	
				        	System.out.println("3. Invocando nuevamente para ver si quedo el tag");				        	
				        	cont = evt.getContentDetails(cont.getContId(), usuario);	
					        System.out.println("   Tags para el contenido con ID " +  cont.getContId());
					        for (int i = 0; i < cont.getTags().size(); i++) {
					        	System.out.println("   " + cont.getTags().get(i).getUserName());
					        }			        	
			        	}	        	
		
			        	// Invocar searchEvents
			        	List<DatatypeEventSummary> searchReturn = evt.searchForEventByName("lala Cumple SUMO", 6); 
			        	if (searchReturn == null){
			        		System.out.println("No se encontró el evento buscado");
			        	}
	        		}
	        	}
	        
	        } else {
	        	System.out.println("ServicesEventRemote was not found");    	
	        }

	    } catch (Exception e) {
			e.printStackTrace();
		}
	    
	}

}
