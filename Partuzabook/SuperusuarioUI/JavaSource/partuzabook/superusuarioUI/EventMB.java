package partuzabook.superusuarioUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class EventMB {

	private static final String SERVICE_EVENT = "PartuzabookEAR/ServicesEvent/remote";
	private static final String SERVICE_USER = "PartuzabookEAR/ServicesUser/remote";
	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final String INPUT_NOT_ZERO = "La duración no puede ser 0";
	private static final String MOD_NOT_FOUND = "No existe el usuario ";
	private static final String NO_MODS = "Se asignó el evento como moderado, pero no se establecieron moderadores.";
	
	private String name;
	private String nameMessage;
	private String description;
	private String descriptionMessage;
	private Date date = new Date();
	private int duration;
	private String durationMessage;
	private String address;
	private String addressMessage;
	private String creator;
	private boolean moderated = true;
	private List<String> mods;
	private String modsMessage;
	private Map<String,String> allCats;
	private String category;
	
	private double latitude;
	private double longitude;
	
	
	private String cantMods;
	
	private List<DatatypeUser> candidates;
	private List<DatatypeUser> results;
	private String suggest = "";
	
	
	//Para la edicion del evento:
	private int evt_id = -1;
	private DatatypeEvent eventToModify = null;
	
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
	
	private void clearMessages() {
		setNameMessage("");
		setDescriptionMessage("");
		setDurationMessage("");
		setAddressMessage("");
		setModsMessage("");
	}
	
	private boolean noMessages() {
		return ((nameMessage == null || nameMessage.equals("")) &&
				(descriptionMessage == null || descriptionMessage.equals("")) &&
				(durationMessage == null || durationMessage.equals("")) &&
				(addressMessage == null || addressMessage.equals("")) &&
				(modsMessage == null || modsMessage.equals("")));
	}
	
	public String createEvent() {
		String res = "failure";
		//Limpiamos los mensajes
		clearMessages();
		if(name == null || name.equals(""))
			nameMessage = INPUT_OBLIG;
		if(description == null || description.equals(""))
			descriptionMessage = INPUT_OBLIG;
		if(duration == 0)
			durationMessage = INPUT_NOT_ZERO;
		if(address == null || address.equals(""))
			addressMessage = INPUT_OBLIG;
		
		if(noMessages() && creator != null && category != null && !creator.equals("") && !category.equals("")) {
			Context ctx;
			try {
				ctx = getContext();
				ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
				ServicesUserRemote serviceUser = (ServicesUserRemote) ctx.lookup(SERVICE_USER);
				if(moderated && mods.size()>0) {
					List<Boolean> existsMods = serviceUser.existsNormalUser(mods);
					int index = 0;
					for(ListIterator<Boolean> it = existsMods.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							modsMessage += MOD_NOT_FOUND + mods.get(index) +". ";
						}			
					}
					if(noMessages()) {
						DatatypeEventSummary event = serviceEvent.createEvent(name, description, date, duration, address, creator, moderated, category, latitude, longitude);
						
						serviceEvent.addModtoEvent(event.getEvtId(), mods);
						
						name = null;
						nameMessage  = null;
						description  = null;
						descriptionMessage  = null;
						date = new Date();
						duration  = 0;
						durationMessage  = null;
						address  = null;
						addressMessage  = null;
						moderated = true;
						mods = null;
						modsMessage = null;
						category = null;
						
						res  = "success";
					}						
				} else if(moderated){
					//Es moderado pero no tiene mods.
					modsMessage = NO_MODS;
					res = "failure";
				} else {
					serviceEvent.createEvent(name, description, date, duration, address, creator, moderated, category, latitude, longitude);
					res = "success";
				}				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				clearAll();
			} catch (Exception e) {
				e.printStackTrace();
				clearAll();
			}
		}		
		clearAll();
		if(res.equals("success"))
			clearMessages();
		return res;
	}
	
	
	
	
	private void clearAll() {
		name = "";
		//nameMessage = "";
		description = "";
		//descriptionMessage = "";
		date = new Date();
		duration = 0;
		//durationMessage = "";
		address = "";
		//addressMessage = "";
		moderated = true;
		mods = null;
		//modsMessage = "";
		latitude = 0;
		longitude = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getEvtName();
		}		
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getDescription();
		}
		return description;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getDate();
		}
		return date;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getDuration();
		}
		return duration;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getAddress();
		}
		return address;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return "";
		}
		return creator;
	}
	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}
	public boolean isModerated() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getModsUsernames() == null;
		}
		return moderated;
	}
	public void setMods(List<String> mods) {
		this.mods = mods;
	}
	public List<String> getMods() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getModsUsernames();
		}
		if(mods == null) {
			mods = new ArrayList<String>();
		}
		return mods;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			return eventToModify.getEventCategory();
		}
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
				//TODO: Redirigir a una pï¿½gina de error
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
		if(suggest != null && !suggest.equals("")) {
			getMods().add(suggest);
		}
	}
	
	public void resetMod() {
		mods = null;
		modsMessage = null;
	}
	
	public int getCantMods() {
		return getMods().size();
	}

	public void setNameMessage(String nameMessage) {
		this.nameMessage = nameMessage;
	}

	public String getNameMessage() {
		return nameMessage;
	}

	public void setDescriptionMessage(String descriptionMessage) {
		this.descriptionMessage = descriptionMessage;
	}

	public String getDescriptionMessage() {
		return descriptionMessage;
	}

	public void setDurationMessage(String durationMessage) {
		this.durationMessage = durationMessage;
	}

	public String getDurationMessage() {
		return durationMessage;
	}

	public void setAddressMessage(String addressMessage) {
		this.addressMessage = addressMessage;
	}

	public String getAddressMessage() {
		return addressMessage;
	}

	public void setModsMessage(String modsMessage) {
		this.modsMessage = modsMessage;
	}

	public String getModsMessage() {
		return modsMessage;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	
	
	public String updateEvent() throws Exception{
		if(evt_id == -1 || eventToModify == null)
			throw new Exception("No deberías estar aca!");
		
		String res = "failure";
		//Limpiamos los mensajes
		clearMessages();
		if(name == null || name.equals(""))
			nameMessage = INPUT_OBLIG;
		if(description == null || description.equals(""))
			descriptionMessage = INPUT_OBLIG;
		if(duration == 0)
			durationMessage = INPUT_NOT_ZERO;
		if(address == null || address.equals(""))
			addressMessage = INPUT_OBLIG;
		
		if(noMessages() && creator != null && category != null && !creator.equals("") && !category.equals("")) {
			Context ctx;
			try {
				ctx = getContext();
				ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
				ServicesUserRemote serviceUser = (ServicesUserRemote) ctx.lookup(SERVICE_USER);
				if(moderated && mods.size()>0) {
					List<Boolean> existsMods = serviceUser.existsNormalUser(mods);
					int index = 0;
					for(ListIterator<Boolean> it = existsMods.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							modsMessage += MOD_NOT_FOUND + mods.get(index) +". ";
						}			
					}
					if(noMessages()) {
						//DatatypeEventSummary event = serviceEvent.updateEvent(evt_id, name, description, date, duration, address, moderated, category, latitude, longitude);
						
						//serviceEvent.updateModstoEvent(evt_id, mods);
						
						name = null;
						nameMessage  = null;
						description  = null;
						descriptionMessage  = null;
						date = new Date();
						duration  = 0;
						durationMessage  = null;
						address  = null;
						addressMessage  = null;
						moderated = true;
						mods = null;
						modsMessage = null;
						category = null;
						evt_id = -1;
						eventToModify = null;
						res  = "success";
					}						
				} else if(moderated){
					//Es moderado pero no tiene mods.
					modsMessage = NO_MODS;
					res = "failure";
				} else {
					serviceEvent.createEvent(name, description, date, duration, address, creator, moderated, category, latitude, longitude);
					res = "success";
				}				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				clearAll();
			} catch (Exception e) {
				e.printStackTrace();
				clearAll();
			}
		}		
		clearAll();
		if(res.equals("success"))
			clearMessages();
		return res;
	}
	
	private void initEventToModify() {
		try {
			Context ctx = getContext();
			ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
			eventToModify = serviceEvent.getEventDetails(evt_id);
		} catch(NamingException e) {
			
		}
	}

	public void setEvt_id(int evt_id) {
		this.evt_id = evt_id;
	}

	public int getEvt_id() {
		return evt_id;
	}
	
	
	
}
