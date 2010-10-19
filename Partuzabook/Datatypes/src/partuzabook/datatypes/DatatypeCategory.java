package partuzabook.datatypes;

import java.io.Serializable;
import java.util.List;

public class DatatypeCategory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int categoryId;
	private String category;
	private List<DatatypeContent> contents;
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public List<DatatypeContent> getContents() {
		return contents;
	}

	public void setContents(List<DatatypeContent> contents) {
		this.contents = contents;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
