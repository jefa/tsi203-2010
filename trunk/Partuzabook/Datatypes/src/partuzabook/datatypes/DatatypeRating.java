package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeRating implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer score;

	public DatatypeContent content;
	
	public DatatypeUser user;

	
	public DatatypeRating() {
		
	}
}
