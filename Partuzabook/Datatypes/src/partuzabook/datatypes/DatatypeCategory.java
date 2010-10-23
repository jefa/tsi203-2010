package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeCategory extends DatatypeCategorySummary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<DatatypeContent> contents;
	
	public List<DatatypeContent> getContents() {
		return contents;
	}

	public void setContents(List<DatatypeContent> contents) {
		this.contents = contents;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
