package partuzabook.datatypes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatatypeNotification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer type;
	
	public String reference;
	
	public Date notDate;
	
	public String formattedDate;
	
	public String text;
	
	public Boolean read;
	
	public String userFrom;
	
	public String userTo;
	
	
	public DatatypeNotification() {
		
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public String getReference(){
		return this.reference;
	}
	
	public void setReference(String ref){
		this.reference = ref; 
	}
	
	public Date getNotDate() {
		return this.notDate;
	}
	
	public void setNotDate(Date date) {
		this.notDate = date;
	}
	
	public String getFormattedDate() {
		SimpleDateFormat df = new SimpleDateFormat("d MMM yyyy"); 
		return df.format(this.notDate);
	}
	
	public void setFormattedDate(String fDate) {
		this.formattedDate = fDate;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public Boolean getRead(){
		return this.read;
	}
	
	public void setRead(Boolean isRead){
		this.read = isRead;
	}

	public String getUserFrom(){
		return this.userFrom;
	}
	
	public void setUserFrom(String userFrom){
		this.userFrom = userFrom;
	}
	
	public String getUserTo(){
		return this.userTo;
	}

	public void setUserTo(String userTo){
		this.userTo = userTo;
	}
	
}
