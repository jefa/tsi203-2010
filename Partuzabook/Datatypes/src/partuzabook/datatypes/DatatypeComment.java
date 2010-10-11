package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeComment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer contentId;
	
	public String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public DatatypeComment() {
		
	}
}
