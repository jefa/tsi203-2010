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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}
	
	
}
