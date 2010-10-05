package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeComment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String text;

	public DatatypeContent content;
	
	public DatatypeUser user;
	
	
	public DatatypeComment() {
		
	}
}
