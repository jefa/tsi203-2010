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
	
	private String duration;

	private String eventCategory;
	
	private Integer coverId;
	
	private String hashtag;
	
	private List<String> modsUsernames;
	private List<String> participantsUsernames;
	
	private boolean hasAlbum;
	
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
		hashtag = datSummary.getHashtag();
		hasAlbum = datSummary.getHasAlbum();
		duration = datSummary.getDuration();
		modsUsernames = datSummary.getModsUsernames();
		participantsUsernames = datSummary.getParticipantsUsernames();
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
	
	public void setCoverId(Integer coverId) {
		this.coverId = coverId;
	}

	public Integer getCoverId() {
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

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getHashtag() {
		return hashtag;
	}
	
	public boolean getHasAlbum(){
		return hasAlbum;
	}
	
	public void setHasAlbum(boolean alb){
		this.hasAlbum = alb;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setParticipantsUsernames(List<String> participantsUsernames) {
		this.participantsUsernames = participantsUsernames;
	}

	public List<String> getParticipantsUsernames() {
		return participantsUsernames;
	}
}
