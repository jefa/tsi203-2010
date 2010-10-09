package partuzabook.datatypes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DatatypeEventSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	public String evtName;
	
	public int evtId;

	public Date date;

	public String description;

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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<DatatypeContent> getContents() {
		return contents;
	}


	public void setContents(List<DatatypeContent> contents) {
		this.contents = contents;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<DatatypeContent> contents;

	
    public DatatypeEventSummary() {
    	
    }

}
