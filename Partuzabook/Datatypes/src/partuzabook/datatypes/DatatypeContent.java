package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final short PHOTO = 0;
	public static final short VIDEO = 1;
	public static final short EXTERNAL = 2;
	
	private Integer contId;
	private String eventName;
	private Integer eventId;  
	private Double avgScore;
	private List<DatatypeCategorySummary> categories;
	private List<DatatypeComment> comments;
	private List<DatatypeRating> ratings;
	private List<DatatypeTag> tags;
	private short type;
	private int pos;
	
	public DatatypeContent() {
		
	}
	
	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public void setCategories(List<DatatypeCategorySummary> categories) {
		this.categories = categories;
	}

	public List<DatatypeCategorySummary> getCategories() {
		return categories;
	}

	public List<DatatypeComment> getComments() {
		return comments;
	}

	public void setComments(List<DatatypeComment> comments) {
		this.comments = comments;
	}

	public List<DatatypeRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<DatatypeRating> ratings) {
		this.ratings = ratings;
	}

	public List<DatatypeTag> getTags() {
		return tags;
	}

	public void setTags(List<DatatypeTag> tags) {
		this.tags = tags;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static short getPhoto() {
		return PHOTO;
	}

	public static short getVideo() {
		return VIDEO;
	}

	public static short getExternal() {
		return EXTERNAL;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public void setEventName(String name){
		this.eventName = name;
	}
	
	public Integer getEventId() {
		return this.eventId;
	}
	
	public void setEventId(Integer id){
		this.eventId = id;
	}
}
