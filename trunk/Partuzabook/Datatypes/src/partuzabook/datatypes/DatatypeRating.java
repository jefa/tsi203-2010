package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeRating implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer score;

	public Integer contentId;
	
	public String userName;
	
	public DatatypeRating() {
		
	}
}
