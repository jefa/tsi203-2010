package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Event;

public class TranslatorEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.evtName = ent.getEvtName();
		dat.evtId = ent.getEvtIdAuto();
		dat.date = ent.getDate();
		dat.description = ent.getDescription();
		return dat;
	}
}
