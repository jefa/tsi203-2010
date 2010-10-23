package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeCategorySummary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int categoryId;
	private String category;
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
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
