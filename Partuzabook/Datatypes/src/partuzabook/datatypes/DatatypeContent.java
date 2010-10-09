package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final short PHOTO = 0;
	public static final short VIDEO = 1;
	public static final short EXTERNAL = 2;
	
	public Integer contId;
	
	public List<DatatypeComment> comments;
	
	public List<DatatypeRating> ratings;
	
	public List<DatatypeTag> tags;
	
	public short type;

	
	public DatatypeContent() {
		
	}
}
