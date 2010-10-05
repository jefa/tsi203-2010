package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeTag implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer posX;

	public Integer posY;

	public DatatypeContent content;
	
	public DatatypeUser user;

	
	public DatatypeTag() {
		
	}
}
