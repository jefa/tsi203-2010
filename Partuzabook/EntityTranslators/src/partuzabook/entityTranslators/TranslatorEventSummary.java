package partuzabook.entityTranslators;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.ModeratedEvent;
import partuzabook.utils.TranslatorCollection;

public class TranslatorEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.setEvtName(ent.getEvtName());
		dat.setEvtId(ent.getEvtIdAuto());
		dat.setDate(ent.getDate());
		dat.setDescription(ent.getDescription());
		dat.setAddress(ent.getAddress());
		dat.setLatitude(ent.getLatitude());
		dat.setLongitude(ent.getLongitude());
		if (ent.getCover() != null) {
			dat.setCoverId(ent.getCover().getCntIdAuto());
		}
		if(ent.getEvtCategory() != null) {
			dat.setEventCategory(ent.getEvtCategory().getCategory());
		}
		if(ent instanceof ModeratedEvent) {
			dat.modsUsernames = TranslatorCollection.translateModUser(((ModeratedEvent)ent).getMyMods());
		}
		else {
			dat.modsUsernames = null;
		}
		dat.setHashtag("#kirchner");
		
		return dat;
	}
}
