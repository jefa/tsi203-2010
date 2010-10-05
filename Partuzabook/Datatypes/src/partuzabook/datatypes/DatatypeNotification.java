package partuzabook.datatypes;

import java.io.Serializable;
import java.util.Date;

public class DatatypeNotification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer type;
	
	public String reference;
	
	public Date notDate;
	
	public String text;
	
	public Boolean read;
	
	public String userFrom;
	
	public String userTo;
	
	
	public DatatypeNotification() {
		
	}
}
