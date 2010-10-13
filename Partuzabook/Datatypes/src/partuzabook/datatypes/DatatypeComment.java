package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeComment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String text;

	public Integer contentId;
	
	public String userName;
	public String userId;
	
	public DatatypeComment() {
		
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}

	public Integer getContentId() {
		return this.contentId;
	}
	
	public void setContentId(Integer id){
		this.contentId = id;
	}

	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String user){
		this.userName = user;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
}
