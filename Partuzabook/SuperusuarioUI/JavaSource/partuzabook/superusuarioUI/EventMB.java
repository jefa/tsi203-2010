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
	private static final String MOD_NOT_FOUND = "No existe el usuario ";
	private static final String PARTICIPANT_NOT_FOUND = "No existe el usuario ";
	private static final String NO_MODS = "Se asign� el evento como moderado, pero no se establecieron moderadores.";
	private static final String EVENT_NOT_FOUND = "No se encontr� el evento";
	
	private String name;
	private String nameMessage;
	private String description;
	private String descriptionMessage;
	private Date date = new Date();
	private String duration;
	private String durationMessage;
	private String address;
	private String addressMessage;
	private String creator;
	private boolean moderated = true;
	private List<String> mods;
	private String modsMessage;
	
	private List<String> participants;
	private String participantsMessage;
	
	private Map<String,String> allCats;
	private String category;
	private String hashtag;
	private double latitude;
	private double longitude;
	
	
	private String cantMods;
	private String cantParticipants;
	
	private List<DatatypeUser> candidatesMods;
	private List<DatatypeUser> resultsMods;
	private String suggestMods = "";
	
	private List<DatatypeUser> candidatesParticipants;
	private List<DatatypeUser> resultsParticipants;
	private String suggestParticipants = "";
	
	
	//Para la edicion del evento:
	private int evt_id = -1;
	private DatatypeEvent eventToModify = null;
	
	private String findName;
	private List<DatatypeEventSummary> eventCandidates;
	private List<DatatypeEventSummary> eventResults;
	private String eventSuggest = "";
	private String findEventMessage;
	
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
		if(duration == null || duration.equals(""))
			durationMessage = INPUT_OBLIG;
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
					List<Boolean> existsUsers = serviceUser.existsNormalUser(participants);
					index = 0;
					for(ListIterator<Boolean> it = existsUsers.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							participantsMessage += PARTICIPANT_NOT_FOUND + participants.get(index) +". ";
						}			
					}
					if(noMessages()) {
						DatatypeEventSummary event = serviceEvent.createEvent(name, description, date, duration, 
								address, creator, moderated, category, latitude, longitude, hashtag);
						
						//Agregar usuarios
						serviceEvent.addParticipantstoEvent(event.getEvtId(), participants);
						
						serviceEvent.addModtoEvent(event.getEvtId(), mods);
						
						name = null;
						nameMessage  = null;
						description  = null;
						descriptionMessage  = null;
						date = new Date();
						duration  = null;
						durationMessage  = null;
						address  = null;
						addressMessage  = null;
						moderated = true;
						mods = null;
						modsMessage = null;
						participants = null;
						participantsMessage = null;
						category = null;
						
						res  = "success";
					}						
				} else if(moderated){
					//Es moderado pero no tiene mods.
					modsMessage = NO_MODS;
					res = "failure";
				} else {
					DatatypeEventSummary event = serviceEvent.createEvent(name, description, date, duration, address, creator, 
							moderated, category, latitude, longitude, hashtag);
					
					//Agregar usuarios
					serviceEvent.addParticipantstoEvent(event.getEvtId(), participants);
					
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
		
		if(res.equals("success")) {
			clearMessages();
			clearAll();
		}
		return res;
	}
	
	
	
	
	private void clearAll() {
		name = "";
		//nameMessage = "";
		description = "";
		//descriptionMessage = "";
		date = new Date();
		duration = null;
		//durationMessage = "";
		address = "";
		//addressMessage = "";
		moderated = true;
		mods = null;
		participants = null;
		//modsMessage = "";
		latitude = 0;
		longitude = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return description;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return date;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDuration() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return duration;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
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
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return moderated;
	}
	public void setMods(List<String> mods) {
		this.mods = mods;
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			eventToModify.setModsUsernames(mods);
		}
		
	}
	public List<String> getMods() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		if(mods == null)
			mods = new ArrayList<String>();
		return mods;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
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
				//TODO: Redirigir a una p�gina de error
				e.printStackTrace();
			}
		}
		return allCats;
	}
	
	public List<DatatypeUser> autocompleteMods(Object suggestParam) {
		setCandidatesMods();
		suggestMods = ((String)suggestParam).toLowerCase();
		resultsMods = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesMods.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestMods)
					|| user.name.toLowerCase().contains(suggestMods)) {
				resultsMods.add(user);
			}
		}
		return resultsMods;
	}
	
	public List<DatatypeUser> autocompleteParticipants(Object suggestParam) {
		setCandidatesParticipants();
		suggestParticipants = ((String)suggestParam).toLowerCase();
		resultsParticipants = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesParticipants.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestParticipants)
					|| user.name.toLowerCase().contains(suggestParticipants)) {
				resultsParticipants.add(user);
			}
		}
		return resultsParticipants;
	}
	
	public void addMod() {
		if(suggestMods != null && !suggestMods.equals("")) {
			if(getMods() == null)
				setMods(new ArrayList<String>());
			getMods().add(suggestMods);
		}
	}
	
	public void addParticipant() {
		if(suggestParticipants != null && !suggestParticipants.equals("")) {
			if(getParticipants() == null)
				setParticipants(new ArrayList<String>());
			getParticipants().add(suggestParticipants);
		}
	}
	
	public void resetMod() {
		mods = null;
		modsMessage = null;
	}
	

	public void resetParticipants() {
		participants = null;
		participantsMessage = null;
	}
	
	public int getCantMods() {
		if(getMods() == null)
			return 0;
		return getMods().size();
	}
	
	public int getCantParticipants() {
		if(getParticipants() == null)
			return 0;
		return getParticipants().size();
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
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return longitude;
	}
	
	
	
	public String updateEvent(){
		if(evt_id == -1 || eventToModify == null)
			return "failure";
		
		String res = "failure";
		//Limpiamos los mensajes
		clearMessages();
		if(name == null || name.equals(""))
			nameMessage = INPUT_OBLIG;
		if(description == null || description.equals(""))
			descriptionMessage = INPUT_OBLIG;
		if(duration == null || duration.equals(""))
			durationMessage = INPUT_OBLIG;
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
					List<Boolean> existsUsers = serviceUser.existsNormalUser(participants);
					index = 0;
					for(ListIterator<Boolean> it = existsUsers.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							participantsMessage += PARTICIPANT_NOT_FOUND + participants.get(index) +". ";
						}			
					}
					if(noMessages()) {
						DatatypeEventSummary event = serviceEvent.updateEvent(evt_id, name, description, date, 
								duration, address, creator, category, latitude, longitude, hashtag);
						
						//Agregar usuarios
						serviceEvent.addParticipantstoEvent(evt_id, participants);
												
						serviceEvent.updateModsEvent(evt_id, mods);
						
						name = null;
						nameMessage  = null;
						description  = null;
						descriptionMessage  = null;
						date = new Date();
						duration  = null;
						durationMessage  = null;
						address  = null;
						addressMessage  = null;
						moderated = true;
						mods = null;
						modsMessage = null;
						participants = null;
						participantsMessage = null;
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
					serviceEvent.updateEvent(evt_id, name, description, date, duration, address, creator, 
							category, latitude, longitude, hashtag);
					
					//Agregar usuarios
					serviceEvent.addParticipantstoEvent(evt_id, participants);
					
					
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
		
		if(res.equals("success")) {
			clearMessages();
			clearAll();
		}
		
		return res;
	}
	
	private void initEventToModify() {
		try {
			Context ctx = getContext();
			ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
			eventToModify = serviceEvent.getEventDetails(evt_id);
			setAddress(eventToModify.getAddress());
			setCategory(eventToModify.getEventCategory());
			setDate(eventToModify.getDate());
			setDescription(eventToModify.getDescription());
			setDuration(eventToModify.getDuration());
			setLatitude(eventToModify.getLatitude());
			setLongitude(eventToModify.getLongitude());
			setModerated(eventToModify.getModsUsernames() != null);
			setMods(eventToModify.getModsUsernames());
			setParticipants(eventToModify.getParticipantsUsernames());
			setName(eventToModify.getEvtName());
			setHashtag(eventToModify.getHashtag());
		} catch(NamingException e) {
			
		}
	}

	public void setEvt_id(int evt_id) {
		this.evt_id = evt_id;
	}

	public int getEvt_id() {
		return evt_id;
	}

	public void setFindName(String findName) {
		this.findName = findName;
	}

	public String getFindName() {
		return findName;
	}
	
	private void setEventCandidates() {
		//if (eventCandidates == null) {
			try {
				Context ctx = getContext();
				ServicesEventRemote service = (ServicesEventRemote) ctx.lookup(SERVICE_EVENT);	
				eventCandidates = service.getSummaryEvents(-1);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
		
	public List<DatatypeEventSummary> findEventAutocomplete(Object suggestParam) {
		setEventCandidates();
		eventSuggest = ((String)suggestParam).toLowerCase();
		eventResults = new ArrayList<DatatypeEventSummary>();
		Iterator<DatatypeEventSummary> it = eventCandidates.iterator();
		while (it.hasNext()) {
			DatatypeEventSummary event = (DatatypeEventSummary) it.next();
			if (event.getEvtName().toLowerCase().contains(eventSuggest)) {
				eventResults.add(event);
			}
		}
		return eventResults;
	}
	
	public void searchEvent() {
		if(eventResults == null)
			return;
		boolean find = false;
		for(Iterator<DatatypeEventSummary> it = eventResults.iterator(); it.hasNext(); ) {
			DatatypeEventSummary evt = it.next();
			if(evt.getEvtName().equals(findName)) {
				find = true;
				evt_id = evt.getEvtId();
				initEventToModify();
			}
		}
		if(!find)
			findEventMessage = EVENT_NOT_FOUND; 
		else
			findName = null;
	}

	public void setFindEventMessage(String findEventMessage) {
		this.findEventMessage = findEventMessage;
	}

	public String getFindEventMessage() {
		return findEventMessage;
	}

	public void setHashtag(String hashtag) {
		if (!hashtag.startsWith("#")) {
			hashtag = "#" + hashtag.substring(0, hashtag.length() > 15 ? 15 : hashtag.length());
		}
		this.hashtag = hashtag;
	}

	public String getHashtag() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		return hashtag;
	}

	public void setCandidatesMods() {
		if (candidatesMods == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesMods = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesMods() {
		return candidatesMods;
	}

	public void setResultsMods(List<DatatypeUser> resultsMods) {
		this.resultsMods = resultsMods;
	}

	public List<DatatypeUser> getResultsMods() {
		return resultsMods;
	}

	public void setSuggestMods(String suggestMods) {
		this.suggestMods = suggestMods;
	}

	public String getSuggestMods() {
		return suggestMods;
	}

	public void setCandidatesParticipants() {
		if (candidatesParticipants == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesParticipants = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesParticipants() {
		return candidatesParticipants;
	}

	public void setResultsParticipants(List<DatatypeUser> resultsParticipants) {
		this.resultsParticipants = resultsParticipants;
	}

	public List<DatatypeUser> getResultsParticipants() {
		return resultsParticipants;
	}

	public void setSuggestParticipants(String suggestParticipants) {
		this.suggestParticipants = suggestParticipants;
	}

	public String getSuggestParticipants() {
		return suggestParticipants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
		if(evt_id != -1) {
			if(eventToModify == null)
				initEventToModify();
			eventToModify.setParticipantsUsernames(participants);
		}
	}

	public List<String> getParticipants() {
		if(evt_id != -1 && eventToModify == null)
			initEventToModify();
		if(participants == null)
			participants = new ArrayList<String>();
		return participants;
	}

	public void setParticipantsMessage(String participantsMessage) {
		this.participantsMessage = participantsMessage;
	}

	public String getParticipantsMessage() {
		return participantsMessage;
	}
}