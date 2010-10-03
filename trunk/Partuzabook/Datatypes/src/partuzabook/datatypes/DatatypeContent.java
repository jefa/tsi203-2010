package partuzabook.datatypes;

import java.util.List;

public class DatatypeContent {
	private static final long serialVersionUID = 1L;
	
	public String url;
	
	public List<DatatypeComment> comments;
	
	public List<DatatypeRating> ratings;
	
	public List<DatatypeTag> tags;

	
	public DatatypeContent() {
		
	}
}
