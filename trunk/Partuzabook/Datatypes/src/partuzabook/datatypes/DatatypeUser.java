package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final short NORMALUSER = 0;
	public static final short CLIENT = 1;
	public static final short ADMIN = 2;

	public String username;
	
	public String name;
	
	public short type;
	
	public DatatypeUser() {
		
	}
}
