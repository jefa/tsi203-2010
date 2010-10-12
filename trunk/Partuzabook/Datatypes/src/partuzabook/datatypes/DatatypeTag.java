package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeTag implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer posX;

	public Integer posY;

	public Integer contentId;
	
	public String userName;

	
	public DatatypeTag() {
		
	}
	
	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}
	
	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}
	
	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
