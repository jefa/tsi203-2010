package partuzabook.datatypes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DatatypeEventSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	public String evtName;

	public Date date;

	public String description;

	public List<DatatypeContent> contents;

	
    public DatatypeEventSummary() {
    	
    }

}