package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeMostTagged implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public DatatypeUser user;
	
	public String eventName;
	
	public Integer cantTags;
	
	public DatatypeMostTagged() {
		
	}
}
