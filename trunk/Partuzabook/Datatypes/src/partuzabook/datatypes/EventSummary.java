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

    public EventSummary() {
    	this.contents = new ArrayList<Content>();
    }

}
