package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeTag;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class TagSuggestionMB  {
	private DatatypeContent contenido;
	private String username;
	private int tagX1;
	private int tagX2;
	private int tagY1;
	private int tagY2;
	private List<DatatypeTag> tagged;
	private List<DatatypeUser> candidates;
	private List<DatatypeUser> results;
	private String suggest = "";
	 
	public TagSuggestionMB() {
		
	}
 
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	private void setCandidates() {
		if (candidates == null) {
			try {
				Context ctx = getContext();
				ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
				candidates = service.getUsersForTag(1, 33);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> autocomplete(Object suggestParam) {
		setCandidates();
		suggest = ((String)suggestParam).toLowerCase();
		results = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidates.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggest)
					|| user.name.toLowerCase().contains(suggest)) {
				results.add(user);
			}
		}
		return results;
	}

	public void setResults(List<DatatypeUser> results) {
		this.results = results;
	}
	
	public String tagUser() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			service.tagUserInContent(contenido.getEventId(), contenido.getContId(), username, suggest, tagX1, tagY1);
			System.out.println("Usuario taggeado");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	public List<DatatypeUser> autocomplete(Object suggest) {
		String pref = (String) suggest;
		ArrayList<string> result = new ArrayList<string>();
		Iterator<string> iterator = lista.iterator();
		while (iterator.hasNext()) {
			String elem = ((String) iterator.next());
			if ((elem != null && elem.toLowerCase().indexOf(pref.toLowerCase()) == 0)
					|| "".equals(pref)) {
				result.add(elem);
			}
		}
		return result;
 
	}
	*/
	
	public String getSuggest() {
		return suggest;
	}
 
 
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public List<DatatypeTag> getTagged() {
		try {
			Context ctx = getContext();
			ServicesEventRemote service = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");	
			tagged = service.getGalleryPhotoAtPos(1, 0).tags;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tagged;
	}

	public void setTagged(List<DatatypeTag> tagged) {
		this.tagged = tagged;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public DatatypeContent getContenido() {
		return contenido;
	}

	public void setContenido(DatatypeContent contenido) {
		this.contenido = contenido;
	}

	public int getTagX1(){
		return this.tagX1;
	}

	public void setTagX1(int tagX1) {
		this.tagX1 = tagX1;
	}
	
	public int getTagX2() {
		return tagX2;
	}

	public void setTagX2(int tagX2) {
		this.tagX2 = tagX2;
	}

	public int getTagY1() {
		return tagY1;
	}

	public void setTagY1(int tagY1) {
		this.tagY1 = tagY1;
	}

	public int getTagY2() {
		return tagY2;
	}

	public void setTagY2(int tagY2) {
		this.tagY2 = tagY2;
	}
 
}