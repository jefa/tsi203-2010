package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeEvent extends DatatypeEventSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<DatatypeCategory> contentCategories;
	
    public DatatypeEvent() {
    	
    }
    
    public DatatypeEvent(DatatypeEventSummary datSummary) {
    	super(datSummary);
    }

	public List<DatatypeCategory> getContentCategories() {
		return contentCategories;
	}

	public void setContentCategories(List<DatatypeCategory> contentCategories) {
		this.contentCategories = contentCategories;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

}
