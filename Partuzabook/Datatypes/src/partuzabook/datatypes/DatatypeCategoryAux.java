package partuzabook.datatypes;

import java.io.Serializable;

public class DatatypeCategoryAux implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String category;
	private boolean value;
	
	public DatatypeCategoryAux(String category, boolean value) {
		this.category = category;
		this.value = value;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public boolean isValue() {
		return value;
	}

}
