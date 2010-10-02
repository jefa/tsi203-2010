package partuzabook.entityTranslators;

import partuzabook.datatypes.EventSummary;

public class TranslatorEvent {
	partuzabook.datatypes.EventSummary fromEntityEventSummary(
			partuzabook.datos.persistencia.beans.Event ent) {
		partuzabook.datatypes.EventSummary dat = new EventSummary();
		dat.evtName = ent.getEvtName();
		dat.date = ent.getDate();
		dat.description = ent.getDescription();
		return dat;
	}
}
