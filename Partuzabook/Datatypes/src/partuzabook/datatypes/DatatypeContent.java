package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final short PHOTO = 0;
	public static final short VIDEO = 1;
	public static final short EXTERNAL_PHOTO = 2;
	public static final short EXTERNAL_VIDEO = 3;
	
	private Integer contId;
	private String description;
	private String eventName;
	private Integer eventId;
	private String uploadedBy;
	private Double avgScore;
	private Integer posAlbum;
	private Integer posGallery;
	//private List<DatatypeCategorySummary> categories;
	private List<DatatypeComment> comments;
	private List<DatatypeTag> tags;
	private short type;
	private String url;
	private String youtubeUrlScreen;
	
	public DatatypeContent() {
		
	}
	
	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public Double getAvgScore() {
		Double avg = (Double) (Math.round(avgScore * 2.0) / 2.0);
		return avg;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getPosAlbum(){
		return this.posAlbum;
	}
	
	public void setPosAlbum(Integer pos){
		this.posAlbum = pos;
	}
	
//	public void setCategories(List<DatatypeCategorySummary> categories) {
//		this.categories = categories;
//	}
//
//	public List<DatatypeCategorySummary> getCategories() {
//		return categories;
//	}

	public List<DatatypeComment> getComments() {
		return comments;
	}

	public void setComments(List<DatatypeComment> comments) {
		this.comments = comments;
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

	public int getPosGallery() {
		return this.posGallery;
	}

	public void setPosGallery(int pos) {
		this.posGallery = pos;
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

	public static short getExternalVideo() {
		return EXTERNAL_VIDEO;
	}
	
	public static short getExternalPhoto() {
		return EXTERNAL_PHOTO;
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

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setYoutubeUrlScreen(String youtubeUrlScreen) {
		this.youtubeUrlScreen = youtubeUrlScreen;
	}

	public String getYoutubeUrlScreen() {
		return getScreen(this.url, "big"); 
	}
	
	private String getScreen(String url, String size ) {
		if(url == null){ return ""; }
		size = (size == null) ? "big" : size;
		String vid = url.substring(25, 36);
		if(size == "small"){
			return "http://img.youtube.com/vi/"+vid+"/2.jpg";
		} else {
			return "http://img.youtube.com/vi/"+vid+"/0.jpg";
		}
	}
}
