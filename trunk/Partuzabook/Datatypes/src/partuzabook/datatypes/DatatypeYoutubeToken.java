package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeYoutubeToken implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String post_url;
	private String token_id;
	
	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}
	public String getPost_url() {
		return post_url;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
		
}