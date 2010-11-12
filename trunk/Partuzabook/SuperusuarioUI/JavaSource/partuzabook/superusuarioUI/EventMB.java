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
	
	//Para la creaci�n de eventos:
	private String nameC;
	private String nameMessageC;
	private String descriptionC;
	private String descriptionMessageC;
	private Date dateC = new Date();
	private String durationC;
	private String durationMessageC;
	private String addressC;
	private String addressMessageC;
	private String creatorC;
	private boolean moderatedC = true;
	private List<String> modsC;
	private String modsMessageC;
	private String hashtagC;
	private double latitudeC;
	private double longitudeC;
	private List<String> participantsC;
	private String participantsMessageC;
	private String categoryC;
	
	private String cantModsC;
	private String cantParticipantsC;
	
	private List<DatatypeUser> candidatesModsC;
	private List<DatatypeUser> resultsModsC;
	private String suggestModsC = "";
	
	private List<DatatypeUser> candidatesParticipantsC;
	private List<DatatypeUser> resultsParticipantsC;
	private String suggestParticipantsC = "";
	
	//Para todos:
	private Map<String,String> allCats;
	
	//Para la edicion de eventos:
	private int evt_id = -1;
	private DatatypeEvent eventToModify = null;
	
	private String findName;
	private List<DatatypeEventSummary> eventCandidates;
	private List<DatatypeEventSummary> eventResults;
	private String eventSuggest = "";
	private String findEventMessage;
	
	private String nameM;
	private String nameMessageM;
	private String descriptionM;
	private String descriptionMessageM;
	private Date dateM = new Date();
	private String durationM;
	private String durationMessageM;
	private String addressM;
	private String addressMessageM;
	private String creatorM;
	private boolean moderatedM = true;
	private List<String> modsM;
	private String modsMessageM;
	private String hashtagM;
	private double latitudeM;
	private double longitudeM;
	private List<String> participantsM;
	private String participantsMessageM;
	private String categoryM;
	
	private String cantModsM;
	private String cantParticipantsM;
	
	private List<DatatypeUser> candidatesModsM;
	private List<DatatypeUser> resultsModsM;
	private String suggestModsM = "";
	
	private List<DatatypeUser> candidatesParticipantsM;
	private List<DatatypeUser> resultsParticipantsM;
	private String suggestParticipantsM = "";
	
	
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
	
	private void clearMessagesC() {
		setNameMessageC("");
		setDescriptionMessageC("");
		setDurationMessageC("");
		setAddressMessageC("");
		setModsMessageC("");
		setParticipantsMessageC("");
	}
	
	private boolean noMessagesC() {
		return ((nameMessageC == null || nameMessageC.equals("")) &&
				(descriptionMessageC == null || descriptionMessageC.equals("")) &&
				(durationMessageC == null || durationMessageC.equals("")) &&
				(addressMessageC == null || addressMessageC.equals("")) &&
				(modsMessageC == null || modsMessageC.equals("")) &&
				(participantsMessageC == null || participantsMessageC.equals("")));
	}
	
	private void clearMessagesM() {
		setNameMessageM("");
		setDescriptionMessageM("");
		setDurationMessageM("");
		setAddressMessageM("");
		setModsMessageM("");
		setParticipantsMessageM("");
	}
	
	private boolean noMessagesM() {
		return ((nameMessageM == null || nameMessageM.equals("")) &&
				(descriptionMessageM == null || descriptionMessageM.equals("")) &&
				(durationMessageM == null || durationMessageM.equals("")) &&
				(addressMessageM == null || addressMessageM.equals("")) &&
				(modsMessageM == null || modsMessageM.equals("")) &&
				(participantsMessageM == null || participantsMessageM.equals("")));
	}
	
	public String createEvent() {
		String res = "failure";
		//Limpiamos los mensajes
		clearMessagesC();
		if(nameC == null || nameC.equals(""))
			nameMessageC = INPUT_OBLIG;
		if(descriptionC == null || descriptionC.equals(""))
			descriptionMessageC = INPUT_OBLIG;
		if(durationC == null || durationC.equals(""))
			durationMessageC = INPUT_OBLIG;
		if(addressC == null || addressC.equals(""))
			addressMessageC = INPUT_OBLIG;
		
		if(noMessagesC() && creatorC != null && categoryC != null && !creatorC.equals("") && !categoryC.equals("")) {
			Context ctx;
			try {
				ctx = getContext();
				ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
				ServicesUserRemote serviceUser = (ServicesUserRemote) ctx.lookup(SERVICE_USER);
				if(moderatedC && modsC.size()>0) {
					List<Boolean> existsMods = serviceUser.existsNormalUser(modsC);
					int index = 0;
					for(ListIterator<Boolean> it = existsMods.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							modsMessageC += MOD_NOT_FOUND + modsC.get(index) +". ";
						}			
					}
					List<Boolean> existsUsers = serviceUser.existsNormalUser(participantsC);
					index = 0;
					for(ListIterator<Boolean> it = existsUsers.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							participantsMessageC += PARTICIPANT_NOT_FOUND + participantsC.get(index) +". ";
						}			
					}
					if(noMessagesC()) {
						DatatypeEventSummary event = serviceEvent.createEvent(nameC, descriptionC, dateC, durationC, 
								addressC, creatorC, moderatedC, categoryC, latitudeC, longitudeC, hashtagC);
						
						//Agregar usuarios
						serviceEvent.addParticipantstoEvent(event.getEvtId(), participantsC);
						
						serviceEvent.addModtoEvent(event.getEvtId(), modsC);
						
						nameC = null;
						nameMessageC  = null;
						descriptionC  = null;
						descriptionMessageC  = null;
						dateC = new Date();
						durationC = null;
						durationMessageC  = null;
						addressC  = null;
						addressMessageC  = null;
						moderatedC = true;
						modsC = null;
						modsMessageC = null;
						participantsC = null;
						participantsMessageC = null;
						categoryC = null;
						
						res  = "success";
					}						
				} else if(moderatedC){
					//Es moderado pero no tiene mods.
					modsMessageC = NO_MODS;
					res = "failure";
				} else {
					DatatypeEventSummary event = serviceEvent.createEvent(nameC, descriptionC, dateC, durationC, addressC, creatorC, 
							moderatedC, categoryC, latitudeC, longitudeC, hashtagC);
					
					//Agregar usuarios
					serviceEvent.addParticipantstoEvent(event.getEvtId(), participantsC);
					
					res = "success";
				}				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				clearAllC();
			} catch (Exception e) {
				e.printStackTrace();
				clearAllC();
			}
		}		
		
		if(res.equals("success")) {
			clearMessagesC();
			clearAllC();
		}
		return res;
	}	
	
	private void clearAllC() {
		nameC = "";
		//nameMessage = "";
		descriptionC = "";
		//descriptionMessage = "";
		dateC = new Date();
		durationC = null;
		//durationMessage = "";
		addressC = "";
		//addressMessage = "";
		moderatedC = true;
		modsC = null;
		participantsC = null;
		//modsMessage = "";
		latitudeC = 0;
		longitudeC = 0;
	}
	
	private void clearAllM() {
		nameM = "";
		//nameMessage = "";
		descriptionM = "";
		//descriptionMessage = "";
		dateM = new Date();
		durationM = null;
		//durationMessage = "";
		addressM = "";
		//addressMessage = "";
		moderatedM = true;
		modsM = null;
		participantsM = null;
		//modsMessage = "";
		latitudeM = 0;
		longitudeM = 0;
	}
	
	public void setNameC(String nameC) {
		this.nameC = nameC;
	}
	public String getNameC() {
		return nameC;
	}
	public void setDescriptionC(String descriptionC) {
		this.descriptionC = descriptionC;
	}
	public String getDescriptionC() {
		return descriptionC;
	}
	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}
	public Date getDateC() {
		return dateC;
	}
	public void setDurationC(String durationC) {
		this.durationC = durationC;
	}
	public String getDurationC() {
		return durationC;
	}
	public void setAddressC(String addressC) {
		this.addressC = addressC;
	}
	public String getAddressC() {
		return addressC;
	}
	public void setCreatorC(String creatorC) {
		this.creatorC = creatorC;
	}
	public String getCreatorC() {
		return creatorC;
	}
	public void setModeratedC(boolean moderatedC) {
		this.moderatedC = moderatedC;
	}
	public boolean isModeratedC() {
		return moderatedC;
	}
	public void setModsC(List<String> modsC) {
		this.modsC = modsC;		
	}
	public List<String> getModsC() {
		if(modsC == null)
			modsC = new ArrayList<String>();
		return modsC;
	}
	public void setCategoryC(String categoryC) {
		this.categoryC = categoryC;
	}
	public String getCategoryC() {
		return categoryC;
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
	
	public List<DatatypeUser> autocompleteModsC(Object suggestParam) {
		setCandidatesModsC();
		suggestModsC = ((String)suggestParam).toLowerCase();
		resultsModsC = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesModsC.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestModsC)
					|| user.name.toLowerCase().contains(suggestModsC)) {
				resultsModsC.add(user);
			}
		}
		return resultsModsC;
	}
	
	public List<DatatypeUser> autocompleteParticipantsC(Object suggestParam) {
		setCandidatesParticipantsC();
		suggestParticipantsC = ((String)suggestParam).toLowerCase();
		resultsParticipantsC = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesParticipantsC.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestParticipantsC)
					|| user.name.toLowerCase().contains(suggestParticipantsC)) {
				resultsParticipantsC.add(user);
			}
		}
		return resultsParticipantsC;
	}
	
	public void addModC() {
		if(suggestModsC != null && !suggestModsC.equals("")) {
			if(getModsC() == null)
				setModsC(new ArrayList<String>());
			getModsC().add(suggestModsC);
		}
	}
	
	public void addParticipantC() {
		if(suggestParticipantsC != null && !suggestParticipantsC.equals("")) {
			if(getParticipantsC() == null)
				setParticipantsC(new ArrayList<String>());
			getParticipantsC().add(suggestParticipantsC);
		}
	}	
	public void resetModC() {
		modsC = null;
		modsMessageC = null;
	}
	public void resetParticipantsC() {
		participantsC = null;
		participantsMessageC = null;
	}
	
	public int getCantModsC() {
		if(getModsC() == null)
			return 0;
		return getModsC().size();
	}
	
	public int getCantParticipantsC() {
		if(getParticipantsC() == null)
			return 0;
		return getParticipantsC().size();
	}

	public void setNameMessageC(String nameMessageC) {
		this.nameMessageC = nameMessageC;
	}

	public String getNameMessageC() {
		return nameMessageC;
	}

	public void setDescriptionMessageC(String descriptionMessageC) {
		this.descriptionMessageC = descriptionMessageC;
	}

	public String getDescriptionMessageC() {
		return descriptionMessageC;
	}

	public void setDurationMessageC(String durationMessageC) {
		this.durationMessageC = durationMessageC;
	}

	public String getDurationMessageC() {
		return durationMessageC;
	}

	public void setAddressMessageC(String addressMessageC) {
		this.addressMessageC = addressMessageC;
	}

	public String getAddressMessageC() {
		return addressMessageC;
	}

	public void setModsMessageC(String modsMessageC) {
		this.modsMessageC = modsMessageC;
	}

	public String getModsMessageC() {
		return modsMessageC;
	}

	public void setLatitudeC(double latitudeC) {
		this.latitudeC = latitudeC;
	}

	public double getLatitudeC() {
		return latitudeC;
	}
	public void setLongitudeC(double longitudeC) {
		this.longitudeC = longitudeC;
	}

	public double getLongitudeC() {
		return longitudeC;
	}	
	
	public void setHashtagC(String hashtagC) {
		if (!hashtagC.startsWith("#")) {
			hashtagC = "#" + hashtagC.substring(0, hashtagC.length() > 15 ? 15 : hashtagC.length());
		}
		this.hashtagC = hashtagC;
	}

	public String getHashtagC() {
		return hashtagC;
	}

	public void setCandidatesModsC() {
		if (candidatesModsC == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesModsC = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesModsC() {
		return candidatesModsC;
	}

	public void setResultsModsC(List<DatatypeUser> resultsModsC) {
		this.resultsModsC = resultsModsC;
	}

	public List<DatatypeUser> getResultsModsC() {
		return resultsModsC;
	}

	public void setSuggestModsC(String suggestModsC) {
		this.suggestModsC = suggestModsC;
	}

	public String getSuggestModsC() {
		return suggestModsC;
	}

	public void setCandidatesParticipantsC() {
		if (candidatesParticipantsC == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesParticipantsC = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesParticipantsC() {
		return candidatesParticipantsC;
	}

	public void setResultsParticipantsC(List<DatatypeUser> resultsParticipantsC) {
		this.resultsParticipantsC = resultsParticipantsC;
	}

	public List<DatatypeUser> getResultsParticipantsC() {
		return resultsParticipantsC;
	}

	public void setSuggestParticipantsC(String suggestParticipantsC) {
		this.suggestParticipantsC = suggestParticipantsC;
	}

	public String getSuggestParticipantsC() {
		return suggestParticipantsC;
	}

	public void setParticipantsC(List<String> participantsC) {
		this.participantsC = participantsC;
	}

	public List<String> getParticipantsC() {
		if(participantsC == null)
			participantsC = new ArrayList<String>();
		return participantsC;
	}

	public void setParticipantsMessageC(String participantsMessageC) {
		this.participantsMessageC = participantsMessageC;
	}

	public String getParticipantsMessageC() {
		return participantsMessageC;
	}
	
	
	public String updateEvent(){
		if(evt_id == -1 || eventToModify == null)
			return "failure";
		
		String res = "failure";
		//Limpiamos los mensajes
		clearMessagesM();
		if(nameM == null || nameM.equals(""))
			nameMessageM = INPUT_OBLIG;
		if(descriptionM == null || descriptionM.equals(""))
			descriptionMessageM = INPUT_OBLIG;
		if(durationM == null || durationM.equals(""))
			durationMessageM = INPUT_OBLIG;
		if(addressM == null || addressM.equals(""))
			addressMessageM = INPUT_OBLIG;
		
		if(noMessagesM() && categoryM != null && !categoryM.equals("") && creatorM != null && !creatorM.equals("")) {
			Context ctx;
			try {
				ctx = getContext();
				ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
				ServicesUserRemote serviceUser = (ServicesUserRemote) ctx.lookup(SERVICE_USER);
				if(moderatedM && modsM.size()>0) {
					List<Boolean> existsMods = serviceUser.existsNormalUser(modsM);
					int index = 0;
					for(ListIterator<Boolean> it = existsMods.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							modsMessageM += MOD_NOT_FOUND + modsM.get(index) +". ";
						}			
					}
					List<Boolean> existsUsers = serviceUser.existsNormalUser(participantsM);
					index = 0;
					for(ListIterator<Boolean> it = existsUsers.listIterator(); it.hasNext(); index++){
						if(!it.next()){
							participantsMessageM += PARTICIPANT_NOT_FOUND + participantsM.get(index) +". ";
						}			
					}
					if(noMessagesM()) {
						DatatypeEventSummary event = serviceEvent.updateEvent(evt_id, nameM, descriptionM, dateM, 
								durationM, addressM, creatorM, categoryM, latitudeM, longitudeM, hashtagM);
						
						//Agregar usuarios
						serviceEvent.addParticipantstoEvent(evt_id, participantsM);
												
						serviceEvent.updateModsEvent(evt_id, modsM);
						
						nameM = null;
						nameMessageM  = null;
						descriptionM  = null;
						descriptionMessageM  = null;
						dateM = new Date();
						durationM  = null;
						durationMessageM  = null;
						addressM  = null;
						addressMessageM  = null;
						moderatedM = true;
						modsM = null;
						modsMessageM = null;
						participantsM = null;
						participantsMessageM = null;
						categoryM = null;
						evt_id = -1;
						eventToModify = null;
						res  = "success";
					}						
				} else if(moderatedM){
					//Es moderado pero no tiene mods.
					modsMessageM = NO_MODS;
					res = "failure";
				} else {
					serviceEvent.updateEvent(evt_id, nameM, descriptionM, dateM, durationM, addressM, creatorM, 
							categoryM, latitudeM, longitudeM, hashtagM);
					
					//Agregar usuarios
					serviceEvent.addParticipantstoEvent(evt_id, participantsM);
					
					
					res = "success";
				}				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				clearAllM();
			} catch (Exception e) {
				e.printStackTrace();
				clearAllM();
			}
		}		
		
		if(res.equals("success")) {
			clearMessagesM();
			clearAllM();
		}
		
		return res;
	}
	
	private void initEventToModify() {
		try {
			Context ctx = getContext();
			ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup(SERVICE_EVENT);
			eventToModify = serviceEvent.getEventDetails(evt_id, true);
			setAddressM(eventToModify.getAddress());
			setCategoryM(eventToModify.getEventCategory());
			setDateM(eventToModify.getDate());
			setDescriptionM(eventToModify.getDescription());
			setDurationM(eventToModify.getDuration());
			setLatitudeM(eventToModify.getLatitude());
			setLongitudeM(eventToModify.getLongitude());
			setModeratedM(eventToModify.getModsUsernames() != null  && eventToModify.getModsUsernames().size() > 0);
			setModsM(eventToModify.getModsUsernames());
			setParticipantsM(eventToModify.getParticipantsUsernames());
			setNameM(eventToModify.getEvtName());
			setHashtagM(eventToModify.getHashtag());
		} catch(NamingException e) {
			
		}
	}

	public void setEvt_id(int evt_id) {
		this.evt_id = evt_id;
		if(evt_id != -1)
			initEventToModify();
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
	
	/****************/
	
	public void setNameM(String nameM) {
		this.nameM = nameM;
	}
	public String getNameM() {
		if(evt_id == -1)
			return "";
		return nameM;
	}
	public void setDescriptionM(String descriptionM) {
		this.descriptionM = descriptionM;
	}
	public String getDescriptionM() {
		if(evt_id == -1)
			return "";
		return descriptionM;
	}
	public void setDateM(Date dateM) {
		this.dateM = dateM;
	}
	public Date getDateM() {
		if(evt_id == -1)
			return new Date();
		return dateM;
	}
	public void setDurationM(String durationM) {
		this.durationM = durationM;
	}
	public String getDurationM() {
		if(evt_id == -1)
			return "";
		return durationM;
	}
	public void setAddressM(String addressM) {
		this.addressM = addressM;
	}
	public String getAddressM() {
		if(evt_id == -1)
			return "";
		return addressM;
	}
	public void setModeratedM(boolean moderatedM) {
		this.moderatedM = moderatedM;
	}
	public boolean isModeratedM() {
		if(evt_id == -1)
			return true;
		return moderatedM;
	}
	public void setModsM(List<String> modsM) {
		this.modsM = modsM;
		//eventToModify.setModsUsernames(modsM);
	}
	public List<String> getModsM() {
		if(evt_id == -1)
			return new ArrayList<String>();
		if(modsM == null)
			modsM = new ArrayList<String>();
		return modsM;
	}
	public void setCategoryM(String categoryM) {
		this.categoryM = categoryM;
	}
	public String getCategoryM() {
		if(evt_id == -1)
			return "";
		return categoryM;
	}
	
	public List<DatatypeUser> autocompleteModsM(Object suggestParam) {
		setCandidatesModsM();
		suggestModsM = ((String)suggestParam).toLowerCase();
		resultsModsM = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesModsM.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestModsM)
					|| user.name.toLowerCase().contains(suggestModsM)) {
				resultsModsM.add(user);
			}
		}
		return resultsModsM;
	}
	
	public List<DatatypeUser> autocompleteParticipantsM(Object suggestParam) {
		setCandidatesParticipantsM();
		suggestParticipantsM = ((String)suggestParam).toLowerCase();
		resultsParticipantsM = new ArrayList<DatatypeUser>();
		Iterator<DatatypeUser> it = candidatesParticipantsM.iterator();
		while (it.hasNext()) {
			DatatypeUser user = (DatatypeUser) it.next();
			if (user.username.toLowerCase().contains(suggestParticipantsM)
					|| user.name.toLowerCase().contains(suggestParticipantsM)) {
				resultsParticipantsM.add(user);
			}
		}
		return resultsParticipantsM;
	}
	
	public void addModM() {
		if(suggestModsM != null && !suggestModsM.equals("")) {
			if(getModsM() == null)
				setModsM(new ArrayList<String>());
			getModsM().add(suggestModsM);
		}
	}
	
	public void addParticipantM() {
		if(suggestParticipantsM != null && !suggestParticipantsM.equals("")) {
			if(getParticipantsM() == null)
				setParticipantsM(new ArrayList<String>());
			getParticipantsM().add(suggestParticipantsM);
		}
	}
	
	public void resetModM() {
		modsM = null;
		modsMessageM = null;
	}
	

	public void resetParticipantsM() {
		participantsM = null;
		participantsMessageM = null;
	}
	
	public int getCantModsM() {
		if(evt_id == -1)
			return 0;
		if(getModsM() == null)
			return 0;
		return getModsM().size();
	}
	
	public int getCantParticipantsM() {
		if(evt_id == -1)
			return 0;
		if(getParticipantsM() == null)
			return 0;
		return getParticipantsM().size();
	}

	public void setNameMessageM(String nameMessageM) {
		this.nameMessageM = nameMessageM;
	}

	public String getNameMessageM() {
		if(evt_id == -1)
			return "";
		return nameMessageM;
	}

	public void setDescriptionMessageM(String descriptionMessageM) {
		this.descriptionMessageM = descriptionMessageM;
	}

	public String getDescriptionMessageM() {
		if(evt_id == -1)
			return "";
		return descriptionMessageM;
	}

	public void setDurationMessageM(String durationMessageM) {
		this.durationMessageM = durationMessageM;
	}

	public String getDurationMessageM() {
		if(evt_id == -1)
			return "";
		return durationMessageM;
	}

	public void setAddressMessageM(String addressMessageM) {
		this.addressMessageM = addressMessageM;
	}

	public String getAddressMessageM() {
		if(evt_id == -1)
			return "";
		return addressMessageM;
	}

	public void setModsMessageM(String modsMessageM) {
		this.modsMessageM = modsMessageM;
	}

	public String getModsMessageM() {
		if(evt_id == -1)
			return "";
		return modsMessageM;
	}

	public void setLatitudeM(double latitudeM) {
		this.latitudeM = latitudeM;
	}

	public double getLatitudeM() {
		if(evt_id == -1)
			return 0;
		return latitudeM;
	}
	public void setLongitudeM(double longitudeM) {
		this.longitudeM = longitudeM;
	}

	public double getLongitudeM() {
		if(evt_id == -1)
			return 0;
		return longitudeM;
	}
	
	public void setHashtagM(String hashtagM) {
		if (!hashtagM.startsWith("#")) {
			hashtagM = "#" + hashtagM.substring(0, hashtagM.length() > 15 ? 15 : hashtagM.length());
		}
		this.hashtagM = hashtagM;
	}

	public String getHashtagM() {
		if(evt_id == -1)
			return "";
		return hashtagM;
	}

	public void setCandidatesModsM() {
		if (candidatesModsM == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesModsM = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesModsM() {
		return candidatesModsM;
	}

	public void setResultsModsM(List<DatatypeUser> resultsModsM) {
		this.resultsModsM = resultsModsM;
	}

	public List<DatatypeUser> getResultsModsM() {
		return resultsModsM;
	}

	public void setSuggestModsM(String suggestModsM) {
		this.suggestModsM = suggestModsM;
	}

	public String getSuggestModsM() {
		return suggestModsM;
	}

	public void setCandidatesParticipantsM() {
		if (candidatesParticipantsM == null) {
			try {
				Context ctx = getContext();
				ServicesUserRemote service = (ServicesUserRemote) ctx.lookup(SERVICE_USER);	
				candidatesParticipantsM = service.findAllNormalUsers();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<DatatypeUser> getCandidatesParticipantsM() {
		return candidatesParticipantsM;
	}

	public void setResultsParticipantsM(List<DatatypeUser> resultsParticipantsM) {
		this.resultsParticipantsM = resultsParticipantsM;
	}

	public List<DatatypeUser> getResultsParticipantsM() {
		return resultsParticipantsM;
	}

	public void setSuggestParticipantsM(String suggestParticipantsM) {
		this.suggestParticipantsM = suggestParticipantsM;
	}

	public String getSuggestParticipantsM() {
		return suggestParticipantsM;
	}

	public void setParticipantsM(List<String> participantsM) {
		this.participantsM = participantsM;
		//eventToModify.setParticipantsUsernames(participantsM);
	}

	public List<String> getParticipantsM() {
		if(evt_id == -1)
			return new ArrayList<String>();
		if(participantsM == null)
			participantsM = new ArrayList<String>();
		return participantsM;
	}

	public void setParticipantsMessageM(String participantsMessageM) {
		this.participantsMessageM = participantsMessageM;
	}

	public String getParticipantsMessageM() {
		if(evt_id == -1)
			return "";
		return participantsMessageM;
	}
	public void setCreatorM(String creatorM) {
		this.creatorM = creatorM;
	}
	public String getCreatorM() {
		return creatorM;
	}
}
