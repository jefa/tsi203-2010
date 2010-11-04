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
	
	public String email;
	
	public String imagePath;
	
	public DatatypeUser() {
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(short type) {
		this.type = type;
	}
	
	public short getType() {
		return type;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return imagePath;
	}
}
