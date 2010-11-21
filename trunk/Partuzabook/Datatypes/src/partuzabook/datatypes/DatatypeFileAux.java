package partuzabook.datatypes;

import java.util.List;
import java.util.Map;

public class DatatypeFileAux extends DataTypeFile {
	private static final long serialVersionUID = 1L;
	
	private List<DatatypeCategoryAux> categoriesAux;
	
	public void setCategoriesAux(List<DatatypeCategoryAux> categoriesAux) {
		this.categoriesAux = categoriesAux;
	}
	
	public List<DatatypeCategoryAux> getCategoriesAux() {
		return categoriesAux;
	}

	
	
}
