package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeEventSummary;

public class TranslatorEvent {
	partuzabook.datatypes.DatatypeEventSummary fromEntityEventSummary(
			partuzabook.datos.persistencia.beans.Event ent) {
		partuzabook.datatypes.DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.evtName = ent.getEvtName();
		dat.date = ent.getDate();
		dat.description = ent.getDescription();
		return dat;
	}
}
