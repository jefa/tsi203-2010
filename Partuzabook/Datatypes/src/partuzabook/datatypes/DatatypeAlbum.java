package partuzabook.datatypes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatatypeAlbum implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private DatatypeEvent event;
	private DatatypeEventSummary eventSummary;

	
	public DatatypeEvent getEvent() {
		return event;
	}

	public void setEvent(DatatypeEvent evt) {
		this.event = evt;
	}

	public DatatypeEventSummary getEventSummary() {
		return eventSummary;
	}

	public void setEventSummary(DatatypeEventSummary dataEvent) {
		this.eventSummary = dataEvent;
	}
	
}
