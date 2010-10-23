package partuzabook.datatypes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatatypeEventSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	private String evtName;
	
	private String address;
	
	private double latitude;
	private double longitude;
	
	private int evtId;

	private Date date;

	private String description;

	private String eventCategory;
	
	private int coverId;
	
	public List<String> modsUsernames;
	
    public DatatypeEventSummary() {
    	
    }

	public DatatypeEventSummary(DatatypeEventSummary datSummary) {
		evtName = datSummary.getEvtName();
		evtId = datSummary.getEvtId();
		date = datSummary.getDate();
		description = datSummary.getDescription();
		address = datSummary.address;
		longitude = datSummary.longitude;
		latitude = datSummary.latitude;
		eventCategory = datSummary.getEventCategory();
		coverId = datSummary.getCoverId();
	}

	public String getEvtName() {
		return evtName;
	}

	public void setEvtName(String evtName) {
		this.evtName = evtName;
	}

	public int getEvtId() {
		return evtId;
	}

	public void setEvtId(int evtId) {
		this.evtId = evtId;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormattedDate() {
		SimpleDateFormat df = new SimpleDateFormat("d MMM yyyy"); 
		return df.format(date);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public void setCoverId(int coverId) {
		this.coverId = coverId;
	}

	public int getCoverId() {
		return coverId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	public void setModsUsernames(List<String> modsUsernames) {
		this.modsUsernames = modsUsernames;
	}
	
	public List<String> getModsUsernames() {
		return modsUsernames;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
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
}
