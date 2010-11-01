package partuzabook.datatypes;

import java.util.List;
import java.util.Map;

public class DatatypeFileAux extends DataTypeFile {

	private Map<String,String> categoriesToSelect;
	private List<String> catsSelected;
	private String catAux;
	private String newCat;
	private List<String> newCatsAux;
	
	public void setCategoriesToSelect(Map<String,String> categoriesToSelect) {
		this.categoriesToSelect = categoriesToSelect;
	}
	
	public Map<String,String> getCategoriesToSelect() {
		return categoriesToSelect;
	}
	
	public void setCatsSelected(List<String> catsSelected) {
		this.catsSelected = catsSelected;
	}
	
	public List<String> getCatsSelected() {
		return catsSelected;
	}
	
	public void setCatAux(String catAux) {
		this.catAux = catAux;
	}
	
	public String getCatAux() {
		return catAux;
	}
	
	public void setNewCat(String newCat) {
		this.newCat = newCat;
	}
	
	public String getNewCat() {
		return newCat;
	}
	
	public void setNewCatsAux(List<String> newCatsAux) {
		this.newCatsAux = newCatsAux;
	}
	
	public List<String> getNewCatsAux() {
		return newCatsAux;
	}
	
	
}
