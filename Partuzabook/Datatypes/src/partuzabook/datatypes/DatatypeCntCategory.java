package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeCntCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final short PHOTO = 0;
	public static final short VIDEO = 1;
	public static final short EXTERNAL = 2;
	
	private Integer cntCategoryId;
	private String category;
	private Integer eventId;  
	private List<DatatypeContent> contents;
	

	public DatatypeCntCategory() {
		
	}
	
	public Integer getCntCategoryId() {
		return this.cntCategoryId;
	}
	
	public void setCntCategoryId(Integer id){
		this.cntCategoryId = id;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String cat){
		this.category = cat;
	}
	
	public Integer getEventId() {
		return this.eventId;
	}
	
	public void setEventId(Integer id){
		this.eventId = id;
	}
	
	public List<DatatypeContent> getContents() {
		return contents;
	}

	public void setContents(List<DatatypeContent> contents) {
		this.contents = contents;
	}

}
