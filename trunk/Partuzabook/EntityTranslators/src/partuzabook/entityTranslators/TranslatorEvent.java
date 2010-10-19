package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeEvent;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.utils.TranslatorCollection;

public class TranslatorEvent extends TranslatorEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEvent dat = new DatatypeEvent((DatatypeEventSummary) super.translate(ent));
		dat.setContentCategories(TranslatorCollection.translateContentCategoriesSummary(ent.getCntCategories()));
		return dat;
	}
}
