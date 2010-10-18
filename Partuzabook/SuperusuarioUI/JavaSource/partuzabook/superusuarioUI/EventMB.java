package partuzabook.superusuarioUI;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class EventMB {

	private String name;
	private String description;
	private Date date = new Date();
	private int duration;
	private String address;
	private String creator;
	private boolean moderated;
	private List<String> mods;
	private String category;
	
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
		return "failure";
		/*
		Context ctx;
		try {
			ctx = getContext();
			ServicesEventRemote serviceEvent = (ServicesEventRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			DatatypeEventSummary event = serviceEvent.createEvent(name, description, date, duration, address, creator, moderated, category);
			//Preguntar si es moderado o no primero
			serviceEvent.addModtoEvent(event.evtId, mods);
			
			return "success";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failure";
		*/
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
		return mods;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}

}
