package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datos.persistencia.beans.CntCategory;

public class TranslatorCategorySummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		CntCategory ent = (CntCategory)iEnt;
		DatatypeCategorySummary dat = new DatatypeCategorySummary();
		dat.setCategoryId(ent.getCatIdAuto());
		dat.setCategory(ent.getCategory());
		return dat;
	}
}
