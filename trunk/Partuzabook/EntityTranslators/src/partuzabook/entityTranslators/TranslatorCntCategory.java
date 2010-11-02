package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeCntCategory;
import partuzabook.datos.persistencia.beans.CntCategory;
import partuzabook.utils.TranslatorCollection;

public class TranslatorCntCategory implements ITranslatable {
	
	public Object translate(Object iEnt) {
		if(!(iEnt instanceof CntCategory)){
			//TODO: throw exception			
		}			
		CntCategory ent = (CntCategory)iEnt;
		DatatypeCntCategory dat = new DatatypeCntCategory();
		dat.setCategory(ent.getCategory());
		dat.setCntCategoryId(ent.getCatIdAuto());
		dat.setContents(TranslatorCollection.translateContent(ent.getContents()));
		dat.setEventId(ent.getEvent().getEvtIdAuto());
		return dat;
	}
}
