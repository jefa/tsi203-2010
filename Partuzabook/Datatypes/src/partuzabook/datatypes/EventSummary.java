package partuzabook.datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventSummary {
	private static final long serialVersionUID = 1L;

	public String evtName;
	public Date date;
	public String description;
	public List<Content> contents;

    public EventSummary(String evtName, Date date, String description) {
    	this.evtName = evtName;
    	this.date = date;
    	this.description = description;
    	this.contents = new ArrayList<Content>();
    }

}
