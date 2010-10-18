package partuzabook.entityTranslators;

import java.util.ArrayList;
import java.util.Iterator;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.ModeratedEvent;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.utils.TranslatorCollection;

public class TranslatorEventSummary implements ITranslatable {
	
	public Object translate(Object iEnt) {
		Event ent = (Event)iEnt;
		DatatypeEventSummary dat = new DatatypeEventSummary();
		dat.evtName = ent.getEvtName();
		dat.evtId = ent.getEvtIdAuto();
		dat.date = ent.getDate();
		dat.description = ent.getDescription();
		if(ent.getContents() != null)
			dat.contents = TranslatorCollection.translateContent(ent.getContents());
		if(ent.getEvtCategory() != null) {
			dat.category = ent.getEvtCategory().getCategory();
		} else {
			dat.category = "";
		}
		if(ent instanceof ModeratedEvent) {
			dat.modsUsernames = new ArrayList<String>();
			if(((ModeratedEvent)ent).getMyMods() != null){
				for(Iterator<NormalUser> it = ((ModeratedEvent)ent).getMyMods().iterator(); it.hasNext();) {
					dat.modsUsernames.add(it.next().getUsername());
				}
			}
		} else {
			dat.modsUsernames = null;
		}
		return dat;
	}
}
