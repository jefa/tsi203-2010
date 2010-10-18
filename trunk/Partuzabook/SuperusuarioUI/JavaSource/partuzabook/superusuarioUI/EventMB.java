package partuzabook.superusuarioUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class EventMB {

	private static final String SERVICE_EVENT = "PartuzabookEAR/ServicesEvent/remote";
	private static final String SERVICE_USER = "PartuzabookEAR/ServicesUser/remote";
	
	private String name;
	private String description;
	private Date date = new Date();
	private int duration;
	private String address;
	private String creator;
	private boolean moderated = true;
	private List<String> mods;
	private Map<String,String> allCats;
	private String category;
	
	private String cantMods;
	
	private List<DatatypeUser> candidates;
	private List<DatatypeUser> results;
	private String suggest = "";
	
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
	
	public String createEvent() {
		Context ctx;
		try {
			ctx = getContext();
			ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
			DatatypeEventSummary event = serviceEvent.createEvent(name, description, date, duration, address, "ggadmin", moderated, category);
			//Preguntar si es moderado o no primero
			serviceEvent.addModtoEvent(event.evtId, mods);
			mods = null;			
			return "success";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator() {
		return creator;
	}
	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}
	public boolean isModerated() {
		return moderated;
	}
	public void setMods(List<String> mods) {
		this.mods = mods;
	}
	public List<String> getMods() {
		if(mods == null) {
			mods = new ArrayList<String>();
		}
		return mods;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setAllCats(Map<String,String> allCats) {
		this.allCats = allCats;
	}
	public Map<String, String> getAllCats() {
		if(allCats == null){
			try {
				Context ctx = getContext(); 
				ServicesEventRemote service = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
				List<String> cats = service.findAllEvtCategories();
				allCats = new HashMap<String,String>();
				for(Iterator<String> it = cats.iterator(); it.hasNext(); ) {
					String entry = it.next();
					allCats.put(entry, entry);
				}
			} catch (NamingException e) {
				//TODO: Redirigir a una página de error
				e.printStackTrace();
			}
		}
		return allCats;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getSuggest() {
		return this.suggest;
	}
	
	private void setCandidates() {
		if (candidates == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidates = service.findAllNormalUsers();
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
	
	public void addMod() {
		getMods().add(suggest);
	}
	
	public void resetMod() {
		mods = null;
	}
	
	public int getCantMods() {
		return getMods().size();
	}
	
}
