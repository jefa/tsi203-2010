package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeCategory;
import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.utils.TranslatorCollection;

public class TranslatorCategorySummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		CntCategory ent = (CntCategory)iEnt;
		DatatypeCategory dat = new DatatypeCategory();
		dat.setCategoryId(ent.getCatIdAuto());
		dat.setCategory(ent.getCategory());
		if (ent.getContents().size() > 0) {
			dat.setContents(TranslatorCollection.translateContentSummary(ent.getContents().subList(0, 1)));			
		}
		return dat;
	}
}
